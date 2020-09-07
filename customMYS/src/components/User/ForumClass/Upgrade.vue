<template>
<div >
    <el-table :data="forumGradeExp" style="width:100em">
        <el-table-column prop="grade" label="等级" min-width="5" align="center">
            <template slot-scope="scope">
                <span :style="'background-color:' + scope.row.lvColor" class="lv-span-wrap"><font color="#fff">Lv.{{scope.row.lv}}</font></span>
            </template>
        </el-table-column>
        <el-table-column prop="experience" label="所需经验值" min-width="5"  align="center">
            <template slot-scope="scope">
                <font color="#ff6600">{{scope.row.exp}}</font>
            </template>
        </el-table-column>
    </el-table>
</div>    
</template>
<script>
export default {
    mounted() {
        this.forumGradeExp = this.$store.state.forumGradeExp;
    },
    data() {
        return {
            forumGradeExp:''
            // forumGradeExp:[
            //     {grade:1,experience:0},
            //     {grade:2,experience:30},
            //     {grade:3,experience:110},
            //     {grade:4,experience:290},
            //     {grade:5,experience:592},
            //     {grade:6,experience:1168},
            //     {grade:7,experience:2190},
            //     {grade:8,experience:2190},
            //     {grade:9,experience:2190},
            //     {grade:10,experience:2920},
            //     {grade:11,experience:3650},
            //     {grade:12,experience:3650},
            //     {grade:13,experience:3942},
            //     {grade:14,experience:4818},
            //     {grade:15,experience:5480},
            //     {grade:16,experience:8030}
            // ]
        }
    },
    
}
</script>
<style>
.lv-span-wrap {
    padding-left:1%;
    padding-right: 1%;
}
</style>