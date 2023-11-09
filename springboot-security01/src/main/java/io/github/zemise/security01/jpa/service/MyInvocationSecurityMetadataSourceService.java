package io.github.zemise.security01.jpa.service;

import io.github.zemise.security01.jpa.domain.SysRolePermission;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

    private final SysService service;

    @Autowired
    public MyInvocationSecurityMetadataSourceService(SysService service) {
        this.service = service;
    }

    /**
     * 每一个资源所需要的角色
     */
    private static HashMap<String, Collection<ConfigAttribute>> map = null;

    private void loadResourceDefine() {
        map = new HashMap<>();
        // 权限资源和角色对应的表，也就是角色权限中间表
        List<SysRolePermission> rolePermissionList = service.findAllRolePermission();

        // 每个资源所需要的权限
        rolePermissionList.forEach(rolePermission -> {
            String url = rolePermission.getUrl();
            String roleName = rolePermission.getRoleName();

            SecurityConfig role = new SecurityConfig(roleName);
            if (map.containsKey(url)) {
                map.get(url).add(role);
            } else {
                map.put(url, new ArrayList<ConfigAttribute>() {{
                    add(role);
                }});
            }
        });
    }

    /**
     * @param object the object being secured
     * @return Collection<ConfigAttribute>
     * @throws IllegalArgumentException
     */
    @Override
    @Transactional
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if (null == map) {
            loadResourceDefine();
            // object中包含用户请求的request信息
            HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();

            for (Iterator<String> iter = map.keySet().iterator(); iter.hasNext(); ) {
                String url = iter.next();
                if (new AntPathRequestMatcher(url).matches(request)) {
                    return map.get(url);
                }
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
//        return false;
        return true;
    }
}
