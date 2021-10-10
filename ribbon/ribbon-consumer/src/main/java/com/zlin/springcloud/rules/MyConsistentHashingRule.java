package com.zlin.springcloud.rules;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 自定义一致性hash算法的负载均衡策略
 * @author zlin
 * @date 20211010
 */
@NoArgsConstructor
public class MyConsistentHashingRule extends AbstractLoadBalancerRule implements IRule {

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {

    }

    @Override
    public Server choose(Object key) {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        // 具体项目可跟不同的参数，如userId等
        String uri = request.getRequestURI() + "?" + request.getQueryString();
        return route(uri.hashCode(), getLoadBalancer().getAllServers());
    }

    private Server route(int reqHash, List<Server> serverList) {
        if (CollectionUtils.isEmpty(serverList)) {
            return null;
        }
        TreeMap<Long, Server> serverTreeMap = new TreeMap<>();
        serverList.forEach(server -> {
            for (int i = 0; i < 8; i++) {
                long serverHash = hash(server.getId() + i);
                serverTreeMap.put(serverHash, server);
            }
        });
        long hash = hash(String.valueOf(reqHash));
        SortedMap<Long, Server> serverSortedMap = serverTreeMap.tailMap(hash);
        // 当hash在环的末尾时，取环的开头
        if (serverSortedMap.isEmpty()) {
            return serverTreeMap.firstEntry().getValue();
        }
        return serverSortedMap.get(serverSortedMap.lastKey());
    }

    private long hash(String key) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] keyByte = key.getBytes(StandardCharsets.UTF_8);
        md5.update(keyByte);
        byte[] digest = md5.digest();
        return (digest[2] & 0xFF << 16)
                | (digest[1] & 0xFF << 8)
                | (digest[0] & 0xFF);
    }
}
