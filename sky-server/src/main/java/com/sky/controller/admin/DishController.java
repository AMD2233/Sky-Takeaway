package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
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


    @ApiOperation("菜品起售, 停售")
    @PostMapping("/status/{status}")
    public Result statusOrStop(@PathVariable Integer status, Long id) {
        dishService.statusOrStop(status, id);
        return Result.success();
    }



    @ApiOperation("根据id查询菜品")
    @GetMapping("/{id}")
    public Result<DishVO> getById(@PathVariable Long id) {
        DishVO dish = dishService.getById(id);
        return Result.success(dish);

    }


    @ApiOperation("修改菜品")
    @PutMapping
    public Result update(@RequestBody DishDTO dishDTO) {
        dishService.update(dishDTO);

        return Result.success();
    }
}
