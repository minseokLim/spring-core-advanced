package hello.aop.proxyvs;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

@Slf4j
public class ProxyCastingTest {

    @Test
    void jdkProxy() {
        final MemberServiceImpl target = new MemberServiceImpl();
        final ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(false);

        final MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();
        // ClassCastException
        Assertions.assertThatThrownBy(() -> {
            final MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy;
        }).isInstanceOf(ClassCastException.class);
    }

    @Test
    void cglibProxy() {
        final MemberServiceImpl target = new MemberServiceImpl();
        final ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true);

        final MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();
        final MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy;
    }
}
