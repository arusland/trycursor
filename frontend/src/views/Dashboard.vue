<template>
  <div class="space-y-6">
    <!-- Page header -->
    <div class="sm:flex sm:items-center sm:justify-between">
      <div>
        <h2 class="text-2xl font-bold text-gray-900">Dashboard</h2>
        <p class="mt-1 text-sm text-gray-500">Overview of your application</p>
      </div>
    </div>

    <!-- Stats cards -->
    <div class="grid grid-cols-1 gap-5 sm:grid-cols-2 lg:grid-cols-4">
      <div class="card">
        <div class="flex items-center">
          <div class="flex-shrink-0">
            <div class="w-8 h-8 bg-blue-500 rounded-lg flex items-center justify-center">
              <svg class="w-5 h-5 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 4.354a4 4 0 110 5.292M15 21H3v-1a6 6 0 0112 0v1zm0 0h6v-1a6 6 0 00-9-5.197m13.5-9a2.5 2.5 0 11-5 0 2.5 2.5 0 015 0z" />
              </svg>
            </div>
          </div>
          <div class="ml-4">
            <p class="text-sm font-medium text-gray-500">Total Users</p>
            <p class="text-2xl font-semibold text-gray-900">{{ stats.totalUsers || 0 }}</p>
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
            <p class="text-sm font-medium text-gray-500">Active Users</p>
            <p class="text-2xl font-semibold text-gray-900">{{ stats.activeUsers || 0 }}</p>
          </div>
        </div>
      </div>

      <div class="card">
        <div class="flex items-center">
          <div class="flex-shrink-0">
            <div class="w-8 h-8 bg-yellow-500 rounded-lg flex items-center justify-center">
              <svg class="w-5 h-5 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 7h8m0 0v8m0-8l-8 8-4-4-6 6" />
              </svg>
            </div>
          </div>
          <div class="ml-4">
            <p class="text-sm font-medium text-gray-500">Growth Rate</p>
            <p class="text-2xl font-semibold text-gray-900">{{ stats.averageAge || 0 }}%</p>
          </div>
        </div>
      </div>

      <div class="card">
        <div class="flex items-center">
          <div class="flex-shrink-0">
            <div class="w-8 h-8 bg-purple-500 rounded-lg flex items-center justify-center">
              <svg class="w-5 h-5 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 19v-6a2 2 0 00-2-2H5a2 2 0 00-2 2v6a2 2 0 002 2h2a2 2 0 002-2zm0 0V9a2 2 0 012-2h2a2 2 0 012 2v10m-6 0a2 2 0 002 2h2a2 2 0 002-2m0 0V5a2 2 0 012-2h2a2 2 0 012 2v14a2 2 0 01-2 2h-2a2 2 0 01-2-2z" />
              </svg>
            </div>
          </div>
          <div class="ml-4">
            <p class="text-sm font-medium text-gray-500">Total Revenue</p>
            <p class="text-2xl font-semibold text-gray-900">$12,345</p>
          </div>
        </div>
      </div>
    </div>

    <!-- Charts and data -->
    <div class="grid grid-cols-1 gap-6 lg:grid-cols-2">
      <!-- User Growth Chart -->
      <div class="card">
        <h3 class="text-lg font-medium text-gray-900 mb-4">User Growth</h3>
        <div class="h-64">
          <LineChart v-if="stats.userGrowth" :data="stats.userGrowth" />
        </div>
      </div>

      <!-- Role Distribution -->
      <div class="card">
        <h3 class="text-lg font-medium text-gray-900 mb-4">Role Distribution</h3>
        <div class="space-y-3">
          <div v-for="(count, role) in stats.roleDistribution" :key="role" class="flex items-center justify-between">
            <span class="text-sm font-medium text-gray-700">{{ role }}</span>
            <span class="text-sm text-gray-500">{{ count }} users</span>
          </div>
        </div>
      </div>
    </div>

    <!-- Recent Activities -->
    <div class="card">
      <h3 class="text-lg font-medium text-gray-900 mb-4">Recent Activities</h3>
      <div class="space-y-3">
        <div v-for="activity in stats.recentActivities" :key="activity.timestamp" class="flex items-center justify-between py-2 border-b border-gray-100 last:border-b-0">
          <div>
            <p class="text-sm font-medium text-gray-900">{{ activity.action }}</p>
            <p class="text-xs text-gray-500">by {{ activity.user }}</p>
          </div>
          <span class="text-xs text-gray-400">{{ activity.timestamp }}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { dashboardService } from '../services/api'
import LineChart from '../components/LineChart.vue'

const stats = ref({})

const loadDashboardStats = async () => {
  try {
    const response = await dashboardService.getDashboardStats()
    stats.value = response.data
  } catch (error) {
    console.error('Error loading dashboard stats:', error)
  }
}

onMounted(() => {
  loadDashboardStats()
})
</script> 