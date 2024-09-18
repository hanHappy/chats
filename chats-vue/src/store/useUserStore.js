import { defineStore } from 'pinia'
import { api } from "@/api/common.js";

export const useUserStore = defineStore('user', {
  state: () => ({
    token: null,
    user: null,
  }),
  getters: {
    isAuthenticated: (state) => !!state.token,
    username: (state) => state.user?.username || ''
  },
  actions: {
    getToken() {
      return `Bearer ${ sessionStorage.getItem('JWT') }`
    },
    setToken(token) {
      this.token = token
      sessionStorage.setItem('JWT', token)
    },
    setUser(user) {
      this.user = user
    },
    logout() {
      this.token = null
      this.user = null
      sessionStorage.removeItem('JWT')
    },
    async signin(credential) {
      if (!credential.username || !credential.password) {
        throw new Error('아이디와 비밀번호를 모두 입력해주세요.');
      }

      try {
        const response = await api.auth.signin(credential);

        this.setToken(await response.token);

        const userData = await api.user.getUser(credential.username);

        this.setUser(userData);

      } catch (error) {
        throw error;
      }
    },
  },
})