package com.durga.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.durga.user.beans.User;
@Repository
public interface UserRepository extends JpaRepository<User, String> {
	Optional<User> findByUserEmail(String userEmail);
	Optional<User> deleteByUserEmail(String userEmail);

}
