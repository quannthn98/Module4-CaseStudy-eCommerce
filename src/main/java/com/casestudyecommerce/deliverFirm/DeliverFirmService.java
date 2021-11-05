package com.casestudyecommerce.deliverFirm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeliverFirmService implements IDeliverFirmService{
    @Autowired
    private IDeliverFirmRepository deliverFirmRepository;

    @Override
    public Page<DeliverFirm> findAll(Pageable pageable) {
        return deliverFirmRepository.findAll(pageable);
    }

    @Override
    public Optional<DeliverFirm> findById(Long id) {
        return deliverFirmRepository.findById(id);
    }

    @Override
    public DeliverFirm save(DeliverFirm deliverFirm) {
        return deliverFirmRepository.save(deliverFirm);
    }

    @Override
    public void deleteById(Long id) {
        deliverFirmRepository.deleteById(id);
    }

    @Override
    public Page<DeliverFirm> findAllByNameContaining(String name, Pageable pageable) {
        return deliverFirmRepository.findAllByNameContaining(name,pageable);
    }

    @Override
    public Optional<DeliverFirm> findByName(String name) {
        return deliverFirmRepository.findByName(name);
    }
}
