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

import co.edu.usbcali.bank.domain.Client;
import co.edu.usbcali.bank.domain.DocumentType;

@SpringBootTest
@Rollback(false)
public class ClientServiceTest {

	final static Logger log = LoggerFactory.getLogger(ClientServiceTest.class);

	Long clieId = 6060L;

	@Autowired
	ClientService clientService;

	@Autowired
	DocumentTypeService documentTypeService;

	@Test
	void fail() {
		assertThrows(Exception.class, () -> {
			Client client = new Client();
			client.setClieId(clieId);
			client.setAdress("Calle");
			client.setDocumentType(null);
			client.setEmail("tatianamp91@gmail");
			client.setEnable("SI");
			client.setName("");
			client.setPhone("");

			clientService.save(client);
		});
	}

	@Test
	@DisplayName("save")
	void atest() {
		Optional<Client> clientOptional = clientService.findById(clieId);
		assertFalse(clientOptional.isPresent(), "El cliente ya existe");

		Client client = new Client();
		client.setClieId(clieId);
		client.setAdress("Calle 54");
		client.setEmail("tatianamp91@gmail.com");
		client.setEnable("S");
		client.setName("Tatiana Moncada");
		client.setPhone("3006078592");

		Optional<DocumentType> documentypeOptional = documentTypeService.findById(1L);
		assertTrue(documentypeOptional.isPresent(), "El tipo de documento no existe");

		DocumentType documentType = documentypeOptional.get();
		client.setDocumentType(documentType);

		try {
			clientService.save(client);
		} catch (Exception e) {
			assertNull(e, e.getMessage());
		}
	}

	@Test
	@DisplayName("findById")
	@Transactional(readOnly = false)
	void btest() {
		log.info("Se ejecuto el fingById");
		Optional<Client> clientOptional = clientService.findById(clieId);
		assertTrue(clientOptional.isPresent(), "El cliente no existe");
	}

	@Test
	@DisplayName("update")
	void ctest() {
		log.info("Se ejecuto el fingById");
		Optional<Client> clientOptional = clientService.findById(clieId);
		assertTrue(clientOptional.isPresent(), "El cliente no existe");

		Client client = clientOptional.get();	
		client.setAdress("Calle 54 # 4 D 47");

		try {
			clientService.update(client);
		} catch (Exception e) {
			assertNull(e, e.getMessage());
		}
	}

	@Test
	@DisplayName("delete")
	void dtest() {
		log.info("Se ejecuto el fingById");
		Optional<Client> clientOptional = clientService.findById(clieId);
		assertTrue(clientOptional.isPresent(), "El cliente no existe");

		Client client = clientOptional.get();

		try {
			clientService.delete(client);
		} catch (Exception e) {
			assertNull(e, e.getMessage());
		}
	}
}