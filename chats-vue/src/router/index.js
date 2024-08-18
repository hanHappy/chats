import { createRouter, createWebHistory } from "vue-router";
import Layout from "@/layouts/Layout.vue";
import { useUserStore } from "@/store/useUserStore.js";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition;
    }
    return { top: 0 };
  },
  routes: [
    {
      path: "/",
      component: Layout,
      children: [
        {
          path: "/signup",
          name: "SignUp",
          component: () => import("../views/SignUp.vue"),
        },
        {
          path: "/signin",
          name: "SignIn",
          component: () => import("../views/SignIn.vue"),
        },
        {
          path: "",
          name: "ChatRooms",
          component: () => import("../views/ChatRoomList.vue"),
          meta: { requiresAuth: true },
        },
        {
          path: "chatrooms/:id",
          name: "ChatRoom",
          component: () => import("../views/ChatRoom.vue"),
          meta: { requiresAuth: true },
        },
        {
          path: "create-chatroom",
          name: "CreateChatRoom",
          component: () => import("../views/CreateChatRoom.vue"),
          meta: { requiresAuth: true },
        },
      ],

    },

  ],
});

router.beforeEach((to, from, next) => {
  const userStore = useUserStore();

  if (to.meta.requiresAuth && !userStore.isAuthenticated) {
    next({ name: 'SignIn' });
  } else {
    next();
  }
});

export default router;
