package org.sang.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.sang.bean.Subscribe;

import java.util.List;

/**
 * Created by wzs on 2020/08/01.
 */
@Mapper
public interface SubscribesMapper {

    List<Subscribe> getSubscribesByUsername(String username);

    List<Subscribe> getFansByUsername(String username);

    List<Subscribe> getSubscribesById(Long id);

    List<Subscribe> getFansById(Long id);

    Integer cancelSubscribeUser(Long aid, Long bid);

    Integer subscribeUser(Subscribe subscribe);

    Subscribe getNewUSubscribeById(Long aid);
}
