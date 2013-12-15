package com.ernesto.list;

import com.ernesto.list.CertificateL;
import com.ernesto.list.EmployeeL;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ManageEmployeeL {

    private static SessionFactory factory;

    public ManageEmployeeL() {
    }

    public static void main(String[] args) {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Error al crear objeto SessionFactory\n" + ex.toString());
            throw new ExceptionInInitializerError(ex);
        }

        ManageEmployeeL ME = new ManageEmployeeL();

        ArrayList set1 = new ArrayList();
        set1.add(new CertificateL("MCA"));
        set1.add(new CertificateL("MBA"));
        set1.add(new CertificateL("PMP"));

        ArrayList set2 = new ArrayList();
        set2.add(new CertificateL("MA"));
        set2.add(new CertificateL("BCA"));

        Integer eID1  = ME.addEmployeeL("Ernesto", "Mancebo", 15000, set1);
        Integer eID2  = ME.addEmployeeL("Geraldo", "Luna", 25000, set2);
        Integer eID3  = ME.addEmployeeL("Nathaniel", "Calderon", 10000);
        Integer eID4  = ME.addEmployeeL("Laura", "Gonzalez", 22000);

        ME.listEmployeeL();
        ME.updateEmployeeLSalary(eID2, 30000);
        ME.deleteEmployeeL(eID3);
        ME.listEmployeeL();
    }

    public Integer addEmployeeL(String fName, String lName, int salary) {
        return this.addEmployeeL(fName, lName, salary, null);
    }

    public Integer addEmployeeL(String fName, String lName, int salary, ArrayList cert) {
        Session session = factory.openSession();
        Transaction trans = null;
        Integer employeeLID = null;

        try {
            trans = session.beginTransaction();
            EmployeeL e = new EmployeeL(fName, lName, salary, cert);
            employeeLID = (Integer) session.save(e);
            trans.commit();
        } catch (HibernateException ex) {
            if (trans != null) {
                trans.rollback();
            }
            ex.printStackTrace();
        } finally {
            session.close();
        }

        return employeeLID;
    }

    public void listEmployeeL() {
        Session session = factory.openSession();
        Transaction trans = null;

        try {
            trans = session.beginTransaction();
            List employeesL = session.createQuery("FROM EmployeeL").list();
            Iterator i = employeesL.iterator();

            while (i.hasNext()) {
                EmployeeL e = (EmployeeL) i.next();
                List certificatesL = e.getCertificates();

                System.out.println(
                    "Nombre: " + e.getFirstName() +
                    " Apellido: " + e.getLastName() +
                    " Salario: " + e.getSalary()
                );

                Iterator i2 = certificatesL.iterator();

                while (i2.hasNext()) {
                    CertificateL c = (CertificateL) i2.next();
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

    public void updateEmployeeLSalary(Integer employeeLID, int salary) {
        Session session = factory.openSession();
        Transaction trans = null;

        try {
            trans = session.beginTransaction();
            EmployeeL e = (EmployeeL) session.get(EmployeeL.class, employeeLID);
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

    public void deleteEmployeeL(Integer employeeLID) {
        Session session = factory.openSession();
        Transaction trans = null;

        try {
            trans = session.beginTransaction();
            EmployeeL e = (EmployeeL) session.get(EmployeeL.class, employeeLID);
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