<script setup>
import {useRouter} from "vue-router";

const router = useRouter();

const goBack = () => {
  router.go(-1);
}

</script>
<template>
  <div class="container h-full mx-auto w-1/3 min-h-screen">
    <div class="chat-room-bg mx-auto bg-gray-400 h-full min-h-screen">
      <header class="h-16 sticky top-0 flex items-center justify-between bg-[#1a1a1a]">
        <div class="text-white">
          <svg
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
              stroke-width="1.5"
              stroke="currentColor"
              class="size-6 cursor-pointer"
              @click="goBack()"
          >
            <path
                stroke-linecap="round"
                stroke-linejoin="round"
                d="M12 9.75 14.25 12m0 0 2.25 2.25M14.25 12l2.25-2.25M14.25 12 12 14.25m-2.58 4.92-6.374-6.375a1.125 1.125 0 0 1 0-1.59L9.42 4.83c.21-.211.497-.33.795-.33H19.5a2.25 2.25 0 0 1 2.25 2.25v10.5a2.25 2.25 0 0 1-2.25 2.25h-9.284c-.298 0-.585-.119-.795-.33Z"
            />
          </svg>
        </div>
        <h1 class="text-white text-lg">한상민</h1>
        <div class="text-white">
          <svg
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
              stroke-width="1.5"
              stroke="currentColor"
              class="size-6"
          >
            <path
                stroke-linecap="round"
                stroke-linejoin="round"
                d="M3.75 6.75h16.5M3.75 12h16.5m-16.5 5.25h16.5"
            />
          </svg>
        </div>
      </header>
      <main class="h-full p-5">
        <div class="message">안녕하세요</div>
      </main>
    </div>
  </div>
</template>
<style scoped>
.message {
  color: white;
  background-color: #1a1a1a;
  border-radius: 8px;
  width: fit-content;
  padding: 10px;

}
</style>
