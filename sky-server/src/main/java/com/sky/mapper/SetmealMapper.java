package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.enumeration.OperationType;
import com.sky.vo.SetmealVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface SetmealMapper {

    @Select("select count(*) from sky_take_out.setmeal where category_id = #{id}")
    Integer getByCategoryId(Integer id);


    Page<SetmealVO> page(SetmealPageQueryDTO setmealPageQueryDTO);


    @AutoFill(OperationType.INSERT)
    void save(Setmeal setmeal);

    @Select("select * from sky_take_out.setmeal where id = #{id}")
    SetmealVO getById(Long id);

    @AutoFill(OperationType.UPDATE)
    void update(Setmeal setmeal);

    @Update("update sky_take_out.setmeal set status = #{status} where id = #{id}")
    void statusOrStop(Integer status, Long id);
}
