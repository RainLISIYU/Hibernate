package com.liang.demo02;

import com.liang.test1.Customer;
import com.liang.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.io.Serializable;

/**
 * @author 梁思禹
 */
public class Hibernate03Test {

    @Test
    public void test1(){

        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();

        Customer customer = session.get(Customer.class, 1L);
        customer.setCust_name("李三政");

        transaction.commit();
        session.close();

    }
}
