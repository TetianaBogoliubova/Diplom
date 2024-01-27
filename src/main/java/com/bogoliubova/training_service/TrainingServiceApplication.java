package com.bogoliubova.training_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrainingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrainingServiceApplication.class, args);
    }


    //<include file="db/changelog/changes/v0.0.1-SNAPSHOT/RTN-283-create-tables-changelog.xml"/>
}


//    @Override
//    public AccountDto createAccount(AccountDto accountDto) {
//        log.info("Create new account.");
//        Client client = clientRepository.findById(UUID.fromString(accountDto.getClientId()))
//                .orElseThrow(() -> new NotFoundClientException("Client not found with id " + accountDto.getClientId()));
//        Account account = accountMapper.mapToEntity(accountDto);
//        account.setClient(client);
//        account.setCreatedAt(new Timestamp(System.currentTimeMillis()));
//        account.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
//        return accountMapper.mapToDto(accountRepository.save(account));
//    }

//@ToString.Include(name = "password")
// private String maskPassword() {
// return "********";}

//@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // not for output
// @ValidPassword	@NotBlank(message = "{validation.field.NotBlank}")
// private String password;