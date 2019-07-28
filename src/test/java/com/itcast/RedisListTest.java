package com.itcast;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * List数据类型
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-*.xml")
public class RedisListTest {

    @Autowired
    private RedisTemplate redisTemplate;

    //添加数据,从左边进栈，先进的后出
    @Test
    public void addValue(){
        try {
            redisTemplate.boundListOps("nameList").leftPush("张飞");
            redisTemplate.boundListOps("nameList").leftPush("刘备");
            redisTemplate.boundListOps("nameList").leftPush("关羽");

            System.out.println("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("添加失败");
        }
    }


    //获取数据
    @Test
    public void getValue(){
        List nameList = redisTemplate.boundListOps("nameList").range(0, 10);
        for (Object o : nameList) {
            System.out.println(o);
        }
    }


    //添加i数据从有边进栈，先进的先出

    @Test
    public void addLeftValue(){
        try {
            redisTemplate.boundListOps("nameList1").rightPush("吕布");
            redisTemplate.boundListOps("nameList1").rightPush("孙权");
            redisTemplate.boundListOps("nameList1").rightPush("赵云");
            System.out.println("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("添加失败");
        }

    }

    @Test
    public void getValueLeft(){
        List nameList1 = redisTemplate.boundListOps("nameList1").range(0, 10);
        for (Object o : nameList1) {
            System.out.println(o);
        }
    }

    @Test
    public void deleValue(){
        try {
            redisTemplate.boundListOps("nameList1").remove(1,"吕布");
            System.out.println("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("删除失败");
        }
    }

    @Test
    public void deleValueAll(){
        try {
            redisTemplate.delete("nameList1");
            System.out.println("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("删除失败");
        }
    }

    //根据下标查询元素
    @Test
    public void findValue(){
        Object nameList1 = redisTemplate.boundListOps("nameList1").index(1);
        System.out.println(nameList1);

    }
}
