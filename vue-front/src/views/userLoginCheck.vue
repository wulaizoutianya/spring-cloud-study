<template>
  <div>
    <div>账号：<input v-model="userLoginInfoObj.userAccount" :maxlength="75" placeholder="用户账号"/></div>
    <div>密码：<input v-model="userLoginInfoObj.userPassword" :maxlength="75" placeholder="用户密码"/></div>
    <div>
      <Button type="primary" @click="userLoginValidate()">登录</Button>
    </div>
  </div>
</template>

<script>
  import urlRouteApi from '@/urlRouteApi'

  export default {


    data() {
      return {
        userLoginInfoObj: {
          userAccount: '',
          userPassword: ''
        }
      }
    },
    methods: {
      async userLoginValidate() {
        alert(this.userLoginInfoObj.userAccount + " ***  " + this.userLoginInfoObj.userPassword + " ** " + urlRouteApi.userLoginCheck);

        this.vueAxios.post(urlRouteApi.userLoginCheck, this.userLoginInfoObj).
        then(function (response) {
          console.log(response);
        }).catch(function (error) {
          console.log(error);
        });
      },
    }
  }
</script>
<style>

</style>
