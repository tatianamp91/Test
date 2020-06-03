package co.edu.usbcali.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.bank.domain.UserType;
import co.edu.usbcali.bank.repository.UserTypeRepository;

@Service
public class UserTypeServiceImpl implements UserTypeService{
	
	@Autowired
	UserTypeRepository userTypeRepository;

	@Override
	@Transactional(readOnly = true)
	public List<UserType> findAll() {
		return userTypeRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<UserType> findById(Long id) {
		return userTypeRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public UserType save(UserType userType) throws Exception {
		return null;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public UserType update(UserType userType) throws Exception {
		return null;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(UserType userType) throws Exception {
		
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Long id) throws Exception {
		
	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return null;
	}

	@Override
	public void validate(UserType userType) throws Exception {
		
	}

}
