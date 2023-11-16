package io.github.zemise.thymeleaf.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">zemise</a>
 * @Date 2023/11/11
 * @since 1.0
 */
@Data
public class UserVo {
    private String username;
    private Integer age;
    private Integer sex;
    private Boolean isVip;
    private Date createdTime;
    private List<String> tags;
}
