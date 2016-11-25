package com.ccm.api.dao.rsvtype.redis;

import org.springframework.stereotype.Repository;
import com.ccm.api.dao.redis.RedisDaoImpl;
import com.ccm.api.dao.rsvtype.RoomQtyDao;

@Repository("roomQtyDao")
public class RoomQtyDaoImpl extends RedisDaoImpl implements RoomQtyDao {
}
