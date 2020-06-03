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

import co.edu.usbcali.bank.domain.DocumentType;

@SpringBootTest
@Rollback(false)
public class DocumentTypeRepositoryTest {
	
	final static Logger log =  LoggerFactory.getLogger(DocumentTypeRepository.class);
	
	@Autowired
	DocumentTypeRepository documentTypeRepository;
	
	
	static Long dotyId = null;
	
	@Test
	@DisplayName("save")
	@Transactional(readOnly = false)
	void atest(){
		log.info("Se ejecuto el test");
		
		DocumentType documentType = new DocumentType();
		documentType.setName("Pasaporte");
		documentType.setEnable("S");
		
		documentTypeRepository.save(documentType);
		dotyId = documentType.getDotyId();
	}
	
	@Test
	@DisplayName("findById")
	@Transactional(readOnly = false)
	void btest(){
		log.info("Se ejecuto el fingById");
		Optional<DocumentType> documentTypeOptional = documentTypeRepository.findById(dotyId);
		assertTrue(documentTypeOptional.isPresent(),"El tipo de documento no existe");
	}
	
	@Test
	@DisplayName("update")
	@Transactional(readOnly = false)
	void ctest(){
		log.info("Se ejecuto el fingById");
		Optional<DocumentType> documentTypeOptional = documentTypeRepository.findById(dotyId);
		assertTrue(documentTypeOptional.isPresent(),"El tipo de documento no existe");
		
		documentTypeOptional.ifPresent(documentType->{
			documentType.setName("Passport");
			documentTypeRepository.save(documentType);
		});
	}
	
	@Test
	@DisplayName("delete")
	@Transactional(readOnly = false)
	void dtest(){
		log.info("Se ejecuto el fingById");
		Optional<DocumentType> documentTypeOptional = documentTypeRepository.findById(dotyId);
		assertTrue(documentTypeOptional.isPresent(),"El tipo de documento no existe");
		
		documentTypeOptional.ifPresent(documentType->{
			documentTypeRepository.delete(documentType);
		});
	}
	
	@BeforeEach
	void beforeEach(){
		log.info("Se ejecuto el BeforeEach");
		assertNotNull(documentTypeRepository, "The documentTypeRepository is null");
	}
	
	@AfterEach
	void aftereEach(){
		log.info("Se ejecuto el AfterEach");
		assertNotNull(documentTypeRepository, "The documentTypeRepository is null");
	}
	
	@Test
	void test() {
		
	}
}