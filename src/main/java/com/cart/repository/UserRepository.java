package com.cart.repository;

import com.cart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email); // 登入時用
	// 透過email 去db找user
	// return 找到的user(->User);

//	boolean existsByEmail(String email); // 註冊時檢查重複

}
