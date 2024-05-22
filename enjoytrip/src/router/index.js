import { createRouter, createWebHistory } from "vue-router";
import { storeToRefs } from "pinia";

import { useMemberStore } from "@/stores/member";


const onlyAuthUser = async (to, from, next) => {
  const memberStore = useMemberStore();
  const { userInfo, isValidToken } = storeToRefs(memberStore);
  const { getUserInfo } = memberStore;
  // console.log(userInfo)

  let token = sessionStorage.getItem("accessToken");

  if (userInfo.value != null && token) {
    await getUserInfo(token);
  }
  if (!isValidToken.value || userInfo.value === null) {
    next({ name: "login" });
  } else {
    next();
  }
};

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "main",
      component: () => import("../views/TheMainView.vue"),
    },
    {
      path: "/ai",
      name: "ai",
      component: () => import("../views/OpenAIView.vue"),
    },
    {
      path: "/members",
      name: "member",
      component: () => import("../views/TheMemberView.vue"),
      redirect: { name: "login" },
      children: [
        {
          path: "mypage",
          name: "mypage",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/members/MemberMypage.vue"),
        },
        {
          path: "myreview",
          name: "myreview",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/members/MemberMyReview.vue"),
        },
        {
          path: "join",
          name: "join",
          component: () => import("@/components/members/MemberJoin.vue"),
        },
        {
          path: "login",
          name: "login",
          component: () => import("@/components/members/MemberLogin.vue"),
        },
        {
          path: "forgot-password",
          name: "forgot-password",
          component: () => import("@/components/members/MemberForgotPassword.vue"),
        },
      ],
    },
    {
      path: "/hotplaces",
      name: "hotplace",
      component: () => import("../views/TheHotplaceView.vue"),
      children: [
        {
          path: "list",
          name: "hotplaceList",
          component: () => import("@/components/hotplaces/HotplaceList.vue"),
        },
        {
          path: "search",
          name: "hotplaceSearch",
          component: () => import("@/components/hotplaces/HotplaceSearch.vue"),
        },
        {
          path: ":hotplaceId",
          name: "hotplaceDetail",
          component: () => import("@/components/hotplaces/HotplaceDetail.vue"),
        },
      ],
    },

    {
      path: "/reviews/form",
      name: "reviewForm",
      beforeEnter: onlyAuthUser,
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
