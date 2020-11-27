package org.dieschnittstelle.ess.utils;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.nio.client.HttpAsyncClient;

public class Http {

    public static CloseableHttpAsyncClient createAsyncClient() {
        return HttpAsyncClients.createDefault();
    }

    public static HttpClient createSyncClient() {
        return createSyncClient(false);
    }

    public static HttpClient createSyncClient(boolean redirecting) {

        // we create a default timeout setting
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(1000)
                .setConnectionRequestTimeout(1000)
                .setSocketTimeout(1000).build();

        try {
            HttpClient client = null;
            if (!redirecting) {
                client = HttpClients.custom()
                        .setDefaultRequestConfig(config)
                        .disableRedirectHandling()
                        .build();
            } else {
                client = HttpClients.custom()
                        .setDefaultRequestConfig(config)
                        .build();
            }

            return client;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
