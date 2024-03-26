import { useOptStore } from '@/stores/optStore'

export class Permission {
  verify(): boolean {
    return true
  }
}

export class IsLogin extends Permission {
  override verify() {
    return useOptStore().isLogin
  }
}
