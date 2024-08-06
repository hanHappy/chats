import {createRouter, createWebHistory} from "vue-router";
import Layout from "@/layouts/Layout.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition;
    }
    return {top: 0};
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
          path: "chatrooms",
          name: "ChatRooms",
          component: () => import("../views/ChatRoomList.vue"),
        },
        {
          path: "chatrooms/:id",
          name: "ChatRoom",
          component: () => import("../views/ChatRoom.vue"),
        },
        {
          path: "create-chatroom",
          name: "CreateChatRoom",
          component: () => import("../views/CreateChatRoom.vue"),
        },
      ],

    },

  ],
});

export default router;
