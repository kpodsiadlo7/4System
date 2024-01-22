package com.project.System.app;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
interface UserRepository extends JpaRepository<UserEntity, Long>{

	List<UserEntity> findByNameOrSurnameOrLogin(String searchTerm, String searchTerm2, String searchTerm3);
}
