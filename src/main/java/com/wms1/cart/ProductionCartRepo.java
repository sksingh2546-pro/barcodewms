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

    @Query("select sk from ProductionCart sk where name_of_item=?1")
    List<ProductionCart> getCartListByBarcode(String name_of_item);

    @Modifying
    @Query(value = "delete from production_cart where user_name=?1 and name_of_item=?2 ", nativeQuery = true)
    @Transactional
    int deleteCartItem(String user_name, String name_of_item);

    @Modifying
    @Query(value = "delete from production_cart where id=?1", nativeQuery = true)
    @Transactional
    int deleteCartItem(long id);

    @Modifying
    @Query(value = "update production_cart set qty=?3 where name_of_item=?2 and user_name=?1 and user_id=?4", nativeQuery = true)
    @Transactional
    int updateQtyByBarcode(String user_name, String name_of_item, int qty,String user_id);

}
