package com.cg.multiple.repository.bank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.multiple.model.bank.AccountEntity;


@Repository
public interface AccountRepository extends JpaRepository<AccountEntity,Long> {

}
