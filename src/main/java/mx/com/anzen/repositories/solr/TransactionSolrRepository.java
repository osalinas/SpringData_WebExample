package mx.com.anzen.repositories.solr;

import java.util.List;

import mx.com.anzen.model.Transaction;
import mx.com.anzen.model.solr.impl.TransactionSolrImpl;

import org.springframework.data.solr.repository.SolrCrudRepository;

public interface TransactionSolrRepository extends SolrCrudRepository<TransactionSolrImpl, String> {
	List<Transaction> findByName(String name);
}
