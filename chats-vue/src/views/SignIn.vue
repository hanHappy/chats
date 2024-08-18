<script setup>

import { ref } from "vue";
import { useRouter } from "vue-router";
import { ApiError, UnknownError } from "@/utils/errors.js";
import { useUserStore } from "@/store/useUserStore.js";
import { ErrorCode } from "@/constants/errorCode.js";

const router = useRouter();
const userStore = useUserStore();

const username = ref();
const password = ref();

const errorMessage = ref('');

const signIn = async () => {
  if (!username.value || !password.value) {
    alert('아이디와 비밀번호를 모두 입력해주세요.');
    return;
  }

  try {
    await userStore.signin({
      username: username.value,
      password: password.value
    });

    await router.push({ name: 'ChatRooms' })
  } catch (error) {
    if (error instanceof ApiError) {
      switch (error.code) {
        case ErrorCode.FORBIDDEN.code:
        case ErrorCode.INVALID_CREDENTIALS.code:
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
      <input type="text" class="w-full mt-2" v-model.trim="username" @keyup.enter="signIn"
             autofocus>
    </label>
  </div>
  <div class="mt-4">
    <label>
      <span class="color-2">PW</span>
      <input type="password" class="w-full mt-2" v-model.trim="password" @keyup.enter="signIn"
             autofocus>
    </label>
  </div>
  <div class="mt-4 flex justify-between">
    <div class="color-5">{{ errorMessage }}</div>
    <RouterLink :to="{name: 'SignUp'}" class="color-2 underline underline-offset-4 mr-3 hover">
      가입
    </RouterLink>
  </div>
  <div class="mt-6">
    <button class="w-full" @click="signIn">입장</button>
  </div>
</template>

<style scoped>
</style>