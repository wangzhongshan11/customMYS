<template>
  <div>
    <!-- <div class="home-search-wrap">
      <transition>
        <homeSearch ref="homeSearch"></homeSearch>
      </transition>
    </div>-->
    <div class="page-container-wrap">
      <el-row style="padding-bottom:30px;padding-top:30px;font-size:20px;background-color:#dddddd;">
        <el-col :span="1" :offset="1">
          <div
            class="flex-div-start-wrap"
            style="height:30px;cursor:pointer;"
            @click="backHomepage"
          >
            <i class="el-icon-arrow-left"></i>
          </div>
        </el-col>
        <el-col :span="1" :offset="1">
          <div class="flex-div-start-wrap" style="height:30px;cursor:pointer;" @click="homeSearch">
            <i class="el-icon-search"></i>
          </div>
        </el-col>
        <el-col :span="16" :offset="0">
          <div class="flex-div-center-wrap" style="height:30px;">
            <span>大别野</span>
          </div>
        </el-col>
        <el-col :span="3" :offset="0">
          <div class="flex-div-end-wrap" style="height:30px;">
            <span
            @click="signInForum"
              style="font-size:15px;cursor:pointer;background-color:#3399ff;color:#fff;border-radius:4px;width:50%;"
            >签到</span>
            <i class="el-icon-info"></i>
          </div>
        </el-col>
      </el-row>

<div
        class="flex-div-around-wrap scroll-gradual-suspension1"
        style="border-bottom: solid 1px #dddddd;background-color:#fff;text-align:center;"
        v-show="false"
      >
        <el-menu
          class="flex-div-around-wrap"
          default-active="0"
          mode="horizontal"
          router
          style="border-bottom: none;width:70%;margin-left:15%;margin-right:15%;"
        >
          <template v-for="(item,index) in this.$router.options.routes[17].children">
            <el-menu-item
              :index="item.path"
              :key="index"
              style="font-size:17px;"
              @click="dabieyeRouteDirect(index)"
              class="dabieye-routes-wrap-suspension"
            >{{item.name}}</el-menu-item>
          </template>
        </el-menu>
      </div>

      <div class="flex-div-around-wrap" style="border-bottom: solid 1px #dddddd;">
        <el-menu
          class="flex-div-around-wrap"
          default-active="0"
          mode="horizontal"
          router
          style="border-bottom: none;width:70%;"
        >
          <template v-for="(item,index) in this.$router.options.routes[17].children">
            <el-menu-item
              :index="item.path"
              :key="index"
              style="font-size:17px;"
              @click="dabieyeRouteDirect(index)"
              class="dabieye-routes-wrap"
            >{{item.name}}</el-menu-item>
          </template>
        </el-menu>
      </div>
      <div class="dabieye-movie-wrap dabieye-right-movie">
        <!-- <keep-alive> -->
        <transition>
          <router-view></router-view>
        </transition>
        <!-- </keep-alive> -->
      </div>
    </div>
  </div>
</template>
<script>
import homeSearch from "../HomeSearch";
import {   postRequest,
  getRequest,
  putRequest,
  deleteRequest, } from '../../../utils/api';
