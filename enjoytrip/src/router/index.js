import { createRouter, createWebHistory } from "vue-router";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "main",
      component: () => import("../views/MainView.vue"),
    },
    {
      path: "/members",
      name: "member",
      component: () => import("../views/MemberView.vue"),
    },
    {
      path: "/hotplaces",
      name: "hotplace",
      component: () => import("../views/HotplaceView.vue"),
    },
    {
      path: "/reviews",
      name: "review",
      component: () => import("../views/ReviewView.vue"),
    },
  ],
});

export default router;
