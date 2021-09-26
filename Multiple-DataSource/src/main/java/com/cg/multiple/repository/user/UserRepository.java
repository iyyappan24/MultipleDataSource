package com.cg.multiple.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.multiple.model.user.UserEntity;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

}
