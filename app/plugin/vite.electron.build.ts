import * as electronBuilder from 'electron-builder'
import { Plugin } from 'vite'
import { buildBackground } from './common'
import path from 'node:path'
import fs from 'node:fs'

export const ev$build = (): Plugin => ({
  name: 'electron-build',
  closeBundle: () => {
    buildBackground()
    const json = JSON.parse(fs.readFileSync('package.json', 'utf-8'))
    fs.writeFileSync('dist/package.json', JSON.stringify(json))
    electronBuilder.build({
      config: {
        appId: 'nathol.explorer.app',
        productName: 'NatholExplorer',
        asar: true,
        directories: {
          output: path.resolve(process.cwd(), 'release'),
          app: path.resolve(process.cwd(), 'dist')
        },
        nsis: {
          oneClick: false,
          allowToChangeInstallationDirectory: true
        }
      }
    })
  }
})
