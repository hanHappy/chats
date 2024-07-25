<script setup>
import {onMounted, ref} from "vue";

const chatRooms = ref([]);

const fetchChatRooms = async () => {
  try {
    const response = await fetch('http://localhost:8080/api/chatrooms');
    if (!response.ok) {
      throw new Error('오류 발생');
    }
    chatRooms.value = await response.json();
  } catch (err) {
    console.log(err);
  }
}

onMounted(() => {
  fetchChatRooms()
});
</script>
<template>
  <main>
    <div class="container h-screen mx-auto py-32  max-w-lg max-h-screen">
      <div class="overflow-y-auto h-full no-scrollbar">
        <div class="sticky top-0 bg-[#1a1a1a] pb-4">
          <h1 class="mx-auto text-4xl text-[#979797] font-black text-center">
            해피 서버
          </h1>
          <div class="mt-10 flex items-center justify-end">
            <RouterLink
                to="/chats"
                class="bg-[#40D393] text-[#223547] w-fit py-2 px-3 rounded-lg font-bold hover:bg-[#32be81]"
            >
              채팅방 만들기
            </RouterLink>
          </div>
        </div>
        <div>
          <ul class="mx-auto">
            <li v-for="room in chatRooms">{{ room.name }}</li>
            <li>채팅방</li>
            <li>채팅방</li>
            <li>채팅방</li>
            <li>채팅방</li>
            <li>채팅방</li>
            <li>채팅방</li>
            <li>채팅방</li>
            <li>채팅방</li>
            <li>채팅방</li>
            <li>채팅방</li>
            <li>채팅방</li>
            <li>채팅방</li>
          </ul>
        </div>
      </div>
    </div>
  </main>
</template>
<style scoped>
li {
  color: #979797;
  background-color: #ffffff20;
  border-radius: 8px;
  padding: 16px;
  cursor: pointer;
}

li:nth-child(n+2) {
  margin-top: 8px;
}

li:hover {
  background-color: #ffffff18;
}

.no-scrollbar {
  -ms-overflow-style: none; /* IE and Edge */
  scrollbar-width: none; /* Firefox */
}

.no-scrollbar::-webkit-scrollbar {
  display: none; /* Chrome, Safari and Opera */
}
</style>
