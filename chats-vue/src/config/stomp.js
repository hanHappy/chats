const stompConfig = {
  endpoint: import.meta.env.APP_API_URL + import.meta.env.STOMP_ENDPOINT || 'none',
  listenPrefix: import.meta.env.STOMP_SUBSCRIPTION_DESTINATION || 'none',
  sendPrefix: import.meta.env.STOMP_APPLICATION_DESTINATION_PREFIX || 'none'
}

export default stompConfig;