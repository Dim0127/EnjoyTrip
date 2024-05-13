<script setup>
import { onMounted, ref } from "vue";
//image
import image from "@/assets/img/bg.jpg";

//material components
import MaterialInput from "@/components/materials/MaterialInput.vue";
import MaterialButton from "@/components/materials/MaterialButton.vue";

// material-input
import setMaterialInput from "@/assets/js/material-input";
onMounted(() => {
  setMaterialInput();
});
const showDropdown = ref(false)
</script>
<template>
  <div class="container position-sticky z-index-sticky top-0">
    <div class="row">
      <div class="col-12">
        <DefaultNavbar :sticky="true" :action="{
          route: 'https://www.creative-tim.com/product/vue-material-kit-pro',
          color: 'bg-gradient-success',
          label: 'Buy Now',
        }" />
      </div>
    </div>
  </div>

  <section>
    <div class="page-header min-vh-100">
      <div class="container">
        <div class="row">
          <div
            class="col-6 d-lg-flex d-none h-100 my-auto pe-0 position-absolute top-0 start-0 text-center justify-content-center flex-column">
            <div class="position-relative h-100 m-3 px-7 border-radius-lg d-flex flex-column justify-content-center"
              :style="{
                backgroundImage: `url(${image})`,
                backgroundSize: 'cover',
              }" loading="lazy"></div>
          </div>
          <div class="mt-8 col-xl-5 col-lg-6 col-md-7 d-flex flex-column ms-auto me-auto ms-lg-auto me-lg-5">
            <div class="card d-flex blur justify-content-center shadow-lg my-sm-0 my-sm-6 mt-8 mb-5">
              <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2 bg-transparent">
                <div class="bg-gradient-success shadow-success border-radius-lg p-3">
                  <h4 class="text-white text-success mb-0">회원가입</h4>
                </div>
              </div>
              <div class="card-body">
                <p class="pb-3">
                  EnjoyTrip의 회원이 되어 당신만의 여행 후기를 공유해주세요! 뿌잉뿌잉~
                </p>
                <form id="contact-form" method="post" autocomplete="off">
                  <div class="card-body p-0 my-3">
                    <div class="row">
                      <div class="col-md-6">
                        <MaterialInput class="input-group-static mb-4" type="text" label="아이디" placeholder="Id"
                          id="memberId" />
                        <div class="col-md-6 ps-md-2">
                          <MaterialButton variant="gradient" color="info" size="sm">중복확인</MaterialButton>
                        </div>

                      </div>
                    </div>

                    <div class="row">
                      <div class="col-md-6">
                        <MaterialInput class="input-group-static mb-4" type="password" label="비밀번호"
                          placeholder="Password" id="memberPassword" />
                      </div>
                      <div class="col-md-6 ps-md-2">
                        <MaterialInput class="input-group-static mb-4" type="password" label="비밀번호 확인"
                          placeholder="confirm Password" />
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-md-6">
                        <MaterialInput class="input-group-static mb-4" type="text" label="닉네임" placeholder="Nickname"
                          id="memberName" />
                      </div>

                    </div>
                    <div class="row">
                      <div class="col-md-6">
                        <MaterialInput class="input-group-static mb-4" type="text" label="이메일" placeholder="EmailId"
                          id="memberEmailId" />
                      </div>
                      <div class="dropdown">
                        <MaterialButton variant="gradient" color="success" class="dropdown-toggle"
                          :class="{ show: showDropdown }" @focusout="showDropdown = false" id="dropdownMenuButton"
                          data-bs-toggle="dropdown" :area-expanded="showDropdown"
                          @click.prevent="showDropdown = !showDropdown">
                          @Ssafy.com
                        </MaterialButton>

                        <ul class="dropdown-menu px-2 py-3" :class="{ show: showDropdown }"
                          aria-labelledby="dropdownMenuButton">
                          <li>
                            <a class="dropdown-item border-radius-md">google.com</a>
                          </li>
                          <li>
                            <a class="dropdown-item border-radius-md">daum.net</a>
                          </li>
                          <li>
                            <a class="dropdown-item border-radius-md">naver.com</a>
                          </li>
                        </ul>
                      </div>

                    </div>

                    <div class="row">
                      <div class="mb-3">
                        <label for="formFileSm" class="form-label">프로필 사진</label>
                        <input class="form-control form-control-sm border" id="formFileSm" type="file">
                      </div>

                    </div>


                    <div class="row">
                      <div class="col-md-12 text-center">
                        <MaterialButton variant="gradient" color="success" class="mt-3 mb-0">회원가입</MaterialButton>
                      </div>
                    </div>


                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>
