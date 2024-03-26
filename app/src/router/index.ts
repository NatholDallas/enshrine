import { IsLogin, type Permission } from '@/common/permission'
import { createRouter, createWebHistory, type NavigationGuardWithThis } from 'vue-router'
import { Urls } from './urls'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: Urls.home,
      name: Urls.home,
      redirect: Urls.star,
      beforeEnter: routeCtl({ auth: [IsLogin], backPath: Urls.login }),
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
      beforeEnter: routeCtl({ auth: [IsLogin], nextPath: Urls.home }),
      path: Urls.login,
      name: Urls.login,
      component: () => import('@/views/account/LoginView.vue')
    },
    {
      path: Urls.register,
      name: Urls.register,
      component: () => import('@/views/account/RegisterView.vue')
    }
  ]
})

type RouteCTL = {
  auth?: (typeof Permission)[]
  backPath?: string
  nextPath?: string
}

function routeCtl({ auth, backPath, nextPath }: RouteCTL = {}): NavigationGuardWithThis<undefined> | NavigationGuardWithThis<undefined>[] {
  return async (to, from, next) => {
    if (!auth) return next()
    const $auth = auth.some((e) => new e().verify())
    const path = $auth ? nextPath : backPath
    if (!path) return next()
    to = router.resolve(path)
    return next(to)
  }
}

export default router
