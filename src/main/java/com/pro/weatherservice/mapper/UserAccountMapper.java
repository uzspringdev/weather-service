package com.pro.weatherservice.mapper;

import com.pro.weatherservice.domain.Role;
import com.pro.weatherservice.domain.UserAccount;
import com.pro.weatherservice.dto.UserAccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.LinkedHashSet;
import java.util.Set;

@Mapper
public interface UserAccountMapper {
    UserAccountMapper getInstance = Mappers.getMapper(UserAccountMapper.class);

    UserAccount toEntity(UserAccountDto userAccountDto);

    UserAccountDto toDto(UserAccount userAccount);


    default UserAccount updateFromDto(UserAccountDto userAccountDto, @MappingTarget UserAccount userAccount) {
        if (userAccountDto != null) {
            if (userAccountDto.getId() != null)
                userAccount.setId(userAccountDto.getId());
            if (userAccountDto.getUsername() != null)
                userAccount.setUsername(userAccountDto.getUsername());
            if (userAccountDto.getPassword() != null)
                userAccount.setPassword(userAccountDto.getPassword());
            Set<Role> set;
            if (userAccount.getRoles() != null) {
                set = userAccountDto.getRoles();
                if (set != null) {
                    userAccount.getRoles().addAll(set);
                }
            }

            if (userAccountDto.getUserType() != null)
                userAccount.setUserType(userAccountDto.getUserType());
        }
        return userAccount;
    }
}
