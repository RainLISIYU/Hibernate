package com.liang.demo5;

import com.liang.po.Customer;
import com.liang.po.LinkMan;
import com.liang.utils.HibernateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * HQL查询测试类
 * @author 梁思禹
 */
public class Hibernate1Test {

    @Test
    /**
     * 插入数据
     */
    public void test1(){
        Session session = HibernateUtils.getCurrentSession("hibernate5.cfg.xml");
        Transaction transaction = session.beginTransaction();

        Customer customer = new Customer();
        customer.setCust_name("王五");

        for (int i = 0; i < 5; i++) {
            LinkMan linkMan = new LinkMan();
            linkMan.setLkmName("李秉并"+i);

            customer.getLinkMen().add(linkMan);
            linkMan.setCustomer(customer);
        }

        try {
            session.save(customer);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }

    }

    @Test
    /**
     * HQL查询测试
     */
    public void test2(){
        Session session = HibernateUtils.getCurrentSession("hibernate5.cfg.xml");
        Transaction transaction = session.beginTransaction();

//        简单查询
        /*String hql = "select c.cust_name from Customer c";*/

//        排序查询
        /*String hql = "from Customer order by cust_id desc";*/

//        条件查询
        String hql = "from Customer where cust_source = ? and cust_name like :name";
        try {

            Query query = session.createQuery(hql);
//            位置绑定
            query.setString(0, "百度");
//            名称绑定
            query.setParameter("name", "张%");
            List list = query.list();
            System.out.println(list);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Test
    /**
     * 投影查询,查询所需列
     */
    public void test3(){
        Session session = HibernateUtils.getCurrentSession("hibernate5.cfg.xml");
        Transaction transaction = session.beginTransaction();

        String hql = "select cust_name, cust_source from Customer c";

        try {
            Query query = session.createQuery(hql);
            List<Object[]> list = query.list();
            for (Object[] o: list) {
                System.out.println(Arrays.toString(o));
            }
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }

    }

    @Test
    /**
     * 分组统计查询
     */
    public void test4(){
        Session session = HibernateUtils.getCurrentSession("hibernate5.cfg.xml");
        Transaction transaction = session.beginTransaction();

//        聚合函数:count(), max(), sum()
        String hql = "select count(*) from Customer group by cust_source";

        try{
            Query query = session.createQuery(hql);
//            单个的值
            /*Object o = query.uniqueResult();*/
            List<Long> list = query.list();
            for (Long l: list) {
                System.out.println(l);
            }
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }

    }

}
