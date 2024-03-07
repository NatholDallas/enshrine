<script setup lang="ts">
import { Account } from '@/common/app/api'
import { LoginIn } from '@/common/app/model'
import router from '@/router'
import { Urls } from '@/router/urls'
import { Button, Form, FormItem, Input } from 'vexip-ui'
import { reactive } from 'vue'

const model = reactive(LoginIn.$new())
const { loading, onSuccess, send } = Account.login(model)
onSuccess(() => router.push(Urls.home))
</script>

<template>
  <Form :model="model" :loading="loading" label-align="top">
    <FormItem label="用户名" prop="username">
      <Input placeholder="请输入" />
    </FormItem>
    <FormItem label="密码" prop="password">
      <Input type="password" placeholder="请输入" />
    </FormItem>
    <Button :loading="loading" @click="send" type="info" block>登录</Button>
  </Form>
</template>

<style scoped></style>
