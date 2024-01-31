import { createApp } from 'vue'
import { createPinia } from 'pinia'
import router from '@/router/index'
import './style.css'
import App from './App.vue'
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'

const pinia = createPinia();
pinia.use(piniaPluginPersistedstate);

const app = createApp(App)
app.use(router);
app.use(pinia);

app.mount('#app')
