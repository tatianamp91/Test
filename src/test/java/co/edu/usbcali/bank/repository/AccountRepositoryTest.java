package co.edu.usbcali.bank.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.bank.domain.Account;
import co.edu.usbcali.bank.domain.Client;

@SpringBootTest
@Rollback(false)
public class AccountRepositoryTest {
	
	final static Logger log =  LoggerFactory.getLogger(AccountRepository.class);
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	ClientRepository clientRepository;
	
	String accoId = "0000-1234-1234-1234";
	
	@Test
	@DisplayName("save")
	@Transactional(readOnly = false)
	void atest(){
		log.info("Se ejecuto el test");
		Optional<Account> accountOptional = accountRepository.findById(accoId);
		assertFalse(accountOptional.isPresent(), "La cuenta ya existe");
		
		Optional<Client> clientOptional = clientRepository.findById(1L);
		assertTrue(clientOptional.isPresent(), "El cliente no existe");
		
		Client client = clientOptional.get();
		
		Account account = new Account();
		account.setAccoId(accoId);
		account.setBalance(10000D);
		account.setClient(client);
		account.setEnable("S");
		account.setPassword("1234");
		account.setVersion(1L);
		
		accountRepository.save(account);
	}
	
	@Test
	@DisplayName("findById")
	@Transactional(readOnly = false)
	void btest(){
		log.info("Se ejecuto el fingById");
		Optional<Account> accountOptional = accountRepository.findById(accoId);
		assertTrue(accountOptional.isPresent(),"La cuenta no existe");
	}
	
	@Test
	@DisplayName("update")
	@Transactional(readOnly = false)
	void ctest(){
		log.info("Se ejecuto el fingById");
		Optional<Account> accountOptional = accountRepository.findById(accoId);
		assertTrue(accountOptional.isPresent(),"La cuenta no existe");
		
		accountOptional.ifPresent(account->{
			account.setBalance(20000D);
			accountRepository.save(account);
		});
	}
	
	@Test
	@DisplayName("delete")
	@Transactional(readOnly = false)
	void dtest(){
		log.info("Se ejecuto el fingById");
		Optional<Account> accountOptional = accountRepository.findById(accoId);
		assertTrue(accountOptional.isPresent(),"La cuenta no existe");
		
		accountOptional.ifPresent(account->{
			accountRepository.delete(account);
		});
	}
	
	@BeforeEach
	void beforeEach(){
		log.info("Se ejecuto el BeforeEach");
		assertNotNull(accountRepository, "The accountRepository is null");
		assertNotNull(clientRepository, "The clientRepository is null");
	}
	
	@AfterEach
	void aftereEach(){
		log.info("Se ejecuto el AfterEach");
		assertNotNull(accountRepository, "The accountRepository is null");
	}
	
	@Test
	void test() {
		
	}
}