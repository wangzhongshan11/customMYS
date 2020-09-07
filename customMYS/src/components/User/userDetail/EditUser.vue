<template>
  <div style="background-color:white;margin-top:-30px;">
    <transition>
      <div class="avatar-change-div" v-if="avatarChangeIndex">
        <el-header style="background-color:white;">
          <el-row style="margin-top:-20px;width:100%;">
            <el-col :span="1">
              <div>
                <i class="el-icon-arrow-left" @click="closeAvatarChange" style="cursor:pointer;"></i>
              </div>
            </el-col>
            <el-col :span="6" :offset="8">
              <div>
                <span>修改头像</span>
              </div>
            </el-col>
            <el-col :span="4" :offset="5">
              <div>
                <span style="color:#3366ff;cursor:pointer;" @click="saveAvatarChange">保存</span>
              </div>
            </el-col>
          </el-row>
        </el-header>
        <el-main>
          <hr />
          <div>
            <!-- <img class="user-avatar" :src="require('../../../img/希儿3.jpg')" alt="wzs" style="cursor:pointer;"/> -->
            <img class="user-avatar" :src="currentUser.userface" alt="wzs" style="cursor:pointer;" />
          </div>
          <div>
            <div class="avatar-lists">
              <img
                class="user-avatar"
                v-for="(item,index) in avatarNames"
                :key="index"
                :src="require('../../../img/avatar/'+item)"
                :alt="require('../../../img/avatar/'+item)"
                style="padding:3%;cursor:pointer;"
                @click="changeAvatar(item)"
              />
            </div>
          </div>
        </el-main>
      </div>
    </transition>

    <el-header style="background-color:white;">
      <el-row style="margin-top:-20px;width:100%;">
        <el-col :span="1">
          <div>
            <i class="el-icon-arrow-left" style="cursor:pointer;" @click="goback"></i>
          </div>
        </el-col>
        <el-col :span="6" :offset="8">
          <div>
            <span>修改信息</span>
          </div>
        </el-col>
        <el-col :span="4" :offset="5">
          <div>
            <span style="color:#3366ff;cursor:pointer;" @click="saveUserChange">保存</span>
          </div>
        </el-col>
      </el-row>
    </el-header>
    <el-main>
      <hr />

      <div>
        <img
          class="user-avatar"
          :src="currentUser.userface"
          alt="wzs"
          style="cursor:pointer;"
          @click="avatarChange"
        />
      </div>
      <span @click="avatarChange" style="cursor:pointer;">点击修改头像</span>

      <el-form ref="editUser" :model="editUser">
        <div class="flex-div-start-wrap nick-div">
          <span class="flex-div-center-wrap" style="width:13%">昵称</span>
          <el-input
            v-model="editUser.nickname"
            :placeholder="currentUser.nickname"
            class="font-25px"
          ></el-input>
        </div>
        <div class="flex-div-start-wrap sex-div">
          <span class="flex-div-center-wrap" style="width:11.4%">性别</span>
          <el-radio v-model="editUser.userSex" label="0">男</el-radio>
          <el-radio v-model="editUser.userSex" label="1">女</el-radio>
          <el-radio v-model="editUser.userSex" label="2">保密</el-radio>
        </div>
        <div style="margin-top:40px;">
          <div class="flex-div-start-wrap" style="margin-bottom:15px;">
            <span>个性签名:</span>
            <span style="font-size:10px;color:#dddddd;">&nbsp;&nbsp;(最多可输入48个文字)</span>
          </div>
          <textarea
            v-model="editUser.userSignature"
            :placeholder="currentUser.userSignature"
            rows="10"
            class="textarea-inherit"
          ></textarea>
        </div>
      </el-form>
    </el-main>
  </div>
