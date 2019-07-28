package com.itecst;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisDemoTest {

    @Autowired
    private static RedisTemplate redisTemplate;

    public static void main(String[] args) {

        //String类型
        redisTemplate.opsForValue().set("xingming","hanjinghui");
    }

}
