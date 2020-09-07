<template>
  <div class="change-password-wrap">
    <div class="change-password-header">
      <el-row >
        <el-col :span="1" :offset="1">
          <div>
            <i class="el-icon-arrow-left" @click="goback" style="cursor:pointer;"></i>
          </div>
        </el-col>
        <el-col :span="5" :offset="8">
          <div>
            <span>修改密码</span>
          </div>
        </el-col>
      </el-row>
    </div>
    <div
      class="change-password-main"
    >
      <div class="flex-div-start-wrap">
        <span class="font-12px">|请设置账号178****8336的新密码</span>
      </div>
      <el-form ref="newPassword" :model="newPassword">
        <el-form-item style="margin-top:20px;">
          <el-input v-model="newPassword.first" placeholder="新密码"></el-input>
        </el-form-item>
        <el-form-item style="margin-top:-20px;">
          <el-input v-model="newPassword.confirm" placeholder="确认密码"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button
            type="primary"
            @click="submitForm('newPassword')"
            size="medium"
            style="width:100%;"
          >确定</el-button>
        </el-form-item>
        <el-form-item style="margin-top:-15px;">
          <el-button type="primary" @click="goback" size="medium" style="width:100%;">返回</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>
<script>
import { putRequest } from "../../../utils/api";
export default {
  mounted() {
    this.currentUser = this.$store.state.currentUser;
  },
  data() {
    return {
      currentUser: "",
      newPassword: {
        first: null,
        confirm: null,
      },
    };
  },
  methods: {
    submitForm(formName) {
      var _this = this;
      putRequest("/resetPassword", {
        id: _this.currentUser.id,
        password: _this.newPassword.confirm,
      }).then(
        function (msg) {
          if (msg.status !== 200) {
            _this.$message({ type: "error", message: "修改失败!" });
          } else {
            _this.$message({ type: msg.data.status, message: msg.data.msg });
            _this.currentUser.password = _this.newPassword.confirm;
            _this.$store.dispatch("setCurrentUser", _this.currentUser);
            // console.log({type: msg.data.status, message:msg.data.msg});
          }
        },
        {
          function(msg) {
            _this.$message({ type: "error", message: "修改失败!" });
          },
        }
      );
    },
    goback() {
      this.$router.push({
        name: "UserPassCard",
      });
    },
  },
};
</script>
<style>
.font-12px {
  font-size: 12px;
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
.change-password-main .el-input__inner {
  border-top-width: 0px;
  border-left-width: 0px;
  border-right-width: 0px;
  border-bottom-width: 1px;
  border-radius: 0px;
}
.change-password-header {
  background-color: #fff;
  height: 80px;
  line-height: 80px;
  font-size: 20px;
}
.change-password-main {
  margin-top: 50px;
  background-color: #fff;
  padding-top: 0px;
  padding-left: 3%;
  padding-right: 2%;
}
.change-password-wrap {
  background-color: #fff;
  height: 100%;
  position: absolute;
  top: 0px;
  left: 0px;
  width: 100%;
  overflow-y: auto;
}
</style>