<script setup lang="ts">
defineProps<{
  loading?: boolean
}>()
</script>

<template>
  <div class="p-4">
    <div v-if="loading">Loading...</div>
    <div v-else>
      <slot></slot>
    </div>
  </div>
</template>

<style scoped></style>
