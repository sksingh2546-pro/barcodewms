package com.wms1.excel;

import com.wms1.addProduct.AddProduct;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ExcelRepo extends CrudRepository<Excel,Long> {

    @Query("select sk from Excel sk where name_of_item=?1 and user_name=?2")
    List<Excel> getNameOfItemList(String name_of_item,String user_name);

    @Query("select sk from Excel sk where user_name=?1 ")
    List<Excel> getNameOfItemList1(String user_name);

    @Modifying
    @Query(value = "update excel_upload set name_of_item=?1,hsn=?2,carton_gross_weight=?3,packaging=?4,per_pcs_weight=?5,num_pcs=?6 where name_of_item=?7 and user_name=?8",nativeQuery = true)
    @Transactional
    int updateSku(String name_of_itemNew,String hsn,float carton_gross_weight,int packaging,float per_pcs_weight,
                  int num_pcs,String name_of_itemOld,String user_name);
}
