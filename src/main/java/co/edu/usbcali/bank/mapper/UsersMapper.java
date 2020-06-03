package co.edu.usbcali.bank.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.edu.usbcali.bank.domain.Users;
import co.edu.usbcali.bank.dto.UsersDTO;

@Mapper
public interface UsersMapper {
	
	@Mapping(source = "userType.ustyId", target = "ustyId")
	UsersDTO toUsersDTO(Users user);
	
	@Mapping(target = "userType.ustyId", source = "ustyId")
	Users toUsers(UsersDTO usersDTO);
	
	List<UsersDTO> toUsersDTOs(List<Users> users);
	
	List<Users> toUsers(List<UsersDTO> usersDTOs);
}
