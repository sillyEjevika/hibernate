package dao.impl;
import dao.EmployeeDao;
import dao.HibernateSessionFactoryUtil;
import model.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
   /* private static final String INSERT = "INSERT INTO employee (name, surname, gender, age, city_id) " +
            "VALUES (?, ?, ?, ?, ?)";

    private static final String FIND_LAST_EMPLOYEE = "SELECT * FROM employee ORDER BY id DESC LIMIT 1 ";
    private static final String FIND_BY_ID = "SELECT * FROM employee WHERE id = ? ";
    private static final String FIND_ALL = "SELECT * FROM employee ";
    private static final String UPDATE = "UPDATE employee SET name=?, surname = ?, gender= ?, age = ?, city_id = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM employee WHERE id = ?";*/
    //private final CityDao cityDao = new CityDaoImpl();

    @Override
    public Employee create(Employee employee) {
        /*if (employee.getCity()!= null && cityDao.findById(employee.getCity()).isEmpty()){
            employee.setCity(null);
        }*/
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

    /*private Employee readEmployee(ResultSet resultSet) throws SQLException {
        Long cityId = resultSet.getObject("city_id", Long.class);
        City city = null;
        if (cityId != null) {
            city = (City) cityDao.findById(cityId).orElse(null);
        }
        return new Employee(resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("surname"),
                resultSet.getString("gender"),
                resultSet.getInt("age"),
                city);

    }*/
}
