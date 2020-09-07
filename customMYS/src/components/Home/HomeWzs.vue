<template>
  <div>
    <!-- <div class="home-search-wrap">
      <transition>
        <homeSearch ref="homeSearch"></homeSearch>
      </transition>
    </div>-->
    <homeForumSetting ref="homeForumSetting" @closeSetChannel="closeSetChannel"></homeForumSetting>
    <div class="home-page-tran-head">
      <transition>
        <div
          v-show="setChannelIndex"
          class="flex-div-around-wrap gradual-style-mousemove"
          style="opacity:1;background-color:#3399FF;padding-bottom:30px;padding-top:30px;position:fixed;width:98%;z-index:91;top:0px;left:0px;"
        >
          <div>
            <i
              class="el-icon-search"
              style="color:#dddddd;font-size:30px;cursor:pointer;"
              @click="homeSearch"
            ></i>
          </div>
          <div
            class="swiper-container"
            style="color:#dddddd;width:60%;margin-left:-50px;margin-right:-50px;font-size:20px;height:40px;"
          >
            <div class="swiper-wrapper">
              <div
                class="swiper-slide flex-div-center-wrap swiper-slide-forums"
                v-for="(item,index) in forumnames"
                :key="index"
                style="height:40px;cursor:pointer;"
                @click="changeCurrentForum(index)"
              >{{item}}</div>
            </div>
          </div>
          <div>
            <i
              class="el-icon-s-unfold"
              style="color:#dddddd;font-size:30px;height:40px;cursor:pointer;"
              @click="setChannel"
            ></i>
          </div>
        </div>
      </transition>
      <homeDabieye ref="homeDabieye" v-show="showWhichForum[0]"></homeDabieye>
      <homeBBB ref="homeBBB" v-show="showWhichForum[1]"></homeBBB>
      <homeBB ref="homeBB" v-show="showWhichForum[2]"></homeBB>
      <homeYuanShen ref="homeYuanShen" v-show="showWhichForum[3]"></homeYuanShen>
      <homeWeiDing ref="homeWeiDing" v-show="showWhichForum[4]"></homeWeiDing>
    </div>
  </div>
