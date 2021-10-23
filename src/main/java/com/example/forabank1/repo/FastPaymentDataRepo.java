package com.example.forabank1.repo;

import com.example.forabank1.domain.FastPaymentData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FastPaymentDataRepo extends JpaRepository<FastPaymentData, Long> {
}
