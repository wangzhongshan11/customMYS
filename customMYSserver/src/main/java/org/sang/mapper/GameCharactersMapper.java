package org.sang.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.sang.bean.GameCharacter;

import java.util.List;

/**
 * Created by wzs on 2020/08/01.
 */
@Mapper
public interface GameCharactersMapper {
    List<GameCharacter> getGameCharactersByUsername(String username);

    Integer bindOtherGameChara(GameCharacter gameCharacter);

    Integer noDefaultChara();

    Integer changeDefaultChara(Integer changeIndex);
}
