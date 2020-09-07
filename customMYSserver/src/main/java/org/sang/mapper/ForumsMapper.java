package org.sang.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.sang.bean.Forum;
import org.sang.bean.UserForum;

import java.util.List;

/**
 * Created by wzs on 2020/08/01.
 */
@Mapper
public interface ForumsMapper {

    List<UserForum> getForumsByUsername(String username);

    List<UserForum> getForumsById(Long id);

    List<Forum> getForumsAll();
}
