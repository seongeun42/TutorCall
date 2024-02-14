import { fileURLToPath, URL } from 'node:url'
import ckeditor5 from '@ckeditor/vite-plugin-ckeditor5'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import * as path from 'path'
import { createRequire } from 'node:module'
import { manualChunksPlugin } from 'vite-plugin-webpackchunkname'

const require = createRequire(import.meta.url)
// https://vitejs.dev/config/
export default defineConfig({
  build: {
    rollupOptions: {
      external: [
        /^node:.*/,
      ]
    }
  },
  plugins: [
    vue(),
    manualChunksPlugin(),
    ckeditor5({
      theme: require.resolve('@ckeditor/ckeditor5-theme-lark')
    })
  ],
  resolve: {
    alias: {
      // '@': fileURLToPath(new URL('./src', import.meta.url))
      '@': path.resolve(__dirname, './src')
    }
  }
})
