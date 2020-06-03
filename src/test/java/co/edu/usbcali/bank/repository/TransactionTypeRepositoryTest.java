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

import co.edu.usbcali.bank.domain.TransactionType;

@SpringBootTest
@Rollback(false)
public class TransactionTypeRepositoryTest {
	
	final static Logger log =  LoggerFactory.getLogger(TransactionTypeRepository.class);
	
	@Autowired
	TransactionTypeRepository transactionTypeRepository;
	
	
	static Long trtyId = null;
	
	@Test
	@DisplayName("save")
	@Transactional(readOnly = false)
	void atest(){
		log.info("Se ejecuto el test");
		
		TransactionType transactionType = new TransactionType();
		transactionType.setName("PAGO");
		transactionType.setEnable("S");
		
		transactionTypeRepository.save(transactionType);
		trtyId = transactionType.getTrtyId();
	}
	
	@Test
	@DisplayName("findById")
	@Transactional(readOnly = false)
	void btest(){
		log.info("Se ejecuto el fingById");
		Optional<TransactionType> transactionTypeOptional = transactionTypeRepository.findById(trtyId);
		assertTrue(transactionTypeOptional.isPresent(),"El tipo de transacción no existe");
	}
	
	@Test
	@DisplayName("update")
	@Transactional(readOnly = false)
	void ctest(){
		log.info("Se ejecuto el fingById");
		Optional<TransactionType> transactionTypeOptional = transactionTypeRepository.findById(trtyId);
		assertTrue(transactionTypeOptional.isPresent(),"El tipo de transacción no existe");
		
		transactionTypeOptional.ifPresent(transactionType->{
			transactionType.setName("PAGO TARJETA CREDITO");
			transactionTypeRepository.save(transactionType);
		});
	}
	
	@Test
	@DisplayName("delete")
	@Transactional(readOnly = false)
	void dtest(){
		log.info("Se ejecuto el fingById");
		Optional<TransactionType> transactionTypeOptional = transactionTypeRepository.findById(trtyId);
		assertTrue(transactionTypeOptional.isPresent(),"El tipo de transacción no existe");
		
		transactionTypeOptional.ifPresent(transactionType->{
			transactionTypeRepository.delete(transactionType);
		});
	}
	
	@BeforeEach
	void beforeEach(){
		log.info("Se ejecuto el BeforeEach");
		assertNotNull(transactionTypeRepository, "The transactionTypeRepository is null");
	}
	
	@AfterEach
	void aftereEach(){
		log.info("Se ejecuto el AfterEach");
		assertNotNull(transactionTypeRepository, "The transactionTypeRepository is null");
	}
	
	@Test
	void test() {
		
	}
}