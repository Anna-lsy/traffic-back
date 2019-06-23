package cn.edu.usst.anna.web.repository;

import cn.edu.usst.anna.web.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner,String> {
}
