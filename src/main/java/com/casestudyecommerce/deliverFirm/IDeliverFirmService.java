package com.casestudyecommerce.deliverFirm;

import com.casestudyecommerce.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface IDeliverFirmService extends IGeneralService<DeliverFirm> {
    Page<DeliverFirm> findAllByNameContaining(String name, Pageable pageable);
    Optional<DeliverFirm> findByName(String name);
}
