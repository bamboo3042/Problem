package com.koreait.day2.repository;

import com.koreait.day2.model.entity.OrderGroup;
import com.koreait.day2.model.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {
    Optional<Partner> findByParternerId(String parternerId);

    Optional<Partner> findByParternerName(String parternerName);

    Optional<Partner> findByParternerCeoName(String parternerCeoName);
}
