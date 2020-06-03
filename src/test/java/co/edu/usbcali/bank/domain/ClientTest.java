package co.edu.usbcali.bank.domain;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Rollback (false)
class ClientTest {
	
	@PersistenceContext
	EntityManager entityManager;
	
	Long clieId = 4040L;

	@Test
	@DisplayName("save")
	@Transactional (readOnly = false)
	void test() {
		assertNotNull(entityManager);
		Client client = entityManager.find(Client.class, clieId);
		assertNull(client, "El cliente ya existe");
		
		client = new Client();
		client.setClieId(clieId);
		client.setAdress("Calle 54");
		client.setEmail("tatianamp91@gmail.com");
		client.setEnable("S");
		client.setName("Tatiana Moncada");
		client.setPhone("3006078592");
		
		DocumentType documentType = entityManager.find(DocumentType.class, 1L);
		assertNotNull(documentType, "El tipo de documento no existe");
		
		client.setDocumentType(documentType);
		entityManager.persist(client);
	}
	
	@DisplayName("findById")
	@Test
	@Transactional(readOnly = true)
	void btest() {
		assertNotNull(entityManager);
		Client client = entityManager.find(Client.class, clieId);
		assertNotNull(client, "El cliente ya existe");
	}
	
	@DisplayName("update")
	@Test
	@Transactional(readOnly = false)
	void ctest() {
		assertNotNull(entityManager);
		Client client = entityManager.find(Client.class, clieId);
		assertNotNull(client, "El cliente ya existe");
		
		client.setAdress("Calle 54 # 4 D 47");
		
		entityManager.merge(client);
	}
	
	@DisplayName("delete")
	@Test
	@Transactional(readOnly = false)
	void dtest() {
		assertNotNull(entityManager);
		Client client = entityManager.find(Client.class, clieId);
		assertNotNull(client, "El cliente ya existe");
		
		entityManager.remove(client);
	}
}