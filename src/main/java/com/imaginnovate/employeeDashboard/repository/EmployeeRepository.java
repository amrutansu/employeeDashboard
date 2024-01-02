package com.imaginnovate.employeeDashboard.repository;

import com.imaginnovate.employeeDashboard.entity.EmployeeDAO;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<EmployeeDAO,Long> {
    public EmployeeDAO findByEmployeeId(String employeeId);
}
