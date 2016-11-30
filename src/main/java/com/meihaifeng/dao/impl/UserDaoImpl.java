package com.meihaifeng.dao.impl;


import com.meihaifeng.dao.UserDao;
import com.meihaifeng.entity.AbstractBaseRedisDao;
import com.meihaifeng.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 浙江卓锐科技股份有限公司
 *
 * @author meihf
 * @date 2016/8/18
 * @description
 */
@Repository
public class UserDaoImpl extends AbstractBaseRedisDao<String,User> implements UserDao {

    @Autowired
    protected RedisTemplate<Serializable, Serializable> redisTemplate;

    public boolean saveUser(final User user) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection)
                    throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] key  = serializer.serialize(user.getId());
                byte[] name = serializer.serialize(user.getName());
                return connection.setNX(key, name);
            }
        });
        return result;
    }

    /**
     * 批量新增 使用pipeline方式
     *<br>------------------------------<br>
     *@param list
     *@return
     */
    public boolean saveUser(final List<User> list) {
        Assert.notEmpty(list);
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection)
                    throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                for (User user : list) {
                    byte[] key  = serializer.serialize(user.getId());
                    byte[] name = serializer.serialize(user.getName());
                    connection.setNX(key, name);
                }
                return true;
            }
        }, false, true);
        return result;
    }

    /**
     * 删除
     * <br>------------------------------<br>
     * @param key
     */
    public void delete(Serializable key) {
        List<Serializable> list = new ArrayList<Serializable>();
        list.add(key);
        delete(list);
    }

    /**
     * 删除多个
     * <br>------------------------------<br>
     * @param keys
     */
    public void delete(List<Serializable> keys) {
        redisTemplate.delete(keys);
    }

    /**
     * 修改
     * <br>------------------------------<br>
     * @param user
     * @return
     */
    public boolean update(final User user) {
        String key = user.getId();
        if (getUser(key) == null) {
            throw new NullPointerException("数据行不存在, key = " + key);
        }
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection)
                    throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] key  = serializer.serialize(user.getId());
                byte[] name = serializer.serialize(user.getName());
                connection.set(key, name);
                return true;
            }
        });
        return result;
    }

    /**
     * 通过key获取
     * <br>------------------------------<br>
     * @param keyId
     * @return
     */
    public User getUser(final String keyId) {
        User result = redisTemplate.execute(new RedisCallback<User>() {
            public User doInRedis(RedisConnection connection)
                    throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] key = serializer.serialize(keyId);
                byte[] value = connection.get(key);
                if (value == null) {
                    return null;
                }
                String name = serializer.deserialize(value);
                return new User(keyId, name, null);
            }
        });
        return result;
    }

}
