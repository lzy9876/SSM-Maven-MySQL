package cn.lzy.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisUtil
 * @Author:Liziy
 * @Date 2020/5/30 20:19
 * @Description: Redis工具类
 **/
@Component
public class RedisUtil {
    @Autowired
    RedisTemplate redisTemplate;

    /**
     * @Author liziyang
     * @Description 获取缓存
     * @Date 20:21 2020/5/30
     * @Param [key] 键
     * @return java.lang.Object 值
     **/
    public Object get(String key){
        return key==null?null:redisTemplate.opsForValue().get(key);
    }

    /**
     * @Author liziyang
     * @Description 添加缓存
     * @Date 20:22 2020/5/30
     * @Param [key, value]
     * @return boolean false 失败 / true 成功
     **/
    public boolean set(String key,Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * @Author liziyang
     * @Description 添加缓存并设置过期时间
     * @Date 13:33 2020/5/31
     * @Param   [key, 键
     *          value, 值
     *          time 时间(秒) time要大于0 如果time小于等于0 将设置无限期]
     * @return boolean true成功 false 失败
     **/
    public boolean set(String key,Object value,long time){
        try {
            if(time>0){
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            }else{
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断key是否存在
     * @param key 键
     * @return true 存在 false不存在
     */
    public boolean hasKey(String key){
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key 获取过期时间
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public long getExpire(String key){
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }

    /**
     * @Author liziyang
     * @Description 根据key1,key2 模糊查询key
     * @Date 17:23 2020/6/4
     * @Param [key1, key2]
     * @return java.util.Set
     **/
    public Set getKeys(String key1, String key2){
       return redisTemplate.keys(key1 + key2);
    }

    /**
     * 删除缓存
     * @param key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public void del(String ... key){
        if(key!=null&&key.length>0){
            if(key.length==1){
                redisTemplate.delete(key[0]);
            }else{
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }
}
