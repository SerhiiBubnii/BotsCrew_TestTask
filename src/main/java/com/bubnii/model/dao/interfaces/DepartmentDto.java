package com.bubnii.model.dao.interfaces;

import com.bubnii.model.entity.Department;

import java.util.Optional;

public interface DepartmentDto extends GeneralDAO<Department, Integer> {
    Optional<Department> findByHeadOfDepartmentName(String departmentName);
}
