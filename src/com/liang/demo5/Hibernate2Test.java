package com.liang.demo5;

import com.liang.po.Customer;
import com.liang.utils.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.junit.Test;

/**
 * QBC测试类
 * @author 梁思禹
 */
public class Hibernate2Test {
    @Test
    /**
     * 普通和排序查询
     */
    public void test1(){
        Session session = HibernateUtils.getCurrentSession("hibernate5.cfg.xml");
        Transaction transaction = session.beginTransaction();

        try {
            Criteria criteria = session.createCriteria(Customer.class);
            criteria.addOrder(Order.desc("cust_id"));
            System.out.println(criteria.list());
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }

    }
}
