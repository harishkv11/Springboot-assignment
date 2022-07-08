package com.testing.testing.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.testing.testing.model.Provider;
import com.testing.testing.repository.ProviderRepository;

@RestController
@RequestMapping("/api/v1/providers")
public class ProviderController {
    @Autowired
    private ProviderRepository providerRepository;

    // Create Provider
    @PostMapping("/create")
    public Provider creatProvider(@RequestParam String providerName, 
                                  @RequestParam String flowName,
                                  @RequestParam String downtime_from,
                                  @RequestParam String downtime_to){

        Provider provider = new Provider(providerName,flowName,LocalDateTime.parse(downtime_from),LocalDateTime.parse(downtime_to));
        return providerRepository.save(provider);
    }

    //Read All Providers
    @GetMapping("/getAll")
    public List<Provider> getAlProviders(){
        return providerRepository.findAll();
    }

    //Read Provider with Id = providerName
    @GetMapping("/getbyId/{name}")
    public ResponseEntity<Provider> getProviderWithId(@RequestParam String name){
        Provider provider = providerRepository.findById(name)
                .orElseThrow();
        return ResponseEntity.ok(provider);
    }

    //Update Provider
    @PutMapping("update")
    public ResponseEntity<Provider> updateProvider(@RequestParam String providerName, 
                                                   @RequestParam String flowName, 
                                                   @RequestParam String downtime_from, 
                                                   @RequestParam String downtime_to){

        Provider provider = providerRepository.findById(providerName).orElseThrow();
        provider.setProvider_name(providerName);
        provider.setFlow_name(flowName);
        provider.setDowntime_from(LocalDateTime.parse(downtime_from));
        provider.setDowntime_to(LocalDateTime.parse(downtime_to));

        providerRepository.save(provider);
        return ResponseEntity.ok(provider);
    }

    //Delete Provider with Id = providerName
    @DeleteMapping("deleteById")
    public ResponseEntity<Provider> deleteProviderWithId(@RequestParam String name){
        Provider provider = providerRepository.findById(name).orElseThrow();
        providerRepository.delete(provider);
        return ResponseEntity.ok(provider);
    }

}
