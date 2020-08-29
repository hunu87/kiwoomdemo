package com.kiwoom.demo.account.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kiwoom.demo.account.vo.Authority;

@Repository
public interface AuthoritiesRepository extends JpaRepository<Authority, Long> {
	List<Authority> findByUsername(String username);
}
