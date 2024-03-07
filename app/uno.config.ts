import { defineConfig, presetUno, presetIcons, transformerDirectives } from 'unocss'

defineConfig({
  rules: [],
  presets: [presetUno(), presetIcons()],
  transformers: [transformerDirectives()]
})
