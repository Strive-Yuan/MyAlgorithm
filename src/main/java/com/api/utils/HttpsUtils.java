package com.api.utils;


import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Map;

public final class HttpsUtils {

    private static final int CONNECT_TIMEOUT = 5000; // 连接超时时间，毫秒
    private static final int READ_TIMEOUT = 10000; // 读取超时时间，毫秒

    public static String doGet(String url) throws IOException {
        return doGet(url, null);
    }

    public static String doGet(String url, Map<String, String> headers) throws IOException {
        HttpURLConnection conn = openConnection(url, HttpMethod.GET, headers);
        return readResponse(conn);
    }

    public static String doPost(String url, String body) throws IOException {
        return doPost(url, body, null);
    }

    public static String doPost(String url, String body, Map<String, String> headers) throws IOException {
        HttpURLConnection conn = openConnection(url, HttpMethod.POST, headers);
        if (body != null && !body.isEmpty()) {
            conn.setDoOutput(true);
            try (OutputStream os = conn.getOutputStream()) {
                os.write(body.getBytes(StandardCharsets.UTF_8));
            }
        }
        return readResponse(conn);
    }

    private static HttpURLConnection openConnection(String url, HttpMethod method, Map<String, String> headers) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        if (conn instanceof HttpsURLConnection) {
            ((HttpsURLConnection) conn).setSSLSocketFactory(getTrustedSocketFactory());
            ((HttpsURLConnection) conn).setHostnameVerifier(getTrustedHostnameVerifier());
        }
        conn.setRequestMethod(method.toString());
        conn.setConnectTimeout(CONNECT_TIMEOUT);
        conn.setReadTimeout(READ_TIMEOUT);
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        return conn;
    }

    private static String readResponse(HttpURLConnection conn) throws IOException {
        int statusCode = conn.getResponseCode();
        InputStream is;
        if (statusCode >= 200 && statusCode <= 299) {
            is = conn.getInputStream();
        } else {
            is = conn.getErrorStream();
        }
        if (is == null) {
            throw new IOException("Unable to read response stream");
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }
        is.close();
        return os.toString(StandardCharsets.UTF_8.name());
    }

    private static SSLSocketFactory getTrustedSocketFactory() throws IOException {
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{new TrustAllManager()}, new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new IOException(e);
        }
    }

    private static HostnameVerifier getTrustedHostnameVerifier() {
        return (hostname, session) -> true;
    }

    private static final class TrustAllManager implements X509TrustManager {
        public void checkClientTrusted(X509Certificate[] chain, String authType) {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    public enum HttpMethod {
        GET, POST
    }

    enum ContentType {
        JSON("application/json;charset=UTF-8"),
        FORM_URLENCODED("application/x-www-form-urlencoded");

        private final String value;

        ContentType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}


