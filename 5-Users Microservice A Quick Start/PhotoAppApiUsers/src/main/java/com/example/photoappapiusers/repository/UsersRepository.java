package com.example.photoappapiusers.repository;

import com.example.photoappapiusers.modal.User;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<User, String> {
}
