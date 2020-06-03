package co.edu.usbcali.bank.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.edu.usbcali.bank.domain.Client;

@SpringBootTest
public class ClientRepositoryQueryTest {
	
	final static Logger log =  LoggerFactory.getLogger(ClientRepository.class);
	
	@Autowired
	ClientRepository clientReposity;
	
	@Test
	void findWithTwoAccounts() {
		List<Client> clients = clientReposity.findWithTwoAccounts();
		clients.forEach(client->log.info(client.getName()));
	}

	@Test
	void findAll() {
		List<Client> clients = clientReposity.findAll();
		clients.forEach(client->log.info(client.getName()));
	}
	
	@Test
	void findByEmail() {
		List<Client> clients = clientReposity.findByEmail("tatianampq@gmail.com");
		clients.forEach(client->log.info(client.getName()));
	}
	
	@Test
	void findByNameLike() {
		List<Client> clients = clientReposity.findByNameLike("%T%");
		clients.forEach(client->log.info(client.getName()));
	}
}
