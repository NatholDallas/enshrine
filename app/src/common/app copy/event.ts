import { useRequest } from 'alova'
import { Event, RequestErrorEvent, RequestInternetErrorEvent, RequestStartEvent, RequestSuccessEvent, subscribe } from './core/event'

export class AuthenticatedEvent extends Event {
  constructor() {
    super()
  }
}

subscribe<RequestStartEvent>(RequestStartEvent.name, (e) => {
  if (e.method.type === 'GET') return
})

subscribe<RequestSuccessEvent>(RequestSuccessEvent.name, (e) => {
  if (e.method.type === 'GET') return
})

subscribe<RequestErrorEvent>(RequestErrorEvent.name, (e) => {})

subscribe<RequestInternetErrorEvent>(RequestInternetErrorEvent.name, () => {})

subscribe<AuthenticatedEvent>(AuthenticatedEvent.name, (e) => {})
