<template>
  <div class="space-y-6">
    <!-- Page header -->
    <div class="sm:flex sm:items-center sm:justify-between">
      <div>
        <h2 class="text-2xl font-bold text-gray-900">Users</h2>
        <p class="mt-1 text-sm text-gray-500">Manage your application users</p>
      </div>
      <div class="mt-4 sm:mt-0">
        <button @click="showCreateModal = true" class="btn-primary">
          Add User
        </button>
      </div>
    </div>

    <!-- Users table -->
    <div class="card">
      <div class="overflow-x-auto">
        <table class="min-w-full divide-y divide-gray-200">
          <thead class="bg-gray-50">
            <tr>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Name</th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Email</th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Role</th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Created</th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Actions</th>
            </tr>
          </thead>
          <tbody class="bg-white divide-y divide-gray-200">
            <tr v-for="user in users" :key="user.id">
              <td class="px-6 py-4 whitespace-nowrap">
                <div class="text-sm font-medium text-gray-900">{{ user.name }}</div>
              </td>
              <td class="px-6 py-4 whitespace-nowrap">
                <div class="text-sm text-gray-500">{{ user.email }}</div>
              </td>
              <td class="px-6 py-4 whitespace-nowrap">
                <span class="inline-flex px-2 py-1 text-xs font-semibold rounded-full"
                      :class="getRoleBadgeClass(user.role)">
                  {{ user.role }}
                </span>
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                {{ formatDate(user.createdAt) }}
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm font-medium">
                <button @click="editUser(user)" class="text-primary-600 hover:text-primary-900 mr-3">
                  Edit
                </button>
                <button @click="deleteUser(user.id)" class="text-red-600 hover:text-red-900">
                  Delete
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <!-- Create/Edit User Modal -->
    <div v-if="showCreateModal || showEditModal" class="fixed inset-0 bg-gray-600 bg-opacity-50 overflow-y-auto h-full w-full z-50">
      <div class="relative top-20 mx-auto p-5 border w-96 shadow-lg rounded-md bg-white">
        <div class="mt-3">
          <h3 class="text-lg font-medium text-gray-900 mb-4">
            {{ showEditModal ? 'Edit User' : 'Create User' }}
          </h3>
          <form @submit.prevent="showEditModal ? updateUser() : createUser()">
            <div class="space-y-4">
              <div>
                <label class="block text-sm font-medium text-gray-700">Name</label>
                <input v-model="form.name" type="text" required class="input-field" />
              </div>
              <div>
                <label class="block text-sm font-medium text-gray-700">Email</label>
                <input v-model="form.email" type="email" required class="input-field" />
              </div>
              <div>
                <label class="block text-sm font-medium text-gray-700">Role</label>
                <select v-model="form.role" required class="input-field">
                  <option value="">Select Role</option>
                  <option value="Admin">Admin</option>
                  <option value="Manager">Manager</option>
                  <option value="User">User</option>
                </select>
              </div>
            </div>
            <div class="flex justify-end space-x-3 mt-6">
              <button type="button" @click="closeModal" class="btn-secondary">
                Cancel
              </button>
              <button type="submit" class="btn-primary">
                {{ showEditModal ? 'Update' : 'Create' }}
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { userService } from '../services/api'

const users = ref([])
const showCreateModal = ref(false)
const showEditModal = ref(false)
const editingUserId = ref(null)
const form = ref({
  name: '',
  email: '',
  role: ''
})

const loadUsers = async () => {
  try {
    const response = await userService.getAllUsers()
    users.value = response.data
  } catch (error) {
    console.error('Error loading users:', error)
  }
}

const createUser = async () => {
  try {
    await userService.createUser(form.value)
    await loadUsers()
    closeModal()
  } catch (error) {
    console.error('Error creating user:', error)
  }
}

const editUser = (user) => {
  editingUserId.value = user.id
  form.value = { ...user }
  showEditModal.value = true
}

const updateUser = async () => {
  try {
    await userService.updateUser(editingUserId.value, form.value)
    await loadUsers()
    closeModal()
  } catch (error) {
    console.error('Error updating user:', error)
  }
}

const deleteUser = async (userId) => {
  if (confirm('Are you sure you want to delete this user?')) {
    try {
      await userService.deleteUser(userId)
      await loadUsers()
    } catch (error) {
      console.error('Error deleting user:', error)
    }
  }
}

const closeModal = () => {
  showCreateModal.value = false
  showEditModal.value = false
  editingUserId.value = null
  form.value = { name: '', email: '', role: '' }
}

const getRoleBadgeClass = (role) => {
  const classes = {
    'Admin': 'bg-red-100 text-red-800',
    'Manager': 'bg-yellow-100 text-yellow-800',
    'User': 'bg-green-100 text-green-800'
  }
  return classes[role] || 'bg-gray-100 text-gray-800'
}

const formatDate = (dateString) => {
  return new Date(dateString).toLocaleDateString()
}

onMounted(() => {
  loadUsers()
})
</script> 