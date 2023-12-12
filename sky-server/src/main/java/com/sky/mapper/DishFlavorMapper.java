package com.sky.mapper;


import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishFlavorMapper {
    void insert(List<DishFlavor> flavors);

    @Select("select * from sky_take_out.dish_flavor where dish_id = #{id}")
    List<DishFlavor> getByDishId(Long id);




    @Delete("delete from sky_take_out.dish_flavor where dish_id = #{id}")
    void byDishIddelete(Long id);


    void deleteList(List<Long> ids);
}
