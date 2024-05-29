package com.ucaproject.ucaattendancesystem.service;

import com.ucaproject.ucaattendancesystem.dto.FacultyDto;

import java.util.List;

public interface FacultyService {
    FacultyDto createFaculty(Long personInfoId, Long roomId, FacultyDto facultyDto);
    List<FacultyDto> getAllFaculty();
    List<FacultyDto> getFacultyByPersonInfoId(Long personInfoId);
    List<FacultyDto> getFacultyByRoomId(Long roomId);
    FacultyDto getFacultyById(Long personInfoId, Long roomId, Long facultyId);
    FacultyDto updateFaculty(Long personInfoId, Long roomId, Long facultyId, FacultyDto facultyDto);
    void deleteFaculty(Long personInfoId, Long roomId, Long facultyId);
}
