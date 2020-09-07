<template>
<div>
      <el-header style="background-color:white;">
        <el-row style="margin-top:10px;width:100%;">
          <el-col :span="1">
            <div>
              <i class="el-icon-arrow-left"></i>
            </div>
          </el-col>
          <el-col :span="6" :offset="8">
            <div>
              <span>修改头像</span>
            </div>
          </el-col>
          <el-col :span="4" :offset="5">
            <div>
              <span style="color:#3366ff;">保存</span>
            </div>
          </el-col>
        </el-row>
      </el-header>
      <el-main>
          <hr>
                  <div>
          <img class="user-avatar" src="../../../img/希儿3.jpg" alt="wzs" />
        </div>
        <div>
            <img class="user-avatar" src="../../../img/希儿3.jpg" alt="wzs" />
            <img class="user-avatar" src="../../../img/崩坏三.jpg" alt="wzs" />
            <img class="user-avatar" src="../../../img/原神.jpg" alt="wzs" />
            <!-- 作废了 -->
        </div>
      </el-main>
</div>
    
</template>
<script>
export default {
    
}
</script>
<style>

</style>