package com.liang.demo5;

import com.liang.po.Customer;
import com.liang.utils.HibernateUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

/**
 * SQL查询
 * @author 梁思禹
 */
public class Hibernate4Test {
    @Test
    /**
     * SQL查询
     */
    public void test1(){
        Session session = HibernateUtils.getCurrentSession("hibernate5.cfg.xml");
        Transaction transaction = session.beginTransaction();

        SQLQuery sqlQuery = session.createSQLQuery("select c.cust_id from cst_customer c");
        System.out.println(sqlQuery.list());
        transaction.commit();
    }
}
