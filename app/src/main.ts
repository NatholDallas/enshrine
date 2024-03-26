import { createPinia } from 'pinia'
import { createApp } from 'vue'

import Particles from 'particles.vue3'
import App from './App.vue'
import router from './router'

import 'vexip-ui/css/dark/index.css'
import 'vexip-ui/css/index.css'
import 'virtual:uno.css'
import './assets/main.css'

const pinia = createPinia()

const app = createApp(App)
app.use(Particles)
app.use(pinia)
app.use(router)

app.mount('#app')
