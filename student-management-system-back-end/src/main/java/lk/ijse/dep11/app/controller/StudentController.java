package lk.ijse.dep11.app.controller;

import lk.ijse.dep11.app.dto.ResponseDTO;
import lk.ijse.dep11.app.dto.StudentDTO;
import lk.ijse.dep11.app.entity.Student;
import lk.ijse.dep11.app.repository.StudentRepo;
import lk.ijse.dep11.app.service.StudentService;
import lk.ijse.dep11.app.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping
    public ResponseEntity saveStudent(@RequestBody StudentDTO studentDTO){
        try {
            String res = studentService.saveStudent(studentDTO);
            if(res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(studentDTO);
                return new ResponseEntity(responseDTO, HttpStatus.CREATED);
            }else if (res.equals("06")){
                responseDTO.setCode((VarList.RSP_DUPLICATED));
                responseDTO.setMessage("Duplicated");
                responseDTO.setContent(studentDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }else {
                responseDTO.setCode((VarList.RSP_FAIL));
                responseDTO.setMessage("failed, Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            responseDTO.setCode((VarList.RSP_ERROR));
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity updateStudent(@RequestBody StudentDTO studentDTO){
        try {
            String res = studentService.updateStudent(studentDTO);
            if(res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(studentDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            }else if (res.equals("01")){
                responseDTO.setCode((VarList.RSP_NO_DATA_FOUND));
                responseDTO.setMessage("Not a registered student");
                responseDTO.setContent(studentDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }else {
                responseDTO.setCode((VarList.RSP_FAIL));
                responseDTO.setMessage("failed, Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            responseDTO.setCode((VarList.RSP_ERROR));
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity getAllStudents(){
        List<StudentDTO> studentDTOList = studentService.getAllStudents();
        try {
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("Success");
            responseDTO.setContent(studentDTOList);
            return new ResponseEntity(responseDTO, HttpStatus.OK);

        } catch (Exception e) {
            responseDTO.setCode((VarList.RSP_ERROR));
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{studentId}")
    public ResponseEntity searchStudent(@PathVariable int studentId){
        StudentDTO studentDTO = studentService.searchStudent(studentId);
        try {
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("Success");
            responseDTO.setContent(studentDTO);
            return new ResponseEntity(responseDTO, HttpStatus.OK);

        } catch (Exception e) {
            responseDTO.setCode((VarList.RSP_ERROR));
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = "/{studentId}")
    public ResponseEntity deleteStudent(@PathVariable int studentId){
        String res = studentService.deleteStudent(studentId);
        try {
            if(res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Deleted");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.OK);
            }else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No Student available with this student id");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            responseDTO.setCode((VarList.RSP_ERROR));
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
