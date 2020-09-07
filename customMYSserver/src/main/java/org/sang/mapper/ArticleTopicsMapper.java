package org.sang.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sang.bean.ArticleTopic;
import org.sang.bean.Topic;
import java.util.List;


/**
 * Created by wzs on 2020/08/01.
 */
@Mapper
public interface ArticleTopicsMapper {
    List<ArticleTopic> getArticleTopicsByAid(Long aid);
    Integer saveArticleTopics(List<ArticleTopic> topicList, int aid);

    List<Topic> searchTopicsByKeywords(@Param(value = "keywords") String keywords);

    Topic getTopicById(Long id);
}
