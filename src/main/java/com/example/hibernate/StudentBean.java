package com.example.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StudentBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static SessionFactory factory;

    public StudentBean() {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }


    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();


        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List<Student> studentsDB = session.createQuery("from student").list();


            for (Iterator iterator = studentsDB.iterator(); iterator.hasNext();) {
                Student student = (Student) iterator.next();
                students.add(student);
            }
            tx.commit();
        } catch (HibernateException hibernateException) {
            if (tx != null) {
                tx.rollback();
                hibernateException.printStackTrace();
            }
        } finally {
            session.close();
        }

        return students;
    }

    public boolean delete(int id) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            /**
             Student studentDB = session.find(Student.class, id);

             session.delete(studentDB);
             */

            String query = "DELETE FROM student WHERE id=" +id;
            session.createQuery(query).executeUpdate();

            tx.commit();
            return true;
        } catch (HibernateException hibernateException) {
            if (tx != null) {
                tx.rollback();
                hibernateException.printStackTrace();
            }
        } finally {
            session.close();
        }
        return false;
    }

    public boolean add(Student student) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            session.save(student);

            tx.commit();
            return true;
        } catch (HibernateException hibernateException) {
            if (tx != null) {
                tx.rollback();
                hibernateException.printStackTrace();
            }
        } finally {
            session.close();
        }
        return false;
    }

    public boolean update(Student student) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Student studentDB = session.find(Student.class, student.getId());
            studentDB = student;
            session.update(studentDB);

            tx.commit();
            return true;
        } catch (HibernateException hibernateException) {
            if (tx != null) {
                tx.rollback();
                hibernateException.printStackTrace();
            }
        } finally {
            session.close();
        }
        return false;
    }


}
