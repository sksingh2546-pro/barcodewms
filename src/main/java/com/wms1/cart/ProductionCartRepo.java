package com.wms1.cart;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductionCartRepo extends CrudRepository<ProductionCart, Long> {
    @Query("select sk from ProductionCart sk where user_name=?1 and type=?2")
    List<ProductionCart> getCartListByUserName(String user_name, String type);

    @Modifying
    @Query(value = "delete from production_cart where user_name=?1 and barcode=?2 ", nativeQuery = true)
    @Transactional
    int deleteCartItem(String user_name, String barcode);

    @Modifying
    @Query(value = "delete from production_cart where id=?1", nativeQuery = true)
    @Transactional
    int deleteCartItem(long id);

}
