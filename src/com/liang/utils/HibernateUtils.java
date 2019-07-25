package com.liang.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtils {

    /**
     * 获得普通session
     * @return session
     */
    public static Session openSession(){
//      加载配置文件
        Configuration configuration = new Configuration().configure();
//      获得Session工厂
        SessionFactory sessionFactory = configuration.buildSessionFactory();
//      获得Session
        return sessionFactory.openSession();
//        配置seesion绑定本地线程后可用，所得session不能close
        /*return sessionFactory.getCurrentSession();*/
    }

    public static Session openSession(String file){
//      加载配置文件
        Configuration configuration = new Configuration().configure(file);
//      获得Session工厂
        SessionFactory sessionFactory = configuration.buildSessionFactory();
//      获得Session
        return sessionFactory.openSession();
    }

    /**
     * 返回绑定本地线程的session对象
     * @return session
     */
    public static Session getCurrentSession(){

        Configuration configuration = new Configuration().configure();

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        return sessionFactory.getCurrentSession();

    }

    public static Session getCurrentSession(String file){

        Configuration configuration = new Configuration().configure(file);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        return sessionFactory.getCurrentSession();

    }

}
