package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

public interface DishService {
    void save(DishDTO dishDTO);

    PageResult page(DishPageQueryDTO dishPageQueryDTO);

    void statusOrStop(Integer status, Long id);

    DishVO getById(Long id);

    void update(DishDTO dishDTO);

    void deleteList(List<Long> ids);

    List<Dish> list(Long categoryId);
}
