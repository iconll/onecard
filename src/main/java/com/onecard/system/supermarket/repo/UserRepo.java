package com.onecard.system.suppermarket.repo;

import com.onecard.system.suppermarket.entity.GoodsType;
import com.onecard.system.suppermarket.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 商品分类repo
 */
public interface UserRepo extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    User findByUserName(String name);

}
