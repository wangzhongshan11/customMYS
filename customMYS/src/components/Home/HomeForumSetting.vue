<template>
  <div class="set-channel-page">
    <transition>
      <!-- <div class="page-container set-channel-page-display" style="display:none;"> -->
      <div v-show="!setChannelIndex" class="page-container1">
        <el-row
          style="padding-bottom:30px;padding-top:30px;font-size:20px;background-color:#dddddd;"
        >
          <el-col :span="2" :offset="1">
            <div class="flex-div-start-wrap" style="height:30px;cursor:pointer;">
              <i class="el-icon-arrow-left" @click="closeSetChannel"></i>
            </div>
          </el-col>
          <el-col :span="18" :offset="0">
            <div class="flex-div-center-wrap" style="height:30px;">
              <span>首页频道设置</span>
            </div>
          </el-col>
          <el-col :span="2" :offset="0">
            <div class="flex-div-end-wrap" style="height:30px;">
              <span style="font-size:15px;cursor:pointer;">确定</span>
            </div>
          </el-col>
        </el-row>
        <div
          style="height:25px;padding-left:4.5%;padding-right:4.5%;background-color:#dddddd;padding-top:15px;padding-bottom:15px;"
        >
          <span style="color:#000000;font-weight:bold;font-size:17px;float:left;">我的频道</span>
          <span style="float:right;font-size:15px;">长按拖动排序</span>
        </div>
        <div
          class="forums-label-drag"
          v-show="item.myChannel"
          v-for="(item,index) in forumsList"
          :key="index"
          style="background-color:#fff;"
        >
          <el-row style="padding-top:10px;padding-bottom:10px;">
            <el-col :span="4" :offset="0">
              <div style="height:80px;" class="flex-div-center-wrap">
                <img src="../../img/大别野.jpg" alt="wzs" style="height:60px;width:60px;" />
              </div>
            </el-col>
            <el-col :span="17" :offset="0">
              <div class="flex-div-start-wrap" style="height:80px;">
                <span>{{item.forumname}}</span>
              </div>
            </el-col>
            <el-col :span="1" :offset="0">
              <div class="flex-div-start-wrap" style="height:80px;">
                <i
                  class="el-icon-remove-outline"
                  style="font-size:20px;cursor:pointer;"
                  v-show="!(item.forumname == '大别野')"
                  @mousedown="moveOutMychannel(index)"
                ></i>
              </div>
            </el-col>
            <el-col :span="1" :offset="0">
              <div class="flex-div-end-wrap" style="height:80px;">
                <i class="el-icon-s-operation" style="font-size:20px;cursor:pointer;"></i>
                
              </div>
            </el-col>
          </el-row>
          <hr
            style="background-color:#dddddd;height:0.5px;border:none;width:90%;margin-bottom:0px;"
          />
        </div>

        <div
          class="tjpd-wrap"
          style="height:25px;padding-left:4.5%;padding-right:4.5%;background-color:#dddddd;padding-top:15px;padding-bottom:15px;"
        >
          <span style="color:#000000;font-weight:bold;font-size:17px;float:left;">推荐频道</span>
        </div>
        <div
          class="forums-label-tuijian"
          v-show="!item.myChannel"
          v-for="(item,index1) in forumsListTuijian"
          :key="index1+'tuijian'"
          style="background-color:#fff;"
        >
          <el-row style="padding-top:10px;padding-bottom:10px;">
            <el-col :span="4" :offset="0">
              <div style="height:80px;" class="flex-div-center-wrap">
                <img src="../../img/大别野.jpg" alt="wzs" style="height:60px;width:60px;" />
              </div>
            </el-col>
            <el-col :span="17" :offset="0">
              <div class="flex-div-start-wrap" style="height:80px;">
                <span>{{item.forumname}}</span>
              </div>
            </el-col>
            <el-col :span="1" :offset="0">
              <div class="flex-div-start-wrap" style="height:80px;">
                <i class="el-icon-remove-outline" style="font-size:20px;" v-show="false"></i>
              </div>
            </el-col>
            <el-col :span="1" :offset="0">
              <div class="flex-div-end-wrap" style="height:80px;">
                <i
                  class="el-icon-circle-plus-outline"
                  style="font-size:20px;cursor:pointer;"
                  @mousedown="addMychannel(item.forumname,index1)"
                ></i>
              </div>
            </el-col>
          </el-row>
          <hr
            style="background-color:#dddddd;height:0.5px;border:none;width:90%;margin-bottom:0px;"
          />
        </div>
      </div>
    </transition>
  </div>
