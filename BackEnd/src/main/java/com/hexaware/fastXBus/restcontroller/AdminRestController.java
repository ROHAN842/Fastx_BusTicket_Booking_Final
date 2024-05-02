package com.hexaware.fastXBus.restcontroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.fastXBus.dto.AdminDTO;
import com.hexaware.fastXBus.entity.Admin;
import com.hexaware.fastXBus.exceptions.AdminNotFoundException;
import com.hexaware.fastXBus.service.IAdminService;
import com.hexaware.fastXBus.service.JwtService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/v1/admins")
public class AdminRestController {
    @Autowired
    private IAdminService adminService;
    @Autowired
    AuthenticationManager authenticationManager;

    private static final Logger logger = LoggerFactory.getLogger(AdminRestController.class);

    @PostMapping("/create")
    public ResponseEntity<Admin> createAdmin(@RequestBody AdminDTO admindto) {
        logger.info("Admin created");
        Admin createdAdmin = adminService.createAdmin(admindto);
        return new ResponseEntity<>(createdAdmin, HttpStatus.CREATED);
    }

    @PutMapping("/update/{adminId}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Admin> updateAdmin(@RequestBody AdminDTO admindto, @PathVariable Long adminId) {
        logger.info("Admin updated");
        Admin updatedAdmin = adminService.updateAdmin(admindto, adminId);
        return ResponseEntity.ok(updatedAdmin);
    }

    @DeleteMapping("/delete/{adminId}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long adminId) {
        logger.info("Admin deleted");
        adminService.deleteAdmin(adminId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getById/{adminId}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<AdminDTO> getAdmin(@PathVariable Long adminId) {
        if (adminId == 0) {
            throw new AdminNotFoundException(HttpStatus.BAD_REQUEST, "payment not found" + adminId);
        }
        AdminDTO adminDTO = adminService.getAdmin(adminId);
        return ResponseEntity.ok(adminDTO);
    }

    @GetMapping("/getall")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<Admin>> getAllAdmin() {
        logger.info("All Admin ");
        List<Admin> allAdmin = adminService.getAllAdmin();
        return ResponseEntity.ok(allAdmin);
    }
}
