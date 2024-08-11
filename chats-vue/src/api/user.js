import { handleResponse } from "@/api/common.js";

const BASE_URL = 'http://localhost:8080/api/users';

export const user = {
  async getInfo(user) {
    const params = new URLSearchParams();
    for (const [key, value] of Object.entries(user)) {
      params.append(key, value);
    }

    const url = `${ BASE_URL }?${ params.toString() }`;

    const response = await fetch(url, {
      method: 'GET',
    });
    return handleResponse(response);
  },
}