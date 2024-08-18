import { request, URL } from "@/api/common.js";

export const chatroomApi = {
  async getAllChatRooms() {
    return await request({
      endpoint: URL.CHAT_ROOM,
    })
  },
};