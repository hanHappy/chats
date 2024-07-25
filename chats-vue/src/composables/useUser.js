import {ref, readonly} from 'vue';

const userId = ref(null);

export function useUser() {
  const setUser = (id) => {
    userId.value = id;
  };

  const clearUser = () => {
    userId.value = null;
  };

  const isLoggedIn = () => {
    return !!userId.value;
  };

  return {
    userId : readonly(userId),
    setUser,
    clearUser,
    isLoggedIn,
  };
}