</template>
<script>
import { putRequest } from '../../../utils/api';
import QS from 'qs';
export default {
  mounted: function () {
    this.currentUser = this.$store.state.currentUser;
    this.editUser = this.currentUser;
    // console.log(this.currentUser);
    // console.log(this.editUser);
    this.avatarNames = require
      .context("../../../img/avatar", false, /.jpg$/)
      .keys();
    this.avatarNames.forEach((item, index) => {
      var avatarName = item;
      this.avatarNames[index] = avatarName.slice(2);
    });
  },
  methods: {
    
    avatarChange() {
      this.avatarChangeIndex = true;
    },
    closeAvatarChange() {
      this.avatarChangeIndex = false;
    },
    changeAvatar(avatarName) {
      document.getElementsByClassName(
        "user-avatar"
      )[0].src = require("../../../img/avatar/" + avatarName);
      // console.log(this.avatarNames);
      // console.log(avatarName);
      // console.log(require("../../../img/avatar/" + avatarName));
      // console.log(document.getElementsByClassName("user-avatar")[0].src);
      // console.log(document.getElementsByClassName("user-avatar")[0].src.indexOf('img') + 4);
      // console.log(require("../../../img/avatar/" + avatarName));
      // console.log(this.currentUser.userface);
      // console.log('/img/avatar/' + document.getElementsByClassName("user-avatar")[0].src.slice(33,-12));
    },
    saveAvatarChange() {
      this.editUser.userface = '/img/avatar/' + document.getElementsByClassName("user-avatar")[0].src.slice(33,-12) + '.jpg';
      console.log(this.editUser.userface);
      this.closeAvatarChange();
    },
    saveUserChange() {
      var _this = this;
      this.editUser.id = this.currentUser.id;
      console.log(this.editUser);
      putRequest('/editUser',{'editUser':JSON.stringify(this.editUser)}).then(
        function(msg) {
          if(msg.status !== 200) {
            _this.$message({type:'error', message:'更新失败!'});
          }
          _this.$message({type: msg.data.status, message:msg.data.msg});
          _this.$store.dispatch("setCurrentUser", _this.editUser);
          // console.log({type: msg.data.status, message:msg.data.msg});
        },{
          function (msg) {
            _this.$message({type:'error', message:'更新失败!'});
          }
        }
      )
    },
    goback() {
      this.$router.push({
        name: this.$store.state.subscribePrePath,
      });
    },
  },
  data() {
    return {
      currentUser: "",
      editUser: {
        id: '',
        nickname: "",
        userface: "",
        userSignature: "",
        userSex: "",
      },
      editUser: '',
      editUserResult:'',
      avatarChangeIndex: false,
      avatarNames: [],
      avatarName1: "../../../img./崩坏三.jpg",
    };
  },
};
</script>
<style>
.el-input__inner {
  background-color: transparent;
  border: 0;
  padding-left: 0px;
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
.flex-div-center-wrap {
  display: flex;
  align-items: center;
  justify-content: center;
}
.textarea-inherit {
  width: 96%;
  overflow: auto;
  padding: 2%;
  border-radius: 5px;
}
.nick-div {
  margin-top: 30px;
  border: 1px solid #dddddd;
  border-top-left-radius: 5px;
  border-top-right-radius: 5px;
}
.sex-div {
  margin-top: 0px;
  border: 1px solid #dddddd;
  height: 40px;
  border-bottom-left-radius: 5px;
  border-bottom-right-radius: 5px;
  border-top: none;
}
textarea {
  outline: none;
  caret-color: #3366ff;
}
.v-enter {
  opacity: 0;
  transform: translateY(100%);
}
.v-leave-to {
  opacity: 0;
  transform: translateY(100%);
  position: absolute;
}
.v-enter-active,
.v-leave-active {
  transition: all 0.5s ease;
}
.avatar-change-div {
  z-index: 99;
  position: absolute;
  top: 30px;
  left: 8px;
  width: 100%;
  background-color: #fff;
}
.avatar-lists {
  padding-left: 30%;
  padding-right: 30%;
  border: 1px solid #dddddd;
  border-radius: 4px;
  position: absolute;
  height: 220%;
  overflow-y: auto;
  background-color: #fff;
}
</style>