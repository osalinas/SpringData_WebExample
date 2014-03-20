package mx.com.anzen.repositories.solr;

import mx.com.anzen.model.solr.impl.CustomerSolrImpl;

import org.springframework.data.solr.repository.SolrCrudRepository;

public interface CustomerSolrRepository extends SolrCrudRepository<CustomerSolrImpl, String> {

}