</template>
<script>
export default {
  mounted() {
    this.elsHTML = document.getElementsByClassName("forums-label-drag");
    this.elsHTML1 = document.getElementsByClassName("forums-label-tuijian");
    this.els = new Array(Array.from(this.elsHTML).length);
    Array.from(this.elsHTML).forEach((item, index) => {
      this.els[index] = item;
    });
    this.elsTuijian = new Array(Array.from(this.elsHTML1).length);
    Array.from(this.elsHTML1).forEach((item, index) => {
      this.elsTuijian[index] = item;
    });
    this.els.forEach((item, index) => {
      this.els[index].addEventListener("mousedown", this.touchStart);
    });
  },
  methods: {
    closeSetChannel() {
      this.setChannelIndex = true;
      //   this.$refs.homeDabieye.setChannelIndex = true;
      this.$emit("closeSetChannel", true);
    },
    addMychannel(forumname, index) {
      var e = window.event || arguments.callee.caller.arguments[0];
      e.stopPropagation();
      var _this = this;
      var elsTop = new Array(_this.els.length);
      var scrollTop = document.getElementsByClassName("page-container")[0]
        .scrollTop;
      _this.els.forEach((item, ind) => {
        elsTop[ind] = item.getBoundingClientRect().top + scrollTop;
      });
      var currElsIndex;

      Array.from(this.elsHTML1)[index].addEventListener("mouseup", mouseup);
      function mouseup(e) {
        console.log("mouseup");
        _this.forumsListTuijian[index].myChannel = true;
        Array.from(_this.elsHTML).forEach((item, index) => {
          console.log(Array.from(_this.elsHTML));
          if (Array.from(_this.elsHTML)[index].innerText == forumname) {
            _this.els.push(Array.from(_this.elsHTML)[index]);
            _this.els[_this.els.length - 1].style.top =
              Math.max(elsTop) +
              _this.els[0].getBoundingClientRect().bottom -
              _this.els[0].getBoundingClientRect().top;
            elsTop.push(
              Math.max(elsTop) +
                _this.els[0].getBoundingClientRect().bottom -
                _this.els[0].getBoundingClientRect().top
            );
          }
        });
      }
    },
    moveOutMychannel(index) {
      console.log(this.els);
      var e = window.event || arguments.callee.caller.arguments[0];
      e.stopPropagation();
      var _this = this;
      var elsTop = new Array(_this.els.length);
      var scrollTop = document.getElementsByClassName("page-container")[0]
        .scrollTop;
      _this.els.forEach((item, ind) => {
        elsTop[ind] = item.getBoundingClientRect().top + scrollTop;
      });
      var currElsIndex;
      console.log(_this.forumsListCopy);
      console.log(_this.forumsList);
      //   _this.forumsList.forEach((item,index) => {
      //       _this.forumsList[index] = _this.forumsListCopy[index];
      //   })
      _this.forumsListCopy.forEach((item, ind) => {
        console.log("ind:" + ind + "index:" + index);
        if (
          _this.forumsList[index].forumname ==
          _this.forumsListCopy[ind].forumname
        ) {
          currElsIndex = ind;
        }
      });
      console.log("moveOutMychannel:" + index);
      console.log("currElsIndex:" + currElsIndex);
      Array.from(this.elsHTML)[index].addEventListener("mouseup", mouseup);
      function mouseup(e) {
        console.log("mouseup");
        console.log(_this.els);
        // Array.from(_this.elsHTML)[index].style.display = "none";
        // Array.from(_this.elsHTML1)[index].style.display = "block";
        _this.els.forEach((item, ind) => {
          if (ind > currElsIndex) {
            console.log(
              "ind:" + ind + "," + _this.forumsListCopy[ind].forumname
            );
            console.log("elsTop[ind - 1]:" + elsTop[ind - 1]);
            console.log(_this.els[ind]);
            _this.els[ind].style.top = elsTop[ind - 1] + "px";
          }
        });
        // _this.els[currElsIndex].style.display = 'none';
        _this.forumsList[index].myChannel = false;
        _this.els.splice(currElsIndex, 1);
        _this.forumsListCopy.splice(currElsIndex, 1);
        _this.forumsListTuijian.push(_this.forumsList[index]);
        // _this.forumsListTuijian[_this.forumsListTuijian.length-1].m
        // _this.forumsList.splice(index, 1);
        console.log(_this.forumsListTuijian);
        if (_this.els[0].style.position == "absolute") {
          document.getElementsByClassName("tjpd-wrap")[0].style.paddingTop =
            _this.els.length *
              (_this.els[0].getBoundingClientRect().bottom -
                _this.els[0].getBoundingClientRect().top) +
            15 +
            "px";
        }
        Array.from(_this.elsHTML)[index].removeEventListener(
          "mouseup",
          mouseup
        );
      }
    },

    touchStart(e) {
      var _this = this;
      console.log("touchStart");
      _this.els.forEach((item, index) => {
        if (item.getBoundingClientRect().top == 0) {
          _this.els.splice(index, 1);
          _this.forumsListCopy.splice(index, 1);
        }
      });

      var scrollTop = document.getElementsByClassName("page-container")[0]
        .scrollTop;
      var elsTop = new Array(_this.els.length);
      var tjpdTop = document
        .getElementsByClassName("tjpd-wrap")[0]
        .getBoundingClientRect().top;
      console.log(_this.els);

      Array.from(_this.els).forEach((item, index) => {
        elsTop[index] = item.getBoundingClientRect().top + scrollTop;
      });
      elsTop = elsTop.sort();
      console.log(elsTop);
      Array.from(_this.els).forEach((item, index) => {
        _this.els[index].style.position = "absolute";
        _this.els[index].style.width = "100%";
        _this.els[index].style.zIndex = "90";
        _this.els[index].style.top = elsTop[index] + "px";
      });
      document.getElementsByClassName("tjpd-wrap")[0].style.paddingTop =
        // elsTop[elsTop.length - 1] + elsTop[1] - 2 * elsTop[0] + 15 + "px";
        elsTop.length *
          (_this.els[0].getBoundingClientRect().bottom -
            _this.els[0].getBoundingClientRect().top) +
        15 +
        "px";
      var touchStartEY = e.clientY + scrollTop;
      var currEForum;
      elsTop.forEach((item, index) => {
        console.log("index:" + index + "," + elsTop[index]);
        if (
          index < elsTop.length - 1 &&
          touchStartEY > elsTop[index] &&
          touchStartEY < elsTop[index + 1]
        ) {
          currEForum = index;
        } else if (index == elsTop.length - 1 && touchStartEY > elsTop[index]) {
          currEForum = index;
        }
      });
      console.log(touchStartEY);
      console.log(currEForum);
      var el = _this.els[currEForum];
      var currEForum1 = currEForum;
      var currEForumTop =
        _this.els[currEForum].getBoundingClientRect().top + scrollTop;
      var currEForumTop1 =
        _this.els[currEForum].getBoundingClientRect().top + scrollTop;
      _this.els[currEForum].style.zIndex = "99";
      var timer = setTimeout(function () {
        console.log("longPress");
        e.preventDefault();
      }, 1000);

      console.log(elsTop);
      el.addEventListener("mouseup", touchEnd);
      el.addEventListener("mousemove", touchMove);
      function touchEnd(e) {
        console.log("touchEnd");
        _this.els[currEForum].style.top = currEForumTop1 + "px";
        // Array.from(_this.els).forEach((item, index) => {
        //   _this.els[index].style.position = "";
        //   _this.els[index].style.width = "100%";
        // });
        // document.getElementsByClassName("tjpd-wrap")[0].style.paddingTop =
        //   15 + "px";
        clearTimeout(timer);
        el.removeEventListener("mouseup", touchEnd);
        el.removeEventListener("mousemove", touchMove);
      }
      function touchMove(e) {
        if (Math.abs(e.clientY - touchStartEY) > 5) {
          clearTimeout(timer);
        }
        var currEY = e.clientY;
        console.log("currEForumTop:" + currEForumTop);
        console.log("currEForumTop1:" + currEForumTop1);
        console.log("e.clientY:" + e.clientY + scrollY);
        _this.els[currEForum].style.top =
          e.clientY - touchStartEY + currEForumTop + scrollTop + "px";
        Array.from(_this.els).forEach((item, index) => {
          if (index == currEForum) {
          } else if (
            index > currEForum &&
            elsTop[index] <
              _this.els[currEForum].getBoundingClientRect().top + scrollTop
          ) {
            _this.els[index].style.top = elsTop[index - 1] + "px";
            var forumTemp = _this.forumsListCopy[index];
            _this.forumsListCopy[index] = _this.forumsListCopy[currEForum];
            _this.forumsListCopy[currEForum] = forumTemp;
            var elTemp = _this.els[index];
            _this.els[index] = _this.els[currEForum];
            _this.els[currEForum] = elTemp;
            currEForum = index;
            currEForumTop1 = elsTop[index];
            console.log(_this.els);
            console.log(_this.forumsList);
          } else if (
            index < currEForum &&
            elsTop[index] >
              _this.els[currEForum].getBoundingClientRect().top + scrollTop
          ) {
            _this.els[index].style.top = elsTop[index + 1] + "px";
            var forumTemp = _this.forumsListCopy[index];
            _this.forumsListCopy[index] = _this.forumsListCopy[currEForum];
            _this.forumsListCopy[currEForum] = forumTemp;
            var elTemp = _this.els[index];
            _this.els[index] = _this.els[currEForum];
            _this.els[currEForum] = elTemp;
            currEForum = index;
            currEForumTop1 = elsTop[index];
          }
        });
        console.log(_this.els);
      }
    },
  },
  data() {
    return {
      forumsListCopy: [
        {
          id: "1",
          forumname: "大别野",
          forumface: "../../img/大别野.jpg",
          sortIndex: "0",
          myChannel: true,
        },
        {
          id: "2",
          forumname: "崩坏3",
          forumface: "../../img/崩坏三.jpg",
          sortIndex: "1",
          myChannel: true,
        },
        {
          id: "3",
          forumname: "崩坏学园2",
          forumface: "../../img/崩坏学园2.jpg",
          sortIndex: "2",
          myChannel: true,
        },
        {
          id: "4",
          forumname: "原神",
          forumface: "../../img/原神.jpg",
          sortIndex: "3",
          myChannel: true,
        },
        {
          id: "5",
          forumname: "未定事件簿",
          forumface: "../../img/未定事件簿.jpg",
          sortIndex: "4",
          myChannel: true,
        },
      ],
      forumsList: [
        {
          id: "1",
          forumname: "大别野",
          forumface: "../../img/大别野.jpg",
          sortIndex: "0",
          myChannel: true,
        },
        {
          id: "2",
          forumname: "崩坏3",
          forumface: "../../img/崩坏三.jpg",
          sortIndex: "1",
          myChannel: true,
        },
        {
          id: "3",
          forumname: "崩坏学园2",
          forumface: "../../img/崩坏学园2.jpg",
          sortIndex: "2",
          myChannel: true,
        },
        {
          id: "4",
          forumname: "原神",
          forumface: "../../img/原神.jpg",
          sortIndex: "3",
          myChannel: true,
        },
        {
          id: "5",
          forumname: "未定事件簿",
          forumface: "../../img/未定事件簿.jpg",
          sortIndex: "4",
          myChannel: true,
        },
      ],
      forumsListTuijian: [],
      setChannelIndex: true,
      elsHTML: [],
      elsHTML1: [],
      els: "",
    };
  },
};
</script>
<style>
</style>