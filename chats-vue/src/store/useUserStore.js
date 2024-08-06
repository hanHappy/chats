import {defineStore} from 'pinia'
import {HttpStatus} from "@/utils/HttpStatus.js";

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
      localStorage.setItem('token', token)
    },
    setUser(user) {
      this.user = user
    },
    logout() {
      this.token = null
      this.user = null
      localStorage.removeItem('token')
    },
    async signUp(credentials) {
      try {
        const response = await fetch('/api/auth/signup', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(credentials),
        });

        if (!response.ok) {
          const errorData = await response.json();
          throw new Error(errorData.message || '회원가입에 실패했습니다.');
        }

        const data = await response.json();

        if (response.status === HttpStatus.CREATED) {

          // 필요한 경우 여기서 추가 작업을 수행할 수 있습니다.
          // 예: 자동 로그인이 필요한 경우
          // this.setUser(data);
          // this.setToken(data.token); // 만약 토큰이 반환된다면

          return data;
        } else {
          throw new Error('회원가입에 실패했습니다.');
        }
      } catch (error) {
        console.error('회원가입 오류:', error);
        throw new Error(error.message || '회원가입 처리 중 오류가 발생했습니다.');
      }
    },
    async login(credentials) {
      // 여기에 로그인 API 호출 로직을 구현합니다
      // 예시:
      // const response = await api.login(credentials)
      // this.setToken(response.token)
      // this.setUser(response.user)
    },
    async checkAuth() {
      const token = localStorage.getItem('token')
      if (token) {
        this.setToken(token)
        // 토큰으로 사용자 정보를 가져오는 API 호출
        // const user = await api.getUserInfo()
        // this.setUser(user)
      }
    },
  },
})