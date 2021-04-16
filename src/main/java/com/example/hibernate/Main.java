package com.example.hibernate;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {
        StudentBean studentBean = new StudentBean();

        /**
         * Get all students
         */
        List<Student> students = studentBean.getStudents();
        for(int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            System.out.println(student.getId());
            System.out.println(student.getFirstName());
            System.out.println(student.getLastName());
            System.out.println(student.getEmail());
            System.out.println();
        }

        /**
         * Delete
         */
        studentBean.delete(2);

        students = studentBean.getStudents();
        for(int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            System.out.println(student.getId());
            System.out.println(student.getFirstName());
            System.out.println(student.getLastName());
            System.out.println(student.getEmail());
            System.out.println();
        }

        /**
         * Add
         */
        Student student = new Student();
        student.setId(2);
        student.setFirstName("Ulrike");
        student.setLastName("Mayer");
        student.setEmail("test@mail.com");
        studentBean.add(student);

        students = studentBean.getStudents();
        for(int i = 0; i < students.size(); i++) {
            student = students.get(i);
            System.out.println(student.getId());
            System.out.println(student.getFirstName());
            System.out.println(student.getLastName());
            System.out.println(student.getEmail());
            System.out.println();
        }

        /**
         * Update
         */
        students = studentBean.getStudents();
        for(int i = 0; i < students.size(); i++) {
            student = students.get(i);
            if (student.getId() == 2) {
                student.setFirstName("Alexander");
                studentBean.update(student);
            }
        }

        students = studentBean.getStudents();
        for(int i = 0; i < students.size(); i++) {
            student = students.get(i);
            System.out.println(student.getId());
            System.out.println(student.getFirstName());
            System.out.println(student.getLastName());
            System.out.println(student.getEmail());
            System.out.println();
        }



    }
}
