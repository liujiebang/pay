package com.github.liujiebang.pay.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;


public class ClientCustomSSL {


    public static String doRefund(String url, String data, String certPath, String mchId) throws Exception {
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(initCert(certPath, mchId))
                .build();
        try {
            // 设置响应头信息
            HttpPost httpost = new HttpPost(url);
            httpost.addHeader("Connection", "keep-alive");
            httpost.addHeader("Accept", "*/*");
            httpost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            httpost.addHeader("Host", "api.mch.weixin.qq.com");
            httpost.addHeader("X-Requested-With", "XMLHttpRequest");
            httpost.addHeader("Cache-Control", "max-age=0");
            httpost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
            httpost.setEntity(new StringEntity(data, "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(httpost);
            try {
                HttpEntity entity = response.getEntity();
                String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
                EntityUtils.consume(entity);
                return jsonStr;
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }

    private static SSLConnectionSocketFactory initCert(String certPath, String mchId) throws Exception {
        /**
         * 注意PKCS12证书 是从微信商户平台-》账户设置-》 API安全 中下载的
         */
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        /**
         *此处要改
         *wxconfig.SSLCERT_PATH : 指向你的证书的绝对路径，带着证书去访问
         */
        //P12文件目录
        FileInputStream instream = new FileInputStream(new File(certPath));
        try {
            /**
             * 下载证书时的密码、默认密码是你的MCHID mch_id
             * */
            keyStore.load(instream, mchId.toCharArray());
        } finally {
            instream.close();
        }

        // Trust own CA and all self-signed certs
        /**
         *
         * 下载证书时的密码、默认密码是你的MCHID mch_id
         * */
        SSLContext sslcontext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, mchId.toCharArray())
                .build();
        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[]{"TLSv1"},
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        return sslsf;
    }
}
