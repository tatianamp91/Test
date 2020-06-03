package co.edu.usbcali.bank.repository;

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
import co.edu.usbcali.bank.domain.RegisteredAccount;

@SpringBootTest
@Rollback(false)
public class RegisteredAccountRepositoryTest {
	
	final static Logger log =  LoggerFactory.getLogger(RegisteredAccountRepository.class);
	
	@Autowired
	RegisteredAccountRepository RegisteredAccountRepository;
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	static Long reacId = null;
	
	@Test
	@DisplayName("save")
	@Transactional(readOnly = false)
	void atest(){
		log.info("Se ejecuto el test");
		
		Optional<Client> clientOptional = clientRepository.findById(1L);
		assertTrue(clientOptional.isPresent(), "El cliente no existe");
		
		Optional<Account> accountOptional = accountRepository.findById("0031-0825-4207-7451");
		assertTrue(accountOptional.isPresent(), "La cuenta no existe");
		
		Client client = clientOptional.get();
		Account account = accountOptional.get();
		
		RegisteredAccount registeredAccount = new RegisteredAccount();
		registeredAccount.setClient(client);
		registeredAccount.setAccount(account);
		registeredAccount.setEnable("S");
		
		RegisteredAccountRepository.save(registeredAccount);
		reacId = registeredAccount.getReacId();
	}
	
	@Test
	@DisplayName("findById")
	@Transactional(readOnly = false)
	void btest(){
		log.info("Se ejecuto el fingById");
		Optional<RegisteredAccount> registeredAccountOptional = RegisteredAccountRepository.findById(reacId);
		assertTrue(registeredAccountOptional.isPresent(), "El registro no existe");
	}
	
	@Test
	@DisplayName("update")
	@Transactional(readOnly = false)
	void ctest(){
		log.info("Se ejecuto el fingById");
		Optional<RegisteredAccount> registeredAccountOptional = RegisteredAccountRepository.findById(reacId);
		assertTrue(registeredAccountOptional.isPresent(), "El registro no existe");
		
		registeredAccountOptional.ifPresent(registeredAccount->{
			registeredAccount.setEnable("N");
			RegisteredAccountRepository.save(registeredAccount);
		});
	}
	
	@Test
	@DisplayName("delete")
	@Transactional(readOnly = false)
	void dtest(){
		log.info("Se ejecuto el fingById");
		Optional<RegisteredAccount> registeredAccountOptional = RegisteredAccountRepository.findById(reacId);
		assertTrue(registeredAccountOptional.isPresent(), "El registro no existe");
		
		registeredAccountOptional.ifPresent(registeredAccount->{
			RegisteredAccountRepository.delete(registeredAccount);
		});
	}
	
	@BeforeEach
	void beforeEach(){
		log.info("Se ejecuto el BeforeEach");
		assertNotNull(RegisteredAccountRepository, "The RegisteredAccountRepository is null");
		assertNotNull(clientRepository, "The clientRepository is null");
		assertNotNull(accountRepository, "The accountRepository is null");
	}
	
	@AfterEach
	void aftereEach(){
		log.info("Se ejecuto el AfterEach");
		assertNotNull(RegisteredAccountRepository, "The RegisteredAccountRepository is null");
	}
	
	@Test
	void test() {
		
	}
}