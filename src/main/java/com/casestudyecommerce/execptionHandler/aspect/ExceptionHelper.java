package com.casestudyecommerce.execptionHandler.aspect;

import com.casestudyecommerce.execptionHandler.Exception.DuplicateException;
import com.casestudyecommerce.execptionHandler.Exception.UnauthorizedException;
import com.casestudyecommerce.user.users.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestController
@RestControllerAdvice
public class ExceptionHelper {
    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<User> isDuplicated(){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> isUnauthorized(){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
