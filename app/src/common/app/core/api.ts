import axios, { AxiosError, type AxiosRequestConfig, type AxiosResponse } from 'axios'
import { ref, type Ref } from 'vue'
import type { Model } from './model'

export type AxiosSuccessHandler<T = any> = (response: AxiosResponse<T>) => void
export type AxiosErrorHandler<T = any> = (reason: AxiosError<T>) => void
export type AxiosCompleteHandler = () => void

export class AxiosRequestHandler<T = any> {
  success: AxiosSuccessHandler<T> = () => {}
  error: AxiosErrorHandler<T> = () => {}
  complete: AxiosCompleteHandler = () => {}
}

export class ResponseState<T extends Model = Model> {
  readonly data: Ref<T> = ref(undefined!)
  readonly response: Ref<AxiosResponse<T>> = ref(undefined!)
  readonly loading: Ref<boolean> = ref(false)
  onSuccess: (func: AxiosSuccessHandler<T>) => void = () => {}
  onError: (func: AxiosErrorHandler<T>) => void = () => {}
  onComplete: (func: AxiosCompleteHandler) => void = () => {}
  send: () => Promise<void> = async () => {}
}

export class AxiosInst {
  private static readonly instance = axios.create({
    baseURL: 'http://localhost',
    withCredentials: true,
    timeout: 10000
  })

  static $request<T extends Model = Model>(config: AxiosRequestConfig): ResponseState<T> {
    const state = new ResponseState<T>()
    const handler = new AxiosRequestHandler<T>()
    state.onSuccess = (func) => (handler.success = func)
    state.onError = (func) => (handler.error = func)
    state.onComplete = (func) => (handler.complete = func)
    state.send = () => this.request(state, config, handler)
    return state
  }

  static async request<T extends Model = Model>(state: ResponseState<T>, config: AxiosRequestConfig, handler: AxiosRequestHandler<T>) {
    state.loading.value = true
    await AxiosInst.instance
      .request(config)
      .then((response: AxiosResponse) => {
        state.data.value = response.data
        state.response.value = response
        handler.success(response)
      })
      .catch((reason: AxiosError<T>) => {
        handler.error(reason)
      })
      .finally(() => {
        handler.complete()
        state.loading.value = false
      })
  }
}

export abstract class BaseRequest<I extends Model, O extends Model> {
  abstract readonly path: string

  list(page: number) {
    return AxiosInst.$request<O[]>({ url: this.path, method: 'GET', params: { page } })
  }

  create(data: I) {
    return AxiosInst.$request({ url: this.path, method: 'POST', data })
  }

  retrieve(id: number) {
    return AxiosInst.$request({ url: `${this.path}/${id}`, method: 'GET' })
  }

  update(id: number, data: O) {
    return AxiosInst.$request({ url: `${this.path}/${id}`, method: 'PUT', data })
  }

  delete(id: number) {
    return AxiosInst.$request({ url: `${this.path}/${id}`, method: 'DELETE' })
  }
}
