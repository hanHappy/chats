<script setup>
import { provide, ref } from 'vue';

const messageContainer = ref(null);

const scrollToBottom = () => {
  if (messageContainer.value) {
    messageContainer.value.scrollTop = messageContainer.value.scrollHeight;
  }
};

/**
 * Prop 드릴링 (like props)
 * https://ko.vuejs.org/guide/components/provide-inject
 */
provide('scrollToBottom', scrollToBottom);
</script>

<template>
  <div class="flex flex-col min-h-screen">
    <header class="sticky top-0 pt-32 pb-10 z-20 flex justify-center">
      <h1>
        <RouterLink :to="{name: 'ChatRooms'}" class="blinking">The Forbidden</RouterLink>
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

@import url('https://fonts.googleapis.com/css2?family=Uncial+Antiqua&display=swap');

.no-scrollbar {
  -ms-overflow-style: none; /* IE and Edge */
  scrollbar-width: none; /* Firefox */
}

.no-scrollbar::-webkit-scrollbar {
  display: none; /* Chrome, Safari and Opera */
}

h1 {
  font-family: 'Uncial Antiqua', cursive;
  font-size: 2.7rem;
  text-align: center;
  white-space: nowrap;
  font-weight: 900;
  color: #979797;
  text-shadow: 3px 3px 2px #3d3d3d;
  letter-spacing: 0.1em;
  text-transform: uppercase;
  background: linear-gradient(to bottom, #979797, #979797);
  -webkit-background-clip: text;
  background-clip: text;
  -webkit-text-fill-color: transparent;
  transition: all 1s ease;
  cursor: pointer;
  animation: blink 1.5s infinite;
  /* 텍스트 선택 방지 */
  -webkit-user-select: none; /* Chrome, Safari, Opera */
  -moz-user-select: none; /* Firefox */
  -ms-user-select: none; /* Internet Explorer/Edge */
  user-select: none; /* Non-prefixed version, currently supported by Chrome, Edge, Opera and Firefox */
}

h1:hover {
  text-shadow: 3px 3px 2px #40D393;
  -webkit-background-clip: text;
  background-clip: text;
  -webkit-text-fill-color: transparent;
  animation: none;
}

@keyframes blink {
  0%, 100% {
    text-shadow: 3px 3px 2px #3d3d3d;
  }
  50% {
    text-shadow: 3px 3px 2px #979797;
  }
}
</style>