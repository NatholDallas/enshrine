import { Model } from './core/model'

export class LoginIn extends Model {
  username: string = ''
  password: string = ''
}

export class RegisterIn extends Model {
  email: string = ''
  code: string = ''
  username: string = ''
  password: string = ''
}

export class EmailIn extends Model {
  email: string = ''
}

export class EmailUpdateIn extends Model {}

export class UserIn extends Model {
  email: string = ''
  username: string = ''
  password: string = ''
  avator: string = ''
}

export class UserOut extends UserIn {
  readonly id: number = -1
}

export class StarIn extends Model {
  name: string = ''
  image: string = ''
  description: string = ''
}

export class StarOut extends StarIn {
  readonly id: number = -1
}

export class VideoIn extends Model {
  title: string = ''
  videoUrl: string = ''
  url: string = ''
  description: string = ''
}

export class VideoOut extends VideoIn {
  readonly id: number = -1
}

export class WebsiteIn extends Model {
  user: number = -1
  title: string = ''
  url: string = ''
  description: string = ''
}

export class WebsiteOut extends WebsiteIn {
  readonly id: number = -1
}
