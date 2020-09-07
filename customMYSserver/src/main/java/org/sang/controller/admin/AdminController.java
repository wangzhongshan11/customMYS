package org.sang.controller.admin;

import org.sang.bean.Article;
import org.sang.bean.RespBean;
import org.sang.bean.Topic;
import org.sang.bean.User;
import org.sang.service.ArticleService;
import org.sang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by wzs on 2020/08/01.
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    ArticleService articleService;
    @Autowired
    UserService userService;
    @RequestMapping(value = "/search/all/{keywords}",method = RequestMethod.GET)
    public Map<Object,Object> searchAllByKeywords(@PathVariable String keywords) {
        Map<Object,Object> respMap = new HashMap<>();
        List<Article> articleList = articleService.searchArticlesByKeywords(keywords);
        List<User> userList = userService.searchUsersByKeywords(keywords);
        List<Topic> topicList = articleService.searchTopicsByKeywords(keywords);
        respMap.put("articleList",articleList);
        respMap.put("userList",userList);
        respMap.put("topicList",topicList);
        return respMap;
    }
//    @RequestMapping(value = "/article/all", method = RequestMethod.GET)
//    public Map<String, Object> getArticleByStateByAdmin(@RequestParam(value = "forumname", defaultValue = "大别野") String forumname, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "count", defaultValue = "6") Integer count, String keywords) {
//        List<Article> articles = articleService.getArticleByState(forumname,-2, page, count, keywords);
//        Map<String, Object> map = new HashMap<>();
//        map.put("articles", articles);
//        map.put("totalCount", articleService.getArticleCountByState(1, null, keywords));
//        return map;
//    }
@RequestMapping(value = "/article/all", method = RequestMethod.GET)
public Map<String, Object> getArticleByForumnameByAdmin(@RequestParam(value = "forumname", defaultValue = "大别野") String forumname) {
    List<Article> articles = articleService.getArticleByForumname(forumname);
    Map<String, Object> map = new HashMap<>();
    map.put("articles", articles);
//    map.put("totalCount", articleService.getArticleCountByState(1, null, keywords));
    return map;
}
    @RequestMapping(value = "/article/all/plate", method = RequestMethod.GET)
    public Map<String, Object> getArticleByPlatenameByAdmin(@RequestParam(value = "platename", defaultValue = "日常") String platename) {
        List<Article> articles = articleService.getArticleByPlatename(platename);
        Map<String, Object> map = new HashMap<>();
        map.put("articles", articles);
//    map.put("totalCount", articleService.getArticleCountByState(1, null, keywords));
        return map;
    }
    @RequestMapping(value = "/article/all/topic", method = RequestMethod.GET)
    public Map<String, Object> getArticleByTopicnameByAdmin(@RequestParam(value = "topicname", defaultValue = "全部") String topicname) {
        List<Article> articles = articleService.getArticleByTopicname(topicname);
        Map<String, Object> map = new HashMap<>();
        map.put("articles", articles);
//    map.put("totalCount", articleService.getArticleCountByState(1, null, keywords));
        return map;
    }
    @RequestMapping(value = "/all/topic", method = RequestMethod.GET)
    public List<Topic> getTopicsByPlatename(@RequestParam(value = "platename", defaultValue = "日常") String platename) {
        List<Topic> topicList = articleService.getTopicsByPlatename(platename);
        return topicList;
    }

    @RequestMapping(value = "/article/dustbin", method = RequestMethod.PUT)
    public RespBean updateArticleState(Long[] aids, Integer state) {
        if (articleService.updateArticleState(aids, state) == aids.length) {
            return new RespBean("success", "删除成功!");
        }
        return new RespBean("error", "删除失败!");
    }
}
