package com.covengers.springapi.repo;

import com.covengers.springapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByNameAndPhoneNumber(String name, String phonenumber);

    User findByPhoneNumber(String phoneNumber);

    User findByUsernameAndPassword(String username, String password);
}
