package co.edu.usbcali.bank.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.bank.domain.Users;
import co.edu.usbcali.bank.repository.UserTypeRepository;
import co.edu.usbcali.bank.repository.UsersRepository;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	UserTypeRepository userTypeRepository;

	@Autowired
	Validator validator;

	@Override
	@Transactional(readOnly = true)
	public List<Users> findAll() {
		return usersRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Users> findById(String id) {
		return usersRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Users save(Users user) throws Exception {
		validate(user);
		if (usersRepository.findById(user.getUserEmail()).isPresent()) {
			throw new Exception("El usuario con id:" + user.getUserEmail() + " ya existe");
		}
		return usersRepository.save(user);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Users update(Users user) throws Exception {
		validate(user);
		Optional<Users> usersOptional = usersRepository.findById(user.getUserEmail());
		if (usersOptional.isPresent() == false) {
			throw new Exception("El usuario con id:" + user.getUserEmail() + " no existe");
		}
		return usersRepository.save(user);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Users user) throws Exception {
		if (user == null) {
			throw new Exception("El usuario es nulo");
		}
		Optional<Users> usersOptional = usersRepository.findById(user.getUserEmail());

		if (usersOptional.isPresent() == false) {
			throw new Exception("El usuario con id:" + user.getUserEmail() + " no existe");
		}

		user = usersOptional.get();

		if (user.getTransactions().size() > 0) {
			throw new Exception("El usuario con id:" + user.getUserEmail() + " tiene transacciones asociadas");
		}

		usersRepository.delete(user);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(String id) throws Exception {
		Optional<Users> usersOptional = usersRepository.findById(id);
		if (usersOptional.isPresent() == false) {
			throw new Exception("El usuario con id:" + id + " no existe");
		}

		usersRepository.delete(usersOptional.get());
	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return null;
	}

	@Override
	public void validate(Users user) throws Exception {
		if (user == null) {
			throw new Exception("El usuario es nulo");
		}

		Set<ConstraintViolation<Users>> constraintViolations = validator.validate(user);

		if (constraintViolations.size() > 0) {
			StringBuilder strMessage = new StringBuilder();

			for (ConstraintViolation<Users> constraintViolation : constraintViolations) {
				strMessage.append(constraintViolation.getPropertyPath().toString());
				strMessage.append(" - ");
				strMessage.append(constraintViolation.getMessage());
				strMessage.append(". \n");
			}
			throw new Exception(strMessage.toString());
		}
	}
}