import type { Arg } from 'alova'
import { createAlova } from 'alova'
import GlobalFetch from 'alova/GlobalFetch'
import VueHook from 'alova/vue'
import { RequestCompleteEvent, RequestErrorEvent, RequestInternetErrorEvent, RequestStartEvent, RequestSuccessEvent } from './event'
import type { Model } from './models'

export const alovaInstApi = createAlova({
  baseURL: '/api/',
  statesHook: VueHook,
  requestAdapter: GlobalFetch(),
  storageAdapter: {
    get(key) {
      const data = sessionStorage.getItem(key)
      return data ? JSON.parse(data) : data
    },
    set(key, value) {
      sessionStorage.setItem(key, JSON.stringify(value))
    },
    remove(key) {
      sessionStorage.removeItem(key)
    }
  },
  errorLogger: () => {},
  beforeRequest(method) {
    new RequestStartEvent(method).emit()
  },
  localCache: {
    GET: 0
  },
  responded: {
    async onSuccess(response, method) {
      if (response.redirected) location.href = response.url
      if (response.headers.get('Content-Type')?.includes('application/json')) {
        const data = await response.json()
        if (response.status >= 400) {
          new RequestErrorEvent(response, method, data).emit()
          throw new Error()
        }
        new RequestSuccessEvent(response, method, data).emit()
        return data
      }
      if (response.status >= 400) {
        const error = { detail: 'Error' }
        new RequestErrorEvent(response, method, error).emit()
        throw new Error()
      }
      new RequestSuccessEvent(response, method, null).emit()
      return await response.text()
    },
    async onError(response: Response | Error, method) {
      if (response instanceof Error) {
        new RequestInternetErrorEvent(response, method).emit()
      } else {
        let error = { detail: 'Error' }
        if (response.headers.get('Content-Type')?.includes('application/json')) {
          error = await response.json()
        }
        new RequestErrorEvent(response, method, error).emit()
      }
      throw response
    },
    async onComplete(method) {
      new RequestCompleteEvent(method).emit()
    }
  }
})

export type DataProxyConfig = {
  excludeFields?: string[]
  /** 文件上传 */
  multipart?: boolean
  data?: () => { [key: string | symbol]: any }
}

export type AlovaConfig = {
  params?: Arg
}

export type MethodConfig = AlovaConfig

export type RequestConfig = DataProxyConfig &
  MethodConfig & {
    modelType?: typeof Model
    detail?: boolean
    pk?: any
  }

export function ensureEndSlash(url: any) {
  url = `${url}`
  return url.endsWith('/') ? url : `${url}/`
}

export class RestRequest<M = any> {
  constructor(public url: string = '', public modelType?: typeof Model) {}

  _getModelType(config?: RequestConfig) {
    return config?.modelType ? config?.modelType : this.modelType
  }

  list<R extends any[] | null = M[]>(subUrl?: string, config?: RequestConfig) {
    return alovaInstApi.Get<R, Model[]>(
      `${this.url}${subUrl}`,
      configUtil.getListConfig<R>(this._getModelType(config),
      config
      ))
  }

  get<R extends any | null = M>(subUrl?: string, config?: RequestConfig) {
    return alovaInstApi.Get<R, Model>(`${this.url}${subUrl}`, configUtil.getConfig<R>(this._getModelType(config), config))
  }

  post<R extends any | null = M>(subUrl?: string, model?: Model, config?: RequestConfig) {
    return alovaInstApi.Post<R, Model>(
      `${this.url}${subUrl}`,
      model,
      configUtil.postConfig<R>(this._getModelType(config), config)
    )
  }

  put<R extends any | null = M>(subUrl?: string, model?: Model, config?: RequestConfig) {
    return alovaInstApi.Put<R, Model>(
      `${this.url}${subUrl}`,
      this._makeData(model, config),
      configUtil.putConfig<R>(this._getModelType(config), model, config)
    )
  }

  delete(subUrl?: SubUrl, model?: Model, config?: RequestConfig) {
    return alovaInstApi.Delete<void>(`${this.url}${subUrl}`,, this._makeData(model, config), configUtil.deleteConfig(config))
  }
}

