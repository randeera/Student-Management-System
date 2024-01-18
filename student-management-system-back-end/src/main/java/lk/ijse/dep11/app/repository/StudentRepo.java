package lk.ijse.dep11.app.repository;

import lk.ijse.dep11.app.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface StudentRepo extends JpaRepository<Student , Integer> {

}
