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

import co.edu.usbcali.bank.domain.UserType;

@SpringBootTest
@Rollback(false)
public class UserTypeRepositoryTest {
	
	final static Logger log =  LoggerFactory.getLogger(UserTypeRepository.class);
	
	@Autowired
	UserTypeRepository userTypeRepository;
	
	
	static Long ustyId = null;
	
	@Test
	@DisplayName("save")
	@Transactional(readOnly = false)
	void atest(){
		log.info("Se ejecuto el test");
		
		UserType userType = new UserType();
		userType.setName("Gerente");
		userType.setEnable("S");
		
		userTypeRepository.save(userType);
		ustyId = userType.getUstyId();
	}
	
	@Test
	@DisplayName("findById")
	@Transactional(readOnly = false)
	void btest(){
		log.info("Se ejecuto el fingById");
		Optional<UserType> userTypeOptional = userTypeRepository.findById(ustyId);
		assertTrue(userTypeOptional.isPresent(),"El tipo de usuario no existe");
	}
	
	@Test
	@DisplayName("update")
	@Transactional(readOnly = false)
	void ctest(){
		log.info("Se ejecuto el fingById");
		Optional<UserType> userTypeOptional = userTypeRepository.findById(ustyId);
		assertTrue(userTypeOptional.isPresent(),"El tipo de usuario no existe");
		
		userTypeOptional.ifPresent(userType->{
			userType.setName("Gerente Comercial");
			userTypeRepository.save(userType);
		});
	}
	
	@Test
	@DisplayName("delete")
	@Transactional(readOnly = false)
	void dtest(){
		log.info("Se ejecuto el fingById");
		Optional<UserType> userTypeOptional = userTypeRepository.findById(ustyId);
		assertTrue(userTypeOptional.isPresent(),"El tipo de usuario no existe");
		
		userTypeOptional.ifPresent(userType->{
			userTypeRepository.delete(userType);
		});
	}
	
	@BeforeEach
	void beforeEach(){
		log.info("Se ejecuto el BeforeEach");
		assertNotNull(userTypeRepository, "The userTypeRepository is null");
	}
	
	@AfterEach
	void aftereEach(){
		log.info("Se ejecuto el AfterEach");
		assertNotNull(userTypeRepository, "The userTypeRepository is null");
	}
	
	@Test
	void test() {
		
	}
}