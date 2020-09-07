package org.sang.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.sang.bean.*;
import org.sang.service.ArticleService;
import org.sang.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wzs on 2020/08/01.
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    @Autowired
    ArticleService articleService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public RespBean addNewArticle1(Article article) {
        int result = articleService.addNewArticle1(article);
        if (result == 1) {
            return new RespBean("success", article.getId() + "");
        } else {
            return new RespBean("error", article.getState() == 0 ? "文章保存失败!" : "文章发表失败!");
        }
    }

    @RequestMapping(value = "/new/article", method = RequestMethod.POST)
    public Map<Object,Object> addNewArticle(String article) {
        Article article1 = new Article();
        try {
            article1 = new ObjectMapper().readValue(article, Article.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Map<Object,Object> resMap = new HashMap<>();
        int result = articleService.addNewArticle(article1);
        if (result == 1) {
            Article newArticle = articleService.getNewArticleByUid(article1.getUid());
            resMap.put("newArticle",newArticle);
            resMap.put("respBean",new RespBean("success", "文章发表成功!")) ;
        } else {
            resMap.put("respBean",new RespBean("success", "文章发表失败!"));
        }
        return resMap;
    }

    @RequestMapping(value = "/new/articleComment", method = RequestMethod.POST)
    public Map<Object, Object> addNewArticleComment(String newArticleComment) {
        Map<Object, Object> resMap = new HashMap<>();
        Comment newArticleComment1 = new Comment();
        try {
            newArticleComment1 = new ObjectMapper().readValue(newArticleComment, Comment.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        int result = articleService.addNewArticleComment(newArticleComment1);
        if (result == 1) {
            Comment newArticleComment2 = articleService.getNewArticleCommentByUid(newArticleComment1.getUid());
            resMap.put("newArticleComment", newArticleComment2);
            resMap.put("respBean", new RespBean("success", "发表评论成功!"));
        } else {
            resMap.put("respBean", new RespBean("error", "发表评论失败!"));
        }
        return resMap;
    }

    @RequestMapping(value = "/new/commetToComment", method = RequestMethod.POST)
    public Map<Object, Object> addNewCommentToComment(String newCommentToComment) {
        Map<Object, Object> resMap = new HashMap<>();
        Comment newCommentToComment1 = new Comment();
        try {
            newCommentToComment1 = new ObjectMapper().readValue(newCommentToComment, Comment.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        int result = articleService.addNewArticleComment(newCommentToComment1);
        if (result == 1) {
            Comment newCommentToComment2 = articleService.getNewArticleCommentByUid(newCommentToComment1.getUid());
            resMap.put("newCommentToComment", newCommentToComment2);
            resMap.put("respBean", new RespBean("success", "发表评论成功!"));
        } else {
            resMap.put("respBean", new RespBean("error", "发表评论失败!"));
        }
        return resMap;
    }

    @RequestMapping(value = "/viewCount", method = RequestMethod.PUT)
    public RespBean updateArticleViewCount(String aid) {
        Long aid1 = Long.valueOf(aid);
        if (articleService.updateArticleViewCount(aid1) == 1) {
            return new RespBean("success", "浏览+1！");
        }
        return new RespBean("error", "浏览更新失败！");
    }

    /**
     * 上传图片
     *
     * @return 返回值为图片的地址
     */
    @RequestMapping(value = "/uploadimg", method = RequestMethod.POST)
    public RespBean uploadImg(HttpServletRequest req, MultipartFile image) {
        StringBuffer url = new StringBuffer();
        String filePath = "/img/articleimg/" + sdf.format(new Date());
        ServletContext servletContext = req.getServletContext();
        String imgFolderPath = servletContext.getRealPath(filePath);
        File imgFolder = new File(imgFolderPath);
        if (!imgFolder.exists()) {
            imgFolder.mkdirs();
        }
        System.out.println("imgFolderPath" + imgFolderPath);
        System.out.println("imgFolder" + imgFolder);
        url.append(req.getScheme())
                .append("://")
                .append(req.getServerName())
                .append(":")
                .append(req.getServerPort())
                .append(req.getContextPath())
                .append(filePath);
        String imgName = UUID.randomUUID() + "_" + image.getOriginalFilename().replaceAll(" ", "");
        try {
            IOUtils.write(image.getBytes(), new FileOutputStream(new File(imgFolder, imgName)));
            url.append("/").append(imgName);
            System.out.println(url.toString());
            return new RespBean("success", url.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new RespBean("error", "上传失败!");
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Map<String, Object> getArticleByForumname(@RequestParam(value = "forumname", defaultValue = "大别野") String forumname) {
        List<Article> articles = articleService.getArticleByForumname(forumname);
        Map<String, Object> map = new HashMap<>();
        map.put("articles", articles);
        return map;
    }

    @RequestMapping(value = "/{aid}", method = RequestMethod.GET)
    public Article getArticleById(@PathVariable Long aid) {
        return articleService.getArticleById(aid);
    }

    @RequestMapping(value = "/{aid}/descComments", method = RequestMethod.GET)
    public List<Comment> getArticleCommentsDescById(@PathVariable Long aid) {
        return articleService.getArticleCommentsDescById(aid);
    }

    @RequestMapping(value = "/{aid}/ascComments", method = RequestMethod.GET)
    public List<Comment> getArticleCommentsAscById(@PathVariable Long aid) {
        return articleService.getArticleCommentsAscById(aid);
    }

    @RequestMapping(value = "/cancelSub/{aid}/{uid}", method = RequestMethod.DELETE)
    public RespBean cancelSubscribeArticle(@PathVariable Long aid, @PathVariable Long uid) {
        if (articleService.cancelSubscribeArticle(aid, uid) == 1) {
            return new RespBean("success", "取消点赞成功!");
        } else {
            return new RespBean("error", "取消点赞失败!");
        }
    }

    @RequestMapping(value = "/sub/{aid}/{uid}", method = RequestMethod.POST)
    public Map<Object, Object> subscribeArticle(@PathVariable Long aid, @PathVariable Long uid) {
        Map<Object, Object> resMap = new HashMap<>();
        ASubscribe aSubscribe = new ASubscribe();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        aSubscribe.setAid(aid);
        aSubscribe.setUid(uid);
        aSubscribe.setaSubscribeDate(timestamp);
        Integer result = articleService.subscribeArticle(aSubscribe);
        if (result == 1) {
        ASubscribe newASubscribe = articleService.getNewASubscribeById(aSubscribe.getUid());
        resMap.put("newASubscribe", newASubscribe);
            resMap.put("respBean", new RespBean("success", "点赞成功!"));
        } else {
            resMap.put("respBean", new RespBean("success", "点赞失败!"));
        }
        return resMap;
    }

    @RequestMapping(value = "/cancelSubComment/{aid}/{cid}/{uid}", method = RequestMethod.DELETE)
    public RespBean cancelSubscribeComment(@PathVariable Long aid,@PathVariable Long cid, @PathVariable Long uid) {
        if (articleService.cancelSubscribeComment(aid,cid, uid) == 1) {
            return new RespBean("success", "取消评论点赞成功!");
        } else {
            return new RespBean("error", "取消评论点赞失败!");
        }
    }

    @RequestMapping(value = "/subComment/{aid}/{cid}/{uid}", method = RequestMethod.POST)
    public Map<Object, Object> subscribeComment(@PathVariable Long aid,@PathVariable Long cid, @PathVariable Long uid) {
        Map<Object, Object> resMap = new HashMap<>();
        CSubscribe cSubscribe = new CSubscribe();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        cSubscribe.setAid(aid);
        cSubscribe.setCid(cid);
        cSubscribe.setUid(uid);
        cSubscribe.setcSubscribeDate(timestamp);
        Integer result = articleService.subscribeComment(cSubscribe);
        if (result == 1) {
            CSubscribe newCSubscribe = articleService.getNewCSubscribeById(cSubscribe.getUid());
            resMap.put("newCSubscribe", newCSubscribe);
            resMap.put("respBean", new RespBean("success", "点赞成功!"));
        } else {
            resMap.put("respBean", new RespBean("success", "点赞失败!"));
        }
        return resMap;
    }

    @RequestMapping(value = "/cancelFav/{aid}/{uid}", method = RequestMethod.DELETE)
    public RespBean cancelFavoriteArticle(@PathVariable Long aid, @PathVariable Long uid) {
        if (articleService.cancelFavoriteArticle(aid, uid) == 1) {
            return new RespBean("success", "取消点赞成功!");
        }
        return new RespBean("error", "取消点赞失败!");
    }

    @RequestMapping(value = "/fav/{aid}/{uid}", method = RequestMethod.POST)
    public Map<Object, Object> favoriteArticle(@PathVariable Long aid, @PathVariable Long uid) {
        Map<Object, Object> resMap = new HashMap<>();
        AFavorite aFavorite = new AFavorite();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        aFavorite.setAid(aid);
        aFavorite.setUid(uid);
        aFavorite.setaFavoriteDate(timestamp);
        Integer result = articleService.favoriteArticle(aFavorite);
        if (result == 1) {
        AFavorite newAFavorite = articleService.getNewAFavoriteById(aFavorite.getUid());
        resMap.put("newAFavorite", newAFavorite);
            resMap.put("respBean", new RespBean("success", "收藏成功!"));
        } else {
            resMap.put("respBean", new RespBean("success", "收藏失败!"));
        }
        return resMap;
    }



    @RequestMapping(value = "/plate/all", method = RequestMethod.GET)
    public List<Plate> getPlatesAll() {
        return articleService.getPlatesAll();
    }

    @RequestMapping(value = "/forum/all", method = RequestMethod.GET)
    public List<Forum> getForumsAll() {
        return articleService.getForumsAll();
    }

    @RequestMapping(value = "/dustbin", method = RequestMethod.PUT)
    public RespBean updateArticleState(Long[] aids, Integer state) {
        if (articleService.updateArticleState(aids, state) == aids.length) {
            return new RespBean("success", "删除成功!");
        }
        return new RespBean("error", "删除失败!");
    }

    @RequestMapping(value = "/restore", method = RequestMethod.PUT)
    public RespBean restoreArticle(Integer articleId) {
        if (articleService.restoreArticle(articleId) == 1) {
            return new RespBean("success", "还原成功!");
        }
        return new RespBean("error", "还原失败!");
    }

    @RequestMapping("/dataStatistics")
    public Map<String, Object> dataStatistics() {
        Map<String, Object> map = new HashMap<>();
        List<String> categories = articleService.getCategories();
        List<Integer> dataStatistics = articleService.getDataStatistics();
        map.put("categories", categories);
        map.put("ds", dataStatistics);
        return map;
    }
    @RequestMapping(value = "/topic/{id}", method = RequestMethod.GET)
    public Topic getTopicById(@PathVariable Long id) {
        return articleService.getTopicById(id);
    }
    @RequestMapping(value = "/hot",method = RequestMethod.GET)
    public List<Article> getHotArticles() {
        return articleService.getHotArticles();
    }
    @RequestMapping(value = "/subUser/{id}",method = RequestMethod.GET)
    public List<Article> getArticlesOfSubUserByUid(@PathVariable Long id) {
        return articleService.getArticlesOfSubUserByUid(id);
    }
}
