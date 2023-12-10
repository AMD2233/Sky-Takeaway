package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
       PageResult pageResult = categoryService.list(categoryPageQueryDTO);
       return Result.success(pageResult);

    }
}
