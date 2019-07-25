package com.liang.demo02;

import com.liang.po.Customer;
import com.liang.utils.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.*;

/**
 * @author 梁思禹
 */
public class Hibernate05Test {

    /**
     * 测试Query
     */
    @Test
    public void test1(){
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();

//        String hql = "from Customer where cust_name like ?";
        String hql = "from Customer";
        Query query = session.createQuery(hql);
//        设置条件
//        query.setParameter(0, "张%");

//        分页查询
        query.setFirstResult(1);
        query.setMaxResults(2);

        List list = query.list();

        System.out.println(list);

        transaction.commit();
        session.close();
    }

    /**
     *测试Criteria
     *
     */
    @Test
    public void test2(){
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(Customer.class);
//        条件查询
        /*criteria.add(Restrictions.like("cust_name", "张%"));*/
//        分页操作
        criteria.setFirstResult(3);
        criteria.setMaxResults(3);
        List list = criteria.list();

        System.out.println(list);

        transaction.commit();
        session.close();
    }

}
