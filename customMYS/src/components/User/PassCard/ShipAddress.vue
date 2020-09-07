<template>
  <div>
    <el-container>
      <el-header style="background-color:white;padding:0px;margin-top:-45px;">
        <el-row style="margin-top:10px;width:100%;">
          <el-col :span="1">
            <div>
              <i class="el-icon-arrow-left" @click="goBack" style="cursor:pointer;"></i>
            </div>
          </el-col>
          <el-col :span="6" :offset="8">
            <div>
              <span>管理收货地址</span>
            </div>
          </el-col>
        </el-row>
      </el-header>
      <el-main style="background-color:#fff;">
        <div v-for="(item,index) in currentUser.shipAddresses" :key="index">
          <div class="flex-div-start-wrap">
            <span style="font-size:12px;">|寄送地址</span>
          </div>
          <div style="margin-top:20px;margin-left:15px;">
            <div class="flex-div-start-wrap" style="font-size:12px;">
              <span>{{item.uRealname}}&nbsp;&nbsp;({{item.uAreaCode}}){{item.uPhone}}</span>
            </div>
            <div class="flex-div-start-wrap" style="font-size:12px;">
              <span>{{item.shipAddress}}&nbsp;{{item.detailShipAddress}}</span>
            </div>
            <div class="flex-div-start-wrap">
              <span class="usual-addr-wrap">常用地址</span>
            </div>
            <el-row>
              <el-col :span="4" :offset="0">
                <div class="flex-div-start-wrap" style="height:40px;">
                  <!-- <el-radio
                    v-model="isDefaultShipAddrArr[index].isDefaultShipAddr"
                    label="1"
                    size="mini"
                    fill="#3366ff"
                    @change="changeDefaultAddress(index)"
                  >
                    <span style="font-size:12px;">设为默认</span>
                  </el-radio>-->
                  <label>
                    <input
                      type="radio"
                      name="isDefayltShipAddress"
                      v-model="isDefaultShipAddrArr[index].isDefaultShipAddr"
                      value="1"
                      @click="changeDefaultAddress(index)"
                      :checked="isDefaultShipAddrArr[index].isDefaultShipAddr"
                    />设为默认
                  </label>
                </div>
              </el-col>
              <el-col :span="4" :offset="16">
                <div class="flex-div-end-wrap" style="height:40px;">
                  <span style="color:#3366ff;font-size:12px;cursor:pointer;" @click="editChosenShipAddress(index)">编辑&nbsp;&nbsp;</span>
                  <span style="color:#3366ff;font-size:12px;cursor:pointer;" @click="deleteChosenShipAddress(index)">删除</span>
                </div>
              </el-col>
            </el-row>
            <hr />
          </div>
        </div>
        <div class="flex-div-start-wrap" @click="shipAddressChange" style="cursor:pointer;">
          <i class="el-icon-circle-plus-outline" style="color:#3366ff"></i>
          <span style="font-size:12px;">&nbsp;添加新地址</span>
        </div>
      </el-main>
    </el-container>
  </div>
</template>
<script>
import { putRequest,deleteRequest } from "../../../utils/api";
export default {
  mounted() {
    this.currentUser = this.$store.state.currentUser;
    // console.log(this.currentUser);
    this.currentUser.shipAddresses.forEach((item, index) => {
      this.isDefaultShipAddrArr.push({
        id: item.id,
        isDefaultShipAddr: item.isDefaultShipAddr,
      });
    });

  },
  data() {
    return {
      defaultAddr: "",
      currentUser: "",
      isDefaultShipAddrArr: []
    };
  },
  methods: {
    shipAddressChange() {
      this.$router.push({
        name: "UserShipAddressChange",
        // ,params: { currentUser: this.currentUser },
        params: { editChosenShipAddress: '' },
      });
    },
    goBack() {
      this.$router.push({
        name: "UserPassCard",
      });
    },
    changeDefaultAddress(changeIndex) {
      var _this = this;
      this.isDefaultShipAddrArr.forEach((item, index) => {
        if (index == changeIndex) {
          _this.isDefaultShipAddrArr[index].isDefaultShipAddr = 1;
        } else {
          _this.isDefaultShipAddrArr[index].isDefaultShipAddr = 0;
        }
      });

      putRequest("/changeDefaultAddress", {
        changeIndex: _this.currentUser.shipAddresses[changeIndex].id,
      }).then(
        function (msg) {
          if (msg.status !== 200) {
            _this.$message({ type: "error", message: "设置失败!" });
          } else {
            _this.$message({ type: msg.data.status, message: msg.data.msg });

            _this.currentUser.shipAddresses.forEach((item, index) => {
              if (index == changeIndex) {
                _this.currentUser.shipAddresses[index].isDefaultShipAddr = 1;
              } else {
                _this.currentUser.shipAddresses[index].isDefaultShipAddr = 0;
              }
            });

            _this.$store.dispatch("setCurrentUser", _this.currentUser);
            // console.log({type: msg.data.status, message:msg.data.msg});
          }
        },
        {
          function(msg) {
            _this.$message({ type: "error", message: "设置失败!" });
          },
        }
      );
    },
    editChosenShipAddress(editIndex) {
      this.$router.push({
        name: "UserShipAddressChange",
         params: { editChosenShipAddress: this.currentUser.shipAddresses[editIndex],chosenShipAddressIndex: editIndex },
      });
    },
    deleteChosenShipAddress(deleteIndex) {
      var _this=this;
deleteRequest("/deleteChosenShipAddress", {
        deleteIndex: _this.currentUser.shipAddresses[deleteIndex].id,
      }).then(
        function (msg) {
          if (msg.status !== 200) {
            _this.$message({ type: "error", message: "删除失败!" });
          } else {
            _this.$message({ type: msg.data.status, message: msg.data.msg });

            _this.currentUser.shipAddresses.splice(deleteIndex,1);

            _this.$store.dispatch("setCurrentUser", _this.currentUser);
            // console.log({type: msg.data.status, message:msg.data.msg});
          }
        },
        {
          function(msg) {
            _this.$message({ type: "error", message: "删除失败!" });
          },
        }
      );
    }
  },
};
</script>
<style>
.usual-addr-wrap {
  border-radius: 4px 4px 4px 4px;
  color: white;
  background-color: #3366ff;
  font-size: 1px;
  padding: 2px;
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
</style>