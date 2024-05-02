package com.hexaware.fastXBus.restcontroller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.fastXBus.dto.BusesDTO;
import com.hexaware.fastXBus.entity.Buses;
import com.hexaware.fastXBus.exceptions.BusesNotFoundException;
import com.hexaware.fastXBus.service.IBusesService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/v1/buses")
public class BusesRestController {
    @Autowired
    private IBusesService busesService;

    private static final Logger logger = LoggerFactory.getLogger(BusesRestController.class);

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_BUSOPERATOR')")
    public ResponseEntity<Buses> createBuses(@RequestBody BusesDTO busdto) {
        logger.info("Bus created");
        Buses createdBus = busesService.createBuses(busdto);
        return new ResponseEntity<>(createdBus, HttpStatus.CREATED);
    }

    @PutMapping("/update/{busId}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_BUSOPERATOR')")
    public ResponseEntity<Buses> updateBuses(@RequestBody BusesDTO busdto, @PathVariable Long busId) {
        logger.info("Bus updated");
        Buses updatedBus = busesService.updateBuses(busdto, busId);
        return ResponseEntity.ok(updatedBus);
    }

    @DeleteMapping("/delete/{busId}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_BUSOPERATOR')")
    public ResponseEntity<Void> deleteBuses(@PathVariable Long busId) {
        logger.info("Bus deleted");
        busesService.deleteBuses(busId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getBusById/{busId}")
    public ResponseEntity<BusesDTO> getBusById(@PathVariable Long busId) {
        if (busId == 0) {
            throw new BusesNotFoundException(HttpStatus.BAD_REQUEST, "bus not found" + busId);
        }
        BusesDTO busDTO = busesService.getBusById(busId);
        return ResponseEntity.ok(busDTO);
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_BUSOPERATOR','ROLE_USER')")
    public ResponseEntity<List<BusesDTO>> getAllBuses() {
        logger.info("All Bus ");
        List<BusesDTO> allBuses = busesService.getAllBuses();
        return ResponseEntity.ok(allBuses);
    }

    @GetMapping("/details")
    public ResponseEntity<List<BusesDTO>> getAllBusesByCitiesAndDate(
            @RequestParam("sourceCity") String sourceCity,
            @RequestParam("destinationCity") String destinationCity,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<BusesDTO> buses = busesService.getAllBusesByCitiesAndDate(sourceCity, destinationCity, date);
        return ResponseEntity.ok(buses);
    }
}
