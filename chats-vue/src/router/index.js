import {createRouter, createWebHistory} from "vue-router";
import Layout from "@/layouts/Layout.vue";

const router = createRouter({
  history : createWebHistory(import.meta.env.BASE_URL),
  routes : [
    {
      path : "/",
      component : Layout,
      children : [
        {
          path : "",
          name : "Home",
          component : () => import("../views/Home.vue"),
        },
        {
          path : "/chatroom/:id",
          name : "ChatRoom",
          component : () => import("../views/ChatRoom.vue"),
        },
        {
          path : "/create-chatroom",
          name : "CreateChatRoom",
          component : () => import("../views/CreateChatRoom.vue"),
        },
      ],

    },

  ],
});

export default router;
