package com.httpclient.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by Administrator on 2016/8/9.
 */
public class HttpBase  {
    private RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(15000)
            .setConnectTimeout(15000)
            .setConnectionRequestTimeout(15000)
            .build();
public String sendHttpPost(HttpPost httpPost){

    CloseableHttpClient httpClient = null;
    CloseableHttpResponse response = null;
    HttpEntity entity = null;
    String responseContent = null;
    try {
        // 创建默认的httpClient实例.
        httpClient = HttpClients.createDefault();
        httpPost.setConfig(requestConfig);
        // 执行请求
        response = httpClient.execute(httpPost);
        entity = response.getEntity();
        responseContent = EntityUtils.toString(entity, "UTF-8");

    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            // 关闭连接,释放资源
            if (response != null) {
                response.close();
            }
            if (httpClient != null) {
                httpClient.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    return  responseContent;

}

}
