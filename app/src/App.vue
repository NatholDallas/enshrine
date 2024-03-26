<script setup lang="ts">
import { ConfigProvider } from 'vexip-ui'
</script>

<template>
  <ConfigProvider>
    <RouterView v-slot="{ Component }">
      <Transition name="fade" mode="out-in">
        <component :is="Component" />
      </Transition>
    </RouterView>
  </ConfigProvider>
</template>

<style scoped></style>
