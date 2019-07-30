package com.liang.demo5;

import com.liang.po.Customer;
import com.liang.utils.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.*;
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

    @Test
    /**
     * 条件查询
     */
    public void test2(){
        Session session = HibernateUtils.getCurrentSession("hibernate5.cfg.xml");
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(Customer.class);

        try{
            criteria.add(Restrictions.eq("cust_source", "百度"));
            System.out.println(criteria.list());
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Test
    /**
     * 统计查询
     */
    public void test3(){
        Session session = HibernateUtils.getCurrentSession("hibernate5.cfg.xml");
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(Customer.class);

        try{
            /**
             * add   :普通的条件
             * addOrder : 排序
             * setProjection :聚合函数和group by、having
             */
            criteria.setProjection(Projections.rowCount());
            Object o = criteria.uniqueResult();
            System.out.println(o);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    /**
     * 离线查询
     */
    public void test4(){
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
        detachedCriteria.add(Restrictions.eq("cust_source", "百度"));

        Session session = HibernateUtils.getCurrentSession("hibernate5.cfg.xml");
        Transaction transaction = session.beginTransaction();

        try {
            Criteria criteria = detachedCriteria.getExecutableCriteria(session);
            System.out.println(criteria.list());
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

}
