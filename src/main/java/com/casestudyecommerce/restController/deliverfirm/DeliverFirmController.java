package com.casestudyecommerce.restController.deliverfirm;

import com.casestudyecommerce.deliverFirm.DeliverFirm;
import com.casestudyecommerce.deliverFirm.IDeliverFirmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/deliverfirm")
public class DeliverFirmController {
    @Autowired
    private IDeliverFirmService deliverFirmService;

    @GetMapping
    public ResponseEntity<Page<DeliverFirm>> findAll(@RequestParam(name = "q") Optional<String> q,
                                        @PageableDefault(sort = "name",size = 5) Pageable pageable){
        Page<DeliverFirm> deliverFirmPage;
        if(!q.isPresent()){
            deliverFirmPage=deliverFirmService.findAll(pageable);
        }else {
            deliverFirmPage=deliverFirmService.findAllByNameContaining(q.get(),pageable);
        }
        return new ResponseEntity<>(deliverFirmPage,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliverFirm> findById(@PathVariable("id") Long id){
        Optional<DeliverFirm> deliverFirmOptional= deliverFirmService.findById(id);
        if(!deliverFirmOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(deliverFirmOptional.get(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DeliverFirm> createDeliverFirm(@Valid @RequestBody DeliverFirm deliverFirm,
                                                         BindingResult bindingResult){
        Optional<DeliverFirm> checkDeliverFirmExist= deliverFirmService.findByName(deliverFirm.getName());
        if(checkDeliverFirmExist.isPresent()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(deliverFirmService.save(deliverFirm),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeliverFirm> updateDeliverFirm(@PathVariable("id") Long id,
                                                         @Valid  @RequestBody DeliverFirm deliverFirm,
                                                         BindingResult bindingResult
                                                         ){
        Optional<DeliverFirm> deliverFirmOptional= deliverFirmService.findById(id);
        Optional<DeliverFirm> checkDeliverFirmExist= deliverFirmService.findByName(deliverFirm.getName());
        if(checkDeliverFirmExist.isPresent()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }else if(bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else if(!deliverFirmOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        deliverFirm.setId(id);
        deliverFirmService.save(deliverFirm);
        return new ResponseEntity<>(deliverFirmOptional.get(),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeliverFirm> deleteDeliverFirm(@PathVariable("id") Long id){
        Optional<DeliverFirm> deliverFirmOptional= deliverFirmService.findById(id);
        if(!deliverFirmOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        deliverFirmService.deleteById(id);
        return new ResponseEntity<>(deliverFirmOptional.get(),HttpStatus.OK);
    }
}
