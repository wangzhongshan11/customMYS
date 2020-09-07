<template>
  <div
    style="height:100%;position:absolute;top:0px;left:0px;width:100%;overflow-y:auto;background-color:#fff;"
  >
    <div style="text-align:left;padding-left:3%;padding-right:3%;padding-top:20px;">
      <i
        class="el-icon-arrow-left"
        style="color:#000000;font-size:25px;cursor:pointer;"
        @click="goback()"
      ></i>
    </div>
    <div style="text-align:left;height:200px;line-height:200px;padding-left:3%;padding-right:3%;">
      <span style="color:#000000;font-size:30px;font-weight:bold;">注册</span>
    </div>

    <el-form
      ref="registerUser"
      :rules="rules"
      :model="registerUser"
      style="padding-left:3%;padding-right:3%;"
    >
      <el-row style="margin-top:30px;">
        <el-col :span="2" :offset="0">
          <div class="flex-div-center-wrap" style="height:60px;">
            <span style="font-size:20px;">+86</span>
          </div>
        </el-col>
        <el-col :span="1" :offset="0">
          <div class="flex-div-center-wrap" style="height:60px;">
            <span style="font-size:20px;">|</span>
          </div>
        </el-col>
        <el-col :span="20" :offset="1">
          <div
            class="flex-div-center-wrap register-input-wrap"
            style="height:60px;margin-left:-3%;"
          >
            <el-form-item prop="userPhone" style="width:100%;">
              <el-input v-model="registerUser.userPhone" placeholder="请输入手机号" class="font-12px"></el-input>
            </el-form-item>
          </div>
        </el-col>
      </el-row>
      <el-form-item style="margin-top:0px;" class="register-input-wrap" prop="verCode">
        <el-input v-model="registerUser.verCode" placeholder="验证码" class="font-12px"></el-input>
      </el-form-item>
      <el-form-item style="margin-top:0px;" class="register-input-wrap" prop="username">
        <el-input v-model="registerUser.username" placeholder="请输入用户名" class="font-12px"></el-input>
      </el-form-item>
      <el-form-item style="margin-top:0px;" class="register-input-wrap" prop="password">
        <el-input v-model="registerUser.password" placeholder="设置密码" class="font-12px"></el-input>
      </el-form-item>
      <el-form-item style="margin-top:0px;" class="register-input-wrap" prop="confirmPassword">
        <el-input v-model="registerUser.confirmPassword" placeholder="确认密码" class="font-12px"></el-input>
      </el-form-item>
      <el-form-item style="margin-top:60px;">
        <el-button
          type="primary"
          @click="submitForm('registerUser')"
          size="medium"
          style="width:100%;"
        >注册</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import { postRequest } from "../utils/api";
export default {
  data() {
    const checkPassword = (rule, value, callback) => {
      if (value == this.registerUser.confirmPassword) {
        return callback();
      }
      callback(new Error("密码不一致"));
    };
    const checkConfirmPassword = (rule, value, callback) => {
      if (value == this.registerUser.password) {
        return callback();
      }
      callback(new Error("密码不一致"));
    };
    return {
      rules: {
        userPhone: [
          { required: true, message: "请输入手机号", trigger: "blur" },
        ],
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { validator: checkPassword, trigger: "blur" },
        ],
        confirmPassword: [
          { required: true, message: "请重新输入密码", trigger: "blur" },
          { validator: checkConfirmPassword, trigger: "blur" },
        ],
      },
      registerUser: {
        username: "",
        password: "",
        userPhone: "",
        confirmPassword: "",
        verCode: "",
      },
    };
  },
  methods: {
    goback() {
      this.$router.push({ name: "登录" });
    },
    submitForm(formName) {
      var _this = this;
      postRequest("/user/register", {
        registerUser: JSON.stringify({
          username: _this.registerUser.username,
          password: _this.registerUser.password,
          userPhone: _this.registerUser.userPhone,
        }),
      }).then(
        function (msg) {
          if (msg.status == 200) {              
            _this.$message({
              type: msg.data.status,
              message: msg.data.msg,
            });
          } else {
            this.$message({ type: "error", message: "注册成功" });
          }
        },
        function (msg) {
          this.$message({ type: "error", message: "注册失败" });
        }
      );
    },
  },
};
</script>
<style>
.register-input-wrap .el-input__inner {
  background-color: transparent;
  border-radius: 0px;
  border-left: 0;
  border-top: 0;
  border-right: 0;
  font-size: 20px;
}
.flex-div-start-wrap {
  display: flex;
  align-items: center;
  justify-content: flex-start;
}
.flex-div-end-wrap {
  display: flex;
  align-items: center;
  justify-content: flex-end;
}
.flex-div-around-wrap {
  display: flex;
  align-items: center;
  justify-content: space-around;
}
.flex-div-center-wrap {
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>