package mx.com.anzen.controllers;

import mx.com.anzen.model.Transaction;
import mx.com.anzen.repositories.solr.TransactionSolrRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/transactions")
public class TransactionsController {
	
	@Autowired TransactionSolrRepository transactionRepository;
	
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Iterable<? extends Transaction> getTransactions(){
		
		Iterable<? extends Transaction> transactions = transactionRepository.findAll();

		return transactions;
	}
}
