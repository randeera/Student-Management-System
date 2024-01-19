package lk.ijse.dep11.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private int studentId;
    private String studentName;
    private String studentAddress;
    private String mobileNumber;
}
