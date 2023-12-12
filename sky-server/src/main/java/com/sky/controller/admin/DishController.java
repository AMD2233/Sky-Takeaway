package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/admin/dish")
@Api(tags = "菜品相关接口")
public class DishController {

    @Autowired
    DishService dishService;


    @PostMapping
    @ApiOperation("新增菜品...")
    public Result save(@RequestBody DishDTO dishDTO) {
        dishService.save(dishDTO);
        return Result.success();
    }


    @ApiOperation("分页展示")
    @GetMapping("page")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO) {
       PageResult pageResult = dishService.page(dishPageQueryDTO);
        return Result.success(pageResult);
    }
}
