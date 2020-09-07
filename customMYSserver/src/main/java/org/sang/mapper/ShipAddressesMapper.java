package org.sang.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.sang.bean.ShipAddress;

import java.util.List;

/**
 * Created by wzs on 2020/08/01.
 */
@Mapper
public interface ShipAddressesMapper {
    List<ShipAddress> getShipAddressesByUsername(String username);

    Integer newShipAddress(ShipAddress shipAddress);

    Integer changeDefaultAddress(Integer changeIndex);
    Integer noDefaultAddress();

    Integer editChosenShipAddress(ShipAddress shipAddress);

    Integer deleteChosenShipAddress(Integer deleteIndex);
}
