import { ApiError, UnknownError } from "@/utils/errors.js";
import { authApi } from "@/api/auth.js";
import { userApi } from "@/api/user.js";

export const api = {
  auth: authApi,
  user: userApi
}

const BASE_URL = 'http://localhost:8080/api';

export const URL = {
  SIGN_IN: '/auth/signin',
  SIGN_UP: '/auth/signup',
  USER: '/users'
}

const getHeaders = () => {
  const headers = {
    'Content-Type': 'application/json',
  };
  const token = sessionStorage.getItem('JWT');
  if (token) {
    headers['Authorization'] = `Bearer ${ token }`;
  }
  return headers;
};

export async function request(options) {
  const { method = 'GET', endpoint, data, headers = {} } = options;

  const config = {
    method,
    headers: { ...getHeaders(), ...headers },
  };

  if (data) {
    config.body = JSON.stringify(data);
  }

  const response = await fetch(`${ BASE_URL }${ endpoint }`, config);

  return handleResponse(response);
}

export async function handleResponse(response) {
  if (!response.ok) {
    const { code, status, message, errors = [] } = await response.json();
    if (code && status && message) {
      throw new ApiError(code, status, message, errors);
    } else {
      throw new UnknownError(response.status, '서버 오류가 발생하였습니다');
    }
  }
  return response.json();
}

export async function objectToSearchParams(data) {
  const params = new URLSearchParams();
  if (data) {
    for (const [key, value] of Object.entries(data)) {
      params.append(key, value);
    }
  }
  return params.toString();
}