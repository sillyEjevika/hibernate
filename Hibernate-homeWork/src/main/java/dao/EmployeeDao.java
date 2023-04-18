package dao;

import model.Employee;

import java.util.List;

public interface EmployeeDao {

    Employee create(Employee employee);

    Employee readById(Integer id);

    List<Employee> readAll();

    Employee updateById(Employee employee);

    void deleteById(Employee employee);
}
