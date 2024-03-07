export class Model {
  static $new<T extends Model>(this: new (initData?: object) => T, data?: object): T {
    const obj = new this(data) as T
    return Object.assign(obj, data)
  }
}
