import { ApiError, UnknownError } from "@/utils/errors.js";

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