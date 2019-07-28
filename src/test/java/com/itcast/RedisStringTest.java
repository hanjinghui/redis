package com.itcast;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-*.xml")
public class RedisStringTest {


    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * String类型
     */
    @Test
    public void addRedis(){

        redisTemplate.opsForValue().set("num","123");//添加
        Object num = redisTemplate.opsForValue().get("num");//根据键获取值
        System.out.println(num);

        //设置查询数据的时间限制，超过10秒就返回null,设置可以的过期时间
        redisTemplate.opsForValue().set("name","hanjinghui",20, TimeUnit.SECONDS);

        Object name = redisTemplate.opsForValue().get("name");
        System.out.println(name);

        //设置在添加数据时  键相同，覆盖之前的值，，设置偏移覆盖位置
        redisTemplate.opsForValue().set("key","hello world");
        redisTemplate.opsForValue().set("key","redis");//从第6个开始覆盖
        Object key = redisTemplate.opsForValue().get("key");
        System.out.println(key);

        try {
            redisTemplate.boundValueOps("wed").set("123");
            System.out.println("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("添加失败");
        }
    }


    //添加数据并且设置数据的过期时间
    @Test
    public void StringsetValue(){
        try {
            redisTemplate.boundValueOps("kk").set("kk",10,TimeUnit.SECONDS);
            System.out.println("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("添加失败");
        }
    }

    @Test
    public void StringgetValue(){
        Object kk = redisTemplate.boundValueOps("kk").get();
        System.out.println(kk);
    }

    @Test
    public void StringDeletValue(){
        try {
            redisTemplate.delete("kk");
            System.out.println("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("删除失败");
        }
    }

}
