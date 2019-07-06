package com.modules.generator.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.modules.generator.entity.MonthlyDustFall;
import com.modules.generator.pojo.MonthlyDustFallVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author : linli
 * @time : 2019/7/5 13:59
 * @description :
 */
public interface MonthlyDustFallDao extends BaseMapper<MonthlyDustFall> {

    @Select("SELECT * FROM t_monthly_dust_fall WHERE year = ${year} AND month = ${month}")
    List<MonthlyDustFall> selectByDataTime(@Param("year") int year, @Param("month") int month);

    @Select("SELECT site_id, site_name, c.* FROM dust_device AS device LEFT JOIN (SELECT a.* FROM t_monthly_dust_fall AS a INNER JOIN (\n" +
            "SELECT mn, MAX(data_time) AS data_time FROM t_monthly_dust_fall GROUP BY mn) AS b ON a.mn = b.mn AND a.data_time = b.data_time) AS c\n" +
            "ON device.mn = c.mn GROUP BY site_id \n")
    List<MonthlyDustFallVo> selectLastestData();
}
