package com.onecard.system.suppermarket.service;

import com.huaying.framework.response.BaseResponse;
import com.huaying.framework.response.CommonSuccessResponse;
import com.huaying.framework.utils.StringUtils;
import com.kmut.retail.entity.Merchant;
import com.kmut.retail.entity.MerchantUser;
import com.kmut.retail.entity.Supplier;
import com.kmut.retail.repo.MerchantRepo;
import com.kmut.retail.repo.MerchantUserRepo;
import com.kmut.retail.repo.SupplierRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(rollbackFor=Exception.class)
public class SupplierService extends BaseService {

    @Autowired
    SupplierRepo supplierRepo;
    @Autowired
    MerchantRepo merchantRepo;
    @Autowired
    MerchantUserRepo merchantUserRepo;

    /**
     * 保存
     * @param supplier
     * @return
     */
    public BaseResponse save(Supplier supplier,Integer merchantId,Integer userId){
        if(supplier.getId()==null){
            MerchantUser merchantUser=merchantUserRepo.findOne(userId);
            Merchant merchant=merchantRepo.findOne(merchantId);
            supplier.setMerchant(merchant);
            supplier.setMerchantUser(merchantUser);
            supplier.setCreateTime(new Date());
        }else{
            Supplier target = supplierRepo.findOne(supplier.getId());
            supplier = (Supplier) po2po(supplier, target);
        }
        supplier = supplierRepo.save(supplier);
        return returnSave(supplier, false);
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    public BaseResponse query(Integer id){
        Supplier supplier = supplierRepo.findOne(id);
        return returnGet(supplier, true);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public BaseResponse delete(Integer id){
        supplierRepo.delete(id);
        return new CommonSuccessResponse();
    }

    /**
     * 列表查询
     * @param name
     * @param merchantId
     * @param pageNo
     * @param pageSize
     * @return
     */
    public BaseResponse list(String name, Integer merchantId, Integer pageNo, Integer pageSize){
        Page page = null;
        if(StringUtils.isNotEmpty(name)){
            page = supplierRepo.findByMerchantIdAndNameLike(merchantId, "%"+name+"%", new PageRequest(pageNo, pageSize));
        }else{
            page = supplierRepo.findByMerchantId(merchantId, new PageRequest(pageNo, pageSize));
        }
        return returnList(page, false);
    }

}
