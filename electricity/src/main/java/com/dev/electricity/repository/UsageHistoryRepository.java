package com.dev.electricity.repository;

import com.dev.electricity.entity.UsageHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface UsageHistoryRepository extends JpaRepository<UsageHistory, Long> {
    UsageHistory findByDate(LocalDate date);
}
