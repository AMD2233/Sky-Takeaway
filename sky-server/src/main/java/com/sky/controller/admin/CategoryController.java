package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



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
}
