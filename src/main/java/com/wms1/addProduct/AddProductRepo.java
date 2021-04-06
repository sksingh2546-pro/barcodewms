package com.wms1.addProduct;

import com.wms1.production.Production;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
public interface AddProductRepo extends CrudRepository<AddProduct,Long> {

    @Modifying
    @Query(value = "insert into add_product(barcode,name_of_item,no_of_pcs,per_pcs_weight,packaging," +
            "carton_gross_weight,hsn,date,qty,status)values(?1,?2,?3,?4,?5,?6,?7,?8,1,'valid')", nativeQuery = true)
    @Transactional
    int insertData(String barcode,String name_of_item,int no_of_pcs,float per_pcs_weight,int packaging,
                   float carton_gross_weight,String hsn,String date);


    @Query("select sk from AddProduct sk where barcode=?1 and status='valid'")
    List<AddProduct> getBarcodeList(String barcode);

    @Query("select sk from AddProduct sk where status='expire'")
    List<AddProduct> getExpireList();

    @Modifying
    @Query(value = "update add_product set status='expire' where barcode=?1", nativeQuery = true)
    @Transactional
    int updateProductStatus(String barcode);

    @Modifying
    @Query(value = "update add_product set qty=?1 where barcode=?2", nativeQuery = true)
    @Transactional
    int updateProduction(int qty,String barcode);

    @Query("select sk.name_of_item from AddProduct sk")
    Set<String>getNameOfItem();

    @Query("select sum(sk.qty) from AddProduct sk where name_of_item=?1")
    int sumOfQuantity(String name_of_item);

    @Query("select sk from AddProduct sk where name_of_item=?1")
    List<AddProduct> getDataWithNameOfItem(String name_of_item);

    @Query("select sum(sk.qty) from AddProduct sk ")
    int sumOfQuantity();

}
