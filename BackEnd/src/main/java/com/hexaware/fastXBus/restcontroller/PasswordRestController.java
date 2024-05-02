package com.hexaware.fastXBus.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.fastXBus.dto.PasswordDTO;
import com.hexaware.fastXBus.service.IPasswordService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/v1/password")
public class PasswordRestController {
    @Autowired
    private IPasswordService passwordservice;
    
    @PutMapping("/updatePassword")
    public ResponseEntity<String> updatePassword(@RequestBody PasswordDTO passwordDTO) {
        String message = passwordservice.updateUserPassword(passwordDTO);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
 