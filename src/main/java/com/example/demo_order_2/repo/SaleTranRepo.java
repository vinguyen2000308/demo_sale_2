package com.example.demo_order_2.repo;

import com.example.demo_order_2.domain.SaleTran;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleTranRepo extends JpaRepository<SaleTran, Long> {
}
