package com.example.demo_sale_2.repo;

import com.example.demo_sale_2.domain.CustomerDebit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDebitRepo extends JpaRepository<CustomerDebit, Long> {
}
