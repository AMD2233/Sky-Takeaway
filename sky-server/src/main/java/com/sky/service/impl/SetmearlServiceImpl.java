package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.context.BaseContext;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.exception.SetmealEnableFailedException;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealDishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Random;


@Service
public class SetmearlServiceImpl implements SetmealService {
    @Autowired
    SetmealMapper setmealMapper;
    @Autowired
    SetmealDishMapper setmealDishMapper;
    @Autowired
    DishMapper dishMapper;

    @Override
    public PageResult page(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageHelper.startPage(setmealPageQueryDTO.getPage(), setmealPageQueryDTO.getPageSize());
        Page<SetmealVO> page = setmealMapper.page(setmealPageQueryDTO);

        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    @Transactional
    public void save(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);

        setmealMapper.save(setmeal);

        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();

        setmealDishes.stream().forEach(
                setmealDish -> {
                    setmealDish.setSetmealId(setmeal.getId());
                    setmealDishMapper.save(setmealDish);

                }
        );

    }


    @Override
    public SetmealVO getById(Long id) {
       SetmealVO setmealVO = setmealMapper.getById(id);
       List<SetmealDish> list = setmealDishMapper.getBySetmealId(id);
       setmealVO.setSetmealDishes(list);

        return setmealVO;
    }


    @Override
    @Transactional
    public void update(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);

        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        Long setmealId = setmeal.getId();

        setmealMapper.update(setmeal);
        setmealDishMapper.deleteBySetmealId(setmealId);

        setmealDishes.stream().forEach(
                setmealDish -> {
                    setmealDish.setSetmealId(setmealId);
                    setmealDishMapper.save(setmealDish);
                }
        );
    }

    @Override
    public void statusOrStop(Integer status, Long id) {
        /*
            方案一
        List<SetmealDish> bySetmealId = setmealDishMapper.getBySetmealId(id);
        for (SetmealDish setmealDish : bySetmealId) {


            Long dishId = setmealDish.getDishId();
            Integer dishStatus = dishMapper.query(dishId);




            if (dishStatus == StatusConstant.DISABLE) {
                throw new SetmealEnableFailedException(MessageConstant.SETMEAL_ENABLE_FAILED);
            }

        }*/

        // 方案二
        List<Integer>  dishStatuss = dishMapper.query(id);
        for (Integer dishStatus : dishStatuss) {
            if (dishStatus == StatusConstant.DISABLE) {
                throw new SetmealEnableFailedException(MessageConstant.SETMEAL_ENABLE_FAILED);
            }
        }


        setmealMapper.statusOrStop(status, id);

    }


    @Override
    @Transactional
    public void deleteList(List<Long> ids) {
       List<Integer> statusls= setmealMapper.queryStatus(ids);
        for (Integer status : statusls) {
            if (status == StatusConstant.ENABLE) {
                throw new SetmealEnableFailedException(MessageConstant.SETMEAL_ON_SALE);
            }
        }


       setmealDishMapper.deleteBySetmealIds(ids);
       setmealMapper.deleteList(ids);

    }
}
