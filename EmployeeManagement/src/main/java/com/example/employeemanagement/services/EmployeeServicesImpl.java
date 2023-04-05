package com.example.employeemanagement.services;

import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.exception.EmployeeException;
import com.example.employeemanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServicesImpl implements EmployeeServices{

    @Autowired
    private EmployeeRepository employeeRepo;

    @Override
    public void saveEmployee(Employee employee) throws EmployeeException {
         this.employeeRepo.save(employee);
    }

    @Override
    public List<Employee> allEmployeeList() throws EmployeeException {
        List<Employee> employees = employeeRepo.findAll();
        return employees;
    }

    @Override
        public Employee getEmployeeByID(Long id) throws EmployeeException {
        Optional<Employee> optionalEmployee = employeeRepo.findById(id);
        Employee employee = null;
        if(optionalEmployee.isPresent()){
            return optionalEmployee.get();
        }
        throw new EmployeeException("Employee does not exist with this Employee ID : "+id);
    }

    @Override
    public void deleteEmployeeByID(Long id) throws EmployeeException {
        this.employeeRepo.deleteById(id);
    }

    @Override
    public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.employeeRepo.findAll(pageable);
    }


}
