package com.liang.demo03;

import com.liang.po.Customer;
import com.liang.po.LinkMan;
import com.liang.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

/**
 * @author 梁思禹
 */
public class Hibernate01Test {

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

}
