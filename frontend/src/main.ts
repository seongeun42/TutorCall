import { createApp } from 'vue'
import { createPinia } from 'pinia'
import router from '@/router/index'
import './style.css'
import './ckeditor-reset.css'
import App from './App.vue'
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'
import CKEditor from '@ckeditor/ckeditor5-vue'

const pinia = createPinia()
pinia.use(piniaPluginPersistedstate)

const app = createApp(App)

app.use(CKEditor)
app.use(router)
app.use(pinia)

app.mount('#app')
