<script setup>
import { useRouter, RouterLink } from "vue-router";
import { useMemberStore } from "@/stores/member";
import { storeToRefs } from "pinia";

import ArrDark from "@/assets/img/down-arrow-dark.svg";

const memberStore = useMemberStore();
const { isLogin } = storeToRefs(memberStore);
const { userLogout } = memberStore;

const router = useRouter()

const logout = () => {
  userLogout();
  router.replace({ name: "main" })
};

</script>

<template>
  <nav
    class="navbar navbar-expand-lg top-0 d-flex align-items-center my-3 blur border-radius-lg z-index-3 py-2 shadow py-2 start-0 end-0 mx-4 position-absolute mt-4">
    <div class="container">
      <!-- 메인 -->
      <RouterLink class="navbar-brand d-none d-md-block text-dark font-weight-bolder ms-sm-3" :to="{ name: 'main' }">
        EnjoyTrip
      </RouterLink>
      <!-- 메뉴 -->
      <div class="collapse navbar-collapse w-100 pt-3 pb-2 py-lg-0" id="navigation">
        <ul class="navbar-nav navbar-nav-hover ms-auto">
          <RouterLink class="d-flex justify-content-center ms-sm-3" :to="{ name: 'ai' }">
            <i class="material-icons ms-2" aria-hidden="true" style="font-size: 24px;">psychology_alt</i>
            &nbsp;AI 여행지 추천
          </RouterLink>
        </ul>
        <!-- 관광지 -->
        <ul class="navbar-nav navbar-nav-hover">
          <RouterLink class="d-flex justify-content-center ms-sm-3" :to="{ name: 'attraction' }">
            <i class="material-icons ms-2" aria-hidden="true" style="font-size: 24px;">beach_access</i>
            &nbsp;관광지 검색
          </RouterLink>
        </ul>
        <!-- 핫플레이스 -->
        <ul class="navbar-nav navbar-nav-hover">
          <li class="nav-item dropdown dropdown-hover">
            <a role="button" class="nav-link d-flex cursor-pointer d-flex justify-content-center text-dark ms-sm-3 p-0"
              id="dropdownMenuPages" data-bs-toggle="dropdown" aria-expanded="false">
              <i class="material-icons ms-2" aria-hidden="true" style="font-size: 24px;">favorite</i>
              <span style="font-size: 16px;">&nbsp;핫플레이스</span>
              <img :src="ArrDark" alt="down-arrow" class="arrow ms-2 d-lg-block d-none" />
            </a>

            <div class="dropdown-menu dropdown-menu-animation ms-n3 dropdown-md p-3 border-radius-xl mt-0 mt-lg-3"
              aria-labelledby="dropdownMenuPages">
              <div class="row d-none d-lg-block">
                <div class="col-12 px-4">
                  <div class="row">
                    <div class="position-relative">
                      <RouterLink :to="{ name: 'hotplaceList' }" class="dropdown-item border-radius-md">
                        <span>핫플레이스 게시판</span>
                      </RouterLink>
                      <RouterLink :to="{ name: 'hotplaceSearch' }" class="dropdown-item border-radius-md">
                        <span>핫플레이스 등록하기</span>
                      </RouterLink>
                    </div>
                  </div>
                </div>
              </div>
            </div>

          </li>
        </ul>

        <!-- 로그인 상태에 따라 -->
        <!-- 로그인 되어 있지 않다면 -->
        <template v-if="!isLogin">
          <!-- 로그인 -->
          <ul class="navbar-nav navbar-nav-hover">
            <RouterLink class="d-flex justify-content-center ms-sm-3" :to="{ name: 'login' }">
              <i class="material-icons ms-2" aria-hidden="true" style="font-size: 24px;">login</i>
              &nbsp; 로그인
            </RouterLink>
          </ul>
          <!-- 회원가입 -->
          <ul class="navbar-nav navbar-nav-hover">
            <RouterLink class="d-flex justify-content-center ms-sm-3" :to="{ name: 'join' }">
              <i class="material-icons ms-2" aria-hidden="true" style="font-size: 24px;">person</i>
              &nbsp;회원가입
            </RouterLink>
          </ul>
        </template>
        <!-- 로그인 되어 있다면 -->
        <template v-else>
          <ul class="navbar-nav navbar-nav-hover">
            <RouterLink class="d-flex justify-content-center ms-sm-3" :to="{ name: 'myreview' }">
              <i class="material-icons ms-2" aria-hidden="true" style="font-size: 24px;">edit</i>
              &nbsp;마이 리뷰
            </RouterLink>
          </ul>

          <ul class="navbar-nav navbar-nav-hover">
            <RouterLink class="d-flex justify-content-center ms-sm-3" :to="{ name: 'mypage' }">
              <i class="material-icons ms-2" aria-hidden="true" style="font-size: 24px;">notifications</i>
              &nbsp;마이 페이지
            </RouterLink>
          </ul>

          <ul class="navbar-nav navbar-nav-hover">
            <RouterLink class="d-flex justify-content-center ms-sm-3" :to="{ name: 'mypage' }" @click.prevent="logout">
              <i class="material-icons ms-2" aria-hidden="true" style="font-size: 24px;">logout</i>
              &nbsp;로그아웃
            </RouterLink>
          </ul>
        </template>

      </div>
    </div>
  </nav>
</template>