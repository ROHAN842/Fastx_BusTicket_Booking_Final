package com.hexaware.fastXBus.restcontroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.fastXBus.dto.BusOperatorsDTO;
import com.hexaware.fastXBus.entity.BusOperators;
import com.hexaware.fastXBus.exceptions.BusOperatorNotFoundException;
import com.hexaware.fastXBus.service.IBusOperatorsService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/v1/busoperators")
public class BusOperatorRestController {
    @Autowired
    private IBusOperatorsService busoperators;

    @Autowired
    AuthenticationManager authenticationManager;

    private static final Logger logger = LoggerFactory.getLogger(BusOperatorRestController.class);

    @PostMapping("/create")
    public ResponseEntity<BusOperators> createBusOperators(@RequestBody BusOperatorsDTO busoperatordto) {
        logger.info("BusOperator created");
        BusOperators createdOperator = busoperators.createBusOperators(busoperatordto);
        return new ResponseEntity<>(createdOperator, HttpStatus.CREATED);
    }

    @PutMapping("/update/{operatorId}")
    @PreAuthorize("hasAnyAuthority('ROLE_BUSOPERATOR')")
    public ResponseEntity<BusOperators> updateBusOperators(@RequestBody BusOperatorsDTO busoperatordto, @PathVariable Long operatorId) {
        logger.info("BusOperator updated");
        BusOperators updatedOperator = busoperators.updateBusOperators(busoperatordto, operatorId);
        return ResponseEntity.ok(updatedOperator);
    }

    @DeleteMapping("/delete/{operatorId}")
    @PreAuthorize("hasAnyAuthority('ROLE_BUSOPERATOR','ROLE_ADMIN')")
    public ResponseEntity<Void> deleteBusOperators(@PathVariable Long operatorId) {
        logger.info("BusOperator deleted");
        busoperators.deleteBusOperators(operatorId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getById/{operatorId}")
    public ResponseEntity<BusOperatorsDTO> getBusOperatorsById(@PathVariable Long operatorId) {
        if (operatorId == 0) {
            throw new BusOperatorNotFoundException(HttpStatus.BAD_REQUEST, "Bus Operator not found" + operatorId);
        }
        BusOperatorsDTO busOperatorDTO = busoperators.getBusOperatorsById(operatorId);
        return ResponseEntity.ok(busOperatorDTO);
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<BusOperators>> getAllBusOperators() {
        logger.info("All BusOperator ");
        List<BusOperators> allOperators = busoperators.getAllBusOperators();
        return ResponseEntity.ok(allOperators);
    }
}
