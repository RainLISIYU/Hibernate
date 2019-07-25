package com.liang.demo02;


import com.liang.po.Customer;
import com.liang.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.io.Serializable;

/**
 * 主键创建策略
 * increment,identity,sequence,native,uuid,foreign,assigned
 * 持久化对象的三种状态
 * 瞬时态、持久态、脱管态
 * @author 梁思禹
 */
public class Hibernate02Test {

    @Test
    public void test1(){
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        //瞬时态
        Customer customer = new Customer();
        customer.setCust_name("赵虎");
        //持久态
        Serializable id = session.save(customer);
        System.out.println(session.load(Customer.class, id));
        transaction.commit();
        session.close();
        //托管态
        System.out.println(customer.getCust_name());
    }
//    测试increment的非线程安全
//    @Test
//    public void test2(){
//        Session session = HibernateUtils.openSession();
//        Transaction transaction = session.beginTransaction();
//        Customer customer = new Customer();
//        customer.setCust_name("赵虎");
//        session.save(customer);
//        transaction.commit();
//        session.close();
//    }
}
