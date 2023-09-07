package io.github.zemise.springbootmybaits.mapper;

import io.github.zemise.springbootmybaits.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from t_user")
    public Select.List findAll();
}
