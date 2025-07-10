<template>
  <div class="space-y-6">
    <!-- Page header -->
    <div class="sm:flex sm:items-center sm:justify-between">
      <div>
        <h2 class="text-2xl font-bold text-gray-900">Analytics</h2>
        <p class="mt-1 text-sm text-gray-500">Detailed analytics and performance metrics</p>
      </div>
    </div>

    <!-- Performance metrics -->
    <div class="grid grid-cols-1 gap-5 sm:grid-cols-2 lg:grid-cols-4">
      <div class="card">
        <div class="flex items-center">
          <div class="flex-shrink-0">
            <div class="w-8 h-8 bg-blue-500 rounded-lg flex items-center justify-center">
              <svg class="w-5 h-5 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 10V3L4 14h7v7l9-11h-7z" />
              </svg>
            </div>
          </div>
          <div class="ml-4">
            <p class="text-sm font-medium text-gray-500">Response Time</p>
            <p class="text-2xl font-semibold text-gray-900">{{ analytics.averageResponseTime || 0 }}ms</p>
          </div>
        </div>
      </div>

      <div class="card">
        <div class="flex items-center">
          <div class="flex-shrink-0">
            <div class="w-8 h-8 bg-green-500 rounded-lg flex items-center justify-center">
              <svg class="w-5 h-5 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z" />
              </svg>
            </div>
          </div>
          <div class="ml-4">
            <p class="text-sm font-medium text-gray-500">Uptime</p>
            <p class="text-2xl font-semibold text-gray-900">{{ analytics.uptime || 0 }}%</p>
          </div>
        </div>
      </div>

      <div class="card">
        <div class="flex items-center">
          <div class="flex-shrink-0">
            <div class="w-8 h-8 bg-red-500 rounded-lg flex items-center justify-center">
              <svg class="w-5 h-5 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
              </svg>
            </div>
          </div>
          <div class="ml-4">
            <p class="text-sm font-medium text-gray-500">Error Rate</p>
            <p class="text-2xl font-semibold text-gray-900">{{ analytics.errorRate || 0 }}%</p>
          </div>
        </div>
      </div>

      <div class="card">
        <div class="flex items-center">
          <div class="flex-shrink-0">
            <div class="w-8 h-8 bg-purple-500 rounded-lg flex items-center justify-center">
              <svg class="w-5 h-5 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 8v8m-4-5v5m-4-2v2m-2 4h12a2 2 0 002-2V6a2 2 0 00-2-2H6a2 2 0 00-2 2v12a2 2 0 002 2z" />
              </svg>
            </div>
          </div>
          <div class="ml-4">
            <p class="text-sm font-medium text-gray-500">Growth Rate</p>
            <p class="text-2xl font-semibold text-gray-900">{{ analytics.growthRate || 0 }}%</p>
          </div>
        </div>
      </div>
    </div>

    <!-- Charts -->
    <div class="grid grid-cols-1 gap-6 lg:grid-cols-2">
      <!-- Monthly Growth Chart -->
      <div class="card">
        <h3 class="text-lg font-medium text-gray-900 mb-4">Monthly User Growth</h3>
        <div class="h-64">
          <BarChart v-if="analytics.monthlyGrowth" :data="analytics.monthlyGrowth" />
        </div>
      </div>

      <!-- User Activity Chart -->
      <div class="card">
        <h3 class="text-lg font-medium text-gray-900 mb-4">Weekly User Activity</h3>
        <div class="h-64">
          <LineChart v-if="analytics.userActivity" :data="analytics.userActivity" />
        </div>
      </div>
    </div>

    <!-- Detailed metrics -->
    <div class="grid grid-cols-1 gap-6 lg:grid-cols-3">
      <!-- User Statistics -->
      <div class="card">
        <h3 class="text-lg font-medium text-gray-900 mb-4">User Statistics</h3>
        <div class="space-y-3">
          <div class="flex justify-between">
            <span class="text-sm text-gray-600">Total Users</span>
            <span class="text-sm font-medium">{{ analytics.totalUsers || 0 }}</span>
          </div>
          <div class="flex justify-between">
            <span class="text-sm text-gray-600">New This Month</span>
            <span class="text-sm font-medium">{{ analytics.usersThisMonth || 0 }}</span>
          </div>
          <div class="flex justify-between">
            <span class="text-sm text-gray-600">Growth Rate</span>
            <span class="text-sm font-medium text-green-600">{{ analytics.growthRate || 0 }}%</span>
          </div>
        </div>
      </div>

      <!-- Performance Metrics -->
      <div class="card">
        <h3 class="text-lg font-medium text-gray-900 mb-4">Performance</h3>
        <div class="space-y-3">
          <div class="flex justify-between">
            <span class="text-sm text-gray-600">Avg Response Time</span>
            <span class="text-sm font-medium">{{ analytics.averageResponseTime || 0 }}ms</span>
          </div>
          <div class="flex justify-between">
            <span class="text-sm text-gray-600">Uptime</span>
            <span class="text-sm font-medium text-green-600">{{ analytics.uptime || 0 }}%</span>
          </div>
          <div class="flex justify-between">
            <span class="text-sm text-gray-600">Error Rate</span>
            <span class="text-sm font-medium text-red-600">{{ analytics.errorRate || 0 }}%</span>
          </div>
        </div>
      </div>

      <!-- System Health -->
      <div class="card">
        <h3 class="text-lg font-medium text-gray-900 mb-4">System Health</h3>
        <div class="space-y-3">
          <div class="flex items-center justify-between">
            <span class="text-sm text-gray-600">Database</span>
            <span class="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium bg-green-100 text-green-800">
              Healthy
            </span>
          </div>
          <div class="flex items-center justify-between">
            <span class="text-sm text-gray-600">API</span>
            <span class="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium bg-green-100 text-green-800">
              Online
            </span>
          </div>
          <div class="flex items-center justify-between">
            <span class="text-sm text-gray-600">Cache</span>
            <span class="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium bg-yellow-100 text-yellow-800">
              Warning
            </span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { dashboardService } from '../services/api'
import LineChart from '../components/LineChart.vue'
import BarChart from '../components/BarChart.vue'

const analytics = ref({})

const loadAnalytics = async () => {
  try {
    const response = await dashboardService.getAnalyticsData()
    analytics.value = response.data
  } catch (error) {
    console.error('Error loading analytics:', error)
  }
}

onMounted(() => {
  loadAnalytics()
})
</script> 