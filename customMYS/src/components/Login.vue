<template>
  <div
    style="height:100%;position:absolute;top:0px;left:0px;width:100%;overflow-y:auto;background-color:#fff;"
  >
    <div
      style="height:300px;padding-left:3%;padding-right:3%;padding-top:20px;"
      class="login-background-wrap"
    >
      <i class="el-icon-close" style="color:#000000;font-size:30px;float:left;"></i>
      <span
        style="color:#000000;font-size:25px;float:right;cursor:pointer;"
        @click="goRegister()"
      >注册</span>
    </div>
    <div style="clear:both;"></div>
    <el-form
      :rules="rules"
      class="login-container"
      label-position="left"
      label-width="0px"
      v-loading="loading"
      style="padding-left:3%;padding-right:3%;"
      :model="loginForm"
      ref="loginForm"
    >
      <h3 class="login_title">账号密码登录</h3>
      <el-form-item prop="username" class="login-input-wrap">
        <el-input
          type="text"
          v-model="loginForm.username"
          auto-complete="off"
          placeholder="账号"
          style="height: 40px;"
        ></el-input>
      </el-form-item>
      <el-form-item prop="password" class="login-input-wrap">
        <el-input type="password" v-model="loginForm.password" auto-complete="off" placeholder="密码"></el-input>
      </el-form-item>
      <!-- <el-checkbox class="login_remember" v-model="checked" label-position="left">记住密码</el-checkbox> -->
      <el-form-item style="width: 100%">
        <el-button type="primary" @click.native.prevent="submitClick" style="width: 100%">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import { postRequest, getRequest } from "../utils/api";
import { putRequest } from "../utils/api";
import QS from "qs";
export default {
  mounted() {},
  data() {
    return {
      rules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
        ],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }],
      },
      checked: true,
      loginForm: {
        username: "wzs",
        password: "123",
      },
      loading: false,
    };
  },
  methods: {
    submitClick: function () {
      var _this = this;
      this.loading = true;
      postRequest("/login", {
        username: this.loginForm.username,
        password: this.loginForm.password,
      }).then(
        (resp) => {
          _this.loading = false;
          if (resp.status == 200) {
            var json = resp.data;
            // this.loadBlogs("大别野");
            // this.loadBlogs("崩坏3");
            // this.loadBlogs("崩坏学园2");
            // this.loadBlogs("原神");
            // this.loadBlogs("未定事件簿");
            console.log(json);
            if (json.status == "success") {
              getRequest("/currentUser", {
                username: _this.loginForm.username,
              }).then(
                (resp) => {
                  if (resp.status == 200) {
                    // _this.$store.state.currentUser = resp.data;
                    _this.$store.dispatch("setCurrentUser", resp.data);
                    console.log(_this.loginForm.username);
                    console.log(resp.data);
                    console.log(_this.$store.state.currentUser);
                  }
                },
                (resp) => {}
              );
              _this.$router.replace({ path: "/homeWzs" });
            } else {
              _this.$alert("登录失败!", "失败!");
            }
          } else {
            _this.$alert("登录失败!", "失败!");
          }
        },
        (resp) => {
          _this.loading = false;
          _this.$alert("找不到服务器⊙﹏⊙∥!", "失败!");
          _this.$router.replace({ path: "/homeWzs" });
        }
      );
    },
    loadBlogs(forumname) {
      var _this = this;
      var url = "";
      url = "/admin/article/all" + "?forumname=" + forumname;
      console.log(url);

      getRequest(url).then(
        (resp) => {
          if (resp.status == 200) {
            console.log("=200");
            console.log(resp.data.articles);
            // _this.articles = resp.data.articles;
            switch (forumname) {
              case "大别野":
                this.$store.dispatch("setDabieyeArticles", resp.data.articles);
                break;
              case "崩坏3":
                this.$store.dispatch("setBBBArticles", resp.data.articles);
                break;
              case "崩坏学园2":
                this.$store.dispatch("setBBArticles", resp.data.articles);
                break;
              case "原神":
                this.$store.dispatch("setYuanShenArticles", resp.data.articles);
                break;
              case "未定事件簿":
                this.$store.dispatch("setWeiDingArticles", resp.data.articles);
                break;
            }
          } else {
            console.log("!200");
            _this.$message({ type: "error", message: "数据加载失败!" });
          }
        },
        (resp) => {
          if (resp.response.status == 403) {
            console.log("=403");
            _this.$message({ type: "error", message: resp.response.data });
          } else {
            console.log("!=403");
            _this.$message({ type: "error", message: "数据加载失败!" });
          }
        }
      );
    },
    goRegister() {
      this.$router.push({ name: "Register" });
    },
  },
};
</script>
<style>
.login-container {
  /* border-radius: 15px;
  background-clip: padding-box;
  margin: 180px auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6; */
}

.login_title {
  margin: 40px auto 20px auto;
  text-align: left;
  color: #000000;
}

.login_remember {
  margin: 0px 0px 35px 0px;
  text-align: left;
}
.login-input-wrap .el-input__inner {
  background-color: transparent;
  border-radius: 0px;
  border-left: 0;
  border-top: 0;
  border-right: 0;
  font-size: 20px;
}
.login-background-wrap {
  background: url(../img/布洛妮娅2.jpg) no-repeat 0px 0px;
  background-size: cover;
}
</style>
