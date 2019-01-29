package cn.tomxin.miaosha.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Service
public class RedisService {

    @Autowired
    JedisPool jedisPool;

    @Autowired
    RedisConfig redisConfig;

    public <T> T get(String key, Class<T> clazz){
        Jedis jedis = jedisPool.getResource();
        try{
            jedisPool.getResource();
        }finally {
            returnToPoll(jedis);
        }
        return null;
    }

    private void returnToPoll(Jedis jedis) {
        if (jedis == null){
            jedis.close();
        }
    }


    @Bean
    public JedisPool jedisPoolFactory(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(redisConfig.getPoolMaxIdle());
        poolConfig.setMaxTotal(redisConfig.getPoolMaxTotal());
        poolConfig.setMaxWaitMillis(redisConfig.getPoolMaxWait() * 1000);
        JedisPool jedisPool = new JedisPool(poolConfig, redisConfig.getHost(), redisConfig.getPort(),
                redisConfig.getTimeout()*1000, redisConfig.getPassword(), 0);
        return jedisPool;
    }
}
