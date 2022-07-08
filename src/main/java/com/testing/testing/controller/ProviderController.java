package com.testing.testing.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.testing.testing.exception.ResourceNotFoundException;
import com.testing.testing.model.Provider;
import com.testing.testing.model.Time;
import com.testing.testing.repository.ProviderRepository;

@RestController
@RequestMapping("/api/v1/")
public class ProviderController {
    @Autowired
    private ProviderRepository providerRepository;

    // Create Provider
    @PostMapping("/create")
    public Provider creatProvider(@RequestParam String providerName, 
                                  @RequestParam String flowName,
                                   @RequestBody Time time){

        Provider provider = new Provider(providerName,flowName,LocalDateTime.parse(time.downtime_from),LocalDateTime.parse(time.downtime_to));
        return providerRepository.save(provider);
    }

    //Read All Providers
    @GetMapping("/getAll")
    public List<Provider> getAlProviders(){
        return providerRepository.findAll();
    }

    //Read Provider with Id = providerName
    @GetMapping("/Providers/{name}")
	public ResponseEntity<Provider> getProviderById(@PathVariable(value = "name") String name)
			throws ResourceNotFoundException {
		Provider provider = providerRepository.findById(name)
				.orElseThrow(() -> new ResourceNotFoundException("Provider not found for name :: " + name));
		return ResponseEntity.ok().body(provider);
	}
    //Update Provider
    @PutMapping("/update")
    public ResponseEntity<Provider> updateProvider(@RequestParam String providerName, 
                                                   @RequestParam String flowName, 
                                                   @RequestBody Time time){

        Provider provider = providerRepository.findById(providerName).orElseThrow();
        provider.setProvider_name(providerName);
        provider.setFlow_name(flowName);
        provider.setDowntime_from(LocalDateTime.parse(time.downtime_from));
        provider.setDowntime_to(LocalDateTime.parse(time.downtime_to));

        providerRepository.save(provider);
        return ResponseEntity.ok(provider);
    }

    //Delete Provider with Id = providerName
    @DeleteMapping("/Providers/{name}")
	public Map<String, Boolean> deleteProvider(@PathVariable(value = "name") String name)
			throws ResourceNotFoundException {
		Provider provider = providerRepository.findById(name)
				.orElseThrow(() -> new ResourceNotFoundException("Provider not found for the name :: " + name));

		providerRepository.delete(provider);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
