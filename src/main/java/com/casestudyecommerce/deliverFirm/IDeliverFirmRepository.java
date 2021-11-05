package com.casestudyecommerce.deliverFirm;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDeliverFirmRepository extends JpaRepository<DeliverFirm, Long> {
    Page<DeliverFirm> findAllByNameContaining(String name, Pageable pageable);
    Optional<DeliverFirm> findByName(String name);
}
