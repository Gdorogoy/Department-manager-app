package web.app.es.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Schema(
        description = "Employee Model Information"
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private Long id;

    @Schema(
            description ="The first name of the employee"
    )
    private String firstName;

    @Schema(
            description ="The lastname of the employee"
    )
    private String lastName;

    @Schema(
            description ="The email of the employee"
    )
    private String email;

    @Schema(
            description ="The code of the department where the employee is in"
    )
    private String departmentCode;




}
