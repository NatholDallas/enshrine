<script setup lang="ts">
import { AccountService, EmailService } from '@/common/app/api'
import { EmailIn, RegisterIn } from '@/common/app/model'
import router from '@/router'
import { Urls } from '@/router/urls'
import { Button, Form, FormItem, Input, Message, Space } from 'vexip-ui'
import { computed, reactive } from 'vue'

const account = new AccountService()
const email = new EmailService()

const registerIn = reactive(RegisterIn.$new())
const emailIn = computed(() => EmailIn.$new({ email: registerIn.email }))

const { loading: registerLoading, send: register } = account.register(registerIn, {
  immediate: false,
  success: () => {
    Promise.all([Message.success({ content: '注册成功, 前往登录界面', duration: 500 })])
      .then(() => {})
      .catch(() => {})
    Message.success({ content: '注册成功, 前往登录界面', duration: 5000 })
    router.push(Urls.login)
  }
})

const { loading: emailLoading, send: sendCode } = email.sendCode(emailIn.value, {
  immediate: false,
  success: () => {}
})
</script>

<template>
  <Form :model="registerIn" :loading="registerLoading" label-align="top">
    <FormItem label="邮箱" prop="email" required>
      <Input placeholder="请输入" />
      <Button class="ml-2" :loading="emailLoading" @click="sendCode">发送验证码</Button>
    </FormItem>
    <FormItem label="用户名" prop="username" required>
      <Input placeholder="请输入" />
    </FormItem>
    <FormItem label="密码" prop="password" required>
      <Input type="password" placeholder="请输入" minlength="" />
    </FormItem>
    <Space class="w-full" vertical>
      <Button type="info" :loading="registerLoading" @click="register" block>注册</Button>
      <Button :loading="registerLoading" @click="$router.push(Urls.login)" block>登录界面</Button>
    </Space>
  </Form>
</template>

<style scoped></style>
