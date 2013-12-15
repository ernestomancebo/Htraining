package com.ernesto.many_to_one;

import com.ernesto.many_to_one.Address;
import com.ernesto.many_to_one.Employee;

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

        Address addr1 = new Address("4ta", "Santo Domingo", "Distrito Nacional", "10607");
        ME.addAddress(addr1);

        Integer eID1  = ME.addEmployee("Yoguie", "Dominguez", 15000, addr1);
        Integer eID2  = ME.addEmployee("Chapo", "Guzman", 18000, addr1);

        ME.listEmployee();
        ME.updateEmployeeSalary(eID2, 30000);
        // ME.deleteEmployee(eID3);
        ME.listEmployee();
    }

    public Integer addAddress(Address a) {
        Session session = factory.openSession();
        Transaction trans = null;
        Integer addressID = null;

        try {
            trans = session.beginTransaction();
            // Address a = new Address(street, city, state, zipcode);
            addressID = (Integer) session.save(a);
            trans.commit();
        } catch (HibernateException ex)  {
            if (trans != null) {
                trans.rollback();
            }
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return addressID;
    }

    public Integer addEmployee(String fName, String lName, int salary) {
        return this.addEmployee(fName, lName, salary, null);
    }

    public Integer addEmployee(String fName, String lName, int salary, Address address) {
        Session session = factory.openSession();
        Transaction trans = null;
        Integer employeeID = null;

        try {
            trans = session.beginTransaction();
            Employee e = new Employee(fName, lName, salary, address);
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
                Address addr = e.getAddress();

                System.out.println("Nombre: "
                                   + e.getFirstName() +
                                   " Apellido: " + e.getLastName() +
                                   " Salario: " + e.getSalary());

                System.out.println("Dirección: " +
                                   addr.getStreet() + ", " + addr.getCity() + ", " +
                                   addr.getState() + ", " + addr.getZipcode());
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