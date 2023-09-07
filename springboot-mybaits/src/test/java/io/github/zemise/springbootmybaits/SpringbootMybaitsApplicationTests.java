package io.github.zemise.springbootmybaits;

import io.github.zemise.springbootmybaits.domain.Host;
import io.github.zemise.springbootmybaits.mapper.HostMapper;
import io.github.zemise.springbootmybaits.mapper.UserMapper;
import org.apache.ibatis.annotations.Select;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringbootMybaitsApplicationTests {
    @Autowired
    private HostMapper hostMapper;
    @Test
    public void testFindAll() {
        List<Host> list = hostMapper.findAll();
        System.out.println(list);
    }

}
