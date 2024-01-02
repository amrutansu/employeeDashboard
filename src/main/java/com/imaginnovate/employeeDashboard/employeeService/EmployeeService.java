package com.imaginnovate.employeeDashboard.employeeService;

import com.imaginnovate.employeeDashboard.entity.EmployeeDAO;
import com.imaginnovate.employeeDashboard.repository.EmployeeRepository;
import com.imaginnovate.employeeDashboard.serviceEntity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public EmployeeDAO saveEmployeeData(Employee employee){

        EmployeeDAO employeeDAO = EmployeeDAO.builder().employeeId(employee.getEmployeeId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .salary(employee.getSalary())
                .doj(employee.getDoj())
                .phoneNumbers(employee.getPhoneNumbers()).build();
        EmployeeDAO employeeDAOSavedEntity = employeeRepository.save(employeeDAO);
        return employeeDAOSavedEntity;
    }
}
