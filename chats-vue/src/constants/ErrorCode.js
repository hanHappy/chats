import {HttpStatus} from "@/constants/HttpStatus.js";

export const ErrorCode = {
  INTERNAL_SERVER_ERROR : {
    code : 'ERR_001',
    status : HttpStatus.INTERNAL_SERVER_ERROR,
    message : '서버 오류가 발생하였습니다'
  },

  // 인증
  UNAUTHORIZED : {
    code : 'AUTH_001',
    status : HttpStatus.UNAUTHORIZED,
    message : '인증에 실패하였습니다'
  },
  FORBIDDEN : {
    code : 'AUTH_002',
    status : HttpStatus.FORBIDDEN,
    message : '권한이 없습니다'
  },
  INVALID_CREDENTIALS : {
    code : 'AUTH_003',
    status : HttpStatus.UNAUTHORIZED,
    message : '아이디 또는 비밀번호가 일치하지 않습니다'
  },

  // User
  USER_NOT_FOUND : {
    code : 'USER_001',
    status : HttpStatus.NOT_FOUND,
    message : '사용자를 찾을 수 없습니다'
  },
  DUPLICATE_USERNAME : {
    code : 'USER_002',
    status : HttpStatus.BAD_REQUEST,
    message : '사용 중인 아이디입니다'
  }
};