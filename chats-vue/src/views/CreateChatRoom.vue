<script setup>
import {ref} from 'vue';
import {useRouter} from "vue-router";

const router = useRouter();

const roomName = ref('');

/**
 * 채팅방 생성
 */
const createChatRoom = async () => {
  if (!roomName.value) {
    return;
  }

  try {
    const response = await fetch('http://localhost:8080/api/chat/rooms', {
      method : 'POST',
      headers : {
        'Content-Type' : 'application/json',
      },
      body : JSON.stringify({name : roomName.value}),
    });

    if (!response.ok) {
      throw new Error('채팅방 생성에 실패했습니다.');
    }

    const result = await response.json();
    await router.push({name : 'ChatRoom', params : {id : result.id}})

  } catch (error) {
    console.error('Error:', error);
  }
};
</script>

<template>
  <form @submit.prevent="createChatRoom">
    <label>
      <span class="color-2">채팅방 이름</span>
      <input type="text" placeholder="채팅방 이름" class="w-full mt-3"
             v-model.trim="roomName" autofocus>
    </label>
    <div class="mt-10 flex justify-end">
      <button>생성</button>
    </div>
  </form>
</template>

<style scoped>

</style>