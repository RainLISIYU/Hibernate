package com.liang.demo04;

import com.liang.po.Role;
import com.liang.po.User;
import com.liang.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

/**
 * 多对多映射关系
 * @author 梁思禹
 */
public class Hibernate01Test {
    @Test
    public void test1(){

        Session session = HibernateUtils.getCurrentSession("manytomany.cfg.xml");
        Transaction transaction = session.beginTransaction();

        User user1 = new User();
        user1.setUser_name("张三");
        User user2 = new User();
        user2.setUser_name("赵四");

        Role role1 = new Role();
        role1.setRole_name("外交");
        Role role2 = new Role();
        role2.setRole_name("公关");
        Role role3 = new Role();
        role3.setRole_name("技术");

        user1.getRoles().add(role1);
        user1.getRoles().add(role2);
        user2.getRoles().add(role2);
        user2.getRoles().add(role3);
        role1.getUsers().add(user1);
        role2.getUsers().add(user1);
        role2.getUsers().add(user2);
        role3.getUsers().add(user2);

//        保存：若多对多必须有乙方放弃外键操作，一般被动方放弃。若单向关联则不用
        session.save(user1);
        session.save(user2);
        session.save(role1);
        session.save(role2);
        session.save(role3);

        transaction.commit();

    }

    @Test
    /**
     * 级联保存测试
     */
    public void test2(){

        Session session = HibernateUtils.getCurrentSession("manytomany.cfg.xml");
        Transaction transaction = session.beginTransaction();

        User user = new User();
        user.setUser_name("实力");

        Role role = new Role();
        role.setRole_name("策划");

        user.getRoles().add(role);
        role.getUsers().add(user);

        session.save(user);

        transaction.commit();

    }

    @Test
    /**
     * 级联删除
     * 若删除User表中的数据在User.hbm.xml中设置cascade="delete"
     */
    public void test3(){

        Session session = HibernateUtils.getCurrentSession("manytomany.cfg.xml");
        Transaction transaction = session.beginTransaction();

//        User user = session.get(User.class, 1L);
        session.delete(session.get(User.class, 10L));

        transaction.commit();

    }

    @Test
    /**
     * 用户选择角色
     */
    public void test4(){

        Session session = HibernateUtils.getCurrentSession("manytomany.cfg.xml");
        Transaction transaction = session.beginTransaction();

        User user = session.get(User.class, 3L);
        Role role = session.get(Role.class, 6L);

        role.getUsers().add(user);

        transaction.commit();

    }

    @Test
    /**
     * 用户改变角色
     */
    public void test5(){

        Session session = HibernateUtils.getCurrentSession("manytomany.cfg.xml");
        Transaction transaction = session.beginTransaction();

        User user = session.get(User.class, 3L);
        Role role1 = session.get(Role.class, 6L);
        Role role2 = session.get(Role.class, 12L);

        user.getRoles().remove(role1);
        user.getRoles().add(role2);

        transaction.commit();

    }

    @Test
    /**
     * 用户删除角色
     */
    public void test6(){

        Session session = HibernateUtils.getCurrentSession("manytomany.cfg.xml");
        Transaction transaction = session.beginTransaction();

        User user = session.get(User.class, 3L);
        Role role2 = session.get(Role.class, 12L);

        user.getRoles().remove(role2);

        transaction.commit();

    }
}
