<template>
  <div
    class="page-container2"
    v-show="homeSearchIndex"
    style="z-index:92;background-color:#dddddd;"
  >
    <div
      style="background-color:#dddddd;height:50px;position:fixed;top:0px;left:0px;width:100%;z-index:93;padding-top:10px;"
    >
      <span
        style="background-color:#fff;border-radius:4px;height:40px;line-height:40px;width:88%;display:inline-block;"
      >
        <i class="el-icon-search"></i>
        <input
          v-model="searchWhat"
          type="text"
          placeholder="搜索帖子或用户"
          style="border:none;width:90%;outline:none;"
        />
        <i
          class="el-icon-circle-close"
          v-show="searchWhat"
          @click="clearSearchInput"
          style="cursor:pointer;"
        ></i>
      </span>
      <span>
        <span
          style="height:25px;padding-bottom:0px;padding-top:7px;cursor:pointer;margin-left:10px;"
          @click="closeHomeSearch()"
          v-show="!searchWhat"
        >取消</span>
        <span
          style="height:25px;padding-bottom:0px;padding-top:7px;cursor:pointer;margin-left:10px;"
          @click="beginSearchWhat()"
          v-show="searchWhat"
        >搜索</span>
      </span>
    </div>
    <div
      v-show="searchResultIndex"
      class="el-menu-searchresult"
      style="height:60px;position:fixed;top:60px;left:0px;width:100%;z-index:93;"
    >
      <el-menu default-active="0" mode="horizontal" router>
        <template v-for="(item,index) in this.$router.options.routes[16].children">
          <el-menu-item
            class="el-menu-item-searchresult"
            :index="item.path"
            :key="index"
          >{{item.name}}</el-menu-item>
        </template>
      </el-menu>
    </div>
    <div
      v-show="hotSearchIndex"
      style="background-color:#fff;padding-left:3%;padding-right:2%;margin-top:60px;"
    >
      <div>
        <div
          style="text-align:left;font-weight:bold;font-size:20px;padding-top:20px;padding-bottom:10px;;"
        >全社热搜</div>
        <div>
          <table cellspacing="15" style="width:100%;">
            <tr>
              <td
                style="background-color:#dddddd;width:40%;border-radius:4px;height:40px;cursor:pointer;"
                v-show="hotSearch[0]"
                @click="beginHotSearch(hotSearch[0])"
              >
                <span
                  style="float:left;border-radius:2px;color:#FF6600;background-color:#FFCC99;border:solid 2px #fff;width:15px;height:15px;margin-top:-12px;margin-left:-2px;font-size:13px;"
                >1</span>
                <span style="float:left;padding-left:5%;">{{hotSearch[0]}}</span>
              </td>
              <td
                style="background-color:#dddddd;width:40%;border-radius:4px;height:40px;cursor:pointer;"
                v-show="hotSearch[1]"
                @click="beginHotSearch(hotSearch[1])"
              >
                <span
                  style="float:left;border-radius:2px;color:#FF6600;background-color:#FFCC99;border:solid 2px #fff;width:15px;height:15px;margin-top:-12px;margin-left:-2px;font-size:13px;"
                >2</span>
                <span style="float:left;padding-left:5%;">{{hotSearch[1]}}</span>
              </td>
            </tr>
            <tr>
              <td
                style="background-color:#dddddd;width:40%;border-radius:4px;height:40px;cursor:pointer;"
                v-show="hotSearch[2]"
                @click="beginHotSearch(hotSearch[2])"
              >
                <span
                  style="float:left;border-radius:2px;color:#FF6600;background-color:#FFCC99;border:solid 2px #fff;width:15px;height:15px;margin-top:-12px;margin-left:-2px;font-size:13px;"
                >3</span>
                <span style="float:left;padding-left:5%;">{{hotSearch[2]}}</span>
              </td>
              <td
                style="background-color:#dddddd;width:40%;border-radius:4px;height:40px;cursor:pointer;"
                v-show="hotSearch[3]"
                @click="beginHotSearch(hotSearch[3])"
              >
                <span
                  style="float:left;border-radius:2px;color:#646464;background-color:#dddddd;border:solid 2px #fff;width:15px;height:15px;margin-top:-12px;margin-left:-2px;font-size:13px;"
                >4</span>
                <span style="float:left;padding-left:5%;">{{hotSearch[3]}}</span>
              </td>
            </tr>
          </table>
        </div>
        <div
          style="text-align:left;font-weight:bold;font-size:20px;padding-top:20px;padding-bottom:10px;;"
        >
          历史搜索
          <!-- <i
            class="el-icon-info"
            style="float:right;font-size:16px;cursor:pointer;"
            @click="clearHistroySearch"
          ></i> -->
          <i class="fa fa-trash-o" aria-hidden="true" style="float:right;font-size:16px;cursor:pointer;"
            @click="clearHistroySearch"></i>
        </div>

        <ul style="list-style-type:none;padding-left:0px;">
          <li
            style="text-align:left;height:50px;"
            v-for="(item,index) in historySearch"
            :key="index"
          >
            <i class="fa fa-clock-o" aria-hidden="true"></i>
            <span style="padding-left:1%;">{{item}}</span>
            <i
              class="el-icon-close"
              style="float:right;cursor:pointer;"
              @click="deleteHistorySearch(index)"
            ></i>
          </li>
        </ul>
      </div>
    </div>
    <div v-show="searchResultIndex" style="margin-top:120px;">
      <!-- <keep-alive> -->
      <router-view ref="searchResult"></router-view>
      <!-- </keep-alive> -->
    </div>
  </div>
