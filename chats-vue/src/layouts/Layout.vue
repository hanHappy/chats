<script setup>
import {ref, provide} from 'vue';

const messageContainer = ref(null);

const scrollToBottom = () => {
  if (messageContainer.value) {
    messageContainer.value.scrollTop = messageContainer.value.scrollHeight;
  }
};

provide('scrollToBottom', scrollToBottom);
</script>

<template>
  <div class="flex flex-col min-h-screen">
    <header class="sticky top-0 pt-32 pb-10 z-20">
      <h1 class="mx-auto text-4xl font-black text-center">
        <RouterLink to="/" class="blinking">해피서버</RouterLink>
      </h1>
    </header>
    <main class="flex-1">
      <div class="relative container mx-auto max-w-lg h-[calc(100vh-13rem)] pb-32">
        <div class="overflow-y-auto h-full no-scrollbar" ref="messageContainer">
          <RouterView/>
        </div>
      </div>
    </main>
  </div>
</template>

<style scoped>

.no-scrollbar {
  -ms-overflow-style: none; /* IE and Edge */
  scrollbar-width: none; /* Firefox */
}

.no-scrollbar::-webkit-scrollbar {
  display: none; /* Chrome, Safari and Opera */
}

.blinking {
  animation: blink 1.2s infinite;
}

@keyframes blink {
  0% {
    color: #979797;
  }
  50% {
    color: #3f3f3f;
  }
  100% {
    color: #979797;
  }
}
</style>