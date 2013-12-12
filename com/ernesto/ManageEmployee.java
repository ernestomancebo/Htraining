package com.ernesto;

import com.ernesto.Certificate;
import com.ernesto.Employee;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ManageEmployee {

    private static SessionFactory factory;

    public ManageEmployee() {
    }

    public static void main(String[] args) {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Error al crear objeto SessionFactory\n" + ex.toString());
            throw new ExceptionInInitializerError(ex);
        }

        ManageEmployee ME = new ManageEmployee();

        TreeSet set1 = new TreeSet();
        set1.add(new Certificate("MCA"));
        set1.add(new Certificate("MBA"));
        set1.add(new Certificate("PMP"));

        TreeSet set2 = new TreeSet();
        set2.add(new Certificate("MA"));
        set2.add(new Certificate("BCA"));

        Integer eID1  = ME.addEmployee("Ernesto", "Mancebo", 15000, set1);
        Integer eID2  = ME.addEmployee("Geraldo", "Luna", 25000, set2);
        Integer eID3  = ME.addEmployee("Nathaniel", "Calderon", 10000);
        Integer eID4  = ME.addEmployee("Laura", "Gonzalez", 22000);

        ME.listEmployee();
        ME.updateEmployeeSalary(eID2, 30000);
        ME.deleteEmployee(eID3);
        ME.listEmployee();
    }

    public Integer addEmployee(String fName, String lName, int salary) {
        return this.addEmployee(fName, lName, salary, null);
    }

    public Integer addEmployee(String fName, String lName, int salary, SortedSet cert) {
        Session session = factory.openSession();
        Transaction trans = null;
        Integer employeeID = null;

        try {
            trans = session.beginTransaction();
            Employee e = new Employee(fName, lName, salary, cert);
            employeeID = (Integer) session.save(e);
            trans.commit();
        } catch (HibernateException ex) {
            if (trans != null) {
                trans.rollback();
            }
            ex.printStackTrace();
        } finally {
            session.close();
        }

        return employeeID;
    }

    public void listEmployee() {
        Session session = factory.openSession();
        Transaction trans = null;

        try {
            trans = session.beginTransaction();
            List employees = session.createQuery("FROM Employee").list();
            Iterator i = employees.iterator();

            while (i.hasNext()) {
                Employee e = (Employee) i.next();
                SortedSet certificates = e.getCertificates();

                System.out.println(
                    "Nombre: " + e.getFirstName() +
                    " Apellido: " + e.getLastName() +
                    " Salario: " + e.getSalary()
                );

                Iterator i2 = certificates.iterator();

                while (i2.hasNext()) {
                    Certificate c = (Certificate) i2.next();
                    System.out.println("Certificado: " + c.getName());
                }
            }
            trans.commit();
        } catch (HibernateException ex) {
            if (trans != null) {
                trans.rollback();
            }
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateEmployeeSalary(Integer employeeID, int salary) {
        Session session = factory.openSession();
        Transaction trans = null;

        try {
            trans = session.beginTransaction();
            Employee e = (Employee) session.get(Employee.class, employeeID);
            e.setSalary(salary);
            session.update(e);
            trans.commit();
        } catch (HibernateException ex) {
            if (trans != null) {
                trans.rollback();
            }
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteEmployee(Integer employeeID) {
        Session session = factory.openSession();
        Transaction trans = null;

        try {
            trans = session.beginTransaction();
            Employee e = (Employee) session.get(Employee.class, employeeID);
            session.delete(e);
            trans.commit();
        } catch (HibernateException ex) {
            if (trans != null) {
                trans.rollback();
            }
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }
}