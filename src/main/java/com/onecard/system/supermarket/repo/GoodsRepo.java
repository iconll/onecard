package com.onecard.system.supermarket.repo;

import com.onecard.system.supermarket.entity.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 商品repo
 */
public interface GoodsRepo extends JpaRepository<Goods, Integer>, JpaSpecificationExecutor<Goods> {

    List<Goods> queryByCode(String code);

    @Query(value = "select g.id,g.name,g.code,g.unit,g.specs," +
            "CAST(IFNULL(SUM(d.num),'0') as signed) as inNum," +//入库数
            "CAST(IFNULL(SUM(b.num),'0') as signed) as inBackNum," +//入库退货数
            "CAST(IFNULL(SUM(od.num),'0') as signed) as outNum," +//出库数
            "CAST(IFNULL(SUM(ob.num),'0') as signed) as outBackNum," +//出库退货数
            "CAST((IFNULL(SUM(d.num),'0')-IFNULL(SUM(b.num),'0')-IFNULL(SUM(od.num),'0')+IFNULL(SUM(ob.num),'0')) as signed) as realNum " +//真实库存
            "from goods g " +
            "left join inbound_detail d on g.id=d.goods_id " +
            "left join inbound_back b on g.id=b.goods_id " +
            "left join outbound_detail od on g.id=od.goods_id " +
            "left join outbound_back ob on g.id=ob.goods_id " +
            "where g.name like %?1% GROUP BY g.id, ?#{#pageable}",
            countQuery = "select count(*) from goods where g.name like %?1%",
            nativeQuery = true)
    Page<Object[]> getStockByName(String name, Pageable pageable);

    @Query(value = "select g.id,g.name,g.code,g.unit,g.specs," +
            "CAST(IFNULL(SUM(d.num),'0') as signed) as inNum," +//入库数
            "CAST(IFNULL(SUM(b.num),'0') as signed) as inBackNum," +//入库退货数
            "CAST(IFNULL(SUM(od.num),'0') as signed) as outNum," +//出库数
            "CAST(IFNULL(SUM(ob.num),'0') as signed) as outBackNum," +//出库退货数
            "CAST((IFNULL(SUM(d.num),'0')-IFNULL(SUM(b.num),'0')-IFNULL(SUM(od.num),'0')+IFNULL(SUM(ob.num),'0')) as signed) as realNum " +//真实库存
            "from goods g " +
            "left join inbound_detail d on g.id=d.goods_id " +
            "left join inbound_back b on g.id=b.goods_id " +
            "left join outbound_detail od on g.id=od.goods_id " +
            "left join outbound_back ob on g.id=ob.goods_id " +
            "where g.code like %?1% GROUP BY g.id, ?#{#pageable}",
            countQuery = "select count(*) from goods where g.code like %?1%",
            nativeQuery = true)
    Page<Object[]> getStockByCode(String code, Pageable pageable);

    @Query(value = "select g.id,g.name,g.code,g.unit,g.specs," +
            "CAST(IFNULL(SUM(d.num),'0') as signed) as inNum," +//入库数
            "CAST(IFNULL(SUM(b.num),'0') as signed) as inBackNum," +//入库退货数
            "CAST(IFNULL(SUM(od.num),'0') as signed) as outNum," +//出库数
            "CAST(IFNULL(SUM(ob.num),'0') as signed) as outBackNum," +//出库退货数
            "CAST((IFNULL(SUM(d.num),'0')-IFNULL(SUM(b.num),'0')-IFNULL(SUM(od.num),'0')+IFNULL(SUM(ob.num),'0')) as signed) as realNum " +//真实库存
            "from goods g " +
            "left join inbound_detail d on g.id=d.goods_id " +
            "left join inbound_back b on g.id=b.goods_id " +
            "left join outbound_detail od on g.id=od.goods_id " +
            "left join outbound_back ob on g.id=ob.goods_id " +
            "GROUP BY g.id, ?#{#pageable}",
            countQuery = "select count(*) from goods",
            nativeQuery = true)
    Page<Object[]> getStock(Pageable pageable);

}
