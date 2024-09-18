<script setup>
import { useRoute } from "vue-router";
import { inject, nextTick, onMounted, ref } from "vue";
import { api } from "@/api/common.js";
import { useUserStore } from "@/store/useUserStore.js";
import stompConfig from "@/config/stomp.js";
import appConfig from "../../app.config.js";

/**
 * nextTick()
 * DOM 업데이트 발생을 감지
 * https://ko.vuejs.org/api/general.html#nexttick
 */

onMounted(() => {

  // 채팅 내역 로드
  fetchChatHistory();

});

const route = useRoute();
const roomId = route.params.id;
const userStore = useUserStore();

const myId = userStore.username;

const messages = ref([]);

const myMessage = ref('');

/**
 * 채팅 내역 로드
 */
async function fetchChatHistory() {
  try {
    const response = await api.chat.getChatMessages(roomId);
    messages.value = response.content;
  } catch (err) {
    console.log(err);
  }
}

/**
 * 메시지 발신
 */
const sendMessage = () => {
  const outgoingMessage = {
    roomId: roomId,
    content: myMessage.value,
    senderId: myId,
  }
  stomp.send(`${stompConfig.sendPrefix}/message`, {}, JSON.stringify(outgoingMessage))
  myMessage.value = '';
}

/**
 * FIXME css 사용하여 항상 최하단에 위치하도록 수정
 * DOM요소가 변할 때 화면 스크롤
 * @type {function(): void}
 */
const scrollToBottom = inject('scrollToBottom');
nextTick(() => {
  scrollToBottom();
});

/**
 * STOMP Connection
 */
const sockJs = new SockJS(stompConfig.endpoint);
const stomp = Stomp.over(sockJs);
const header = {
  'Authorization': useUserStore().getToken()
}

stomp.connect(header, function () {

  // 수신
  stomp.subscribe(`${stompConfig.listenPrefix}/${ roomId }`, function (response) {
    const incomingMessage = JSON.parse(response.body);

    messages.value.push({
      senderId: incomingMessage.senderId,
      content: incomingMessage.content,
    })

    nextTick(() => {
      scrollToBottom();
    });
  });

  // 발신
  const outgoingMessage = {
    roomId: roomId,
    senderId: myId || '익명의 사용자',
  }

  stomp.send(`${stompConfig.sendPrefix}/enter`, {}, JSON.stringify(outgoingMessage))
});


</script>

<template>
  <div class="flex flex-col h-full" ref="messageContainer">
    <div class="flex-1 pb-16">
      <div v-for="msg in messages" class="message flex flex-col">
        <div :class="{'self-end': msg.senderId === myId}">
          <div class="color-2">{{ msg.senderId || '익명의 사용자' }}</div>
          <div
              class="bg-5 w-fit h-fit p-3 rounded-xl max-w-64 color-2 mt-2">
            {{ msg.content }}
          </div>
        </div>
      </div>
    </div>
    <div class="absolute bottom-32 w-full flex gap-2 items-center bg-1">
      <input id="input-message" type="text" placeholder="메시지를 입력해주세요" class="flex-1"
             v-model="myMessage" @keyup.enter="sendMessage" autocomplete="off">
      <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor"
           class="size-8 cursor-pointer color-2 hover" @click="sendMessage">
        <path
            d="M3.478 2.404a.75.75 0 0 0-.926.941l2.432 7.905H13.5a.75.75 0 0 1 0 1.5H4.984l-2.432 7.905a.75.75 0 0 0 .926.94 60.519 60.519 0 0 0 18.445-8.986.75.75 0 0 0 0-1.218A60.517 60.517 0 0 0 3.478 2.404Z"/>
      </svg>
    </div>
  </div>
</template>

<style scoped>
.message:nth-child(n+2) {
  margin-top: 16px;
}
</style>