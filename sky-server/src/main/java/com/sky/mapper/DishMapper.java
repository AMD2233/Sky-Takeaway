package com.sky.mapper;


import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishMapper {

    @Select("select count(*) from sky_take_out.dish where category_id = #{id}")
    Integer getByCategoryId(Integer id);


    @AutoFill(OperationType.INSERT)
    long insert(Dish dish);

    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    void update(Dish dish);

    @Select("select * from sky_take_out.dish where id = #{id}")
    Dish getById(Long id);

    void deleteList(List<Long> ids);



}


