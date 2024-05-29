package com.ucaproject.ucaattendancesystem.service.impl;

import com.ucaproject.ucaattendancesystem.dto.FacultyDto;
import com.ucaproject.ucaattendancesystem.entity.Faculty;
import com.ucaproject.ucaattendancesystem.entity.PersonInfo;
import com.ucaproject.ucaattendancesystem.entity.RoomNo;
import com.ucaproject.ucaattendancesystem.entity.Staff;
import com.ucaproject.ucaattendancesystem.exceptions.FacultyNotFoundException;
import com.ucaproject.ucaattendancesystem.exceptions.PersonInfoNotFoundException;
import com.ucaproject.ucaattendancesystem.exceptions.RoomNoNotFoundException;
import com.ucaproject.ucaattendancesystem.repository.FacultyRepository;
import com.ucaproject.ucaattendancesystem.repository.PersonInfoRepository;
import com.ucaproject.ucaattendancesystem.repository.RoomNoRepository;
import com.ucaproject.ucaattendancesystem.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {

    private FacultyRepository facultyRepository;
    private PersonInfoRepository personInfoRepository;
    private RoomNoRepository roomRepository;

    @Autowired
    public FacultyServiceImpl(FacultyRepository facultyRepository, PersonInfoRepository personInfoRepository, RoomNoRepository roomRepository) {
        this.facultyRepository = facultyRepository;
        this.personInfoRepository = personInfoRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public FacultyDto createFaculty(Long personInfoId, Long roomId, FacultyDto facultyDto) {
        Faculty faculty = mapToEntity(facultyDto);

        PersonInfo personInfo = personInfoRepository.findById(personInfoId).orElseThrow(() -> new PersonInfoNotFoundException("Person Info Not Found"));
        RoomNo room = roomRepository.findById(roomId).orElseThrow(() -> new RoomNoNotFoundException("Room No Not Found"));

        faculty.setPersonInfo(personInfo);
        faculty.setRoom(room);
        Faculty savedFaculty = facultyRepository.save(faculty);

        return mapToDto(savedFaculty);
    }

    @Override
    public List<FacultyDto> getAllFaculty() {
        List<Faculty> facultyList = facultyRepository.findAll();
        return facultyList.stream().map(f -> mapToDto(f)).collect(Collectors.toList());
    }

    @Override
    public List<FacultyDto> getFacultyByPersonInfoId(Long personInfoId) {
        List<Faculty> facultyList = facultyRepository.findByPersonInfoId(personInfoId);
        return facultyList.stream().map(f -> mapToDto(f)).collect(Collectors.toList());
    }

    @Override
    public List<FacultyDto> getFacultyByRoomId(Long roomId) {
        List<Faculty> facultyList = facultyRepository.findByRoomId(roomId);
        return facultyList.stream().map(f -> mapToDto(f)).collect(Collectors.toList());
    }

    @Override
    public FacultyDto getFacultyById(Long personInfoId, Long roomId, Long facultyId) {
        PersonInfo personInfo = personInfoRepository.findById(personInfoId).orElseThrow(() -> new PersonInfoNotFoundException("Person Info Not Found"));
        RoomNo room = roomRepository.findById(roomId).orElseThrow(() -> new RoomNoNotFoundException("Room No Not Found"));
        Faculty faculty = facultyRepository.findById(facultyId).orElseThrow(() -> new FacultyNotFoundException("Faculty Not Found"));

//        if(faculty.getPersonInfo().getId() != faculty.getId()) {
//            throw new FacultyNotFoundException("This person info doesn't belong to this faculty");
//        }
//
//        if(faculty.getRoom().getId() != room.getId()) {
//            throw new RoomNoNotFoundException("This person info doesn't belong to this room");
//        }

        return mapToDto(faculty);
    }

    @Override
    public FacultyDto updateFaculty(Long personInfoId, Long roomId, Long facultyId, FacultyDto facultyDto) {
        PersonInfo personInfo = personInfoRepository.findById(personInfoId).orElseThrow(() -> new PersonInfoNotFoundException("Person Info Not Found"));
        RoomNo room = roomRepository.findById(roomId).orElseThrow(() -> new RoomNoNotFoundException("Room No Not Found"));
        Faculty faculty = facultyRepository.findById(facultyId).orElseThrow(() -> new FacultyNotFoundException("Faculty Not Found"));

//        if(faculty.getPersonInfo().getId() != faculty.getId()) {
//            throw new FacultyNotFoundException("This person info doesn't belong to this faculty");
//        }

//        if(faculty.getRoom().getId() != room.getId()) {
//            throw new RoomNoNotFoundException("This person info doesn't belong to this room");
//        }

        faculty.setName(facultyDto.getName());
        faculty.setCheckInDate(facultyDto.getCheckInDate());
        faculty.setCheckOutDate(facultyDto.getCheckOutDate());
        Faculty savedFaculty = facultyRepository.save(faculty);

        return mapToDto(savedFaculty);
    }

    @Override
    public void deleteFaculty(Long personInfoId, Long roomId, Long facultyId) {
        PersonInfo personInfo = personInfoRepository.findById(personInfoId).orElseThrow(() -> new PersonInfoNotFoundException("Person Info Not Found"));
        RoomNo room = roomRepository.findById(roomId).orElseThrow(() -> new RoomNoNotFoundException("Room No Not Found"));
        Faculty faculty = facultyRepository.findById(facultyId).orElseThrow(() -> new FacultyNotFoundException("Faculty Not Found"));

//        if(faculty.getPersonInfo().getId() != faculty.getId()) {
//            throw new FacultyNotFoundException("This person info doesn't belong to this faculty");
//        }
//
//        if(faculty.getRoom().getId() != room.getId()) {
//            throw new RoomNoNotFoundException("This person info doesn't belong to this room");
//        }

        facultyRepository.delete(faculty);
    }

    private FacultyDto mapToDto(Faculty faculty) {
        FacultyDto facultyDto = new FacultyDto();
        facultyDto.setId(faculty.getId());
        facultyDto.setName(faculty.getName());
        facultyDto.setCheckInDate(faculty.getCheckInDate());
        facultyDto.setCheckOutDate(faculty.getCheckOutDate());
        return facultyDto;
    }

    private Faculty mapToEntity(FacultyDto facultyDto) {
        Faculty faculty = new Faculty();
        faculty.setName(facultyDto.getName());
        faculty.setCheckInDate(facultyDto.getCheckInDate());
        faculty.setCheckOutDate(facultyDto.getCheckOutDate());
        return faculty;
    }
}
