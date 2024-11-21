package com.dev.electricity.repository;

import com.dev.electricity.entity.UsageHistory;
import com.dev.electricity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;
import java.util.Optional;

@Repository
public interface UsageHistoryRepository extends JpaRepository<UsageHistory, Long> {
    UsageHistory findByMonthUsage(YearMonth monthUsage);
    Optional<UsageHistory> findByUserAndMonthUsage(User user, YearMonth monthUsage);

}
