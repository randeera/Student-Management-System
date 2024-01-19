package lk.ijse.dep11.app.service;

import lk.ijse.dep11.app.dto.StudentDTO;
import lk.ijse.dep11.app.entity.Student;
import lk.ijse.dep11.app.repository.StudentRepo;
import lk.ijse.dep11.app.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private ModelMapper modelMapper;

    // create operation
    public String saveStudent(StudentDTO studentDTO){
        if (studentRepo.existsById(studentDTO.getStudentId())){
           return VarList.RSP_DUPLICATED;
        }else {
            studentRepo.save(modelMapper.map(studentDTO, Student.class));
            return VarList.RSP_SUCCESS;
        }
    }
    // update operation
    public String updateStudent(StudentDTO studentDTO){
        if (!studentRepo.existsById(studentDTO.getStudentId())){
            return VarList.RSP_NO_DATA_FOUND;
        }else {
            studentRepo.save(modelMapper.map(studentDTO, Student.class));
            return VarList.RSP_SUCCESS;
        }
    }

    // get Operation
    public List<StudentDTO> getAllStudents(){
        List<Student> studentList = studentRepo.findAll();
        return modelMapper.map(studentList, new TypeToken<ArrayList<StudentDTO>>(){
        }.getType());
    }

    // search operation - get one student detail
    public StudentDTO searchStudent(int studentId){
        if(studentRepo.existsById(studentId)){
            Student student = studentRepo.findById(studentId).orElse(null);
            return modelMapper.map(student, StudentDTO.class);
        }else {
            return null;
        }
    }

    // delete operation
    public String deleteStudent(int studentId){
        if (studentRepo.existsById(studentId)){
            studentRepo.deleteById(studentId);
            return VarList.RSP_SUCCESS;
        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

}
