import { cloneDeep } from 'lodash'

export class Model {
  static $new<T extends Model>(this: new (initData?: object) => T, data?: object) {
    const model = new this(data) as T
    Object.assign(model, data)
    return model
  }

  $copy<T extends Model>(this: T): T {
    return cloneDeep(this)
  }
}

export class NativeModel extends Model {
  static $new<T extends Model>(this: new (initData?: object | undefined) => T, data?: object | undefined) {
    return data as T
  }
}
