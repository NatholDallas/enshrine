import { defineStore } from 'pinia'

type User = {
  id: number
  email: string
  username: string
  password: string
  avator: string
}

export const useUserStore = defineStore('user', {
  state: defaultValue,
  actions: {
    reset: () => (useUserStore().$state = defaultValue())
  }
})

function defaultValue(): User {
  return {
    id: -1,
    email: '',
    username: '',
    password: '',
    avator: ''
  }
}
