package com.liang.demo01;

import com.liang.po.Customer;
import com.liang.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

/**
 * hibernate对数据库的基本操作
 * @author 梁思禹
 */
public class HibernateDemo1 {

    /**
     * 添加
     */
    @Test
    public void test1(){
        /*加载hibernate的核心配置文件*/
        Configuration configuration = new Configuration().configure("Customer.cfg.xml");
        /*创建工厂*/
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        /*获取session 类似于Connection的连接*/
        Session session = sessionFactory.openSession();

        /*手动开启事务*/
        Transaction transaction = session.beginTransaction();

        /*编写代码*/
        Customer customer = new Customer();
        customer.setCust_name("李四");

        session.save(customer);

        /*事务提交*/
        transaction.commit();

        /*资源释放*/
        session.close();
    }

    /**
     * 查找
     */
    @Test
    public void test2(){
        Session session = HibernateUtils.openSession();

        Transaction transaction = session.beginTransaction();

//        get方法
        Customer customer = session.get(Customer.class, 1L);
        System.out.println(customer);


////       load方法
//       Customer customer = session.load(Customer.class, 1L);
//       System.out.println(customer);

        transaction.commit();

        session.close();
    }

    /**
     * 更新
     */
    @Test
    public void test3(){
        Session session = HibernateUtils.openSession();

        Transaction transaction = session.beginTransaction();

        /*Customer customer = new Customer();
        customer.setCust_id(1L);
        customer.setCust_mobile("3545842154");
        session.update(customer);*/

        Customer customer = session.get(Customer.class, 1L);
        customer.setCust_level("154684484546");
        session.update(customer);
        customer = session.get(Customer.class, 1L);
        System.out.println(customer);
        transaction.commit();
        session.close();
    }

    /**
     * 删除
     */
    @Test
    public void test4(){

        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();

//        直接创建对象删除
//        Customer customer = new Customer();
//        customer.setCust_id(4L);
//        先查询后删除
        Customer customer = session.get(Customer.class, 5L);
        session.delete(customer);

        transaction.commit();
        session.close();

    }

    /**
     * saveOrUpdate
     */
    @Test
    public void test5(){
        Session session = HibernateUtils.openSession("Customer.cfg.xml");
        Transaction transaction = session.beginTransaction();

        Customer customer = new Customer();
        /*customer.setCust_id(6L);*/
        customer.setCust_name("张三丰");

        session.saveOrUpdate(customer);
        transaction.commit();
        session.close();

    }

}
