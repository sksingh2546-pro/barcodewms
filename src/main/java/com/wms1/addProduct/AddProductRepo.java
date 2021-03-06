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
    @Query(value = "insert into add_product(name_of_item,no_of_pcs,per_pcs_weight,packaging," +
            "carton_gross_weight,hsn,date,qty,status,user_name)values(?1,?2,?3,?4,?5,?6,?7,?8,'valid',?9)", nativeQuery = true)
    @Transactional
    int insertData(String name_of_item,int no_of_pcs,float per_pcs_weight,int packaging,
                   float carton_gross_weight,String hsn,String date,int qty,String user_name);


    @Query("select sk from AddProduct sk where name_of_item=?1 and status='valid' and user_name=?2 and date=?3")
    List<AddProduct> getProductBySkuNameList(String name_of_item, String user_name, String date);

    @Query("select sk from AddProduct sk where name_of_item=?1 and status='valid' and user_name=?2")
    List<AddProduct> getProductBySkuNameList(String name_of_item, String user_name);


    @Query("select sk from AddProduct sk where status='expire' and user_name=?1")
    List<AddProduct> getExpireList(String user_name);

    @Modifying
    @Query(value = "update add_product set status='expire' where name_of_item=?1 and user_name=?2", nativeQuery = true)
    @Transactional
    int updateProductStatus(String name_of_item ,String user_name);

    @Modifying
    @Query(value = "update add_product set qty=?1 where name_of_item=?2 and user_name=?3", nativeQuery = true)
    @Transactional
    int updateProduction(int qty,String name_of_item,String user_name);

    @Query("select sk.name_of_item from AddProduct sk where user_name=?1")
    Set<String>getNameOfItem(String user_name);

   @Query("select sum(sk.qty) from AddProduct sk where user_name=?1 and name_of_item=?2")
    int getNameOfItem(String user_name,String name_of_item);

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

    @Modifying
    @Query(value = "update add_product set no_of_pcs=?1,name_of_item=?2,per_pcs_weight=?3,packaging=?4,carton_gross_weight=?5,hsn=?6 where name_of_item=?7 and user_name=?8", nativeQuery = true)
    @Transactional
    int updateAddProduction(int num_pcs,String name_of_itemNew,float per_pcs_weight,int packaging,float carton_gross_weight,String hsn,
                         String name_of_itemOld,String user_name);

}
