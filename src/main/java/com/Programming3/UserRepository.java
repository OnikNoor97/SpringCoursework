package com.Programming3;

import org.springframework.data.repository.CrudRepository;

import com.Programming3.Model.Employee;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<Employee, Long>
{

}