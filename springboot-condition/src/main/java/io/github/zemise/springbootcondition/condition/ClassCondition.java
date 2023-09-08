package io.github.zemise.springbootcondition.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @since 2023/9/8
 */
public class ClassCondition implements Condition {

    /**
     *
     * @param context 上下文对象。用于获取环境，IOC容器，ClassLoader对象
     * @param metadata 注解元对象。可以用于获得注解定义的属性值
     * @return
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //Environment environment = context.getEnvironment();
        //environment.getProperty()


        //return false;
        // 1. 需求： 导入Jedis坐标后创建Bean
        // 获取注解属性值 value

        // 思路：判断redis.client.jedis.class文件是否存在
        boolean flag = true;
        try {
            Class<?> aClass = Class.forName("redis.clients.jedis.Jedis");
            return flag;
        } catch (ClassNotFoundException e) {
            flag = false;
        }
        return flag;
    }
}
