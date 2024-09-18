import { request, URL } from "@/api/common.js";

export const chatApi = {
  async getAllChatRooms() {
    return await request({
      endpoint: URL.CHAT_ROOM,
    })
  },
  async getChatMessages(roomId) {
    return await request({
      endpoint: URL.CHAT_MESSAGE(roomId),
    })
  },
};