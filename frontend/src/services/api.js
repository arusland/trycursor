import axios from 'axios'

const API_BASE_URL = 'http://localhost:8080/api'

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json'
  }
})

// Request interceptor
api.interceptors.request.use(
  (config) => {
    console.log('Making request to:', config.url)
    return config
  },
  (error) => {
    return Promise.reject(error)
  }
)

// Response interceptor
api.interceptors.response.use(
  (response) => {
    return response
  },
  (error) => {
    console.error('API Error:', error.response?.data || error.message)
    return Promise.reject(error)
  }
)

export const userService = {
  getAllUsers() {
    return api.get('/users')
  },
  
  getUserById(id) {
    return api.get(`/users/${id}`)
  },
  
  createUser(user) {
    return api.post('/users', user)
  },
  
  updateUser(id, user) {
    return api.put(`/users/${id}`, user)
  },
  
  deleteUser(id) {
    return api.delete(`/users/${id}`)
  },
  
  getUsersByRole(role) {
    return api.get(`/users/role/${role}`)
  },
  
  getTotalUsers() {
    return api.get('/users/stats/total')
  },
  
  getRecentUsers() {
    return api.get('/users/recent')
  }
}

export const dashboardService = {
  getDashboardStats() {
    return api.get('/dashboard')
  },
  
  getAnalyticsData() {
    return api.get('/dashboard/analytics')
  }
}

export default api 