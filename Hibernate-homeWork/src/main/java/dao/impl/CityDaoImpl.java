package dao.impl;

import dao.CityDao;
import dao.HibernateSessionFactoryUtil;
import model.City;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class CityDaoImpl implements CityDao {
    private HibernateSessionFactoryUtil hibernateManager = HibernateSessionFactoryUtil.getInstance();
    private volatile City city;
    private volatile List<City> cityList;

    @Override
    public Optional<City> findById(long id) {
        return Optional.empty();
    }

    @Override
    public void createCity(City city) {
        hibernateManager.withEntityManager(em -> {
            em.persist(city);
        });
    }

    @Override
    public City getCity(int id) {
        hibernateManager.withEntityManager(em -> {
            city = em.find(City.class, id);
        });
        return city;
    }

    @Override
    public List<City> getCities() {
        hibernateManager.withEntityManager(em -> {
            cityList = em.createQuery("SELECT city FROM City city").getResultList();
        });
        return cityList;
    }

    @Override
    public void updateCity(int id, City newCity) {
        hibernateManager.withEntityManager(em -> {
            city = em.find(City.class, id);

            newCity.setCityId(city.getCityId());
            em.persist(newCity);
//            employee.setFirstName(newEmployee.getFirstName());
//            employee.setLastName(newEmployee.getLastName());
//            employee.setAge(newEmployee.getAge());
//            employee.setGender(newEmployee.getGender());
//            employee.setCity(newEmployee.getCity());
//
//            em.persist(employee);
        });
    }

    @Override
    public void deleteCity(int id) {
        hibernateManager.withEntityManager(em -> {
            em.remove(em.find(City.class, id));
        });
    }
}
