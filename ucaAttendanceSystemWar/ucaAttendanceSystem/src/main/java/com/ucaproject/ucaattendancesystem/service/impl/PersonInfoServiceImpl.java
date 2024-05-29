package com.ucaproject.ucaattendancesystem.service.impl;

import com.ucaproject.ucaattendancesystem.dto.PersonInfoDto;
import com.ucaproject.ucaattendancesystem.dto.PersonInfoResponse;
import com.ucaproject.ucaattendancesystem.entity.Department;
import com.ucaproject.ucaattendancesystem.entity.PersonInfo;
import com.ucaproject.ucaattendancesystem.entity.RoomNo;
import com.ucaproject.ucaattendancesystem.exceptions.DepartmentNotFoundException;
import com.ucaproject.ucaattendancesystem.exceptions.PersonInfoNotFoundException;
import com.ucaproject.ucaattendancesystem.exceptions.RoomNoNotFoundException;
import com.ucaproject.ucaattendancesystem.repository.DepartmentRepository;
import com.ucaproject.ucaattendancesystem.repository.PersonInfoRepository;
import com.ucaproject.ucaattendancesystem.repository.RoomNoRepository;
import com.ucaproject.ucaattendancesystem.service.PersonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonInfoServiceImpl implements PersonInfoService {

    private PersonInfoRepository personInfoRepository;
    private DepartmentRepository departmentRepository;

    @Autowired
    public PersonInfoServiceImpl(PersonInfoRepository personInfoRepository, DepartmentRepository departmentRepository) {
        this.personInfoRepository = personInfoRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public PersonInfoDto createPersonInfo(Long departmentId, PersonInfoDto personInfoDto) {
        PersonInfo personInfo = mapToEntity(personInfoDto);
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new DepartmentNotFoundException("Department not found"));

        personInfo.setDepartment(department);

        PersonInfo newPersonInfo = personInfoRepository.save(personInfo);
        return mapToDto(newPersonInfo);
    }

    @Override
    public List<PersonInfoDto> getPersonInfoByDepartmentId(Long departmentId) {
        List<PersonInfo> persons = personInfoRepository.findByDepartmentId(departmentId);
        return persons.stream().map(personInfo -> mapToDto(personInfo)).collect(Collectors.toList());
    }

    @Override
    public PersonInfoResponse getAllPersonInfo(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<PersonInfo> persons = personInfoRepository.findAll(pageable);
        List<PersonInfo> listOfPerson = persons.getContent();
        List<PersonInfoDto> content = listOfPerson.stream().map(p -> mapToDto(p)).collect(Collectors.toList());

        PersonInfoResponse personResponse = new PersonInfoResponse();
        personResponse.setContent(content);
        personResponse.setPageNo(persons.getNumber());
        personResponse.setPageSize(persons.getSize());
        personResponse.setTotalElements(persons.getTotalElements());
        personResponse.setTotalPages(persons.getTotalPages());
        personResponse.setLast(persons.isLast());

        return personResponse;
    }

    @Override
    public PersonInfoDto getPersonInfoById(Long personInfoId, Long departmentId) {

        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new DepartmentNotFoundException("Department not found"));
        PersonInfo person = personInfoRepository.findById(personInfoId).orElseThrow(() -> new PersonInfoNotFoundException("PersonInfo not found"));

//        if(person.getDepartment().getId() != person.getId()) {
//            throw new PersonInfoNotFoundException("This person doesn't belong to this department");
//        }

        return mapToDto(person);
    }

    @Override
    public PersonInfoDto updatePersonInfo(Long departmentId, Long personInfoId, PersonInfoDto personInfoDto) {

        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new DepartmentNotFoundException("Department not found"));
        PersonInfo person = personInfoRepository.findById(personInfoId).orElseThrow(() -> new PersonInfoNotFoundException("PersonInfo not found"));

//        if(person.getDepartment().getId() != person.getId()) {
//            throw new PersonInfoNotFoundException("This person doesn't belong to this department");
//        }

        person.setEmail(personInfoDto.getEmail());
        person.setPhone(personInfoDto.getPhone());
        person.setCardNo(personInfoDto.getCardNo());
        person.setGender(personInfoDto.getGender());
        person.setAddress(personInfoDto.getAddress());
        person.setRole(personInfoDto.getRole());
        person.setLocation(personInfoDto.getLocation());

        PersonInfo updatedPerson = personInfoRepository.save(person);

        return mapToDto(updatedPerson);
    }

    @Override
    public void deletePersonInfo(Long personInfoId, Long departmentId) {

        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new DepartmentNotFoundException("Department not found"));
        PersonInfo person = personInfoRepository.findById(personInfoId).orElseThrow(() -> new PersonInfoNotFoundException("PersonInfo not found"));

//        if(person.getDepartment().getId() != person.getId()) {
//            throw new PersonInfoNotFoundException("This person doesn't belong to this department");
//        }

        personInfoRepository.delete(person);
    }

    private PersonInfoDto mapToDto(PersonInfo person) {
        PersonInfoDto personInfoDto = new PersonInfoDto();
        personInfoDto.setId(person.getId());
        personInfoDto.setEmail(person.getEmail());
        personInfoDto.setPhone(person.getPhone());
        personInfoDto.setGender(person.getGender());
        personInfoDto.setCardNo(person.getCardNo());
        personInfoDto.setAddress(person.getAddress());
        personInfoDto.setRole(person.getRole());
        personInfoDto.setLocation(person.getLocation());
        return personInfoDto;
    }

    private PersonInfo mapToEntity(PersonInfoDto personInfoDto) {
        PersonInfo personInfo = new PersonInfo();
        personInfo.setEmail(personInfoDto.getEmail());
        personInfo.setPhone(personInfoDto.getPhone());
        personInfo.setGender(personInfoDto.getGender());
        personInfo.setCardNo(personInfoDto.getCardNo());
        personInfo.setAddress(personInfoDto.getAddress());
        personInfo.setRole(personInfoDto.getRole());
        personInfo.setLocation(personInfoDto.getLocation());
        return personInfo;
    }
}
