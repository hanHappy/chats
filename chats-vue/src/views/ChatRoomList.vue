<script setup>
import {onMounted, ref} from "vue";

onMounted(() => {
  fetchChatRooms()
});

const chatRooms = ref([]);

const fetchChatRooms = async () => {
  try {
    const response = await fetch('http://localhost:8080/api/chat/rooms');
    if (!response.ok) {
      throw new Error('오류 발생');
    }
    chatRooms.value = await response.json();
  } catch (err) {
    console.log(err);
  }
}

</script>
<template>
  <div class="sticky top-0 pt-4 z-10 bg-[#1a1a1a]">
    <div class="flex items-center justify-end">
      <RouterLink to="/create-chatroom" class="button">채팅방 만들기</RouterLink>
    </div>
  </div>
  <div>
    <ul class="mx-auto mt-4">
      <li v-for="room in chatRooms" :key="room.id" class="bg-5 hover">
        <RouterLink :to="{ name: 'ChatRoom', params: { id: room.id } }"
                    class="color-2 w-full h-full p-4 block">
          <span class="color-1 font-bold mr-2">채팅방</span> {{ room.name }}
        </RouterLink>
      </li>
    </ul>
  </div>
</template>
<style scoped>
li {
  border-radius: 8px;
  cursor: pointer;
}

li:nth-child(n+2) {
  margin-top: 8px;
}

</style>
