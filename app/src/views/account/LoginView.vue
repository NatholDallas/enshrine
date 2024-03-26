<script setup lang="ts">
import { options } from '@/common/particles.config'
import LoginForm from '@/components/form/LoginForm.vue'
import { ParticlesComponent as Particles } from 'particles.vue3'
import { loadFull } from 'tsparticles'
import { Card } from 'vexip-ui'
</script>

<template>
  <div class="fixed flex flex-col w-full h-full justify-center items-center">
    <Particles class="z-[-1]" id="tsparticles" :particlesInit="loadFull" :options="options" />
    <Card class="w-100 p-4" title="登录" shadow="hover">
      <LoginForm />
    </Card>
  </div>
</template>

<style scoped></style>
