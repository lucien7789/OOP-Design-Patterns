package gof.behavioural.cor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class HttpDispatcher {
    private static HttpDispatcher instance;
    private List<HttpRequestHandler> handlerChain = new ArrayList<>();
    public Function<HttpRequestHandler, HttpRequestHandler> handlerSupplier =
            (caller) -> handlerChain.get(handlerChain.indexOf(caller) + 1);
    private HttpDispatcher() {}

    public static HttpDispatcher getInstance() {
        if (instance == null) {
            instance = new HttpDispatcher();
        }
        return instance;
    }

    public void addHandler(HttpRequestHandler handler) {
        handlerChain.add(handler);
    }

    public void removeHandler(HttpRequestHandler handler) {
        handlerChain.remove(handler);
    }

    public void dispatch(HttpRequest httpRequest, HttpResponse httpResponse) {
        if (handlerChain.size() == 0) {
            throw new RuntimeException("No Http handlers setup");
        }
        handlerChain.get(0).handle(httpRequest, httpResponse, handlerSupplier);
    }
}
