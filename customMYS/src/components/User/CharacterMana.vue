<template>
  <div class="chara-mana-wrap">
    <div class="chara-mana-header">
      <el-row>
        <el-col :span="1" :offset="1">
          <div>
            <i class="el-icon-arrow-left" style="color: #000000;cursor:pointer;" @click="goback"></i>
          </div>
        </el-col>
        <el-col :span="6" :offset="7">
          <div>
            <span style="color: #000000;">账号游戏信息</span>
          </div>
        </el-col>
      </el-row>
    </div>
    <div class="chara-mana-main">
      <div v-if="showFlag" class="bind-other">
        <div style="padding-left:3%;padding-right:2%;">
        <div style="display:flex;justify-content:flex-start;margin-bottom:10px;padding-left:2%;">
          <span style="font-size:10px;">|请填写想要绑定的角色信息</span>
          <span style="color:#969696;font-size:10px;">(官方服角色自动绑定)</span>
        </div>
        <el-form ref="newCharaForm" :model="newCharaForm" style="padding-left:2%;padding-right:2%;">
          <el-form-item>
            <el-select v-model="newCharaForm.gamename" placeholder="崩坏三" style="width:100%;">
              <el-option value="崩坏3"></el-option>
              <el-option value="崩坏学园2"></el-option>
              <el-option value="原神"></el-option>
              <el-option value="未定事件簿"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-select v-model="newCharaForm.uChannel" placeholder="请选择渠道" style="width:100%;">
              <el-option value="华为"></el-option>
              <el-option value="酷派"></el-option>
              <el-option value="联想"></el-option>
              <el-option value="oppo"></el-option>
              <el-option value="vivo"></el-option>
              <el-option value="金立"></el-option>
              <el-option value="魅族"></el-option>
              <el-option value="豌豆荚"></el-option>
              <el-option value="九游"></el-option>
              <el-option value="小米"></el-option>
              <el-option value="360"></el-option>
              <el-option value="应用宝服"></el-option>
              <el-option value="B站服"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item>
            <el-input v-model="newCharaForm.uCharaUid" placeholder="请输入角色uid"></el-input>
          </el-form-item>
          <el-form-item>
            <el-input v-model="newCharaForm.uCharaGrade" placeholder="请输入角色等级"></el-input>
          </el-form-item>
          <el-form-item>
            <el-input v-model="newCharaForm.verCode" placeholder="请输入验证码"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('newCharForm')" size="medium" style="width: 100%">确定</el-button>
          </el-form-item>
        </el-form>
        </div>
      </div>
      <keep-alive>
        <div style="background-color:#fff;padding-top:40px;padding-left:3%;padding-right:2%;">
          <div style="background-color:#fff;padding-bottom:5px;">
            <div
              v-for="(item,index) in currentUser.gameCharacters"
              :key="index"
              style="background-color:#fff;margin-bottom:45px;"
            >
              <el-row style="height:50px;line-height:50px;">
                <div class="bbb-avatar-wrap">
                  <img :src="item.gameface" alt="wzs" />
                  <span style="font-weight:bold;">&nbsp;{{item.gamename}}</span>
                </div>
              </el-row>
              <hr style="background-color:#dddddd;height:0.5px;border:none;" />
              <el-row style="height:70px;">
                <el-col :span="6" style="">
                  <div style="height:50px;padding-top:15px;">
                    <div style="display:flex; justify-content:flex-start;">
                      <span>{{item.uCharaName}}</span>
                    </div>
                    <div style="display:flex; justify-content:flex-start;">
                      <span style="font-size:10px;color:#969696;">安卓1服:{{item.uCharaGrade}}级</span>
                    </div>
                    <div style="display:flex; justify-content:flex-start;">
                      <span style="font-size:10px;color:#969696;">UID:{{item.uCharaUid}}</span>
                    </div>
                  </div>
                </el-col>
                <el-col :span="6" :offset="12">
                  <div style="height:70px;line-height:70px;">
                    <!-- <div class="default-char-wrap" style="display:flex; justify-content:flex-end;">
                      <el-radio v-model="newCharaForm.isDefaultChara" label="1" border>设为默认角色</el-radio>
                    </div>-->
                    <label>
                      <input
                        type="radio"
                        name="isDefayltChara"
                        v-model="isDefaultCharaArr[index].isDefaultChara"
                        value="1"
                        @click="changeDefaultChara(index)"
                        :checked="isDefaultCharaArr[index].isDefaultChara"
                      />设为默认
                    </label>
                  </div>
                </el-col>
              </el-row>
              <hr style="background-color:#dddddd;height:0.5px;border:none;" />
            </div>
          </div>
          <el-row style="padding-top:50px;background-color:#fff;">
            <el-col :span="14" :offset="5">
              <div>
                <router-link to="/bindOther">
                  <span class="bind-other-wrap" @click="bindOtherRoute">+绑定其他游戏角色</span>
                </router-link>
              </div>
            </el-col>
          </el-row>
        </div>
      </keep-alive>
    </div>
  </div>
