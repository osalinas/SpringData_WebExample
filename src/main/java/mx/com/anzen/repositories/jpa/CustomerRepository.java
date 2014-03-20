package mx.com.anzen.repositories.jpa;

import java.util.List;

import mx.com.anzen.model.Customer;
import mx.com.anzen.model.jpa.impl.CustomerJPAImpl;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true) 
public interface CustomerRepository extends JpaRepository<CustomerJPAImpl, Long> {
	
	List<Customer> findByLastname(String lastname, Pageable pageable);
}
