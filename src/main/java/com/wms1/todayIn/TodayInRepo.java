package com.wms1.todayIn;

import com.wms1.todayOut.TodayOut;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
public interface TodayInRepo extends CrudRepository<TodayIn, Long> {


    @Query("select sum(sk.qty) from TodayIn sk where name_of_item=?1 and date between ?2 and ?3")
    int sumOfQuantity(String name_of_item, String from, String to);

    @Query("select sk.name_of_item from TodayIn sk where  date between ?1 and ?2 and user_name=?3")
    Set<String> getNameOfItem(String from, String to, String user_name);


    @Query("select sum(sk.qty) from TodayIn sk where name_of_item=?1 and date=?2 and user_name=?3")
    int sumOfQuantity1(String name_of_item, String from, String user_name);

    @Query("select sk.name_of_item from TodayIn sk where  date=?1 and user_name=?2")
    Set<String> getNameOfItem1(String from, String user_name);

    @Query("select sk from TodayIn sk where name_of_item=?1 and date=?2")
    List<TodayIn> getDataWithNameOfItem(String name_of_item, String date);

    @Query("select sk from TodayIn sk where name_of_item=?1 and date between ?2 and ?3")
    List<TodayIn> getDataWithNameOfItem(String name_of_item, String from, String to);

    @Query("select sum(sk.qty) from TodayIn sk ")
    int sumOfQuantity();


    @Query("select sum(sk.qty) from TodayIn sk where date=?1 and user_name=?2")
    int sumOfQuantity(String date, String user_name);

    @Query("select sk from TodayIn sk where date=?1 and user_name=?2")
    List<TodayIn> getTodayInProduct(String date, String user_name);

    @Modifying
    @Query(value = "update today_in set qty=?1 where name_of_item=?2 and user_name=?3", nativeQuery = true)
    @Transactional
    int updateTodayIn(int qty, String name_of_item, String user_name);

    @Query("select sk from TodayIn sk where date=?1 and user_name=?2 and name_of_item=?3")
    List<TodayIn> getItemByName(String from, String user_name, String name_of_item);

}
