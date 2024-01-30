import { createApp } from 'vue'
import { createPinia } from 'pinia'
import './style.css'
import App from './App.vue'
import CKEditor from '@ckeditor/ckeditor5-vue'

const app = createApp(App)

app.use(createPinia())
app.use(CKEditor)
app.mount('#app')
