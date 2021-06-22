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
            "carton_gross_weight,hsn,date,qty,status,user_name)values(?1,?2,?3,?4,?5,?6,?7,?8,1,'valid',?9)", nativeQuery = true)
    @Transactional
    int insertData(String barcode,String name_of_item,int no_of_pcs,float per_pcs_weight,int packaging,
                   float carton_gross_weight,String hsn,String date,String user_name);


    @Query("select sk from AddProduct sk where barcode=?1 and status='valid' and user_name=?2")
    List<AddProduct> getBarcodeList(String barcode,String user_name);

    @Query("select sk from AddProduct sk where status='expire' and user_name=?1")
    List<AddProduct> getExpireList(String user_name);

    @Modifying
    @Query(value = "update add_product set status='expire' where barcode=?1 and user_name=?2", nativeQuery = true)
    @Transactional
    int updateProductStatus(String barcode ,String user_name);

    @Modifying
    @Query(value = "update add_product set qty=?1 where barcode=?2 and user_name=?3", nativeQuery = true)
    @Transactional
    int updateProduction(int qty,String barcode,String user_name);

    @Query("select sk.name_of_item from AddProduct sk where user_name=?1")
    Set<String>getNameOfItem(String user_name);

    @Query("select sum(sk.qty) from AddProduct sk where name_of_item=?1 and user_name=?2")
    int sumOfQuantity(String name_of_item,String user_name);

    @Query("select sk from AddProduct sk where name_of_item=?1 and user_name=?2")
    List<AddProduct> getDataWithNameOfItem(String name_of_item,String user_name);

    @Query("select sk from AddProduct sk where user_name=?1")
    List<AddProduct> getDataWithUser_name(String user_name);

    @Query("select sum(sk.qty) from AddProduct sk where user_name=?1")
    int sumOfQuantity1(String user_name);


    @Modifying
    @Query(value = "delete from add_product where qty=0", nativeQuery = true)
    @Transactional
    int deleteAddProductWithZero();
}
