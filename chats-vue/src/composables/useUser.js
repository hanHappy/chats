import {ref, readonly} from 'vue';

const username = ref(null);

export function useUser() {
  const setUser = (id) => {
    username.value = id;
  };

  const clearUser = () => {
    username.value = null;
  };

  const isLoggedIn = () => {
    return !!username.value;
  };

  return {
    username : readonly(username),
    setUser,
    clearUser,
    isLoggedIn,
  };
}