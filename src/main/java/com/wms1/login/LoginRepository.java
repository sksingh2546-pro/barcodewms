
package com.wms1.login;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface LoginRepository extends CrudRepository<Login, Integer> {
    @Modifying
    @Query(value = "update login  set password=?2 where user_name=?1", nativeQuery = true)
    @Transactional
    int insertData(String user_name, String password);

    @Query("select l from Login l where user_name=?1 and password=?2")
    List<Login> getLogin(String user_name, String password);
}
