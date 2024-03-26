import axios, { AxiosError, type AxiosRequestConfig, type AxiosResponse } from 'axios'
import { ref, type Ref } from 'vue'
import type { Model } from './model'

export type EmptyData = {}
export type SuccessHandler<T = any> = (response: AxiosResponse<T>) => void
export type ErrorHandler<T = any> = (reason: AxiosError<T>) => void
export type CompleteHandler = () => void

export class RequestAction<T> {
  immediate?: boolean = true
  success?: SuccessHandler<T> = undefined
  error?: ErrorHandler<T> = undefined
  complete?: CompleteHandler = undefined
}

export class ResponseState<T> {
  readonly data: Ref<T> = ref(undefined!)
  readonly response: Ref<AxiosResponse<T>> = ref(undefined!)
  readonly loading: Ref<boolean> = ref(false)
  send: () => Promise<void> = async () => {}
}

export class AxiosInst {
  private static readonly instance = axios.create({
    baseURL: 'http://localhost/api/v1',
    withCredentials: true,
    timeout: 10000
  })

  static $request<T>(config: AxiosRequestConfig, action: RequestAction<T> = new RequestAction()): ResponseState<T> {
    const state = new ResponseState<T>()
    state.send = AxiosInst.request(config, state, action)
    if (action.immediate) state.send()
    return state
  }

  static request<T>(config: AxiosRequestConfig, state: ResponseState<T>, action: RequestAction<T>) {
    return async () => {
      state.loading.value = true
      AxiosInst.instance
        .request(config)
        .then((response: AxiosResponse<T>) => {
          state.data.value = response.data
          state.response.value = response
          action.success?.(response)
        })
        .catch((reason: AxiosError<T>) => {
          action.error?.(reason)
        })
        .finally(() => {
          state.loading.value = false
          action.complete?.()
        })
    }
  }
}

export interface RequestService {}

export abstract class BaseRequest<I extends Model, O extends Model> implements RequestService {
  abstract readonly path: string

  list(page: number, action: RequestAction<O[]> = new RequestAction()) {
    return AxiosInst.$request<O[]>({ url: this.path, method: 'GET', params: { page } }, action)
  }

  create(data: I, action: RequestAction<O> = new RequestAction()) {
    return AxiosInst.$request<O>({ url: this.path, method: 'POST', data }, action)
  }

  retrieve(id: number, action: RequestAction<O> = new RequestAction()) {
    return AxiosInst.$request({ url: `${this.path}/${id}`, method: 'GET' }, action)
  }

  update(id: number, data: O, action: RequestAction<EmptyData> = new RequestAction()) {
    return AxiosInst.$request({ url: `${this.path}/${id}`, method: 'PUT', data }, action)
  }

  destroy(id: number, action: RequestAction<EmptyData> = new RequestAction()) {
    return AxiosInst.$request({ url: `${this.path}/${id}`, method: 'DELETE' }, action)
  }
}
