<script setup>
import { ref } from "vue";
import { login } from "@/api/member.js";

const memberId = ref("");
const memberPassword = ref("");
const tryLogin = () => {
  const loginRequest = {
    memberId: memberId.value,
    memberPassword: memberPassword.value,
  };
  login(
    loginRequest,
    ({ memberInfo }) => {
      console.log(memberInfo.memberId);
    },
    (error) => {
      console.log(error);
    }
  );
};
</script>

<template>
  <div>
    <h2>login</h2>
    <div>
      <label for="memberId">Input Id : </label>
      <input id="memberId" v-model="memberId" /> <br />
      <label for="memberPassword">Input Password : </label>
      <input id="memberPassword" v-model="memberPassword" /> <br />
      <button @click="tryLogin">Log In</button>
    </div>
  </div>
</template>

<style scoped></style>
