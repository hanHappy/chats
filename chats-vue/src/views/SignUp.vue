<script setup>

import {ref} from 'vue';
import {useUserStore} from '@/store/useUserStore'; // 경로는 실제 위치에 맞게 조정하세요
import {useRouter} from 'vue-router';

const userStore = useUserStore();
const router = useRouter();

const userId = ref('');
const password = ref('');

const signUp = async () => {
  if (!userId.value || !password.value) {
    alert('아이디와 비밀번호를 모두 입력해주세요.');
    return;
  }

  try {
    await userStore.signUp({
      userId: userId.value,
      password: password.value
    });

    alert('회원 가입이 완료되었습니다.');
    await router.push({name: 'SignIn'}); // 회원 가입 후 로그인 페이지로 이동
  } catch (error) {
    console.error('회원 가입 오류:', error);
    alert('회원 가입 중 오류가 발생했습니다. 다시 시도해주세요.');
  }
};

</script>

<template>
  <div class="mt-4">
    <label>
      <span class="color-2">ID</span>
      <input type="text" class="w-full mt-2" v-model.trim="userId" @keyup.enter="signUp" autofocus>
    </label>
  </div>
  <div class="mt-4">
    <label>
      <span class="color-2">PW</span>
      <input type="password" class="w-full mt-2" v-model.trim="password" @keyup.enter="signUp"
             autofocus>
    </label>
  </div>
  <div class="mt-16">
    <button class="w-full" @click="signUp">Sign Up</button>
  </div>
</template>

<style scoped>

</style>