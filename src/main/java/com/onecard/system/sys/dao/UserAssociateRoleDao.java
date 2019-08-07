package com.onecard.system.sys.dao;


import com.onecard.system.common.base.dao.GenericDao;
import com.onecard.system.sys.entity.QueryUserAssociateRole;
import com.onecard.system.sys.entity.User;
import com.onecard.system.sys.entity.UserAssociateRole;

/**
 *@author linzf
 **/
public interface UserAssociateRoleDao extends GenericDao<UserAssociateRole, QueryUserAssociateRole> {

    /**
     * 功能描述：根据用户的ID来删除用户的权限数据
     * @param user
     * @return
     */
    int removeUserRole(User user);
}