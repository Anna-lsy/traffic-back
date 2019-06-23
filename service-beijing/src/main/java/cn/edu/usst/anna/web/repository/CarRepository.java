package cn.edu.usst.anna.web.repository;

import cn.edu.usst.anna.web.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car,String> {
}
