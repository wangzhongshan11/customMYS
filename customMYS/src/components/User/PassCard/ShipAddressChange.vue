<template>
  <div>
    <el-container>
      <el-header style="background-color:white;padding:0px;margin-top:-45px;">
        <el-row style="margin-top:10px;width:100%;">
          <el-col :span="1">
            <div>
              <i class="el-icon-arrow-left" @click="goback" style="cursor:pointer;"></i>
            </div>
          </el-col>
          <el-col :span="8" :offset="7">
            <div>
              <span>管理收货地址-修改地址</span>
            </div>
          </el-col>
        </el-row>
      </el-header>
      <el-main>
        <div class="flex-div-start-wrap">
          <span class="font-12px">|寄送地址</span>
        </div>
        <el-form ref="newShipAddr" :model="{newShipAddr}">
          <el-form-item style="margin-top:30px;">
            <el-row>
              <el-col :span="3" :offset="0">
                <div class="flex-div-start-wrap" style="height:40px;">
                  <span class="font-12px">姓名</span>
                </div>
              </el-col>
              <el-col :span="21" :offset="0">
                <div class="flex-div-start-wrap" style="height:40px;">
                  <el-input v-model="newShipAddr.uRealname" placeholder="请填写姓名" class="font-12px"></el-input>
                </div>
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item style="margin-top:-30px;">
            <el-row>
              <el-col :span="3" :offset="0">
                <div class="flex-div-start-wrap" style="height:40px;">
                  <span class="font-12px">通讯地址</span>
                </div>
              </el-col>
              <el-col :span="21" :offset="0">
                <div class="flex-div-start-wrap" style="height:40px;">
                  <el-input v-model="newShipAddr.shipAddress" placeholder="省份 城市" class="font-12px"></el-input>
                </div>
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item style="margin-top:-30px;">
            <el-row>
              <el-col :span="3" :offset="0">
                <div class="flex-div-start-wrap" style="height:40px;">
                  <span class="font-12px">详细地址</span>
                </div>
              </el-col>
              <el-col :span="21" :offset="0">
                <div class="flex-div-start-wrap" style="height:40px;">
                  <el-input
                    v-model="newShipAddr.detailShipAddress"
                    placeholder="请填写详细地址"
                    class="font-12px"
                  ></el-input>
                </div>
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item style="margin-top:-30px;">
            <el-row>
              <el-col :span="3" :offset="0">
                <div class="flex-div-start-wrap" style="height:40px;">
                  <span class="font-12px">区号</span>
                </div>
              </el-col>
              <el-col :span="21" :offset="0">
                <div class="flex-div-start-wrap" style="height:40px;">
                  <el-input v-model="newShipAddr.uAreaCode" placeholder="请填写区号" class="font-12px"></el-input>
                </div>
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item style="margin-top:-30px;">
            <el-row>
              <el-col :span="3" :offset="0">
                <div class="flex-div-start-wrap" style="height:40px;">
                  <span class="font-12px">联系方式</span>
                </div>
              </el-col>
              <el-col :span="21" :offset="0">
                <div class="flex-div-start-wrap" style="height:40px;">
                  <el-input v-model="newShipAddr.uPhone" placeholder="请填写联系方式" class="font-12px"></el-input>
                </div>
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item style="margin-top:60px;">
            <el-button
              type="primary"
              @click="submitForm('newShipAddr')"
              size="medium"
              style="width:100%;"
            >确定</el-button>
          </el-form-item>
          <el-form-item style="margin-top:-15px;">
            <el-button type="primary" @click="goback" size="medium" style="width:100%;">返回</el-button>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
  </div>
</template>
<script>
import { postRequest, putRequest } from "../../../utils/api";
export default {
  mounted() {
    this.currentUser = this.$store.state.currentUser;
    this.editChosenShipAddress = this.$route.params.editChosenShipAddress;
    this.chosenShipAddressIndex = this.$route.params.chosenShipAddressIndex;
    console.log(this.chosenShipAddressIndex);
    console.log(this.chosenShipAddressIndex != null);
    if (this.chosenShipAddressIndex != null){
      this.newShipAddr = this.editChosenShipAddress;
    }
  },
  data() {
    return {
      currentUser: "",
      newShipAddr: {
        id: "",
        uid: "",
        uRealname: "",
        shipAddress: "",
        detailShipAddress: "",
        uAreaCode: "",
        uPhone: "",
        isDefaultShipAddr: "0",
      },
      editChosenShipAddress: "",
      chosenShipAddressIndex: "",
    };
  },
  methods: {
    submitForm(formName) {
      var _this = this;
      console.log(this.newShipAddr);
      this.newShipAddr.uid = this.currentUser.id;

      if (this.editChosenShipAddress == "") {
        this.newShipAddr.id = this.currentUser.shipAddresses.length + 1;
        postRequest("/newShipAddress", {
          newShipAddr: JSON.stringify(this.newShipAddr),
        }).then(
          function (msg) {
            if (msg.status !== 200) {
              _this.$message({ type: "error", message: "绑定失败!" });
            }
            _this.$message({ type: msg.data.status, message: msg.data.msg });

            _this.currentUser.shipAddresses.push(_this.newShipAddr);
            _this.$store.dispatch("setCurrentUser", _this.currentUser);
            _this.goback();
            // console.log({type: msg.data.status, message:msg.data.msg});
          },
          {
            function(msg) {
              _this.$message({ type: "error", message: "绑定失败!" });
            },
          }
        );
      } else {
        this.newShipAddr.id = this.currentUser.shipAddresses[this.chosenShipAddressIndex].id;
        putRequest("/editChosenShipAddress", {
          newShipAddr: JSON.stringify(this.newShipAddr),
        }).then(
          function (msg) {
            if (msg.status !== 200) {
              _this.$message({ type: "error", message: "编辑失败!" });
            }
            _this.$message({ type: msg.data.status, message: msg.data.msg });
            _this.currentUser.shipAddresses[_this.chosenShipAddressIndex] =
              _this.newShipAddr;
            _this.$store.dispatch("setCurrentUser", _this.currentUser);
            _this.goback();
            // console.log({type: msg.data.status, message:msg.data.msg});
          },
          {
            function(msg) {
              _this.$message({ type: "error", message: "编辑失败!" });
            },
          }
        );
      }
    },
    goback() {
      this.$router.push({
        name: "UserShipAddress",
      });
    },
  },
};
</script>
<style>
.font-12px {
  font-size: 12px;
}
.flex-div-start-wrap .el-input__inner {
  background-color: transparent;
  border: 0;
}
</style>