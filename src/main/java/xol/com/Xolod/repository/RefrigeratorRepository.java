package xol.com.Xolod.repository;


import java.util.List;

import xol.com.Xolod.model.Manufacturer;
import xol.com.Xolod.model.Refrigerator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefrigeratorRepository extends JpaRepository<Refrigerator, Long> {
    // Поиск по модели
    List<Refrigerator> findByModel(String model);

    // Поиск по цвету
    List<Refrigerator> findByColor(String color);

    // Поиск по типу
    List<Refrigerator> findByType(String type);

    // Поиск по цене (<=)
    List<Refrigerator> findByPriceLessThan(Double price);

    // Поиск по цене (>=)
    List<Refrigerator> findByPriceGreaterThan(Double price);

    // Поиск по производителю
    List<Refrigerator> findByManufacturer(Manufacturer manufacturer);

    // Поиск по стране производителя (через связь)
    List<Refrigerator> findByManufacturer_Country(String country);
}
