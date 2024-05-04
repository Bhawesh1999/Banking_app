package net.javaguides.bankingapp.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import net.javaguides.bankingapp.dto.AccountDto;
import net.javaguides.bankingapp.entity.Account;
import net.javaguides.bankingapp.mapper.AccountMapper;
import net.javaguides.bankingapp.repository.AccountRepository;
import net.javaguides.bankingapp.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	private AccountRepository accountRepository;//calling variable accountrepository of type AccountRepository userdefined datatypes
	
	
	public AccountServiceImpl(AccountRepository accountRepository) {//implementing constructor with accountrepository parameter of userdefined type
		this.accountRepository = accountRepository;
	}


	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account = AccountMapper.mapToAccount(accountDto);
		Account savedAccount =accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}


	@Override
	public AccountDto getAccountById(Long id) {
		Account account = accountRepository
				.findById(id)
				.orElseThrow(()-> new RuntimeException("account does not exists"));
		return AccountMapper.mapToAccountDto(account);
	}


	@Override
	public AccountDto Deposit(Long id, double amount) {
		Account account = accountRepository
				.findById(id)
				.orElseThrow(()-> new RuntimeException("account does not exists"));
		double total = account.getBalance()+amount;
		account.setBalance(total);
		Account savedAccount =accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}


	@Override
	public AccountDto withdraw(Long id, double amount) {
		Account account = accountRepository
				.findById(id)
				.orElseThrow(()-> new RuntimeException("account does not exists"));
	if(account.getBalance()<amount) {
		throw new RuntimeException("Insuficient amount");
	}
		double total = account.getBalance()-amount;
		account.setBalance(total);
		Account savedAccount =accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}


	@Override
	public List<AccountDto> getAllAccounts() {
		List<Account> accounts = accountRepository.findAll();
		return accounts.stream().map((account)-> AccountMapper.mapToAccountDto(account))
			.collect(Collectors.toList());
	}


	@Override
	public void deletedAccount(Long id) {
			Account account = accountRepository
					.findById(id)
					.orElseThrow(()-> new RuntimeException("account does not exists"));
			accountRepository.deleteById(id);
		
	}
	
	
	
	

}
