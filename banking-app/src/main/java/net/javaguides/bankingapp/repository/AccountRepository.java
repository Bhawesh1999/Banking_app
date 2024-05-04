package net.javaguides.bankingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.bankingapp.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

}
