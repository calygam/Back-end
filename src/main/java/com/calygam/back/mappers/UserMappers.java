package com.calygam.back.mappers;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.calygam.back.dtos.DataUtilUserDTO;
import com.calygam.back.enums.UserRankEnum;
import com.calygam.back.enums.UserRoleEnum;
import com.calygam.back.enums.UserStatus;
import com.calygam.back.projections.TeacherDashProjection;

@Component
public class UserMappers {
	public  DataUtilUserDTO toDTO(TeacherDashProjection p) {
		DataUtilUserDTO dataUtilUserDTO = new DataUtilUserDTO();
		dataUtilUserDTO.setUserId(p.getUserId());
		dataUtilUserDTO.setUserName(p.getUserName());
		dataUtilUserDTO.setUserEmail(p.getUserEmail());
	
		dataUtilUserDTO.setUserXp(p.getXp());
		dataUtilUserDTO.setUserImage(p.getUserImagePerfil());
		UserRoleEnum roleTarget = UserRoleEnum.values()[p.getUserRole()];
		dataUtilUserDTO.setUserRole(roleTarget);
		String userRank = UserRankEnum.getRankForXpToString(p.getXp());
		dataUtilUserDTO.setUserRank(userRank);
		
		dataUtilUserDTO.setUserMoney(p.getUserMoney()!=null?p.getUserMoney():0);
		UserStatus statusTarget = UserStatus.values()[p.getUserStatus()];
	
		dataUtilUserDTO.setUserStatus(statusTarget);
		return dataUtilUserDTO;
		//caio <- vamos de maneira organizada mapear e isolar toda a parte que irá retornar na dto
		
		
	}
	public  DataUtilUserDTO toUserDTORefact(TeacherDashProjection p) {
		DataUtilUserDTO dataUtilUserDTO = new DataUtilUserDTO();
		dataUtilUserDTO.setUserId(p.getUserId());
		dataUtilUserDTO.setUserName(p.getUserName());
		dataUtilUserDTO.setUserEmail(p.getUserEmail());

		dataUtilUserDTO.setUserXp(p.getXp());
		dataUtilUserDTO.setUserImage(
				p.getUserImagePerfil()==null?null:
				ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/file/read/user/")        
                .path(p.getUserImagePerfil())
                .toUriString());
		System.out.println("------------------------------------------------------");
		System.out.println("------------------------------------------------------");
		System.out.println("------------------------------------------------------");
		System.out.println("------------------------------------------------------");
		System.out.println("------------------------------------------------------");
		System.out.println("------------------------------------------------------");
		System.out.println("------------------------------------------------------");
		System.out.println("------------------------------------------------------");
		System.out.println("------------------------------------------------------");
		System.out.println("------------------------------------------------------");
		System.out.println("------------------------------------------------------");
		System.out.println("------------------------------------------------------");
		System.out.println("------------------------------------------------------");
		System.out.println(dataUtilUserDTO.getUserImage());
		System.out.println(p.getUserImagePerfil());
		UserRoleEnum roleTarget = UserRoleEnum.values()[p.getUserRole()];
		dataUtilUserDTO.setUserRole(roleTarget);
		String userRank = UserRankEnum.getRankForXpToString(p.getXp());
		dataUtilUserDTO.setUserRank(userRank);
		
		dataUtilUserDTO.setUserMoney(p.getUserMoney()!=null?p.getUserMoney():0);
		UserStatus statusTarget = UserStatus.values()[p.getUserStatus()];

		dataUtilUserDTO.setUserStatus(statusTarget);
		return dataUtilUserDTO;
		//caio <- vamos de maneira organizada mapear e isolar toda a parte que irá retornar na dto
		
		
	}
}





