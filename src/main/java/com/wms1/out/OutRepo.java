package com.wms1.out;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface OutRepo extends CrudRepository<Out,Integer> {

    @Query("select out from Out out where status=0")
    List<Out> getOutData();

    @Modifying
    @Query(value = "update sales_no set status=1 where sales_no=?1 and user_name=?2",nativeQuery = true)
    @Transactional
    int updateStatus(String sales_no,String user_name);

    @Query("select out from Out out where sales_no=?1 and user_name=?2")
    List<Out> checkOutData(String sales_no,String user_name);
}
