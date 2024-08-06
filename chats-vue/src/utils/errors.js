export class ApiError extends Error {
  constructor(code, status, message, errors = []) {
    super(message);
    this.name = 'ApiError';
    this.code = code;
    this.status = status;
    this.errors = errors;
  }
}

export class UnknownError extends Error {
  constructor(status, message) {
    super(message);
    this.name = 'UnknownError';
    this.status = status;
  }
}