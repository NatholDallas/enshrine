import { createRouter, createWebHistory } from 'vue-router'
import { Urls } from './urls'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: Urls.home,
      name: Urls.home,
      redirect: Urls.star,
      beforeEnter: async () => {},
      component: () => import('@/views/HomeView.vue'),
      children: [
        {
          path: Urls.star,
          name: Urls.star,
          component: () => import('@/views/StarView.vue')
        },
        {
          path: Urls.video,
          name: Urls.video,
          component: () => import('@/views/VideoView.vue')
        },
        {
          path: Urls.website,
          name: Urls.website,
          component: () => import('@/views/WebsiteView.vue')
        }
      ]
    },
    {
      path: Urls.login,
      name: Urls.login,
      component: () => import('@/views/LoginView.vue')
    }
  ]
})

function routeCtl() {
  // return async (to, from, next) => {
  //   const permission = await aGetPermission(...(options.permissions || []))
  //   let path: string = ''
  //   if (!permission) {
  //     if (options.backPath) path = isFunction(options.backPath) ? options.backPath() : options.backPath
  //   } else {
  //     if (options.nextPath) path = isFunction(options.nextPath) ? options.nextPath(permission) : options.nextPath
  //   }
  //   if (!path) {
  //     return next()
  //   }
  //   to = router.resolve(path)
  //   if (to.meta.callback) to.query.callback = location.pathname
  //   return next(to)
  // }
}

export default router
