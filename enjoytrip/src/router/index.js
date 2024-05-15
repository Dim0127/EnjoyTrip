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
      redirect: { name: "login" },
      children: [
        {
          path: "/mypage",
          name: "mypage",
          component: () => import("@/components/members/MemberMypage.vue"),
        },
        {
          path: "/myreview",
          name: "myreview",
          component: () => import("@/components/members/MemberReview.vue"),
        },
      ],
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
      path: "/hotplaces/list",
      name: "hotplaceList",
      component: () => import("@/components/hotplaces/HotplaceList.vue"),
    },
    {
      path: "/hotplaces/:hotplaceId",
      name: "hotplaceDetail",
      component: () => import("@/components/hotplaces/HotplaceDetail.vue"),
    },
    {
      path: "/hotplaces/search",
      name: "hotplaceSearch",
      component: () => import("@/components/hotplaces/HotplaceSearch.vue"),
    },
    {
      path: "/reviews",
      name: "review",
      component: () => import("../views/TheReviewView.vue"),
    },
    {
      path: "/reviews/form",
      name: "reviewForm",
      component: () => import("@/components/reviews/items/ReviewFormItem.vue"),
    },
    {
      path: "/reviews/list",
      name: "reviewList",
      component: () => import("@/components/reviews/ReviewList.vue"),
    },
    {
      path: "/attractions",
      name: "attraction",
      component: () => import("../views/TheAttractionView.vue"),
    },
  ],
});

export default router;