export default {
  components: {
    homeSearch,
  },
  mounted() {
    document.getElementsByClassName("dabieye-routes-wrap")[0].click();
    document.getElementsByClassName(
      "page-container-wrap"
    )[0].onscroll = this.onmousewheel;
  },
  methods: {
    backHomepage() {
      this.$router.push({
        name: "HomeWzs",
      });
    },
    homeSearch() {
      // this.$refs.homeSearch.homeSearchIndex = true;
      this.$router.push({
        name: "HomeSearch",
        params: { sourceRouteName: this.$route.name },
      });
    },
    dabieyeRouteDirect(ind) {
      // console.log(ind);

      document.getElementsByClassName('dabieye-routes-wrap-suspension')[ind].click();
      document.getElementsByClassName('dabieye-routes-wrap')[ind].click();

      var dabieyeMovies = Array.from(
        document.getElementsByClassName("dabieye-movie-wrap")
      );
      if (ind > this.preRouteIndex) {
        dabieyeMovies.forEach((item, index) => {
          dabieyeMovies[index].classList.add("dabieye-right-movie");
          dabieyeMovies[index].classList.remove("dabieye-left-movie");
        });
      } else if (ind < this.preRouteIndex) {
        dabieyeMovies.forEach((item, index) => {
          dabieyeMovies[index].classList.remove("dabieye-right-movie");
          dabieyeMovies[index].classList.add("dabieye-left-movie");
        });
      }

      this.preRouteIndex = ind;
    },
    onmousewheel(e) {
      var dy = document.getElementsByClassName("page-container-wrap")[0]
        .scrollTop;
      // console.log(dy);
      var suspension1s = Array.from(
        document.getElementsByClassName("scroll-gradual-suspension1")
      );
      var suspension2s = Array.from(
        document.getElementsByClassName("scroll-gradual-suspension2")
      );
      if (dy > 90) {
        suspension1s.forEach((item, index) => {
          suspension1s[index].style.position = "fixed";
          suspension1s[index].style.width = "98%";
          suspension1s[index].style.top = "0px";
          suspension1s[index].style.left = "0px";
          suspension1s[index].style.display = "block";
          suspension1s[index].style.zIndex = "2";
        });
        if (suspension2s.length !== 0) {
          suspension2s.forEach((item, index) => {
            suspension2s[index].style.position = "fixed";
            suspension2s[index].style.width = "98%";
            suspension2s[index].style.top = "61px";
            suspension2s[index].style.display = "block";
            suspension2s[index].style.zIndex = "2";
          });
        }
      } else if (dy <= 90) {
        suspension1s.forEach((item, index) => {
          suspension1s[index].style.position = "";
          suspension1s[index].style.display = "none";
        });
        if (suspension2s.length !== 0) {
          suspension2s.forEach((item, index) => {
            suspension2s[index].style.position = "";
            suspension2s[index].style.display = "none";
          });
        }
      }
    },
    signInForum() {
      var _this = this;
      putRequest("/user/signIn/" + _this.$store.state.currentUser.id + "/" + "大别野").then((resp) => {
        if (resp.status == 200){
          var currentUser = _this.$store.state.currentUser;
          currentUser.userForums.forEach((item,index) =>  {
            if (item.forumname == "大别野") {
              currentUser.userForums[index].uForumExperience +=10; 
            }
          });
          _this.$store.dispatch("setCurrentUser",currentUser);
          _this.$message({type:"success",message:'签到成功！'})

        } else {
          _this.$message({type:"error",message:'签到失败！'})
        }
      },(resp) => {
          _this.$message({type:"error",message:'签到失败！'})
      })
    }
  },
  data() {
    return {
      preRouteIndex: 0,
    };
  },
};
</script>
<style>
.page-container-wrap {
  height: 100%;
  position: absolute;
  top: 0px;
  left: 0px;
  width: 100%;
  overflow-y: auto;
  overflow-x: hidden;
}
.home-search-wrap .v-enter {
  opacity: 0;
  transform: translateY(100%);
}
.home-search-wrap .v-leave-to {
  opacity: 0;
  transform: translateY(100%);
  position: absolute;
}
.home-search-wrap .v-enter-active,
.home-search-wrap .v-leave-active {
  transition: all 0.5s ease;
}
.dabieye-right-movie .v-enter {
  opacity: 0;
  transform: translateX(100%);
}
.dabieye-right-movie .v-leave-to {
  opacity: 0;
  transform: translateX(-100%);
  position: absolute;
}
.dabieye-right-movie .v-enter-active,
.dabieye-right-movie .v-leave-active {
  transition: all 0.5s ease;
}
.dabieye-left-movie .v-enter {
  opacity: 0;
  transform: translateX(-100%);
}
.dabieye-left-movie .v-leave-to {
  opacity: 0;
  transform: translateX(100%);
  position: absolute;
}
.dabieye-left-movie .v-enter-active,
.dabieye-left-movie .v-leave-active {
  transition: all 0.5s ease;
}
</style>