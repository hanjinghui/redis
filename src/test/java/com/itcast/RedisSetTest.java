package com.itcast;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

/**
 * redis中的set数据类型的练习
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-*.xml")
public class RedisSetTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void Setsetvalue(){

        try {
            redisTemplate.boundSetOps("aa").add("刘备");
            redisTemplate.boundSetOps("aa").add("曹操");
            redisTemplate.boundSetOps("aa").add("孙权");

            System.out.println("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("添加失败");
        }
    }


    //获取数据
    @Test
    public void getValue(){
        Set aa = redisTemplate.boundSetOps("aa").members();
        for (Object o : aa) {
            System.out.println(o);
        }
    }

    //删除一条数据
    @Test
    public void deleValueOne(){
        try {
            redisTemplate.boundSetOps("aa").remove("孙权");
            System.out.println("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("删除失败");
        }
    }

    @Test
    public void deleValue(){
        try {
            redisTemplate.delete("aa");
            System.out.println("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("删除失败");
        }

    }
}
