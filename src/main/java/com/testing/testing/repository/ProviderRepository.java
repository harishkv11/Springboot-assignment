package com.testing.testing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.testing.testing.model.Provider;

public interface ProviderRepository extends JpaRepository<Provider,String>{
    
}
