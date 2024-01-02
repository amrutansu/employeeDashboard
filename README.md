# employeeDashboard
EmployeeDashboardForTAxCalculation
#Create Employee api
POST: /employeeDashboard/storeEmployee

{
    "employeeId": "TI0002",
    "firstName": "If I Smell",
    "lastName": "Frame",
    "email": "Thyme@gmail.com",
    "phoneNumbers": "123456789,987654321",
    "doj": "2023-12-11",
    "salary": 98000
}

#GetTaxData for Employee
GET: employeeDashboard/getTaxData/TI0002
