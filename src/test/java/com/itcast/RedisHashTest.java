package com.itcast;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-*.xml")
public class RedisHashTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void add(){
        try {
            redisTemplate.boundHashOps("nameMap").put("行者","武松");
            redisTemplate.boundHashOps("nameMap").put("及时雨","宋江");
            redisTemplate.boundHashOps("nameMap").put("豹子头","林冲");
            System.out.println("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("添加失败");
        }
    }

    @Test
    public void getValue(){
        Object o = redisTemplate.boundHashOps("nameMap").get("及时雨");
        System.out.println(o);
    }

    //得到所有的key
    @Test
    public void getKeys(){
        Set nameMap = redisTemplate.boundHashOps("nameMap").keys();
        System.out.println(nameMap);
    }

    //得到所有的value
    @Test
    public void getValues(){
        List nameMap = redisTemplate.boundHashOps("nameMap").values();
        System.out.println(nameMap);
    }

    //根据键删除值
    @Test
    public void deleValue(){
        try {
            redisTemplate.boundHashOps("nameMap").delete("及时雨");
            System.out.println("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("删除失败");
            System.out.println("我已经修改");
        }
    }
}
