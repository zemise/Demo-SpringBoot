package io.github.zemise.springbootmybaits;

import io.github.zemise.springbootmybaits.domain.Host;
import io.github.zemise.springbootmybaits.mapper.HostMapper;
import io.github.zemise.springbootmybaits.mapper.HostXmlMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringbootMybaitsApplicationTests {
    @Autowired
    private HostMapper hostMapper;

    @Autowired
    private HostXmlMapper hostXmlMapper;

    @Test
    public void testFindAll() {
        List<Host> list = hostMapper.findAll();
        System.out.println(list);
    }

    @Test
    public void testFindAll2() {
        List<Host> list = hostXmlMapper.findAll();
        System.out.println(list);
    }

}
