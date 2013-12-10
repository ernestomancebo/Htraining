import java.util.List;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;

public class ManageEmployee {

private static SessionFactory factory;

	public ManageEmployee() {
	}

	public static void main(String[] args) {
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch(Throwable ex) {
			System.err.println("Error al crear objeto SessionFactory\n" + ex.toString());
			throw new ExceptionInInitializerError(ex);
		}

		ManageEmployee ME = new ManageEmployee();
		Integer eID1  = ME.addEmployee("Ernesto", "Mancebo", 15000);
		Integer eID2  = ME.addEmployee("Maria", "Abad", 25000);
		Integer eID3  = ME.addEmployee("Nathaniel", "Calderon", 10000);

		ME.listEmployee();
		ME.updateEmployeeSalary(eID2, 30000);
		ME.deleteEmployee(eID1);
		ME.listEmployee();
	}


	public Integer addEmployee(String fName, String lName, int salary) {
		Session session = factory.openSession();
		Transaction trans = null;
		Integer employeeID = null;

		try {
			trans = session.beginTransaction();
			Employee e = new Employee(fName, lName, salary);
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

			while(i.hasNext()) {
				Employee e = (Employee) i.next();
				System.out.println(
					"Nombre: " + e.getFirstName() +
					" Apellido: " + e.getLastName() + 
					" Salario: " + e.getSalary()
					);
			}
			trans.commit();
		} catch (HibernateException ex) {
			if(trans != null) {
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
			session.deleteEmployee(e);
			trans.commit();
		} catch(HibernateException ex) {
			if(trans != null) {
				trans.rollback();
			}
			ex.printStackTrace();
		} finally {
			session.close();
		}
	}
}