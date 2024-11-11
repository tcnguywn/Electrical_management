package com.dev.electricity.repository;

import com.dev.electricity.entity.TierConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TierConfigRepository extends JpaRepository<TierConfig, Long> {
    boolean existsByMinValLessThanEqualAndMaxValGreaterThanEqual(Long maxVal, Long minVal);

}
