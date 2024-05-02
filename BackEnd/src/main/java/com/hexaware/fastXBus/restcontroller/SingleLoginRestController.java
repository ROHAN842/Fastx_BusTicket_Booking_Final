package com.hexaware.fastXBus.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.fastXBus.dto.AuthRequest;
import com.hexaware.fastXBus.service.JwtService;
import com.hexaware.fastXBus.service.UserCustomersService;

@RestController
@RequestMapping("/api/v1/allsinglelogin")
@CrossOrigin("http://localhost:4200")
public class SingleLoginRestController {
    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserCustomersService service;
    
    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        String token = null;
        if (authenticate.isAuthenticated()) {
            token = jwtService.generateToken(authRequest.getUsername());
            return ResponseEntity.ok(token);
        } else {
            throw new UsernameNotFoundException("Invalid Username or Password / Invalid request");
        }
    }
    
    @GetMapping("/gettherole/{firstName}")
    public ResponseEntity<String> getRoleByfirstName(@PathVariable String firstName) {
        String role = service.getRoleByfirstName(firstName);
        return ResponseEntity.ok(role);
    }
    
    @GetMapping("/getId/{firstName}")
    public ResponseEntity<Long> getId(@PathVariable String firstName) {
        Long id = service.getId(firstName);
        return ResponseEntity.ok(id);
    }
}
 