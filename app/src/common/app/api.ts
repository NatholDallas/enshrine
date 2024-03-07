import { AxiosInst, BaseRequest } from './core/api'
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
  WebsiteOut
} from './model'

export class Account {
  static login(data: LoginIn) {
    return AxiosInst.$request({ url: '/login', method: 'POST', data })
  }

  static register(data: RegisterIn) {
    return AxiosInst.$request({ url: '/register', method: 'POST', data })
  }

  static logout() {
    return AxiosInst.$request({ url: '/logout', method: 'POST' })
  }
}

export class UserRequest extends BaseRequest<UserIn, UserOut> {
  override readonly path: string = ''
}

export class StarRequest extends BaseRequest<StarIn, StarOut> {
  override readonly path: string = '/star'
}

export class VideoRequest extends BaseRequest<VideoIn, VideoOut> {
  override readonly path: string = '/video'
}

export class WebsiteService extends BaseRequest<WebsiteIn, WebsiteOut> {
  override readonly path: string = '/website'
}
