package io.github.zemise.security01.manytomany;

import io.github.zemise.security01.demo.manytomany.Employee;
import io.github.zemise.security01.demo.manytomany.EmployeeRepository;
import io.github.zemise.security01.demo.manytomany.Role;
import io.github.zemise.security01.demo.manytomany.RoleRepository;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;


@SpringBootTest
public class ManyToManyTest {
    @Resource
    EmployeeRepository employeeRepository;

    @Resource
    RoleRepository roleRepository;

    @Test
    @Transactional
    @Commit // 此处很重要，不加这条注释，可能不会入库
    // 插入
    /**
     * 1. 如果保存的关联数据，希望使用已有的，就需要从数据库中查出来
     *    否则提示游离状态，不能持久化
     *
     * 2. 如果一个业务方法有多个持久话操作，记得加上@Transactional
     *    否则不能公用一个session
     *
     * 3. 在单元测试中用到了@Transactional，如果有增删改的操作，一定要加@commit
     *    因为单元测试中，一般不会考虑修改数据库，认为事务方法为@Transactional，只是测试
     *    所以需要单独加上@commit
     */
    void test() {
        ArrayList<Role> list = new ArrayList<>();
//        list.add(new Role("管理员"));
//        list.add(new Role("普通职工"));
//        list.add(new Role("主任"));
//        list.add(new Role("超级管理员"));

        list.add(roleRepository.findByName("管理员"));
        list.add(roleRepository.findByName("普通职工"));


//        list.add(roleRepository.findById(1L).get());
//        list.add(roleRepository.findById(2L).get());

        Employee employee = new Employee();
        employee.setName("赵七");
        employee.setRoles(list);

        employeeRepository.save(employee);
    }


    @Test
    @Transactional(readOnly = true)
    void testFind() {

        // System.out.println(employeeRepository.findById(1L));
        Employee user = employeeRepository.findByName("赵大");
        System.out.println(user);
        System.out.println(user.getRoles());
    }

    // 删除

    /**
     * 注意点：
     * 多对多其实不适合删除
     * 因为经常出现可能除了和当前这端关联，还会关联另一端
     * 此时删除就会出现：DataIntegrityViolationException
     *   要删除，就要保证没有额外其他另一端数据关联
     */
    @Test
    @Transactional
    @Commit
    void testDelete() {
        Optional<Employee> employee = employeeRepository.findById(1L);
        employeeRepository.delete(employee.get());
    }

    // update
    @Test
    @Transactional
    @Commit
    void testUpdate() {
        Employee user = employeeRepository.findById(7L).get();
        user.setName("张八");

        employeeRepository.save(user);
    }
}
