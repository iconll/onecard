package com.onecard.system.sys.service;


import com.onecard.system.common.base.dao.GenericDao;
import com.onecard.system.common.base.service.GenericService;
import com.onecard.system.sys.dao.UserAssociateRoleDao;
import com.onecard.system.sys.entity.QueryUserAssociateRole;
import com.onecard.system.sys.entity.User;
import com.onecard.system.sys.entity.UserAssociateRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *@author linzf
 **/
@Service("userAssociateRoleService")
@Transactional(rollbackFor={IllegalArgumentException.class})
public class UserAssociateRoleService extends GenericService<UserAssociateRole, QueryUserAssociateRole> {
	@Autowired
	@SuppressWarnings("SpringJavaAutowiringInspection")
	private UserAssociateRoleDao userAssociateRoleDao;
	@Override
	protected GenericDao<UserAssociateRole, QueryUserAssociateRole> getDao() {
		return userAssociateRoleDao;
	}

	/**
	 * 功能描述：根据用户的ID来删除用户的权限数据
	 * @param user
	 * @return
	 */
	public boolean removeUserRole(User user){
		return userAssociateRoleDao.removeUserRole(user)>0;
	}
}