</template>
<script>
import Swiper from "swiper";
import "../../assets/swiper/swiper-bundle.css";
import Vue from "vue";
import homeSearch from "./HomeSearch";
import homeDabieye from "./HomeForums/HomeDabieye";
import homeBBB from "./HomeForums/HomeBBB";
import homeBB from "./HomeForums/HomeBB";
import homeYuanShen from "./HomeForums/HomeYuanShen";
import homeWeiDing from "./HomeForums/HomeWeiDing";
import homeForumSetting from "./HomeForumSetting";
import { getRequest } from "../../utils/api";
import { isNotNullORBlank } from "../../utils/utils";
Vue.directive("dragVerticle", (el, { value }) => {});
export default {
  components: {
    homeSearch,
    homeDabieye,
    homeBBB,
    homeBB,
    homeYuanShen,
    homeWeiDing,
    homeForumSetting,
  },
  data() {
    return {
      timer: "",
      homeSearchIndex: false,
      setChannelIndex: true,
      forumnames: ["大别野", "崩坏3", "崩坏学园2", "原神", "未定事件簿"],
      forumClicks: [1, 0, 0, 0, 0, 0],
      //   showWhichForum: [true, false, false, false, false],
      showWhichForum: [true, true, true, true, true],
      showWhichForumRef: [
        "homeDabieye",
        "homeBBB",
        "homeBB",
        "homeYuanShen",
        "homeWeiDing",
      ],
      preClick: 0,
      articles: [],
      totalCount: "",
    };
  },
  mounted() {
    //   document.getElementsByClassName('swiper-slide-forums')[0].click();
    this.forumsSwiper();
    // this.loadAllArticles();
    // window.bus.$off('reloadAllArticles');
    // window.bus.$on("reloadAllArticles", (data) => {console.log(data);
    // });

    var scrollRegions = Array.from(
      document.getElementsByClassName("scroll-region")
    );
    var pageContainers = Array.from(
      document.getElementsByClassName("page-container")
    );
    scrollRegions.forEach((item, index) => {
      scrollRegions[index].addEventListener("mousedown", this.mousedown);
    });
    pageContainers.forEach((item, index) => {
      pageContainers[index].onscroll = this.onmousewheel;
    });
    document.getElementsByClassName("swiper-slide-forums")[0].style.color =
      "#fff";
    document.getElementsByClassName("swiper-slide-forums")[0].style.fontSize =
      "25px";
    document.getElementsByClassName("swiper-slide-forums")[0].style.fontWeight =
      "bold";
    document
      .getElementsByClassName("swiper-slide-forums")[0]
      .classList.add("dabieye-div");
    if (isNotNullORBlank(this.$route.params.whichForumIndex)) {
      document
        .getElementsByClassName("swiper-slide-forums")
        [this.$route.params.whichForumIndex - 1].click();
    }
  },
  methods: {
    closeSetChannel(val) {
      this.setChannelIndex = val;
      this.$refs.homeDabieye.setChannelIndex = true;
    },
    setChannel() {
      this.setChannelIndex = false;
      this.$refs.homeForumSetting.setChannelIndex = false;
      this.$refs.homeDabieye.setChannelIndex = false;
      this.$refs.homeBBB.setChannelIndex = false;
      this.$refs.homeBB.setChannelIndex = false;
      this.$refs.homeYuanShen.setChannelIndex = false;
      this.$refs.homeWeiDing.setChannelIndex = false;
      var homePageTrans = Array.from(
        document.getElementsByClassName("home-page-tran-search")
      );

      homePageTrans.forEach((item, index) => {
        homePageTrans[index].classList.remove("click-right");
        homePageTrans[index].classList.remove("click-left");
      });
    },
    homeSearch() {
      this.$store.dispatch("setSubscribePrePath", this.$route.name);
      this.$router.push({
        name: "HomeSearch",
      });
    },
    forumsSwiper() {
      new Swiper(".swiper-container", {
        freeMode: true,
        slidesPerView: "3",
        slideToClickedSlide: true,
        centeredSlides: true,
      });
    },
    changeCurrentForum(ind) {
      this.forumClicks.forEach((item, index) => {
        if (index !== ind) {
          this.forumClicks[index] = 0;
        } else if (index == ind) {
          this.forumClicks[ind] = this.forumClicks[ind] + 1;
          this.loadBlogs(this.forumnames[ind]);
        }
      });
      if (this.forumClicks[ind] == 2) {
        this.loadBlogs(this.forumnames[ind]);
        this.forumClicks[ind] = 1;
      }
      var swipersHTMLC = document.getElementsByClassName("swiper-slide-forums");
      Array.from(swipersHTMLC).forEach((item, index) => {
        if (index !== ind) {
          Array.from(swipersHTMLC)[index].style.color = "#dddddd";
          Array.from(swipersHTMLC)[index].style.fontSize = "20px";
          Array.from(swipersHTMLC)[index].style.fontWeight = "normal";
          Array.from(swipersHTMLC)[index].classList.remove("dabieye-div");
        } else {
          Array.from(swipersHTMLC)[index].style.color = "#fff";
          Array.from(swipersHTMLC)[index].style.fontSize = "25px";
          Array.from(swipersHTMLC)[index].style.fontWeight = "bold";
          Array.from(swipersHTMLC)[index].classList.add("dabieye-div");
        }
      });
      var homePageTrans = Array.from(
        document.getElementsByClassName("home-page-tran-search")
      );
      if (ind > this.preClick) {
        homePageTrans.forEach((item, index) => {
          homePageTrans[index].classList.add("click-right");
          homePageTrans[index].classList.remove("click-left");
        });
      } else if (ind < this.preClick) {
        homePageTrans.forEach((item, index) => {
          homePageTrans[index].classList.add("click-left");
          homePageTrans[index].classList.remove("click-right");
        });
      }

      var refs = this.$refs;
      this.showWhichForumRef.forEach((item, index) => {
        if (index !== ind) {
          refs[item].setChannelIndex = false;
        } else {
          refs[item].setChannelIndex = true;
        }
      });
      this.preClick = ind;
    },
    onmousewheel(e) {
      var el = document.getElementsByClassName("scroll-region")[this.preClick];
      var dy = document.getElementsByClassName("page-container")[this.preClick]
        .scrollTop;
      var elTopY = el.getBoundingClientRect().top;
      var firstC = parseFloat((dy / 200) * 204 + 51).toFixed(0);
      var secondC = parseFloat((dy / 200) * 102 + 153).toFixed(0);
      if (firstC > 255) {
        firstC = 255;
      }
      if (firstC < 51) {
        firstC = 51;
      }
      if (secondC > 255) {
        secondC = 255;
      }
      if (secondC < 153) {
        secondC = 153;
      }
      var opacityHeader = parseFloat(0.5 + (dy / 170) * 0.5).toFixed(1);
      var menuFontSize = 30 - (dy / 170) * 5;
      var menuColor = 255 - (dy / 170) * 255;
      if (menuFontSize > 30) {
        menuFontSize = 30;
      }
      if (menuFontSize < 25) {
        menuFontSize = 25;
      }
      if (menuColor > 255) {
        menuColor = 255;
      }
      if (menuColor < 0) {
        menuColor = 0;
      }

      document.getElementsByClassName(
        "gradual-style-mousemove"
      )[0].style.backgroundColor =
        "rgba(" + firstC + "," + secondC + ",255," + opacityHeader + ")";
      document.getElementsByClassName("dabieye-div")[0].style.fontSize =
        menuFontSize + "px";
      document.getElementsByClassName("brny-refresh")[this.preClick].style.top =
        -100 + dy + "px";
      document.getElementsByClassName("dabieye-div")[0].style.color =
        "rgba(" + menuColor + "," + menuColor + "," + menuColor + ",1)";

      if (dy >= 170) {
        document.getElementsByClassName("suspension-discuss")[
          this.preClick
        ].style.display = "block";
      } else {
        document.getElementsByClassName("suspension-discuss")[
          this.preClick
        ].style.display = "none";
      }
    },
    mousedown(e) {
      var _this = this;
      var posX, posY;
      var elTopY;
      var el = document.getElementsByClassName("scroll-region")[this.preClick];
      posX = e.clientX + scrollX;
      posY = e.clientY + scrollY;
      elTopY = el.getBoundingClientRect().top;
      document.addEventListener("mousemove", mousemove);
      document.addEventListener("mouseup", mouseup);

      function mousemove(e) {
        var dx = e.clientX + scrollX - posX;
        var dy = e.clientY + scrollY - posY;
        var firstC = parseFloat((-dy / 200) * 204 + 51).toFixed(0);
        var secondC = parseFloat((-dy / 200) * 102 + 153).toFixed(0);
        if (firstC > 255) {
          firstC = 255;
        }
        if (firstC < 51) {
          firstC = 51;
        }
        if (secondC > 255) {
          secondC = 255;
        }
        if (secondC < 153) {
          secondC = 153;
        }
        var opacityHeader = parseFloat(0.5 + (-dy / 170) * 0.5).toFixed(1);
        var menuFontSize = 30 - (-dy / 170) * 5;
        var menuColor = 255 - (-dy / 170) * 255;
        if (menuFontSize > 30) {
          menuFontSize = 30;
        }
        if (menuFontSize < 25) {
          menuFontSize = 25;
        }
        if (menuColor > 255) {
          menuColor = 255;
        }
        if (menuColor < 0) {
          menuColor = 0;
        }
        console.log("x移动" + dx + ",y移动" + dy);
        document.getElementsByClassName(
          "gradual-style-mousemove"
        )[0].style.backgroundColor =
          "rgba(" + firstC + "," + secondC + ",255," + opacityHeader + ")";
        document.getElementsByClassName("dabieye-div")[0].style.fontSize =
          menuFontSize + "px";
        document.getElementsByClassName("brny-refresh")[
          _this.preClick
        ].style.top = -100 + dy + "px";
        document.getElementsByClassName("dabieye-div")[0].style.color =
          "rgba(" + menuColor + "," + menuColor + "," + menuColor + ",1)";
        el.style.position = "absolute";
        el.style.width = "100%";
        el.style.top = elTopY + dy + "px";

        if (el.getBoundingClientRect().top <= -170) {
          document.getElementsByClassName("suspension-discuss")[
            _this.preClick
          ].style.display = "block";
        } else {
          document.getElementsByClassName("suspension-discuss")[
            _this.preClick
          ].style.display = "none";
        }
      }
      function mouseup(e) {
        document.removeEventListener("mousemove", mousemove);
        document.removeEventListener("mouseup", mouseup);
        if (el.getBoundingClientRect().top > 0) {
          el.style.top = "0px";
          document.getElementsByClassName("brny-refresh")[
            _this.preClick
          ].style.top = "-100px";
        }
      }
    },
    loadBlogs(forumname) {
      var _this = this;
      var url = "";
      url = "/admin/article/all" + "?forumname=" + forumname;
      getRequest(url).then(
        (resp) => {
          if (resp.status == 200) {
            console.log("=200");
            // console.log(resp.data.articles);
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
            // console.log(_this.articles);
          } else {
            _this.$message({ type: "error", message: "数据加载失败!" });
          }
        },
        (resp) => {
          if (resp.response.status == 403) {
            _this.$message({ type: "error", message: resp.response.data });
          } else {
            _this.$message({ type: "error", message: "数据加载失败!" });
          }
        }
      );
    },
    loadAllArticles() {
      this.loadBlogs("大别野");
      this.loadBlogs("崩坏3");
      this.loadBlogs("崩坏学园2");
      this.loadBlogs("原神");
      this.loadBlogs("未定事件簿");
    },
  },
};
</script>
<style>
.page-container {
  height: 100%;
  position: absolute;
  top: 0px;
  left: 0px;
  width: 100%;
  overflow-y: auto;
}
.page-container1 {
  height: 100%;
  position: absolute;
  top: 0px;
  left: 0px;
  width: 100%;
  overflow-y: auto;
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
.set-channel-page .v-enter {
  opacity: 0;
  transform: translateX(100%);
}
.set-channel-page .v-leave-to {
  opacity: 1;
  transform: translateX(100%);
  position: absolute;
}
.set-channel-page .v-enter-active,
.set-channel-page .v-leave-active {
  transition: all 0.5s ease;
}

.home-page-tran .v-enter {
  opacity: 0;
  transform: translateX(-100%);
}
.home-page-tran .v-leave-to {
  opacity: 0;
  transform: translateX(-100%);
  position: absolute;
}
.home-page-tran .v-enter-active,
.home-page-tran .v-leave-active {
  transition: all 0.5s ease;
}
.home-page-tran-head .v-enter {
  opacity: 0;
  transform: translateX(-100%);
}
.home-page-tran-head .v-leave-to {
  opacity: 0;
  transform: translateX(-100%);
  position: absolute;
}
.home-page-tran-head .v-enter-active,
.home-page-tran-head .v-leave-active {
  transition: all 0.5s ease;
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

.click-left .v-enter {
  opacity: 0;
  transform: translateX(-100%);
}
.click-left .v-leave-to {
  opacity: 0;
  transform: translateX(100%);
  position: absolute;
}
.click-left .v-enter-active,
.click-left .v-leave-active {
  transition: all 0.2s ease;
}

.click-right .v-enter {
  opacity: 0;
  transform: translateX(100%);
}
.click-right .v-leave-to {
  opacity: 0;
  transform: translateX(-100%);
  position: absolute;
}
.click-right .v-enter-active,
.click-right .v-leave-active {
  transition: all 0.2s ease;
}
</style>