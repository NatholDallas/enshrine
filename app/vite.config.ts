import { fileURLToPath, URL } from 'node:url'

import vue from '@vitejs/plugin-vue'
import UnoCSS from 'unocss/vite'
import { defineConfig } from 'vite'
import { ev$dev } from './plugin/vite.electron.dev'
import { ev$build } from './plugin/vite.electron.build'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue(), UnoCSS(), ev$build()],
  base: './',
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  }
})
