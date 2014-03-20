package mx.com.anzen.repositories.jpa;

import java.util.List;

import mx.com.anzen.model.Account;
import mx.com.anzen.model.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true) 
public interface AccountRepository extends JpaRepository<Account, Long>{
	
	List<Account> findByCustomer(Customer customer); 
}
