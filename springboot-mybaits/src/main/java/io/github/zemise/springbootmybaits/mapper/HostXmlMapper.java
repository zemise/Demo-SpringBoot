package io.github.zemise.springbootmybaits.mapper;

import io.github.zemise.springbootmybaits.domain.Host;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @since 2023/9/7
 */

@Mapper
public interface HostXmlMapper {
    public List<Host> findAll();
}
