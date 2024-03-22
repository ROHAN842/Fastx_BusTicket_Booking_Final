package com.hexaware.fastXBus.restcontroller;

import java.time.LocalDate;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
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
    public Buses createBuses(@RequestBody BusesDTO busdto) {

		 logger.info("Bus created");
		return busesService.createBuses(busdto);
	}
	
	@PutMapping("/update/{busId}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_BUSOPERATOR')")
	public Buses updateBuses(@RequestBody BusesDTO busdto,@PathVariable Long busId) {

		 logger.info("Bus updated");
		return  busesService.updateBuses(busdto,busId);
	}
	@DeleteMapping("/delete/{busId}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_BUSOPERATOR')")
	public void deleteBuses(@PathVariable Long busId)
	{

		 logger.info("Bus deleted");
		busesService.deleteBuses(busId);
	
	}
	@GetMapping("/getBusById/{busId}")
//	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_BUSOPERATOR','ROLE_USER')")
	public BusesDTO getBusById(@PathVariable Long busId)throws BusesNotFoundException{
		
		
		if(busId==0) {
			throw new BusesNotFoundException(HttpStatus.BAD_REQUEST,"bus not found"+busId);
		}
		return  busesService.getBusById(busId);	
		
	}
	@GetMapping("/getAll")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_BUSOPERATOR','ROLE_USER')")
	public List<BusesDTO> getAllBuses(){
		

		 logger.info("All Bus ");
		return  busesService.getAllBuses();	
		
	}
	 @GetMapping("/details")
	    public List<BusesDTO> getAllBusesByCitiesAndDate(
	            @RequestParam("sourceCity") String sourceCity,
	            @RequestParam("destinationCity") String destinationCity,
	            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
	        return busesService.getAllBusesByCitiesAndDate(sourceCity, destinationCity, date);
	    }

	
	
	

}

