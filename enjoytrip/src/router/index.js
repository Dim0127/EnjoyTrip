import { createRouter, createWebHistory } from "vue-router";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "main",
      component: () => import("../views/TheMainView.vue"),
    },
    {
      path: "/members",
      name: "member",
      component: () => import("../views/TheMemberView.vue"),
    },
    {
      path: "/members/mypage",
      name: "mypage",
      component: () => import("@/components/members/MemberMypage.vue"),
    },
    {
      path: "/members/myreview",
      name: "myreview",
      component: () => import("@/components/members/MemberReview.vue"),
    },
    {
      path: "/join",
      name: "join",
      component: () => import("@/components/members/MemberJoin.vue"),
    },
    {
      path: "/login",
      name: "login",
      component: () => import("@/components/members/MemberLogin.vue"),
    },
    {
      path: "/hotplaces",
      name: "hotplace",
      component: () => import("../views/TheHotplaceView.vue"),
    },
    {
      path: "/reviews",
      name: "review",
      component: () => import("../views/TheReviewView.vue"),
    },
  ],
});

export default router;
