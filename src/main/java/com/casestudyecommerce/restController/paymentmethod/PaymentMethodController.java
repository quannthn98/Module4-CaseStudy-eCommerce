package com.casestudyecommerce.restController.paymentmethod;

import com.casestudyecommerce.payment.IPaymentMethodService;
import com.casestudyecommerce.payment.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/paymentmethod")
public class PaymentMethodController {
    @Autowired
    private IPaymentMethodService paymentMethodService;

    @GetMapping
    public ResponseEntity<Page<PaymentMethod>> findAll(@RequestParam("q")Optional<String> q,
                                                       @PageableDefault(sort = "name",size = 2) Pageable pageable){
        Page<PaymentMethod> paymentMethodPage;
        if(q.isPresent()){
            paymentMethodPage= paymentMethodService.findAllByNameContaining(q.get(),pageable);
        }else {
            paymentMethodPage=paymentMethodService.findAll(pageable);
        }

        return new ResponseEntity<>(paymentMethodPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentMethod> findById(@PathVariable("id") Long id){
        Optional<PaymentMethod> paymentMethodOptional= paymentMethodService.findById(id);
        if(!paymentMethodOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(paymentMethodOptional.get(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PaymentMethod> createPaymentMethod(@Valid @RequestBody PaymentMethod paymentMethod,
                                                             BindingResult bindingResult){
        Optional<PaymentMethod> checkPaymentMethodExist= paymentMethodService.findByName(paymentMethod.getName());
        if(checkPaymentMethodExist.isPresent()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(paymentMethodService.save(paymentMethod),HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentMethod> updatePaymentMethod( @PathVariable("id") Long id,
                                                              @Valid @RequestBody PaymentMethod paymentMethod,
                                                              BindingResult bindingResult){
        Optional<PaymentMethod> paymentMethodOptional= paymentMethodService.findById(id);
        Optional<PaymentMethod> checkPaymentMethodExist= paymentMethodService.findByName(paymentMethod.getName());
        if(checkPaymentMethodExist.isPresent()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }else if(bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if(!paymentMethodOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        paymentMethod.setId(id);
        paymentMethodService.save(paymentMethod);
        return new ResponseEntity<>(paymentMethodOptional.get(),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PaymentMethod> deletePaymentMethod(@PathVariable("id") Long id){
        Optional<PaymentMethod> paymentMethodOptional= paymentMethodService.findById(id);
        if(!paymentMethodOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        paymentMethodService.deleteById(id);
        return new ResponseEntity<>(paymentMethodOptional.get(),HttpStatus.OK);
    }
}
