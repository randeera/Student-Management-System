package lk.ijse.dep11.app.service;

import lk.ijse.dep11.app.dto.StudentDTO;
import lk.ijse.dep11.app.entity.Student;
import lk.ijse.dep11.app.repository.StudentRepo;
import lk.ijse.dep11.app.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private ModelMapper modelMapper;

    public String saveStudent(StudentDTO studentDTO){
        if (studentRepo.existsById(studentDTO.getStudentId())){
           return VarList.RSP_DUPLICATED;
        }else {
            studentRepo.save(modelMapper.map(studentDTO, Student.class));
            return VarList.RSP_SUCCESS;
        }
    }
}
