<script setup lang="ts">
import { StarService } from '@/common/app/api'
import NPadding from '@/components/custom/NPadding.vue'
import { Card, Space, Table, defineTableColumns } from 'vexip-ui'
import { ref } from 'vue'

const starService = new StarService()

const page = ref(1)
const { loading, data } = starService.list(page.value)

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
</script>

<template>
  <NPadding>
    <Space vertical>
      <Card class="w-full" :loading="loading">
        <template #title>数据面板</template>
      </Card>
      <Table class="w-full" :loading="loading" :columns="columns" :data="data"></Table>
    </Space>
  </NPadding>
</template>

<style scoped></style>
