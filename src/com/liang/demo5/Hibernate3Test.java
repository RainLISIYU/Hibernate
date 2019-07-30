package com.liang.demo5;

import com.liang.po.Customer;
import com.liang.utils.HibernateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.*;

/**
 * HQL 多表查询
 * @author 梁思禹
 */
public class Hibernate3Test {

    @Test
    /**
     *
     */
    public void test1(){
        Session session = HibernateUtils.getCurrentSession("hibernate5.cfg.xml");
        Transaction transaction = session.beginTransaction();
//        普通内连接
        /*String hql = "from Customer c inner join c.linkMen";*/
//        迫切内连接
        String hql = "select distinct c from Customer c inner join fetch c.linkMen";
        try {
            Query query = session.createQuery(hql);
            List<Customer> customers = query.list();
            for (Customer o: customers) {
                System.out.println(o);
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }

    }
}
