package com.sky.mapper;


import com.sky.annotation.AutoFill;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DishMapper {

    @Select("select count(*) from sky_take_out.dish where category_id = #{id}")
    Integer getByCategoryId(Integer id);


    @AutoFill(OperationType.INSERT)
    long insert(Dish dish);
}


