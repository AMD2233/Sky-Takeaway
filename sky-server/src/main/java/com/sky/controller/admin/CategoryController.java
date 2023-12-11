package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin/category")
@Api(tags = "分类表接口")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @PostMapping
    @ApiOperation("新增分类")
    public Result save(@RequestBody CategoryDTO categoryDTO) {
        categoryService.save(categoryDTO);
        return Result.success();
    }



    @GetMapping("/page")
    @ApiOperation("分类分页查询")
    public Result<PageResult> list(CategoryPageQueryDTO categoryPageQueryDTO) {
       PageResult pageResult = categoryService.page(categoryPageQueryDTO);
       return Result.success(pageResult);

    }



    @PostMapping("/status/{status}")
    @ApiOperation("启用, 禁用分类")
    public Result statusOrStop(@PathVariable Integer status, long id) {
        categoryService.statusOrStop(status, id);
        return Result.success();
    }



    @PutMapping
    @ApiOperation("修改分类")
    public Result update(@RequestBody CategoryDTO categoryDTO) {
        categoryService.update(categoryDTO);
        return Result.success();
    }

    @DeleteMapping
    @ApiOperation("根据id删除分类")
    public Result delete(Integer id) {
        categoryService.delete(id);
        return Result.success();
    }


    @GetMapping("/list")
    @ApiOperation("根据类型查询分类")
    public Result<List<Category>> list(String type) {
        List<Category> list = categoryService.list(type);
        return Result.success(list);
    }




}
