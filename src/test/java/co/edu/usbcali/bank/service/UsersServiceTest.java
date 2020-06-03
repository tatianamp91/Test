package co.edu.usbcali.bank.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

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
public class UsersServiceTest {

	final static Logger log = LoggerFactory.getLogger(UsersServiceTest.class);

	@Autowired
	UsersService usersService;
	
	@Autowired
	UserTypeService userTypeService;
	
	String userEmail = "tatianamp@bank.com";

	@Test
	void fail() {
		assertThrows(Exception.class, () -> {
			Users users = new Users();
			users.setUserEmail("tatianamp@bank");
			users.setUserType(null);
			users.setEnable("S");
			users.setName("Tatiana Moncada");

			usersService.save(users);
		});
	}
	
	@Test
	@DisplayName("save")
	void atest(){
		log.info("Se ejecuto el test");
		Optional<Users> usersOptional = usersService.findById(userEmail);
		assertFalse(usersOptional.isPresent(), "El usuario ya existe");
		
		Optional<UserType> userTypeOptional = userTypeService.findById(1L);
		assertTrue(userTypeOptional.isPresent(), "El tipo de usuario no existe");
		
		UserType userType = userTypeOptional.get();
		
		Users users = new Users();
		users.setUserEmail(userEmail);
		users.setUserType(userType);
		users.setEnable("S");
		users.setName("Tatiana Moncada");
		
		try {
			usersService.save(users);
		} catch (Exception e) {
			assertNull(e, e.getMessage());
		}
	}
	
	@Test
	@DisplayName("findById")
	void btest(){
		log.info("Se ejecuto el fingById");
		Optional<Users> usersOptional = usersService.findById(userEmail);
		assertTrue(usersOptional.isPresent(), "El usuario no existe");
	}
	
	@Test
	@DisplayName("update")
	@Transactional(readOnly = false)
	void ctest(){
		log.info("Se ejecuto el fingById");
		Optional<Users> usersOptional = usersService.findById(userEmail);
		
		Users users = usersOptional.get();
		users.setName("Sindy Tatiana Moncada Pisso");
		
		try {
			usersService.save(users);
		} catch (Exception e) {
			assertNull(e, e.getMessage());
		}
	}
	
	@Test
	@DisplayName("delete")
	@Transactional(readOnly = false)
	void dtest(){
		log.info("Se ejecuto el fingById");
		Optional<Users> usersOptional = usersService.findById(userEmail);
		assertTrue(usersOptional.isPresent(), "El usuario no existe");
		
		Users users = usersOptional.get();
		
		try {
			usersService.delete(users);
		} catch (Exception e) {
			assertNull(e, e.getMessage());
		}
	}
}