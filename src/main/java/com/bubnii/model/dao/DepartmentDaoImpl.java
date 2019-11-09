package com.bubnii.model.dao;

import com.bubnii.model.dao.interfaces.DepartmentDto;
import com.bubnii.model.entity.Department;
import com.bubnii.model.session.EntityManagerFactoryUtil;

import java.util.List;
import java.util.Optional;

public class DepartmentDaoImpl implements DepartmentDto {

    public Optional<Department> findByID(Integer id) {
        Department department = EntityManagerFactoryUtil.getEntityManager().find(Department.class, id);
        return department != null ? Optional.of(department) : Optional.empty();
    }

    public List<Department> findAll() {
        return EntityManagerFactoryUtil
                .getEntityManager()
                .createQuery("SELECT e FROM Department e")
                .getResultList();
    }

    @Override
    public Optional<Department> findByHeadOfDepartmentName(String departmentName) {
        Department department = (Department) EntityManagerFactoryUtil
                .getEntityManager()
                .createQuery("SELECT e FROM Department e WHERE e.name = : depName")
                .setParameter("depName", departmentName)
                .getSingleResult();
        return department != null ? Optional.of(department) : Optional.empty();
    }
}
