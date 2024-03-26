import type { Method } from 'alova'

export class Event {
  preventFlag = false

  constructor() {}

  emit() {
    return emitEvent(this.constructor.name, this)
  }

  prevent() {
    this.preventFlag = true
  }
}

export class RequestStartEvent extends Event {
  constructor(public method: Method) {
    super()
  }
}

export class RequestSuccessEvent<T = any> extends Event {
  constructor(public response: Response, public method: Method, public data: T) {
    super()
  }
}

export class RequestErrorEvent extends Event {
  constructor(public response: Response, public method: Method, public error: { detail: string[] | string }) {
    super()
  }
}

export class RequestCompleteEvent extends Event {
  constructor(public method: Method) {
    super()
  }
}

export class RequestInternetErrorEvent extends Event {
  constructor(public error: Error, public method: Method) {
    super()
  }
}

export const subscribers: { [key: string]: Function[] } = {}

export function subscribe<T>(eventType: string, func: (event: T) => void) {
  subscribers[eventType] = subscribers[eventType] || []
  if (!subscribers[eventType].includes(func)) {
    unsubscribe(eventType, func)
  }
  subscribers[eventType].push(func)
  return func
}

export function unsubscribe<T>(eventType: string, func: (event: T) => void) {
  if (subscribers[eventType]) {
    subscribers[eventType] = subscribers[eventType].filter((f) => f !== func)
  }
}

export function emitEvent(eventType: string, event: Event) {
  if (!subscribers[eventType]) return
  for (const func of subscribers[eventType]) {
    if (event.preventFlag) break
    func(event)
  }
}
