package com.sms.sms.repo;


import com.sms.sms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.yaml.snakeyaml.events.Event;

import java.util.Optional;

public interface UserRepo extends JpaRepository <User, Long> {

   @Query(value = "SELECT * FROM user WHERE ID=?1",nativeQuery = true)
   User getUserByUserID(String userID);
   @Query(value = "SELECT * FROM user WHERE ID = ?1 AND FACULTY = ?2", nativeQuery = true)
   User getUserByUserIDAndFaculty(String userId,String faculty);

   @Query("SELECT user FROM User user WHERE user.email = ?1")
   Optional<User> findUserByEmail(String email);

}
