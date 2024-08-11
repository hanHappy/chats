<script setup>

import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { auth } from "@/api/auth.js";
import { ApiError, UnknownError } from "@/utils/errors.js";

const router = useRouter();

const username = ref('');
const password = ref('');
const errorMessage = ref('');

const signUp = async () => {
  if (!username.value || !password.value) {
    alert('아이디와 비밀번호를 모두 입력해주세요.');
    return;
  }

  try {
    const response = await auth.signup({
      username: username.value,
      password: password.value,
    });

    alert('회원 가입 성공. 로그인해주세요.')
    router.push({ name: 'SignIn' })

    return response;
  } catch (error) {
    if (error instanceof ApiError) {
      switch (error.code) {
        case 'USER_002':
          errorMessage.value = error.message;
          break;
      }
    } else if (error instanceof UnknownError) {
      console.error(`Unknown error : Status ${ error.status }, Message ${ error.message }`);
      alert('알 수 없는 오류가 발생하였습니다. 다시 시도해주세요.');
      return error;
    } else {
      console.error('Unexpected error :', error);
      alert('예상치 못한 오류가 발생했습니다.');
      return error;
    }
  }

};

</script>

<template>
  <div class="mt-4">
    <label>
      <span class="color-2">ID</span>
      <input type="text" class="w-full mt-2" v-model.trim="username" @keyup.enter="signUp"
             autofocus>
    </label>
  </div>
  <div class="mt-4">
    <label>
      <span class="color-2">PW</span>
      <input type="password" class="w-full mt-2" v-model.trim="password" @keyup.enter="signUp"
             autofocus>
    </label>
  </div>
  <div class="relative">
    <div class="color-5 mt-2 absolute">{{ errorMessage }}</div>
  </div>
  <div class="mt-16">
    <button class="w-full" @click="signUp">회원 가입</button>
  </div>
</template>

<style scoped>

</style>