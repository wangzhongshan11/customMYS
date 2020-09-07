 
# customMYS

#### 介绍
customMYS大致按照米游社app界面样式，现在实现的功能有用户、社区、文章、板块、话题、点赞、关注、搜索，采用Vue+SpringBoot开发。 

>项目还有很多很多的功能没有完善，希望以后能够再完善一下！

项目效果图在doc文件夹中

## 后端技术栈

后端主要采用了：  

1.SpringBoot  
2.SpringSecurity  
3.MyBatis  
4.部分接口遵循Restful风格  
5.MySQL  

## 前端技术栈

前端主要采用了：  

1.Vue  
2.vue-router  
3.vuex
4.axios  
5.ElementUI、swiper  
6.vue-echarts  
7.mavon-editor  


**前端代码编写和调试仅局限在chrome浏览器，其他浏览器兼容问题还未考虑=。=，希望以后能够兼容到其他浏览器！**


# 快速运行  

1.克隆本项目到本地  
2.找到customMYSserver项目中resources目录下的customMYS.sql文件，在MySQL数据库中执行  
3.根据自己本地情况修改数据库配置，数据库配置在SpringBoot项目的application.properties中  
4.在IntelliJ IDEA中运行customMYSserver项目  

**OK服务端启动成功，此时直接在地址栏输入```http://localhost:8081/index.html```即可访问项目，五、六步继续开发。**  

5.进入到customMYS目录中，在命令行依次输入如下命令：  

```
# 安装依赖
npm install

# 在 localhost:8080 启动项目
npm run dev
```  

由于我在customMYS项目中已经配置了端口转发，将数据转发到SpringBoot上，因此项目启动之后，在浏览器中输入```http://localhost:8080```就可以访问前端项目了，所有的请求通过端口转发将数据传到SpringBoot中（此时不要关闭SpringBoot项目）。  

6.继续开发，开发完成后，当项目要上线时，依然进入到customMYS目录，然后执行如下命令：  

```
npm run build
```  

该命令执行成功之后，customMYS目录下生成一个dist文件夹，将该文件夹中的两个文件static和index.html拷贝到SpringBoot项目中resources/static/目录下，然后就可以像第4步那样直接访问了。  


## 其他

QQ号: 1594202958
邮箱: sa007056@mail.ustc.edu.cn

2020.08.10 -- 2020.09.07
I did it!
