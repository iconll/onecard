package com.onecard.system.sys.dao;


import com.onecard.system.common.base.dao.GenericDao;
import com.onecard.system.sys.entity.QueryUserRole;
import com.onecard.system.sys.entity.UserRole;

/**
 *@author linzf
 **/
public interface UserRoleDao extends GenericDao<UserRole, QueryUserRole> {

    /**
     * 功能描述：获取权限菜单数据
     * @param entity
     * @return
     */
    UserRole getUserRoleAssociate(UserRole entity);
	
}