</template>
<script>
import Swiper from "swiper";
import "../../assets/swiper/swiper-bundle.css";
import {
  postRequest,
  getRequest,
  putRequest,
  deleteRequest,
} from "../../utils/api";
import { isNotNullORBlank } from "../../utils/utils";
export default {
  mounted() {
    // console.log(this.$router.options.routes[23]);
    this.historySearch = this.$store.state.historySearch;
    this.hotSearch = this.$store.state.hotSearch;    
    if (isNotNullORBlank(this.$route.params.hotSearchIndex)) {
      this.beginHotSearch(this.hotSearch[this.$route.params.hotSearchIndex-1]);
    }
  },
  data() {
    return {
      homeSearchIndex: true,
      searchResultIndex: false,
      hotSearch: ["全部", "3", "崩", "泳池"],
      historySearch: [
        "鹿鸣",
        "布洛妮娅",
        "原神三测",
        "漫画",
        "崩坏三",
        "鹿鸣",
        "布洛妮娅",
        "原神三测",
        "漫画",
        "崩坏三",
      ],
      searchWhat: "",
      searchResult: "",
      hotSearchIndex: true,
    };
  },
  methods: {
    closeHomeSearch() {
      // console.log(this.$route);
      // this.homeSearchIndex = false;
      this.$router.push({
        name: this.$store.state.subscribePrePath,
      });
    },
    clearSearchInput() {
      this.searchWhat = "";
      this.searchResultIndex = false;
      this.hotSearchIndex = true;
    },
    beginSearchWhat() {
      var _this = this;
      // console.log(this.$router.options.routes);
      // console.log(this.searchWhat.trim());
      // console.log(this.searchWhat.trim() == '');
      console.log(_this.$store.state.currentUser);
      this.addToHistory(this.searchWhat);
      if (!isNotNullORBlank(this.searchWhat.trim())) {
        this.$message({ type: "error", message: "搜索不能为空!" });
        return;
      } else {
        getRequest("/admin/search/all/" + _this.searchWhat).then(
          function (msg) {
            if (msg.status !== 200) {
              _this.$message({ type: "error", message: "搜索失败!" });
            } else {
              // console.log(msg.status);

              _this.searchResult = msg.data;
              console.log(_this.searchResult);
              _this.$store.dispatch("setSearchResult", msg.data);
              // _this.$forceUpdate();
              document.getElementsByClassName("el-menu-item")[0].click();
              _this.hotSearchIndex = false;
              _this.searchResultIndex = true;
              if (_this.$refs.searchResult !== undefined) {
                _this.$refs.searchResult.searchResult = msg.data;
                _this.$refs.searchResult.searchResultRelateTopic =
                  msg.data.topicList;
              }
            }
          },
          {
            function(msg) {
              _this.$message({ type: "error", message: "搜索失败!" });
            },
          }
        );
      }
    },
    beginHotSearch(hotSearch) {
      this.searchWhat = hotSearch;
      this.searchResultIndex = true;
      this.beginSearchWhat();
      // this.addToHistory(hotSearch);
    },
    goWhichResult(index) {
      // this.$router.push(this.$router.options.routes[21].children[index].path);
      document
        .getElementsByClassName("el-menu-item-searchresult")
        [index].click();
    },
    addToHistory(searchWhat) {
      if ((this.historySearch !== null) & (this.historySearch.length < 10)) {
        this.historySearch.unshift(searchWhat);
      } else {
        this.historySearch.unshift(searchWhat);
        this.historySearch.pop();
      }
      this.$store.dispatch("setHistorySearch", this.historySearch);
    },
    clearHistroySearch() {
      this.historySearch = [];
      this.$store.dispatch("setHistorySearch", this.historySearch);
    },
    deleteHistorySearch(index) {
      this.historySearch.splice(index, 1);
      this.$store.dispatch("setHistorySearch", this.historySearch);
    },
  },
};
</script>
<style>
.page-container2 {
  height: 100%;
  position: absolute;
  top: 0px;
  left: 0px;
  width: 100%;
  overflow-y: auto;
}
.search-result-wrap {
  height: 100%;
  position: absolute;
  top: 60px;
  left: 0px;
  width: 100%;
  overflow-y: auto;
  z-index: 99;
  background-color: #fff;
}

.el-menu-searchresult .el-menu {
  display: flex;
  justify-content: space-between;
}
.el-menu-searchresult .el-menu-item {
  margin-left: 5%;
  margin-right: 5%;
}
</style>