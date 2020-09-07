<template>
  <div class="app-container">
    <el-container class="home_container">
      <el-main style="background-color:#fff;">
        <div>
          <router-view></router-view>
        </div>
        <!-- <AddSomething v-show="false" class="add-something-wrap"></AddSomething> -->
        <AddSomething ref="AddSomething"></AddSomething>
      </el-main>
      <el-footer
      class="app-wzs-footer"
        style="position:fixed;left:0px;width:100%;bottom:0px;background-color:#dddddd;z-index:98;height:70px;padding-top:10px;"
      >
        <el-row style>
          <el-col :span="5" style>
            <div style>
              <router-link to="/homeWzs">
                <div class="el-icon-s-home" style="font-size:30px;"></div>
                <div class="appwzs-home-button">首页</div>
              </router-link>
            </div>
          </el-col>
          <el-col :span="5">
            <div>
              <router-link to="/news">
                <div class="el-icon-news"  style="font-size:30px;"></div>
                <div>论坛</div>
              </router-link>
            </div>
          </el-col>
          <el-col :span="4">
            <div class="icon-wrap-plus" style="cursor:pointer;">
              <i class="el-icon-circle-plus" size="medium" @click="addSomething()"></i>
            </div>
          </el-col>
          <el-col :span="5">
            <div>
              <router-link to="/message">
              <div class="el-icon-chat-line-square"  style="font-size:30px;"></div>
              <div>消息</div>
              </router-link>
            </div>
          </el-col>
          <el-col :span="5">
            <div>
              <router-link to="/userwzs">
                <div class="el-icon-user"  style="font-size:30px;"></div>
                <div>我的</div>
              </router-link>
            </div>
          </el-col>
        </el-row>
      </el-footer>
    </el-container>
  </div>
</template>

<script>
import AddSomething from "@/components/Add/AddSomething";
export default {
  data() {
    return {
      msg: 1,
    };
  },
  methods: {
    addSomething() {
      this.$refs.AddSomething.addSomethingIndex = true;
    },
  },
  mounted() {
    console.log(this.$refs);
    console.log(this.$refs.AddSomething);
    document.getElementsByClassName('appwzs-home-button')[0].click();
  },
  components: {
    AddSomething,
  },
};
</script>
<style>
.icon-wrap-plus {
  font-size: 40px;
}
.app-wzs-footer .router-link-active {
  /* border-bottom: 3px solid #3366ff; */
  color: #3366ff;
}
a {
  text-decoration: none;
  color: #000000;
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
</style>