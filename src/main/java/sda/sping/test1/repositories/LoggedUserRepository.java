package sda.sping.test1.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import sda.sping.test1.dto.LoggedUser;

public interface LoggedUserRepository extends CrudRepository<LoggedUser, Integer> {

    @Query("select count(*) from LoggedUser")
    Integer getNuberOfRows();

    LoggedUser getLoggedUserById(int id);
}
