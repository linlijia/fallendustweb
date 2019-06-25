package com.modules.generator.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.modules.generator.entity.TroubleEntity;
import com.modules.generator.pojo.TroubleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * @author novacaine
 * @email superman@gmail.com
 * @date 2019-06-23 23:54:50
 */
@Mapper
public interface TroubleDao extends BaseMapper<TroubleEntity> {
    @Select("select dust_trouble.id as id ,site_name,dust_trouble.mn,happen_time,trouble_code,trouble_description," +
            "trouble_shooter,name as user_name ,solved,solved_method,solved_time from dust_trouble left join " +
            "dust_device on dust_trouble.mn = dust_device.mn left join sys_user on " +
            " dust_trouble.trouble_shooter = sys_user.user_id where 1=1 ${ew.sqlSegment}")
    List<TroubleVO> voPage(Page<TroubleVO> param, @Param("ew") Wrapper<TroubleVO> wrapper);

    @Select("select * from dust_device left join dust_trouble on dust_trouble.mn = dust_device.mn " +
            "  where happen_time > #{start} and happen_time < #{end} or happen_time is NULL ")
    List<TroubleVO> troubleRanking(@Param("start") Date start, @Param("end") Date end);
}
