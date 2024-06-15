package com.sms.sms.repo;

import com.sms.sms.entity.OurUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsersRepo extends JpaRepository<OurUsers, Integer> {

    @Query
    Optional<OurUsers> findById(Integer id);

    @Query
    Optional<OurUsers> findByEmail(String email);

}
