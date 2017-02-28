package emitter;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.message.BasicHeader;
import rx.Observable;
import rx.apache.http.ObservableHttp;
import rx.apache.http.ObservableHttpResponse;

import java.util.ArrayList;
import java.util.List;

public class Client {

    public static void main(String[] args) {
        final RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(3000)
                .setConnectTimeout(500).build();

        List<BasicHeader> headers = new ArrayList<>();
        headers.add(new BasicHeader("Accept","application/json;charset=UTF-8"));

        final CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();

        httpclient.start();

        Observable<ObservableHttpResponse> result =
                ObservableHttp.createGet("http://localhost:8080", httpclient).toObservable();

        result.flatMap(response -> response.getContent().map(bytes -> new String(bytes))).forEach(s -> {
            System.out.println("here =========== "+s);
        });











    }

}
