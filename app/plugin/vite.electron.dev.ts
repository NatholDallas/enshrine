import { spawn } from 'child_process'
import { Plugin } from 'vite'
import fs from 'node:fs'
import { AddressInfo } from 'net'
import { buildBackground } from './common'

export const ev$dev = (): Plugin => ({
  name: 'electron-dev',
  configureServer: (server) => {
    buildBackground()
    server.httpServer?.on('listening', () => {
      const address = server.httpServer?.address() as AddressInfo
      const ipv4 = `http://localhost:${address.port}`
      let process = spawn(require('electron'), ['dist/background.js', ipv4])
      fs.watchFile('src/background.ts', () => {
        process.kill()
        buildBackground()
        process = spawn(require('electron'), ['dist/background.js', ipv4])
      })
    })
  }
})
