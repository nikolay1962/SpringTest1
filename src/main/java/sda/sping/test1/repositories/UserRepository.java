package sda.sping.test1.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import sda.sping.test1.dto.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    Iterable<User> getAllByNationality(String nationality);

    User getUserById(int id);

    @Query("select u.name from User u where u.id = :id")
    String findNameById(@Param("id") Integer id);

    @Query("select u.id from User u where u.name = :name")
    Integer findIdByName(@Param("name") String name);

    User findByUsername(String username);

    @Modifying
    @Transactional
    @Query("delete from User u where u.username = :username")
    void deleteByUsername(@Param("username") String username);

}
