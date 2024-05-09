package web.app.ds.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkDepartmentDto {
    DepartmentDto departmentDto;
    List<EmployeeDto> employeeDtoList;
}
