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
import co.edu.usbcali.bank.domain.Client;
import co.edu.usbcali.bank.domain.DocumentType;

@SpringBootTest
@Rollback(false)
public class ClientRepositoryTest {
	
	final static Logger log =  LoggerFactory.getLogger(ClientRepository.class);
	
	@Autowired
	ClientRepository clientReposity;
	
	@Autowired
	DocumentTypeRepository documentTypeRepository;
	
	Long clieId = 4040L;
	
	@Test
	@DisplayName("save")
	@Transactional(readOnly = false)
	void atest(){
		log.info("Se ejecuto el test");
		Optional<Client> clientOptional = clientReposity.findById(clieId);
		assertFalse(clientOptional.isPresent(), "El cliente ya existe");
		
		Client client = new Client();
		client.setClieId(clieId);
		client.setAdress("Calle 54");
		client.setEmail("tatianamp91@gmail.com");
		client.setEnable("S");
		client.setName("Tatiana Moncada");
		client.setPhone("3006078592");
		
		Optional<DocumentType> documentypeOptional = documentTypeRepository.findById(1L);
		assertTrue(documentypeOptional.isPresent(),"El tipo de documento no existe");
		
		DocumentType documentType = documentypeOptional.get();
		client.setDocumentType(documentType);
		
		clientReposity.save(client);
	}
	
	@Test
	@DisplayName("findById")
	@Transactional(readOnly = false)
	void btest(){
		log.info("Se ejecuto el fingById");
		Optional<Client> clientOptional = clientReposity.findById(clieId);
		assertTrue(clientOptional.isPresent(),"El cliente no existe");
	}
	
	@Test
	@DisplayName("update")
	@Transactional(readOnly = false)
	void ctest(){
		log.info("Se ejecuto el fingById");
		Optional<Client> clientOptional = clientReposity.findById(clieId);
		assertTrue(clientOptional.isPresent(),"El cliente no existe");
		
		clientOptional.ifPresent(client->{
			client.setAdress("Calle 54 # 4 D 47");
			clientReposity.save(client);
		});
	}
	
	@Test
	@DisplayName("delete")
	@Transactional(readOnly = false)
	void dtest(){
		log.info("Se ejecuto el fingById");
		Optional<Client> clientOptional = clientReposity.findById(clieId);
		assertTrue(clientOptional.isPresent(),"El cliente no existe");
		
		clientOptional.ifPresent(client->{
			clientReposity.delete(client);
		});
	}
	
	@BeforeEach
	void beforeEach(){
		log.info("Se ejecuto el BeforeEach");
		assertNotNull(clientReposity, "The clientRepository is null");
		assertNotNull(documentTypeRepository, "The Document Type is null");
	}
	
	@AfterEach
	void aftereEach(){
		log.info("Se ejecuto el AfterEach");
		assertNotNull(clientReposity, "The clientRepository is null");
	}
	
	@Test
	void test() {
		
	}
}