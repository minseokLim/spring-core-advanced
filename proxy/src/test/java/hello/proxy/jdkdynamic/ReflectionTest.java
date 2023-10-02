package hello.proxy.jdkdynamic;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReflectionTest {

    @Test
    void reflection0() {
        Hello target = new Hello();

        log.info("start");
        final String result1 = target.callA();
        log.info("result={}", result1);

        log.info("start");
        final String result2 = target.callB();
        log.info("result={}", result2);
    }

    @Test
    void reflection1() throws Exception {
        final Class<?> classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        final Hello target = new Hello();

        final Method methodA = classHello.getMethod("callA");
        final Object result1 = methodA.invoke(target);
        log.info("result1={}", result1);

        final Method methodB = classHello.getMethod("callB");
        final Object result2 = methodB.invoke(target);
        log.info("result2={}", result2);
    }

    @Test
    void reflection2() throws Exception {
        final Class<?> classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        final Hello target = new Hello();

        final Method methodA = classHello.getMethod("callA");
        dynamicCall(methodA, target);

        final Method methodB = classHello.getMethod("callB");
        dynamicCall(methodB, target);
    }

    private void dynamicCall(Method method, Object target) throws Exception {
        log.info("start");
        final Object result = method.invoke(target);
        log.info("result={}", result);
    }

    @Slf4j
    static class Hello {
        public String callA() {
            log.info("callA");
            return "A";
        }

        public String callB() {
            log.info("callB");
            return "B";
        }
    }
}