</template>
<script>
import { postRequest, putRequest } from "../../utils/api";
export default {
  mounted() {
    this.showFlag = false;
    this.currentUser = this.$store.state.currentUser;
    console.log(this.currentUser);
    this.currentUser.gameCharacters.forEach((item, index) => {
      this.isDefaultCharaArr.push({
        id: item.id,
        isDefaultChara: item.isDefaultChara,
      });
    });
  },
  data() {
    return {
      currentUser: "",
      defaultChar: "",
      showFlag: false,
      newCharaForm: {
        gameface: "",
        gamename: "",
        id: "",
        uid: "",
        gid: "",
        isDefaultChara: 0,
        uCharaGrade: "",
        uCharaName: "",
        uCharaUid: "",
        uChannel: "",
        verCode: "",
      },
      isDefaultCharaArr: [],
    };
  },
  methods: {
    bindOtherRoute() {
      this.showFlag = true;
      // this.$router.push({
      //   name: "UserCharacterMana"
      // });
    },
    goback() {
      if (this.showFlag == true) {
        this.showFlag = false;
      } else {
        this.$router.push({
          name: "Userwzs",
        });
      }
    },
    submitForm(fromName) {
      var _this = this;
      console.log(this.newCharaForm);
      this.newCharaForm.id = this.currentUser.gameCharacters.length + 1;
      this.newCharaForm.uid = this.currentUser.id;
      switch (this.newCharaForm.gamename) {
        case "崩坏三":
          this.newCharaForm.gid = "1";
          this.newCharaForm.gameface = "/img/崩坏三.jpg";
          break;
        case "崩坏学园2":
          this.newCharaForm.gid = "2";
          this.newCharaForm.gameface = "/img/崩坏学园2.jpg";
          break;
        case "原神":
          this.newCharaForm.gid = "3";
          this.newCharaForm.gameface = "/img/原神.jpg";
          break;
        case "未定事件簿":
          this.newCharaForm.gid = "4";
          this.newCharaForm.gameface = "/img/未定事件簿.jpg";
          break;
      }
      postRequest("/bindOtherGameChara", {
        newCharaForm: JSON.stringify(this.newCharaForm),
      }).then(
        function (msg) {
          if (msg.status !== 200) {
            _this.$message({ type: "error", message: "绑定失败!" });
          }
          _this.$message({ type: msg.data.status, message: msg.data.msg });
          _this.currentUser.gameCharacters.push(_this.newCharaForm);
          _this.$store.dispatch("setCurrentUser", _this.currentUser);
          // console.log({type: msg.data.status, message:msg.data.msg});
        },
        {
          function(msg) {
            _this.$message({ type: "error", message: "绑定失败!" });
          },
        }
      );
    },
    changeDefaultChara(changeIndex) {
      var _this = this;
      this.isDefaultCharaArr.forEach((item, index) => {
        if (index == changeIndex) {
          _this.isDefaultCharaArr[index].isDefaultChara = 1;
        } else {
          _this.isDefaultCharaArr[index].isDefaultChara = 0;
        }
      });

      putRequest("/changeDefaultChara", {
        changeIndex: _this.currentUser.gameCharacters[changeIndex].id,
      }).then(
        function (msg) {
          if (msg.status !== 200) {
            _this.$message({ type: "error", message: "设置失败!" });
          } else {
            _this.$message({ type: msg.data.status, message: msg.data.msg });

            _this.currentUser.gameCharacters.forEach((item, index) => {
              if (index == changeIndex) {
                _this.currentUser.gameCharacters[index].isDefaultChara = 1;
              } else {
                _this.currentUser.gameCharacters[index].isDefaultChara = 0;
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
  },
};
</script>
<style>
.chara-mana-header {
  width: 100%;
  background-color: #fff;
  position: fixed;
  top: 0px;
  left: 0px;
  z-index: 2;
  height: 80px;
  line-height: 80px;
  font-size: 20px;
}
.chara-mana-main {
  margin-top: 80px;
  background-color: #fff;
  padding-top: 0px;
  padding-left: 3%;
  padding-right: 2%;
}
.chara-mana-wrap {
  background-color: #fff;
  height: 100%;
  position: absolute;
  top: 0px;
  left: 0px;
  width: 100%;
  overflow-y: auto;
}
.bbb-avatar-wrap {
  display: flex;
  align-items: center;
  justify-content: flex-start;
}

.bbb-avatar-wrap img {
  height: 35px;
  width: 35px;
}
.bind-other-wrap {
  background-color: #3366ff;
  color: white;
  border-radius: 4px 4px 4px 4px;
  height: 50px;
  padding: 10px;
}
.default-char-wrap {
  display: flex;
  align-items: center;
  justify-content: flex-end;
  height: 60px;
}
.router-link-active {
  text-decoration: none;
}
a {
  text-decoration: none;
}
.bind-other {
  position: absolute;
  z-index: 99;
  left: 0px;
  top: 100px;
  width: 100%;
  background-color: #fff;

}
</style>