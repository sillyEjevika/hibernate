package dao;

import model.City;

import java.util.List;
import java.util.Optional;

public interface CityDao {
    Optional<City> findById(long id);
    void createCity(City city);

    City getCity(int id);

    List<City> getCities();

    void updateCity(int id, City city);

    void deleteCity(int id);
}
