import { defineStore } from 'pinia'

export type Opt = {
  isLogin: boolean
}

export const useOptStore = defineStore('opt', {
  state: defaultValue,
  actions: {
    reset: () => (useOptStore().$state = defaultValue())
  },
  persist: {
    storage: localStorage
  }
})

function defaultValue(): Opt {
  return {
    isLogin: false
  }
}
