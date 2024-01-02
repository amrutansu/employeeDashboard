package com.imaginnovate.employeeDashboard.serviceEntity;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@ToString
public class EmployeeTaxationData {
    private String employeeCode;
    private String employeeFirstName;
    private String employeeLastName;
    private double yearlySalary;
    private double taxAmount;
    private double cessAmount;
}
