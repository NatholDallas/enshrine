import { RestRequest, ModelApi, type MethodConfig } from './core/api'

// export class Invitation extends InvitationOut {
//   static $api = new ModelApi<Invitation, string>('invitations', Invitation)

//   static $list(params: { project: number }, config?: MethodConfig) {
//     return Invitation.$api.list({ params, ...config })
//   }

//   static $create(data: InvitationIn, config?: MethodConfig) {
//     return Invitation.$api.create(data, config)
//   }

//   $destroy(config?: MethodConfig) {
//     return Invitation.$api.destroy(this.code, undefined, config)
//   }
// }
