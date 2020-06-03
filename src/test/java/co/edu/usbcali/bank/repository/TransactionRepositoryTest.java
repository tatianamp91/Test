package co.edu.usbcali.bank.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
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
import co.edu.usbcali.bank.domain.Transaction;
import co.edu.usbcali.bank.domain.TransactionType;
import co.edu.usbcali.bank.domain.Users;

@SpringBootTest
@Rollback(false)
public class TransactionRepositoryTest {
	
	final static Logger log =  LoggerFactory.getLogger(TransactionRepository.class);
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	TransactionTypeRepository transactionTypeRepository;
	
	@Autowired
	UsersRepository usersRepository;
	
	static Long tranId = null;
	
	@Test
	@DisplayName("save")
	@Transactional(readOnly = false)
	void atest(){
		log.info("Se ejecuto el test");
		
		Optional<Account> accountOptional = accountRepository.findById("0000-6776-1365-3228");
		assertTrue(accountOptional.isPresent(), "La cuenta no existe");
		
		Optional<TransactionType> transactionTypeOptional = transactionTypeRepository.findById(1L);
		assertTrue(transactionTypeOptional.isPresent(), "El tipo de transacción no existe");
		
		Optional<Users> usersOptional = usersRepository.findById("aadamoviche1@upenn.edu");
		assertTrue(usersOptional.isPresent(), "El usuario no existe");
		
		Account account = accountOptional.get();
		TransactionType transactionType = transactionTypeOptional.get();
		Users users = usersOptional.get();
		
		Transaction transaction = new Transaction();
		transaction.setTranId(null);
		transaction.setAccount(account);
		transaction.setTransactionType(transactionType);
		transaction.setUsers(users);
		transaction.setAmount(20000D);
		transaction.setDate(new Date());
		
		transactionRepository.save(transaction);
		tranId = transaction.getTranId();
	}
	
	@Test
	@DisplayName("findById")
	@Transactional(readOnly = false)
	void btest(){
		log.info("Se ejecuto el fingById");
		Optional<Transaction> transactionOptional = transactionRepository.findById(tranId);
		assertTrue(transactionOptional.isPresent(),"La transacción no existe");
	}
	
	@Test
	@DisplayName("update")
	@Transactional(readOnly = false)
	void ctest(){
		log.info("Se ejecuto el fingById");
		Optional<Transaction> transactionOptional = transactionRepository.findById(tranId);
		assertTrue(transactionOptional.isPresent(),"La transacción no existe");
		
		transactionOptional.ifPresent(transaction->{
			transaction.setAmount(30000D);
			transactionRepository.save(transaction);
		});
	}
	
	@Test
	@DisplayName("delete")
	@Transactional(readOnly = false)
	void dtest(){
		log.info("Se ejecuto el fingById");
		Optional<Transaction> transactionOptional = transactionRepository.findById(tranId);
		assertTrue(transactionOptional.isPresent(),"La transacción no existe");
		
		transactionOptional.ifPresent(transaction->{
			transactionRepository.delete(transaction);
		});
	}
	
	@BeforeEach
	void beforeEach(){
		log.info("Se ejecuto el BeforeEach");
		assertNotNull(transactionRepository, "The transactionRepository is null");
		assertNotNull(accountRepository, "The accountRepository is null");
		assertNotNull(transactionTypeRepository, "The transactionTypeRepository is null");
		assertNotNull(usersRepository, "The usersRepository is null");
	}
	
	@AfterEach
	void aftereEach(){
		log.info("Se ejecuto el AfterEach");
		assertNotNull(transactionRepository, "The transactionRepository is null");
	}	

	@Test
	void test() {
		
	}
}