<script setup lang="ts">
import { Urls } from '@/router/urls'
import { EarthAfrica, Star, Video } from '@vexip-ui/icons'
import type { LayoutUser, MenuOptions } from 'vexip-ui'
import { Layout } from 'vexip-ui'

const user: LayoutUser = {
  name: 'VexipUI',
  email: 'email@vexip-ui.com'
}

const menus: MenuOptions[] = [
  {
    label: 'star',
    name: '明星',
    icon: Star,
    route: { path: Urls.star }
  },
  {
    label: 'video',
    name: '视频',
    icon: Video,
    route: { path: Urls.video }
  },
  {
    label: 'website',
    name: '网站',
    icon: EarthAfrica,
    route: { path: Urls.website }
  }
]
</script>

<template>
  <Layout
    logo="https://www.vexipui.com/vexip-ui.svg"
    sign-name="NatholExplorer"
    :user="user"
    :menus="menus"
    @menu-select="$router.push"
    fit-window
  >
    <template #main>
      <RouterView />
    </template>
  </Layout>
</template>

<style scoped></style>
