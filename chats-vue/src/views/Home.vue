<script setup>

import {ref} from "vue";
import {useUser} from "@/composables/useUser.js";
import {useRouter} from "vue-router";

const router = useRouter();

const userId = ref();

const enter = () => {
  if (!userId) {
    return;
  }

  useUser().setUser(userId);
  router.push({name : 'ChatRooms'})
}
</script>

<template>
  <main>
    <div class="container max-h-screen py-40 mx-auto max-w-lg">
      <h1 class="color-2 font-black text-5xl text-center">금지된 사람들</h1>
      <div class="mt-14">
        <label>
          <span class="color-2">이름</span>
          <input type="text" class="w-full mt-2" v-model.trim="userId" @keyup.enter="enter">
        </label>
      </div>
      <div class="mt-6">
        <button class="w-full" @click="enter">입장</button>
      </div>
    </div>
  </main>
</template>

<style scoped>

</style>