package dao.impl;
import dao.EmployeeDao;
import dao.HibernateSessionFactoryUtil;
import model.City;
import model.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    private HibernateSessionFactoryUtil hibernateManager = HibernateSessionFactoryUtil.getInstance();
    private volatile Employee employee;
    private volatile List<Employee> employeeList;

    @Override
    public Employee create(Employee employee) {

        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            //Serializable createdId = session.save(employee);
            //Employee createdEmployee = session.get(Employee.class, createdId);
            session.saveOrUpdate(employee);
            transaction.commit();
            return employee;
        }
    }

    @Override
    public Employee readById(Integer id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Employee.class, id);
    }

    @Override
    public List<Employee> readAll() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Employee ",
                    Employee.class).list();
        }
    }
    @Override
    public Employee updateById(Employee employee) {
        /*if (employee.getCity()!= null && cityDao.findById(employee.getCity()).isEmpty()) {
            employee.setCity(null);
        }*/
        EntityManager entityManager = HibernateSessionFactoryUtil.getSessionFactory()
                .createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Employee updateById = entityManager.merge(employee);
        entityTransaction.commit();
        return updateById;
    }

    @Override
    public void deleteById(Employee employee) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(employee);
            transaction.commit();
        }
    }

    @Override
    public void addCity(int cityId, Employee employee) {
        hibernateManager.withEntityManager(em -> {
            City city = em.find(City.class, cityId);
            city.add(employee);
            em.persist(employee);
        });
    }

}
