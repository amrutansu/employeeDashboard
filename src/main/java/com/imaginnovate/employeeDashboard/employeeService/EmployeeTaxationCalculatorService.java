package com.imaginnovate.employeeDashboard.employeeService;

import com.imaginnovate.employeeDashboard.entity.EmployeeDAO;
import com.imaginnovate.employeeDashboard.repository.EmployeeRepository;
import com.imaginnovate.employeeDashboard.serviceEntity.Employee;
import com.imaginnovate.employeeDashboard.serviceEntity.EmployeeTaxationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

@Component
public class EmployeeTaxationCalculatorService {
    @Autowired
    EmployeeRepository employeeRepository;

    public EmployeeTaxationData employeeYearlyTaxCalculator(String id) throws ParseException {
        EmployeeDAO employee = employeeRepository.findByEmployeeId(id);

        double yearlySalary = employeeYearlySalary(employee.getSalary(),employee.getDoj());
        double taxToBePaid = calculateTax(yearlySalary);
        double cessAmount = calculateCess(yearlySalary);
        EmployeeTaxationData employeeTaxationData = EmployeeTaxationData.builder().employeeCode(employee.getEmployeeId())
                .employeeFirstName(employee.getFirstName())
                .employeeLastName(employee.getLastName())
                .yearlySalary(yearlySalary)
                .taxAmount(taxToBePaid)
                .cessAmount(cessAmount).build();

        return employeeTaxationData;
    }

    private double calculateCess(double yearlySalary) {
        double cessAmount = 0;
        if (yearlySalary > 2500000)
            cessAmount = yearlySalary*0.02;
        return cessAmount;
    }

    private Double employeeYearlySalary(Long monthlySalary, String employeeJoindate) throws ParseException {
        Double yearlySalary = 0.0;

        LocalDate joiningDate = LocalDate.parse(employeeJoindate);
        LocalDate taxCalculationDate = LocalDate.parse("2023-04-01");
        long diffInDaysTest = ChronoUnit.DAYS.between(taxCalculationDate,joiningDate);

        if (diffInDaysTest <= 0){
            yearlySalary = Double.valueOf((monthlySalary/30)*365);
        }else if (diffInDaysTest > 0){
            long taxabledays = 365-diffInDaysTest;
            yearlySalary = Double.valueOf((monthlySalary/30)*taxabledays);
        }

        return yearlySalary;
    }

    private Double calculateTax(double yearlySalary){
        double taxToBePaid = 0;
        if (yearlySalary > 250000 && yearlySalary<=500000)
            taxToBePaid = (yearlySalary-250000)*0.05;
        else if(yearlySalary > 500000 && yearlySalary < 1000000)
            taxToBePaid = (249999*0.05) + ((yearlySalary-500000)*0.1);
        else if(yearlySalary > 1000000)
            taxToBePaid = (249999*0.05) + (499999*0.1) + ((yearlySalary-1000000)*0.2);

        return taxToBePaid;
    }
}
