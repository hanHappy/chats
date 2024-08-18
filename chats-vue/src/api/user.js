import { handleResponse, request } from "@/api/common.js";
import { URL } from '@/api/common.js'

export const userApi = {
  async getUser(username) {
    return await request({
      endpoint: `${ URL.USER }/${ username }`,
    })
  },
};