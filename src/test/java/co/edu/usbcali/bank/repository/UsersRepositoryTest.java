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

import co.edu.usbcali.bank.domain.UserType;
import co.edu.usbcali.bank.domain.Users;

@SpringBootTest
@Rollback(false)
public class UsersRepositoryTest {
	
	final static Logger log =  LoggerFactory.getLogger(UsersRepository.class);
	
	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	UserTypeRepository userTypeRepository;
	
	String userEmail = "tatianamp@bank.com";
	
	@Test
	@DisplayName("save")
	@Transactional(readOnly = false)
	void atest(){
		log.info("Se ejecuto el test");
		Optional<Users> usersOptional = usersRepository.findById(userEmail);
		assertFalse(usersOptional.isPresent(), "El usuario ya existe");
		
		Optional<UserType> userTypeOptional = userTypeRepository.findById(1L);
		assertTrue(userTypeOptional.isPresent(), "El tipo de usuario no existe");
		
		UserType userType = userTypeOptional.get();
		
		Users users = new Users();
		users.setUserEmail(userEmail);
		users.setUserType(userType);
		users.setEnable("S");
		users.setName("Tatiana Moncada");
		
		usersRepository.save(users);
	}
	
	@Test
	@DisplayName("findById")
	@Transactional(readOnly = false)
	void btest(){
		log.info("Se ejecuto el fingById");
		Optional<Users> usersOptional = usersRepository.findById(userEmail);
		assertTrue(usersOptional.isPresent(), "El usuario no existe");
	}
	
	@Test
	@DisplayName("update")
	@Transactional(readOnly = false)
	void ctest(){
		log.info("Se ejecuto el fingById");
		Optional<Users> usersOptional = usersRepository.findById(userEmail);
		assertTrue(usersOptional.isPresent(), "El usuario no existe");
		
		usersOptional.ifPresent(users->{
			users.setName("Sindy Tatiana Moncada Pisso");
			usersRepository.save(users);
		});
	}
	
	@Test
	@DisplayName("delete")
	@Transactional(readOnly = false)
	void dtest(){
		log.info("Se ejecuto el fingById");
		Optional<Users> usersOptional = usersRepository.findById(userEmail);
		assertTrue(usersOptional.isPresent(), "El usuario no existe");
		
		usersOptional.ifPresent(users->{
			usersRepository.delete(users);
		});
	}
	
	@BeforeEach
	void beforeEach(){
		log.info("Se ejecuto el BeforeEach");
		assertNotNull(usersRepository, "The usersRepository is null");
		assertNotNull(userTypeRepository, "The userTypeRepository is null");
	}
	
	@AfterEach
	void aftereEach(){
		log.info("Se ejecuto el AfterEach");
		assertNotNull(usersRepository, "The usersRepository is null");
	}
	
	@Test
	void test() {
		
	}
}