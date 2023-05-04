package com.pro.weatherservice.mapper;

import com.pro.weatherservice.domain.*;
import com.pro.weatherservice.dto.UserDto;
import com.pro.weatherservice.dto.UserAccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface UserMapper {
    UserMapper getInstance = Mappers.getMapper(UserMapper.class);

    User toEntity(UserDto userDto);

    UserDto toDto(User user);

    UserAccount toUserAccountEntity(UserAccountDto userAccountDto);

    default User updateUserFromDto(UserDto userDto, @MappingTarget User user) {
        if (userDto != null) {
            if (userDto.getId() != null)
                user.setId(userDto.getId());
            if (userDto.getUserAccount() != null)
                user.setUserAccount(updateUserAccountFromDto(userDto.getUserAccount(), user.getUserAccount()));
            if (userDto.getFirstName() != null)
                user.setFirstName(userDto.getFirstName());
            if (userDto.getLastName() != null)
                user.setLastName(userDto.getLastName());
            if (userDto.getPhoneNumber() != null)
                user.setPhoneNumber(userDto.getPhoneNumber());
            if (userDto.getEmail() != null)
                user.setEmail(userDto.getEmail());
            Set<City> set;
            if (user.getCities() != null) {
                set = userDto.getCities();
                if (set != null) {
                    set.stream().filter(city -> !user.getCities().contains(city))
                            .forEach(user.getCities()::add);
                }
            }
            if (userDto.getStatus() != null)
                user.setStatus(userDto.getStatus());
            if (userDto.getLangKey() != null)
                user.setLangKey(userDto.getLangKey());
        }
        return user;
    }

    private UserAccount updateUserAccountFromDto(UserAccountDto userAccountDto, @MappingTarget UserAccount userAccount) {
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
                    set.stream().filter(role -> !userAccount.getRoles().contains(role))
                            .forEach(userAccount.getRoles()::add);
                }
            }

            if (userAccountDto.getId() != null)
                userAccount.setUserType(userAccountDto.getUserType());
        }
        return userAccount;
    }


}
