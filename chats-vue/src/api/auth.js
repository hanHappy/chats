import { ApiError, UnknownError } from '../utils/errors';
import { handleResponse, request, URL } from "@/api/common.js";
import { HTTP_METHOD } from "@/constants/http.js";

const BASE_URL = 'http://localhost:8080/api/auth';

export const authApi = {
  async signin(credentials) {
    return await request({
      method: HTTP_METHOD.POST,
      endpoint: URL.SIGN_IN,
      data: credentials
    })
  },

  async signup(credentials) {
    return await request({
      method: HTTP_METHOD.POST,
      endpoint: URL.SIGN_UP,
      data: credentials
    })
  },
};