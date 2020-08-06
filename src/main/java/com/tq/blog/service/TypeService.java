package com.tq.blog.service;

import com.tq.blog.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 分类相关操作
 */
public interface TypeService {
    Type saveType(Type type);//保存分类
    Type getTypeById(Long id);//id查询分类信息
    Page<Type> listType(Pageable pageable);//分页查询
    Type updateTypeById(Long id,Type type);//根据id修改分类信息
    void deleteTypeById(Long id);//根据id删除分类
}
