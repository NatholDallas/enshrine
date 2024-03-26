<script setup lang="ts">
import { AccountService } from '@/common/app/api'
import { LoginIn } from '@/common/app/model'
import router from '@/router'
import { Urls } from '@/router/urls'
import { useOptStore } from '@/stores/optStore'
import { Button, Form, FormItem, Input, Space } from 'vexip-ui'
import { reactive } from 'vue'

const optStore = useOptStore()

const account = new AccountService()

const loginIn = reactive(LoginIn.$new())
const { loading, send } = account.login(loginIn, {
  immediate: false,
  success: () => {
    optStore.isLogin = true
    router.push(Urls.home)
  }
})

console.log(optStore.isLogin);
</script>

<template>
  <Form :model="loginIn" :loading="loading" label-align="top">
    <FormItem label="用户名" prop="username">
      <Input placeholder="请输入" />
    </FormItem>
    <FormItem label="密码" prop="password">
      <Input type="password" placeholder="请输入" />
    </FormItem>
    <Space class="w-full" vertical>
      <Button :loading="loading" @click="send" type="info" block>登录</Button>
      <Button :loading="loading" @click="$router.push(Urls.register)" block>注册界面</Button>
    </Space>
  </Form>
</template>

<style scoped></style>
