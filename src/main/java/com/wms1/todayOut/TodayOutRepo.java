package com.wms1.todayOut;

import com.wms1.todayIn.TodayIn;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TodayOutRepo extends CrudRepository<TodayOut,Long> {


    @Query("select sum(sk.qty) from TodayOut sk where name_of_item=?1 and date between ?2 and ?3 ")
    int sumOfQuantity1(String name_of_item,String from ,String to);

 @Query("select sum(sk.qty) from TodayOut sk where name_of_item=?1 and date=?2")
    int sumOfQuantity(String name_of_item,String from );

    @Query("select sk.name_of_item from TodayOut sk where date between ?1 and ?2 ")
    Set<String> getNameOfItem1(String date,String to);

  @Query("select sk.name_of_item from TodayOut sk where date=?1")
    Set<String> getNameOfItem(String date);


    @Query("select sk from TodayOut sk where name_of_item=?1 and date between ?2 and ?3")
    List<TodayOut> getDataWithNameOfItem(String name_of_item,String date,String to);

    @Query("select sk from TodayOut sk where name_of_item=?1 and date=?2")
    List<TodayOut> getDataWithNameOfItem(String name_of_item,String date);



    @Query("select sum(sk.qty) from TodayOut sk ")
    int sumOfQuantity();


    @Query("select sum(sk.qty) from TodayOut sk where date=?1")
    int sumOfQuantity(String date);

    @Query("select sk from TodayOut sk where date=?1")
    List<TodayOut> getTodayInProduct(String date);


}
