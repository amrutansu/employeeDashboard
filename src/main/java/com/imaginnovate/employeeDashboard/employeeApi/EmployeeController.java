package com.imaginnovate.employeeDashboard.employeeApi;

import com.imaginnovate.employeeDashboard.employeeService.EmployeeService;
import com.imaginnovate.employeeDashboard.employeeService.EmployeeTaxationCalculatorService;
import com.imaginnovate.employeeDashboard.entity.EmployeeDAO;
import com.imaginnovate.employeeDashboard.serviceEntity.Employee;

import com.imaginnovate.employeeDashboard.serviceEntity.EmployeeTaxationData;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.logging.Logger;

@RestController
@RequestMapping("employeeDashboard")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    EmployeeTaxationCalculatorService employeeTaxationCalculatorService;

    @PostMapping(value = "/storeEmployee",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EmployeeDAO> storeEmployee(@Valid @RequestBody Employee employee){
        try{
            EmployeeDAO employeeDAO = employeeService.saveEmployeeData(employee);
            return ResponseEntity.ok(employeeDAO);
        }catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getTaxData/{employeeId}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EmployeeTaxationData> getEmployeeTaxDetails(@PathVariable String employeeId) {
        try{
            EmployeeTaxationData employeeTaxationData = employeeTaxationCalculatorService.employeeYearlyTaxCalculator(employeeId);
            return new ResponseEntity<>(employeeTaxationData, HttpStatus.OK);
        }catch(Exception exception){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
