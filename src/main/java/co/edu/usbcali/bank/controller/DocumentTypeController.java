package co.edu.usbcali.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.bank.mapper.DocumentTypeMapper;
import co.edu.usbcali.bank.service.DocumentTypeService;

@RestController
@RequestMapping("/api/documentType/")
@CrossOrigin("*")
public class DocumentTypeController {
	
	@Autowired
	DocumentTypeService documentTypeService;
	
	@Autowired
	DocumentTypeMapper documentTypeMapper;
	
	@GetMapping("findAll")
	public ResponseEntity<?> findAll(){
		return ResponseEntity.ok().body(
				documentTypeMapper.toDocumentTypeDTOs(documentTypeService.findAll())
				);
	}

}
