package cn.edu.usst.anna.web.repository;

import cn.edu.usst.anna.web.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
