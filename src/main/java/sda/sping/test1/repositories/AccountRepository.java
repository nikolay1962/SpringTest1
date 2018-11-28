package sda.sping.test1.repositories;

import org.springframework.data.repository.CrudRepository;
import sda.sping.test1.dto.Account;

public interface AccountRepository extends CrudRepository<Account, Integer> {

    Iterable<Account> getAccountsByCurrency(String currency);

    Account getAccountById(Integer id);
}
