package io.github.zemise.springbootmybaits.mapper;

import io.github.zemise.springbootmybaits.domain.Host;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HostMapper {
    @Select("select * from host_summary")
    public List<Host> findAll();
}
