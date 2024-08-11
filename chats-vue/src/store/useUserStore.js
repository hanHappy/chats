import { defineStore } from 'pinia'
import { HttpStatus } from "@/utils/HttpStatus.js";
import { auth } from "@/api/auth.js";

export const useUserStore = defineStore('user', {
  state: () => ({
    token: null,
    user: null,
  }),
  getters: {
    isAuthenticated: (state) => !!state.token,
  },
  actions: {
    setToken(token) {
      this.token = token
      localStorage.setItem('TOKEN', token)
    },
    setUser(user) {
      this.user = user
    },
    logout() {
      this.token = null
      this.user = null
      localStorage.removeItem('TOKEN')
    },
  },
})