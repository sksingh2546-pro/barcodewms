package com.wms1.login;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ManageUserRepo extends CrudRepository<ManageUser,Integer> {
    @Query("select mu from ManageUser mu where user_name=?1 and warehouses=?2")
    List<ManageUser> checkUser(String userName,String warehouses);

    @Modifying
    @Query(value = "delete from user where user_name=?1 and warehouses=?2",nativeQuery = true)
    @Transactional
    int logout(String userName,String warehouses);

}
