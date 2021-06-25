package com.wms1.production;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductionRepo extends CrudRepository<Production,Long> {


    @Modifying
    @Query(value = "insert into production(barcode,name_of_item,per_pcs_weight,packaging," +
            "carton_gross_weight,hsn,num_pcs,user_name)values(?1,?2,?3,?4,?5,?6,?7,?8)", nativeQuery = true)
    @Transactional
    int insertData(String barcode,String name_of_item, float per_pcs_weight,int packaging,
                   float carton_gross_weight,String hsn,int num_pcs,String user_name);

    @Modifying
    @Query(value = "update production set num_pcs=?1,name_of_item=?2,per_pcs_weight=?3,packaging=?4,carton_gross_weight=?5,hsn=?6 where name_of_item=?7 and user_name=?8", nativeQuery = true)
    @Transactional
    int updateProduction(int num_pcs,String name_of_itemNew,float per_pcs_weight,int packaging,float carton_gross_weight,String hsn,
                         String name_of_itemOld,String user_name);

    @Query("select sk from Production sk where barcode=?1 and user_name=?2 ")
    List<Production> getBarcodeList(String barcode,String user_name);


}
