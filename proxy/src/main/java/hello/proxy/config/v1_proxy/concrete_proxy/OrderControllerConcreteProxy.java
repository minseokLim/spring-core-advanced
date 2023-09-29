package hello.proxy.config.v1_proxy.concrete_proxy;

import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.trace.callback.TraceTemplate;
import hello.proxy.trace.logtrace.LogTrace;

public class OrderControllerConcreteProxy extends OrderControllerV2 {

    private final OrderControllerV2 target;
    private final TraceTemplate template;

    public OrderControllerConcreteProxy(final OrderControllerV2 target, final LogTrace logTrace) {
        super(null);
        this.target = target;
        this.template = new TraceTemplate(logTrace);
    }

    @Override
    public String request(String itemId) {
        return template.execute("OrderController.request()", () -> target.request(itemId));
    }

    @Override
    public String noLog() {
        return target.noLog();
    }
}
