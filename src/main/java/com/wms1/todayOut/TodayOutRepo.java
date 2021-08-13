package com.wms1.todayOut;

import com.wms1.todayIn.TodayIn;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
public interface TodayOutRepo extends CrudRepository<TodayOut, Long> {


    @Query("select sum(sk.qty) from TodayOut sk where name_of_item=?1 and date between ?2 and ?3 and user_name=?4 and sales_no=?5")
    int sumOfQuantity1(String name_of_item, String from, String to, String user_name, String sales_no);

    @Query("select sum(sk.qty) from TodayOut sk where name_of_item=?1 and user_name=?2 and sales_no=?3")
    int sumOfQuantity(String name_of_item, String user_name, String sales_no);

    @Query("select sum(sk.qty) from TodayOut sk where name_of_item=?1 and user_name=?2 and date=?3 and sales_no=?4")
    int sumOfQuantity1(String name_of_item, String user_name, String date, String sales_no);

    @Query("select sk from TodayOut sk where date between ?1 and ?2 and user_name=?3")
    Set<TodayOut> getNameOfItem1(String date, String to, String user_name);

    @Query("select sk.name_of_item from TodayOut sk where user_name=?1 and sales_no=?2")
    Set<String> getNameOfItem(String user_name, String sales_no);


    @Query("select sk from TodayOut sk where user_name=?1 and date=?2")
    Set<TodayOut> getNameOfItem1(String user_name, String date);


    @Query("select sk from TodayOut sk where name_of_item=?1 and date between ?2 and ?3 and user_name=?4 and sales_no=?5")
    List<TodayOut> getDataWithNameOfItem(String name_of_item, String date, String to, String user_name, String sales_no);

    @Query("select sk from TodayOut sk where name_of_item=?1 and user_name=?2 and date=?3 and sales_no=?4")
    List<TodayOut> getDataWithNameOfItem(String name_of_item, String user_name, String date, String sales_no);


    @Query("select sk from TodayOut sk where name_of_item=?1 and user_name=?2 and sales_no=?3")
    List<TodayOut> getDataWithNameOfItem(String name_of_item, String user_name, String sales_no);

    @Query("select sk from TodayOut sk where name_of_item=?1 and user_name=?2 and date=?3")
    List<TodayOut> getDataWithNameOfItem1(String name_of_item, String user_name, String date);


    @Query("select sum(sk.qty) from TodayOut sk where user_name=?1")
    int sumOfQuantity1(String user_name);


    @Query("select sum(sk.qty) from TodayOut sk where date=?1")
    int sumOfQuantity(String date);

    @Query("select sk from TodayOut sk where date=?1")
    List<TodayOut> getTodayInProduct(String date);

    @Modifying
    @Query(value = "update today_out set qty=?1 where user_name=?2 and sales_no=?3 and name_of_item=?4",nativeQuery = true)
    @Transactional
    int updateQtyExistingItem(int qty, String user_name, String sales_no, String name_of_item);
}
