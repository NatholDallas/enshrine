import { AxiosInst, BaseRequest as BaseService, RequestAction, type EmptyData, type RequestService } from './core/api'
import {
  UserIn,
  type LoginIn,
  type RegisterIn,
  type UserOut,
  StarIn,
  StarOut,
  VideoIn,
  VideoOut,
  WebsiteIn,
  WebsiteOut,
  EmailIn
} from './model'

export class AccountService implements RequestService {
  login(data: LoginIn, action: RequestAction<EmptyData> = new RequestAction()) {
    return AxiosInst.$request<EmptyData>({ url: '/login', method: 'POST', data }, action)
  }

  register(data: RegisterIn, action: RequestAction<UserOut> = new RequestAction()) {
    return AxiosInst.$request<UserOut>({ url: '/register', method: 'POST', data }, action)
  }

  logout(action: RequestAction<EmptyData> = new RequestAction()) {
    return AxiosInst.$request<EmptyData>({ url: '/logout', method: 'POST' }, action)
  }
}

export class EmailService implements RequestService {
  readonly path: string = '/email'

  sendCode(data: EmailIn, action: RequestAction<EmptyData> = new RequestAction()) {
    return AxiosInst.$request<EmptyData>({ url: this.path, method: 'POST', data }, action)
  }
}

export class UserService implements RequestService {
  readonly path: string = '/user'

  retrieve(action: RequestAction<UserOut> = new RequestAction()) {
    return AxiosInst.$request<UserOut>({ url: this.path, method: 'GET' }, action)
  }

  update(data: UserIn, action: RequestAction<EmptyData> = new RequestAction()) {
    return AxiosInst.$request<EmptyData>({ url: this.path, method: 'PUT', data }, action)
  }

  destroy(action: RequestAction<EmptyData> = new RequestAction()) {
    return AxiosInst.$request<EmptyData>({ url: this.path, method: 'DELETE' }, action)
  }
}

export class StarService extends BaseService<StarIn, StarOut> {
  override readonly path: string = '/star'
}

export class VideoService extends BaseService<VideoIn, VideoOut> {
  override readonly path: string = '/video'
}

export class WebsiteService extends BaseService<WebsiteIn, WebsiteOut> {
  override readonly path: string = '/website'
}
