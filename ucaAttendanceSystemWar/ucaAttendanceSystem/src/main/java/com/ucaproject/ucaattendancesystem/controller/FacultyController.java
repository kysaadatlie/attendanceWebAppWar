package com.ucaproject.ucaattendancesystem.controller;

import com.ucaproject.ucaattendancesystem.dto.FacultyDto;
import com.ucaproject.ucaattendancesystem.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class FacultyController {

    private FacultyService facultyService;

    @Autowired
    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("faculty")
    public ResponseEntity<List<FacultyDto>> getAllFaculty() {
        return new ResponseEntity<>(facultyService.getAllFaculty(), HttpStatus.OK);
    }

    @GetMapping("/person_info/{personInfoId}/faculty")
    public List<FacultyDto> getFacultyByPersonInfoId(@PathVariable Long personInfoId) {
        return facultyService.getFacultyByPersonInfoId(personInfoId);
    }

    @GetMapping("/room/{roomId}/faculty")
    public List<FacultyDto> getFacultyByRoomId(@PathVariable Long roomId) {
        return facultyService.getFacultyByRoomId(roomId);
    }

    @GetMapping("/person_info/{personInfoId}/room/{roomId}/faculty/{facultyId}")
    public ResponseEntity<FacultyDto> getFacultyById(@PathVariable(value = "personInfoId") Long personInfoId, @PathVariable(value = "roomId") Long roomId, @PathVariable(value = "facultyId") Long facultyId) {
        FacultyDto facultyDto = facultyService.getFacultyById(personInfoId, roomId, facultyId);
        return new ResponseEntity<>(facultyDto, HttpStatus.OK);
    }

    @PostMapping("/person_info/{personInfoId}/room/{roomId}/faculty/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<FacultyDto> createFaculty(@PathVariable(value = "personInfoId") Long personInfoId, @PathVariable(value = "roomId") Long roomId, @RequestBody FacultyDto facultyDto) {
        return new ResponseEntity<>(facultyService.createFaculty(personInfoId, roomId, facultyDto), HttpStatus.OK);
    }

    @PutMapping("/person_info/{personInfoId}/room/{roomId}/faculty/{facultyId}/update")
    public ResponseEntity<FacultyDto> updateFaculty(@PathVariable(value = "personInfoId") Long personInfoId, @PathVariable(value = "roomId") Long roomId, @PathVariable(value = "facultyId") Long facultyId, @RequestBody FacultyDto facultyDto) {
        FacultyDto response = facultyService.updateFaculty(personInfoId, roomId, facultyId, facultyDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/person_info/{personInfoId}/room/{roomId}/faculty/{facultyId}/delete")
    public ResponseEntity<String> deleteFaculty(@PathVariable(value = "personInfoId") Long personInfoId, @PathVariable(value = "roomId") Long roomId, @PathVariable(value = "facultyId") Long facultyId) {
        facultyService.deleteFaculty(personInfoId, roomId, facultyId);
        return new ResponseEntity<>("Faculty deleted", HttpStatus.OK);
    }
}
