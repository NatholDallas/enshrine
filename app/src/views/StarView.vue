<script setup lang="ts">
import { StarRequest } from '@/common/app/api'
import ComCtl from '@/components/ComCtl.vue'
import { Card, Table, defineTableColumns } from 'vexip-ui'
import { ref } from 'vue'

const page = ref(1)
const { loading, data, send } = new StarRequest().list(page.value)

const columns = defineTableColumns([
  {
    name: '明星名称',
    key: 'name'
  },
  {
    name: '封面',
    key: 'image'
  },
  {
    name: '描述',
    key: 'description'
  }
])

send()
</script>

<template>
  <ComCtl :loading="loading">
    <Card :loading="loading">
      <template #title> 数据面板 </template>
    </Card>
    <Table class="w-full" :loading="loading" :columns="columns" :data="data"></Table>
  </ComCtl>
</template>

<style scoped></style>
