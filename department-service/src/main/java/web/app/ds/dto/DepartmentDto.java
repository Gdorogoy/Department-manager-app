package web.app.ds.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Schema(
        description = "Department Model Information"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    private Long id;
    @Schema(
        description ="The name of the department"
    )
    private String departmentName;

    @Schema(
            description ="The description of the department:"
    )
    private String departmentDescription;

    @Schema(
            description ="The code of the department to assign employees to it"
    )
    private String departmentCode;



}
