package com.aim.aimbatch.repository;

import com.aim.aimbatch.model.TenantKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TenantKeyRepository extends JpaRepository<TenantKey, Long> {
    @Query("SELECT tenantKey FROM TenantKey tenantKey WHERE tenantKey.healthPlanId = ?1")
    public Optional<TenantKey> findByHealthPlanId(Long healthPlanId);

    @Query("SELECT tenantKey FROM TenantKey tenantKey WHERE tenantKey.batchCode = ?1")
    public Optional<TenantKey> findByBatchCode(String batchCode);
}
