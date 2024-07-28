<script setup>

import {ref} from "vue";
import {useUser} from "@/composables/useUser.js";
import {useRouter} from "vue-router";

const router = useRouter();

const userId = ref();
const password = ref();

const enter = () => {
  if (!userId) {
    return;
  }

  useUser().setUser(userId);
  router.push({name: 'ChatRooms'})
}
</script>

<template>
  <div class="mt-4">
    <label>
      <span class="color-2">ID</span>
      <input type="text" class="w-full mt-2" v-model.trim="userId" @keyup.enter="enter" autofocus>
    </label>
  </div>
  <div class="mt-4">
    <label>
      <span class="color-2">PW</span>
      <input type="password" class="w-full mt-2" v-model.trim="password" @keyup.enter="enter"
             autofocus>
    </label>
  </div>
  <div class="mt-4 flex justify-end">
    <RouterLink :to="{name: 'SignUp'}" class="color-2 underline underline-offset-4 mr-3 hover">Sign
      Up
    </RouterLink>
  </div>
  <div class="mt-6">
    <button class="w-full" @click="enter">Sign In</button>
  </div>
</template>

<style scoped>
</style>