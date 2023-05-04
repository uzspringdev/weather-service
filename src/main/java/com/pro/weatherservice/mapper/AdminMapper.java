package com.pro.weatherservice.mapper;

import com.pro.weatherservice.domain.Admin;
import com.pro.weatherservice.domain.Role;
import com.pro.weatherservice.domain.UserAccount;
import com.pro.weatherservice.dto.AdminDto;
import com.pro.weatherservice.dto.UserAccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Mapper
public interface AdminMapper {
    AdminMapper getInstance = Mappers.getMapper(AdminMapper.class);

    Admin toEntity(AdminDto adminDto);

    AdminDto toDto(Admin admin);

    UserAccount toUserAccountEntity(UserAccountDto userAccountDto);

    default Admin updateAdminFromDto(AdminDto adminDto, @MappingTarget Admin admin) {
        if (adminDto != null) {
            if (adminDto.getId() != null)
                admin.setId(adminDto.getId());
            if (adminDto.getUserAccount() != null)
                admin.setUserAccount(updateUserAccountFromDto(adminDto.getUserAccount(), admin.getUserAccount()));
            if (adminDto.getFirstName() != null)
                admin.setFirstName(adminDto.getFirstName());
            if (adminDto.getLastName() != null)
                admin.setLastName(adminDto.getLastName());
            if (adminDto.getPhoneNumber() != null)
                admin.setPhoneNumber(adminDto.getPhoneNumber());
            if (adminDto.getEmail() != null)
                admin.setEmail(adminDto.getEmail());
            if (adminDto.getStatus() != null)
                admin.setStatus(adminDto.getStatus());
            if (adminDto.getLangKey() != null)
                admin.setLangKey(adminDto.getLangKey());
        }
        return admin;
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
            if (userAccountDto.getId() != null)
                userAccount.setCreatedAt(userAccountDto.getCreatedAt());
            if (userAccountDto.getId() != null)
                userAccount.setUpdatedAt(userAccountDto.getUpdatedAt());
        }
        return userAccount;
    }


}
