package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.result.PageResult;

public interface CategoryService {
    void save(CategoryDTO categoryDTO);

    PageResult list(CategoryPageQueryDTO categoryPageQueryDTO);

    void statusOrStop(Integer status, long id);

    void update(CategoryDTO categoryDTO);
}
