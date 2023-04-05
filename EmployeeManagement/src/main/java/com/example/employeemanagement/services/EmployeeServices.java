package com.example.employeemanagement.services;

import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.exception.EmployeeException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeServices {
    public void saveEmployee(Employee employee) throws EmployeeException;
    public List<Employee> allEmployeeList() throws EmployeeException;
    public Employee getEmployeeByID(Long id) throws EmployeeException;
    public void deleteEmployeeByID(Long id) throws EmployeeException;
    Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
