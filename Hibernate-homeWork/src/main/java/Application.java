
import dao.CityDao;
import dao.EmployeeDao;
import dao.impl.CityDaoImpl;
import dao.impl.EmployeeDaoImpl;
import model.City;
import model.Employee;
import java.util.List;

public class Application {
    public static void main(String[] args) {

        EmployeeDao employeeDAO = new EmployeeDaoImpl();
        CityDao cityDao = new CityDaoImpl();

        int cityId1 = 19;
        int cityId2 = 18;
        Employee employee1 = new Employee("Маня", "Федотова", "female", 22);
        Employee employee2 = new Employee("Вика", "Сидорова", "female", 25);
        Employee employee3 = new Employee("Ваня", "Макаров", "male", 38);
        employeeDAO.addCity(cityId1, employee1);
        employeeDAO.addCity(cityId2, employee2);
        employeeDAO.addCity(cityId2, employee3);
//        employeeDAO.createEmployee(employee1);
//        Employee test = new Employee("a", "aa", "f", 25, 7);
//        employeeDAO.updateEmployee(1, test);
//        System.out.println(employeeDAO.getEmployee(1));
//        employeeDAO.deleteEmployee(20);
//        System.out.println(employeeDAO.getEmployee(20));
    }

}