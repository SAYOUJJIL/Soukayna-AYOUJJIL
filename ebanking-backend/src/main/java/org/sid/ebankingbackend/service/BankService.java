package org.sid.ebankingbackend.service;

import org.sid.ebankingbackend.entities.BankAccount;
import org.sid.ebankingbackend.entities.CurrentAccount;
import org.sid.ebankingbackend.entities.SavingAccount;
import org.sid.ebankingbackend.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BankService {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    public void consulter(){
        BankAccount bankAccount = bankAccountRepository.findById("3911a851-3f60-44db-9e28-679a413e94a5").orElse(null);
        if(bankAccount!=null){
            System.out.println("***********************");
            System.out.println(bankAccount.getId());
            System.out.println(bankAccount.getBalance());
            System.out.println(bankAccount.getStatus());
            System.out.println(bankAccount.getCreateAt());
            System.out.println(bankAccount.getCustomer().getName());
            System.out.println(bankAccount.getClass().getSimpleName());
            if(bankAccount instanceof CurrentAccount){
                System.out.println("Over Draft : "+((CurrentAccount) bankAccount).getOverDraft());
            }
            else if (bankAccount instanceof SavingAccount)
            {
                System.out.println("Rate : "+((SavingAccount) bankAccount).getInterestRate());
            }
            bankAccount.getAccountOperations().forEach(accountOperation -> {
                System.out.println("============================");
                System.out.print(accountOperation.getType()+"\t");
                System.out.print(accountOperation.getOperationDate()+"\t");
                System.out.println(accountOperation.getAmount()+"\t");

            });
            System.out.println("============================");
            System.out.println("***********************");
        }
    }
}
