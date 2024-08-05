<script setup>

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
  <div class="mt-16">
    <button class="w-full" @click="enter">Sign Up</button>
  </div>
</template>

<style scoped>

</style>