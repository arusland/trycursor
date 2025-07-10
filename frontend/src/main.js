import { createApp } from 'vue'
import { createRouter, createWebHistory } from 'vue-router'
import App from './App.vue'
import './style.css'

// Import components
import Dashboard from './views/Dashboard.vue'
import Users from './views/Users.vue'
import Analytics from './views/Analytics.vue'

// Create router
const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: '/', redirect: '/dashboard' },
    { path: '/dashboard', component: Dashboard },
    { path: '/users', component: Users },
    { path: '/analytics', component: Analytics }
  ]
})

// Create and mount app
const app = createApp(App)
app.use(router)
app.mount('#app') 