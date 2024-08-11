// src/api/auth.js

import { ApiError, UnknownError } from '../utils/errors';
import { handleResponse } from "@/api/common.js";

const BASE_URL = 'http://localhost:8080/api/auth';

export const auth = {
  async signin(credentials) {
    const response = await fetch(`${ BASE_URL }/signin`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(credentials),
    });
    return handleResponse(response);
  },

  async signup(credentials) {
    const response = await fetch(`${ BASE_URL }/signup`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(credentials),
    });
    return handleResponse(response);
  },

};