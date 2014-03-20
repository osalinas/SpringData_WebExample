package mx.com.anzen.controllers;

import java.util.ArrayList;
import java.util.List;

import mx.com.anzen.model.Customer;
import mx.com.anzen.repositories.jpa.CustomerRepository;
//import mx.com.anzen.repositories.jpa.CustomerRepository;
import mx.com.anzen.repositories.solr.CustomerSolrRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Iterables;

@Controller
@RequestMapping("/customers")
public class CustomersController {
	
	@Autowired CustomerRepository customerRepository;
	@Autowired CustomerSolrRepository customerSolrRepository;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Iterable<? extends Customer> getCurtomers()
	{
		Iterable<? extends Customer> customers =  customerRepository.findAll();
		Iterable<? extends Customer> custoemrs_ = customerSolrRepository.findAll();
		Iterable<? extends Customer> custoemrs_ful = Iterables.concat(customers, custoemrs_);
		
		List<String> ids = new ArrayList<String>();
		
		for(Customer c: customers){
			ids.add(c.getId().toString());
		}
		
		customerSolrRepository.findAll(ids);
		
		return custoemrs_ful;
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void putCurtomer()
	{
		//CustomerRepository.save(entity)
	}

}