export class ModelApi<M extends Model, P = number> extends RestRequest<M> {
  constructor(url: Url | string, modelType: typeof Model) {
    super(url, modelType)
  }

  list<R extends Model[] | null = M[]>(config?: RequestConfig) {
    return this.getList<R>(undefined, config)
  }

  create<R extends Model | null = M>(data: Model, config?: RequestConfig) {
    return this.post<R>(undefined, data, config)
  }

  retrieve<R extends Model | null = M>(pk: P, config?: RequestConfig) {
    return this.get<R>(undefined, { pk, ...config })
  }

  update<R extends Model | null = M>(pk: P, model?: Model, config?: RequestConfig) {
    return this.put<R>(undefined, model, { pk, ...config })
  }

  destroy(pk: P, model?: Model, config?: RequestConfig) {
    return this.delete(undefined, model, { pk, ...config })
  }
}

export const configUtil = {
  makeHeaders(headers?: { [key: string]: string }, config?: RequestConfig) {
    headers = { ...headers }
    if (config?.multipart) {
      delete headers['Content-Type']
    }
    return headers
  },

  getConfig<M extends any | null>(type?: typeof Model, config?: RequestConfig) {
    return {
      params: config?.params ? dataProxy(config.params) : undefined,
      headers: this.makeHeaders({}, config),
      transformData(responseData: Model | null): M {
        if (!responseData || !type) return null as M
        return type.$new(responseData) as M
      },
      ...config
    }
  },

  postConfig<M extends any | null>(type?: typeof Model, config?: RequestConfig) {
    return {
      params: config?.params ? dataProxy(config.params) : undefined,
      headers: this.makeHeaders(
        {
          'Content-Type': 'application/json'
        },
        config
      ),
      transformData(responseData: Model | null): M {
        if (!responseData || !type) return null as M
        return type.$new(responseData) as M
      },
      ...config
    }
  },

  putConfig<M extends any | null>(type?: typeof Model, model?: Model, config?: RequestConfig) {
    return {
      params: config?.params ? dataProxy(config.params) : undefined,
      headers: this.makeHeaders(
        {
          'Content-Type': 'application/json'
        },
        config
      ),
      transformData(responseData: Model | null): M {
        if (!responseData || !type || !model) return null as M
        return Object.assign(model, responseData) as M
      },
      ...config
    }
  },

  deleteConfig(config?: RequestConfig) {
    return {
      params: config?.params ? dataProxy(config.params) : undefined,
      headers: this.makeHeaders({}, config),
      ...config
    }
  },

  getListConfig<M extends any[] | null>(type?: typeof Model, config?: RequestConfig) {
    return {
      params: config?.params ? dataProxy(config.params) : undefined,
      headers: this.makeHeaders({}, config),
      transformData(responseData: Model[] | null): M {
        if (!responseData || !type) return null as M
        return responseData.map((x) => type.$new(x)) as M
      },
      ...config
    }
  },

  postListConfig<M extends any[] | null>(type?: typeof Model, config?: RequestConfig) {
    return {
      params: config?.params ? dataProxy(config.params) : undefined,
      headers: this.makeHeaders(
        {
          'Content-Type': 'application/json'
        },
        config
      ),
      transformData(responseData: Model[] | null): M {
        if (!responseData || !type) return null as M
        return responseData.map((x) => type.$new(x)) as M
      },
      ...config
    }
  },

  putListConfig<M extends any[] | null>(type?: typeof Model, models?: Model[], config?: RequestConfig) {
    return {
      params: config?.params ? dataProxy(config.params) : undefined,
      headers: this.makeHeaders(
        {
          'Content-Type': 'application/json'
        },
        config
      ),
      transformData(responseData: Model[] | null): M {
        if (!responseData || !type || !models) return null as M
        return models.map((x, index) => type.$init(x, responseData[index])) as M
      },
      ...config
    }
  },

  deleteListConfig(config?: RequestConfig) {
    return {
      params: config?.params ? dataProxy(config.params) : undefined,
      headers: this.makeHeaders({}, config),
      ...config
    }
  }
}
