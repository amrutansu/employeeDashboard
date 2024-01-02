package com.imaginnovate.employeeDashboard;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imaginnovate.employeeDashboard.employeeApi.EmployeeController;
import com.imaginnovate.employeeDashboard.employeeService.EmployeeService;
import com.imaginnovate.employeeDashboard.entity.EmployeeDAO;
import com.imaginnovate.employeeDashboard.serviceEntity.Employee;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

//@SpringBootTest
@WebMvcTest(EmployeeController.class)
class EmployeeControllerTests {

	@Mock
	EmployeeService employeeService;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeController employeeController;

	@Test
	void testEmployeeCreation() throws Exception {
		Employee employee = Employee.builder()
				.employeeId("E00001")
				.email("abc@abc.avc")
				.firstName("employeeFirstName")
				.lastName("employeeLastName")
				.phoneNumbers("1234567890")
				.doj("01/05/2023")
				.salary(123456L)
				.build();

		EmployeeDAO employeeDAO = EmployeeDAO.builder().id(1L).employeeId(employee.getEmployeeId())
						.email(employee.getEmail()).firstName(employee.getFirstName())
						.lastName(employee.getLastName()).phoneNumbers(employee.getPhoneNumbers())
						.doj(employee.getDoj()).salary(employee.getSalary()).build();


		Mockito.when(employeeService.saveEmployeeData(Mockito.any(Employee.class))).thenReturn(employeeDAO);
		mockMvc.perform(MockMvcRequestBuilders.post("/employeeDashboard/storeEmployee")
				.content(asJsonString(employee))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
				//.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	void testEmployeeCreationWithEmptyFirstName() throws Exception {
		Employee employee = Employee.builder()
				.employeeId("E00001")
				.email("abc@abc.avc")
				.firstName("")
				.lastName("employeeLastName")
				.phoneNumbers("1234567890")
				.doj("01/05/2023")
				.salary(123456L)
				.build();

		EmployeeDAO employeeDAO = EmployeeDAO.builder().id(1L).employeeId(employee.getEmployeeId())
				.email(employee.getEmail()).firstName(employee.getFirstName())
				.lastName(employee.getLastName()).phoneNumbers(employee.getPhoneNumbers())
				.doj(employee.getDoj()).salary(employee.getSalary()).build();


		Mockito.when(employeeService.saveEmployeeData(Mockito.any(Employee.class))).thenReturn(employeeDAO);
		mockMvc.perform(MockMvcRequestBuilders.post("/employeeDashboard/storeEmployee")
						.content(asJsonString(employee))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError());
		//.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	void testEmployeeCreationWithInvalidEmail() throws Exception {
		Employee employee = Employee.builder()
				.employeeId("E00001")
				.email("abcabc.avc")
				.firstName("employeeFirstName")
				.lastName("employeeLastName")
				.phoneNumbers("1234567890")
				.doj("01/05/2023")
				.salary(123456L)
				.build();

		EmployeeDAO employeeDAO = EmployeeDAO.builder().id(1L).employeeId(employee.getEmployeeId())
				.email(employee.getEmail()).firstName(employee.getFirstName())
				.lastName(employee.getLastName()).phoneNumbers(employee.getPhoneNumbers())
				.doj(employee.getDoj()).salary(employee.getSalary()).build();


		Mockito.when(employeeService.saveEmployeeData(Mockito.any(Employee.class))).thenReturn(employeeDAO);
		mockMvc.perform(MockMvcRequestBuilders.post("/employeeDashboard/storeEmployee")
						.content(asJsonString(employee))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError());
		//.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
	}

	private static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
