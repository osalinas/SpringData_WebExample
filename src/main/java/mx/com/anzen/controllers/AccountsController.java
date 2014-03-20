package mx.com.anzen.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.com.anzen.model.Account;
import mx.com.anzen.model.redis.impl.AccountRedisImpl;
import mx.com.anzen.repositories.redis.AccountRedisRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/accounts")
public class AccountsController {
	
	@Autowired AccountRedisRepository accountRedisRepository;

	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public void postAccount(@RequestBody AccountRedisImpl acc){
		
	}
	
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Account> getAccounts(){
		List<Account> accounts = new ArrayList<Account>(0);
		accounts.add(new AccountRedisImpl(1L, new Date()));
		accounts.add(new AccountRedisImpl(2L, new Date()));
		accounts.add(new AccountRedisImpl(3L, new Date()));
		accounts.add(new AccountRedisImpl(4L, new Date()));
		accounts.add(new AccountRedisImpl(5L, new Date()));
		accounts.add(new AccountRedisImpl(6L, new Date()));
		
		try{
			Account acc = new AccountRedisImpl(6L, new Date());
			accountRedisRepository.save(acc);
			System.out.println("accountRedisRepository.count() = "+ accountRedisRepository.count());
			System.out.println("accountRedisRepository.exists() = "+accountRedisRepository.exists(acc.getId().toString()));
			System.out.println("accountRedisRepository.findOne() = "+accountRedisRepository.findOne(acc.getId().toString()));
			
			System.out.println("accountRedisRepository.findAll() = ");
			
			Iterable<Account> itr = accountRedisRepository.findAll();
			for(Account a: itr){
				System.out.println(">>>>>>>>>>>>>>> ");
				System.out.println("a = "+a);
				System.out.println("a.getId() = "+a.getId());
				System.out.println("a.getExpireat() = "+a.getExpireat());
			}
			accountRedisRepository.delete(acc);
			
			///////////////////////////////
			Account acc1 = new AccountRedisImpl(89L, new Date());
			accountRedisRepository.save(acc1);
			accountRedisRepository.delete(acc1);
			///////////////////////////////
			Account acc2 = new AccountRedisImpl(8L, new Date());
			accountRedisRepository.save(acc2);
			accountRedisRepository.delete(acc2.getId().toString());
			///////////////////////////////
			List<Account> arr = new ArrayList<Account>(0);
			arr.add(new AccountRedisImpl(1L, new Date()));
			arr.add(new AccountRedisImpl(2L, new Date()));
			arr.add(new AccountRedisImpl(3L, new Date()));
			arr.add(new AccountRedisImpl(4L, new Date()));
			arr.add(new AccountRedisImpl(5L, new Date()));
			arr.add(new AccountRedisImpl(6L, new Date()));
			arr.add(new AccountRedisImpl(7L, new Date()));
			arr.add(new AccountRedisImpl(8L, new Date()));
			accountRedisRepository.save(arr);
			accountRedisRepository.delete(arr);
			
			accountRedisRepository.save(arr);
			accountRedisRepository.deleteAll();
			
			accountRedisRepository.save(arr);
			Iterable<Account> itr2 = accountRedisRepository.findAll();
			for(Account  a : itr2){
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>><<");
				System.out.println("z = "+a);
				System.out.println("z.getId() = "+a.getId());
				System.out.println("z.getExpireat() = "+a.getExpireat());
			}
			
			List<String> keys = new ArrayList<String>();
			keys.add("1");
			keys.add("2");
			keys.add("3");
			
			Iterable<Account> itr3 =accountRedisRepository.findAll(keys);
			for(Account  b : itr3){
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>><<");
				System.out.println("b = "+b);
				System.out.println("b.getId() = "+b.getId());
				System.out.println("b.getExpireat() = "+b.getExpireat());
			}
			
			Sort sort = new Sort(Direction.DESC, "id");
			Iterable<Account> itr4 = accountRedisRepository.findAll(sort);
			for(Account  c : itr4){
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>><<");
				System.out.println("c = "+c);
				System.out.println("c.getId() = "+c.getId());
				System.out.println("c.getExpireat() = "+c.getExpireat());
			}
			
			PageRequest page = new PageRequest(1,2,Direction.ASC, new String[]{"id"});
			
			Iterable<Account> itr5 = accountRedisRepository.findAll(page);
			for(Account  e : itr5){
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>><<");
				System.out.println("e = "+e);
				System.out.println("e.getId() = "+e.getId());
				System.out.println("e.getExpireat() = "+e.getExpireat());
			}
			///////////////////////////////
			
			
		}catch(Exception e){
			e.printStackTrace(System.out);
		}
		
		return accounts;
	}
}
