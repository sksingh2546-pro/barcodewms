package com.wms1.cart;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductionCartRepo extends CrudRepository<ProductionCart, Long> {
    @Query("select sk from ProductionCart sk where user_id=?1 and type=?2 and user_name=?3")
    List<ProductionCart> getCartListByUserName(String user_id, String type, String user_name);

    @Query("select sk from ProductionCart sk where name_of_item=?3 and user_name=?2 and type=?1 and sales_no=?4 and user_id=?5")
    List<ProductionCart> getCartListByTypeName(String type, String user_name, String name_of_item, String sales_no, String user_id);

    @Query("select sk from ProductionCart sk where user_name=?3 and type=?2 and user_id=?1 and name_of_item=?4")
    List<ProductionCart> getCartListByUserName(String user_id, String type, String user_name, String name_of_item);

    @Modifying
    @Query(value = "delete from production_cart where user_name=?1 and name_of_item=?2 and type=?3 and user_id=?4 and sales_no=?5", nativeQuery = true)
    @Transactional
    int deleteCartItem(String user_name, String name_of_item, String type, String user_id, String sales_no);

    @Modifying
    @Query(value = "delete from production_cart where user_name=?1 and name_of_item=?2 and type=?3 and user_id=?4", nativeQuery = true)
    @Transactional
    int deleteCartItem(String user_name, String name_of_item, String type, String user_id);

    @Modifying
    @Query(value = "delete from production_cart where id=?1", nativeQuery = true)
    @Transactional
    int deleteCartItem(long id);

    @Modifying
    @Query(value = "update production_cart set qty=?3 where name_of_item=?2 and user_name=?1 and user_id=?4", nativeQuery = true)
    @Transactional
    int updateQtyByBarcode(String user_name, String name_of_item, int qty, String user_id);

    @Query("select qty from ProductionCart where name_of_item=?3 and user_name=?2 and type=?1")
    int getCartQtyBySalesNo(String type, String user_name, String name_of_item);

}
