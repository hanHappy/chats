const appConfig = {
  api: {
    baseUrl: import.meta.env.APP_API_URL || 'http://localhost:5173',
  },
  app: {
    title: import.meta.env.APP_TITLE || 'My App',
  },
  features: {
    // enableChat: import.meta.env.VITE_ENABLE_CHAT === 'true' || false,
    // maxUsers: parseInt(import.meta.env.VITE_MAX_USERS) || 10,
  },
}

export default appConfig;