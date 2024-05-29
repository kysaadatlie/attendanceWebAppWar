package com.ucaproject.ucaattendancesystem.service;

import com.ucaproject.ucaattendancesystem.dto.PersonInfoDto;
import com.ucaproject.ucaattendancesystem.dto.PersonInfoResponse;

import java.util.List;

public interface PersonInfoService {
    PersonInfoDto createPersonInfo(Long departmentId, PersonInfoDto personInfoDto);
    List<PersonInfoDto> getPersonInfoByDepartmentId(Long departmentId);
    PersonInfoResponse getAllPersonInfo(int pageNo, int pageSize);
    PersonInfoDto getPersonInfoById(Long personInfoId, Long departmentId);
    PersonInfoDto updatePersonInfo(Long departmentId, Long personInfoId, PersonInfoDto personInfoDto);
    void deletePersonInfo(Long departmentId, Long personInfoId);
}
