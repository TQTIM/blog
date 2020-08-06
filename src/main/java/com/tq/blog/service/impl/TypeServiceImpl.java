package com.tq.blog.service.impl;

import com.tq.blog.entity.Type;
import com.tq.blog.interceptor.NotFoundException;
import com.tq.blog.repository.TypeRepository;
import com.tq.blog.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    TypeRepository typeRepository;

    @Transactional//开启事务
    @Override
    public Type saveType(Type type) {
        return  typeRepository.save(type);
    }

    @Transactional
    @Override
    public Type getTypeById(Long id) {
        return typeRepository.getOne(id);
    }

    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);//分页查询
    }

    @Transactional
    @Override
    public Type updateTypeById(Long id, Type type) {
        Type t = typeRepository.getOne(id);//先根据id查到数据库type数据
        if (t == null) {
            throw new NotFoundException("不存在改类型");
        }
        BeanUtils.copyProperties(type,t);//用自带工具类将传过来的对象type属性值拷贝到对象t
        return typeRepository.save(t);//新赋值的t保存
    }

    @Transactional
    @Override
    public void deleteTypeById(Long id) {
        typeRepository.deleteById(id);
    }
}
