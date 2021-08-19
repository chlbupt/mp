package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.entity.Product;
import com.atguigu.mybatisplus.entity.User;
import com.atguigu.mybatisplus.mapper.ProductMapper;
import com.atguigu.mybatisplus.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class InterceptorTests {
    @Resource
    private UserMapper userMapper;

    @Resource
    private ProductMapper productMapper;

    @Test
    public void testSelectPage(){
        Page<User> userPage = new Page<>(2, 5);
        userMapper.selectPage(userPage, null);
        List<User> users = userPage.getRecords();
        users.forEach(System.out::println);

        long total = userPage.getTotal();
        System.out.println("总共" + total + "页");

        boolean bn = userPage.hasNext();
        System.out.println("下一页?" + bn);

        boolean bp = userPage.hasPrevious();
        System.out.println("上一页?" + bp);
    }

    @Test
    public void testSelectPageByAge(){
        Page<User> pageParam = new Page<>(3, 5);
        userMapper.selectPageByAge(pageParam, 50);

        List<User> users = pageParam.getRecords();
        users.forEach(System.out::println);
    }

    @Test
    public void testConcurrentUpdate(){
        // 小李取价格
        Product p1 = productMapper.selectById(1L);
        // 小王取价格
        Product p2 = productMapper.selectById(1L);

        // 小李 + 50
        p1.setPrice(p1.getPrice() + 50);
        int result1 = productMapper.updateById(p1);
        System.out.println("小李修改结果：" + result1);
        // 小王 - 30
        p2.setPrice(p2.getPrice() - 30);
        int result2 = productMapper.updateById(p2);
        System.out.println("小王修改结果：" + result2);
        // 小王重试
        if(result2 == 0){
            p2 = productMapper.selectById(1L);
            p2.setPrice(p2.getPrice() - 30);
            result2 = productMapper.updateById(p2);
        }
        System.out.println("小王重试修改的结果：" + result2);

        // 老板看价格
        final Product p3 = productMapper.selectById(1L);
        System.out.println("最后的结果：" + p3.getPrice());
    }
}
