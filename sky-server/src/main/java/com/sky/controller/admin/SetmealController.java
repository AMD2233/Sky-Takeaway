package com.sky.controller.admin;


import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "套餐管理相关接口")
@RequestMapping("admin/setmeal")
@Slf4j
public class SetmealController {
    @Autowired
    SetmealService setmealService;


    @ApiOperation("分页查询")
    @GetMapping("/page")
    public Result<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO) {
       PageResult pageResult =  setmealService.page(setmealPageQueryDTO);
       return Result.success(pageResult);
    }




    @ApiOperation("新增套餐")
    @PostMapping
    public Result save(@RequestBody SetmealDTO setmealDTO) {
        setmealService.save(setmealDTO);

        return Result.success();
    }



    @ApiOperation("修改套餐")
    @PutMapping
    public Result update(@RequestBody SetmealDTO setmealDTO) {
        setmealService.update(setmealDTO);

        return Result.success();
    }



    @ApiOperation("根据id查询套餐")
    @GetMapping("/{id}")
    public Result<SetmealVO> getById(@PathVariable Long id) {

       SetmealVO setmealVO = setmealService.getById(id);
       return Result.success(setmealVO);
    }


    @ApiOperation("起售停售")
    @PostMapping("/status/{status}")
    public Result statusOrStop(@PathVariable Integer status, Long id) {

        setmealService.statusOrStop(status, id);
        return Result.success();
    }


    @ApiOperation("批量删除")
    @DeleteMapping
    public Result deleteList(@RequestParam List<Long> ids) {
        setmealService.deleteList(ids);
        return Result.success();
    }

}
