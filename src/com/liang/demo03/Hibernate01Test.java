package com.liang.demo03;

import com.liang.po.Customer;
import com.liang.po.LinkMan;
import com.liang.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

/**
 * 一对多测试
 * @author 梁思禹
 */
public class Hibernate01Test {

    /**
     * 未设置级联方式
     */
    @Test
    public void Test1(){

        Session session = HibernateUtils.getCurrentSession("LinkMan.cfg.xml");
        Transaction transaction = session.beginTransaction();

        Customer customer1 = new Customer();
        customer1.setCust_name("张三");
        Customer customer2 = new Customer();
        customer2.setCust_name("赵四");

        LinkMan linkMan1 = new LinkMan();
        linkMan1.setLkmName("王五");
        LinkMan linkMan2 = new LinkMan();
        linkMan2.setLkmName("任六");
        LinkMan linkMan3 = new LinkMan();
        linkMan3.setLkmName("瓦七");

        linkMan1.setCustomer(customer1);
        linkMan2.setCustomer(customer1);
        linkMan3.setCustomer(customer2);
        customer1.getLinkMen().add(linkMan1);
        customer1.getLinkMen().add(linkMan2);
        customer2.getLinkMen().add(linkMan3);

        session.save(linkMan1);
        session.save(linkMan2);
        session.save(linkMan3);
        session.save(customer1);
        session.save(customer2);

        transaction.commit();

    }

    /**
     *设置了级联方式cascade="save-update"
     */
    @Test
    public void Test2(){

        Session session = HibernateUtils.getCurrentSession("LinkMan.cfg.xml");
        Transaction transaction = session.beginTransaction();

        Customer customer = new Customer();
        customer.setCust_name("连杰");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("成龙");

        customer.getLinkMen().add(linkMan);
        linkMan.setCustomer(customer);

        session.save(customer);

        transaction.commit();

    }
    /**
     * 测试对象导航
     */
    @Test
    public void test3(){

        Session currentSession = HibernateUtils.getCurrentSession("LinkMan.cfg.xml");
        Transaction transaction = currentSession.beginTransaction();

        Customer customer = new Customer();
        customer.setCust_name("张龙");

        LinkMan linkMan1 = new LinkMan();
        linkMan1.setLkmName("赵虎");
        LinkMan linkMan2 = new LinkMan();
        linkMan2.setLkmName("包龙");

        linkMan1.setCustomer(customer);
        customer.getLinkMen().add(linkMan2);

//        储存与LinkMan1有关的所有对象--3个
        currentSession.save(linkMan1);
//        储存与customer有关的所有对象--2个
        /*currentSession.save(customer);*/

        transaction.commit();
    }

    /**
     * 级联删除
     */
    @Test
    public void test4(){
        Session session = HibernateUtils.getCurrentSession("LinkMan.cfg.xml");
        Transaction transaction = session.beginTransaction();

//        未设置只删除客户，联系人外键改为null
        /*Customer customer = session.get(Customer.class, 1L);*/
        /*session.delete(customer);*/

//        设置级联删除，Customer配置中设置cascade="delete"
        Customer customer = session.get(Customer.class, 2L);
        session.delete(customer);

        transaction.commit();

    }

    /**
     * 测试inverse
     */
    @Test
    public void test5(){
        Session session = HibernateUtils.getCurrentSession("LinkMan.cfg.xml");
        Transaction transaction = session.beginTransaction();

        Customer customer = session.get(Customer.class, 2L);
        LinkMan linkMan = session.get(LinkMan.class, 2L);

        /*customer.getLinkMen().add(linkMan);*/
        linkMan.setCustomer(customer);

        transaction.commit();
    }

}
