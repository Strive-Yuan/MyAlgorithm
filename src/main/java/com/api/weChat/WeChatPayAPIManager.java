//package com.api.weChat;
//
//import okhttp3.HttpUrl;
//import org.apache.http.Header;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.utils.URIBuilder;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.message.BasicHeader;
//import org.apache.http.util.EntityUtils;
//
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.security.KeyFactory;
//import java.security.NoSuchAlgorithmException;
//import java.security.PrivateKey;
//import java.security.Signature;
//import java.security.spec.InvalidKeySpecException;
//import java.security.spec.PKCS8EncodedKeySpec;
//import java.util.Base64;
//import java.util.List;
//import java.util.Map;
//
//public class WeChatPayAPIManager {
//    private static final String mchId = "";
//    private static final String serialNo = "";
//    private static final String privateKey = "";
//    private static String URL = "";
//    private static final String nonceStr = "";
//
//
//    public static void main(String[] args) throws Exception {
//        URL = URL + "?bill_date=2023-04-04";
//        System.out.println("URL:" + URL);
//        HttpUrl url = HttpUrl.parse(URL);
//        String token = getToken("GET", url, "");
//        System.out.println(token);
//        List<Header> headers = List.of(
//                new BasicHeader("Content-Type", "application/json"),
//                new BasicHeader("Authorization", "WECHATPAY2-SHA256-RSA2048 " + token),
//                new BasicHeader("Accept", "application/json"),
//                new BasicHeader("User-Agent", "Mozilla/5.0" + token)
//        );
//        Map<String, String> paramMap = Map.of(
////                "bill_date", "2023-04-04"
//        );
//        doGET(URL, headers, paramMap);
//
//    }
//
//
//    static String getToken(String method, HttpUrl url, String body) throws Exception {
//        long timestamp = System.currentTimeMillis() / 1000;
//        String message = buildMessage(method, url, timestamp, nonceStr, body);
//        System.out.println("message:" + message);
//        String signature = sign(message.getBytes(StandardCharsets.UTF_8));
//        return "mchid=\"" + mchId + "\","
//                + "nonce_str=\"" + nonceStr + "\","
//                + "timestamp=\"" + timestamp + "\","
//                + "serial_no=\"" + serialNo + "\","
//                + "signature=\"" + signature + "\"";
//    }
//
//    static String sign(byte[] message) throws Exception {
//        Signature sign = Signature.getInstance("SHA256withRSA");
//        sign.initSign(getPrivateKey());
//        sign.update(message);
//
//        return Base64.getEncoder().encodeToString(sign.sign());
//    }
//
//    static String buildMessage(String method, HttpUrl url, long timestamp, String nonceStr, String body) {
//        String canonicalUrl = url.encodedPath();
//        System.out.println("canonicalUrl:" + canonicalUrl);
//        if (url.encodedQuery() != null) {
//            canonicalUrl += "?" + url.encodedQuery();
//        }
//        return method + "\n"
//                + canonicalUrl + "\n"
//                + timestamp + "\n"
//                + nonceStr + "\n"
//                + body + "\n";
//    }
//
//    public static PrivateKey getPrivateKey() throws IOException {
//        //证书私钥
//        String content = "";
//        try {
//            String privateKey = content.replace("-----BEGIN PRIVATE KEY-----", "")
//                    .replace("-----END PRIVATE KEY-----", "")
//                    .replaceAll("\\s+", "");
//
//            KeyFactory kf = KeyFactory.getInstance("RSA");
//            return kf.generatePrivate(
//                    new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey)));
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException("当前Java环境不支持RSA", e);
//        } catch (InvalidKeySpecException e) {
//            throw new RuntimeException("无效的密钥格式");
//        }
//    }
//
//
//    public static String doGET(String url, List<Header> headers, Map<String, String> paramMap) throws Exception {
//        String result;
//        URIBuilder uriBuilder = new URIBuilder(url);
//        if (paramMap != null) {
//            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
//                uriBuilder.addParameter(entry.getKey(), entry.getValue());
//            }
//        }
//        HttpGet httpGet = new HttpGet(uriBuilder.build());
//        if (headers != null && !headers.isEmpty()) {
//            headers.forEach(httpGet::addHeader);
//        }
//
//        try (CloseableHttpClient httpclient = HttpClients.createDefault();
//             CloseableHttpResponse response = httpclient.execute(httpGet)) {
//            result = EntityUtils.toString(response.getEntity(), "utf-8");
//            int statusCode = response.getStatusLine().getStatusCode();
//            System.out.println(statusCode);
//            System.out.println(result);
//            if (response.getStatusLine().getStatusCode() != 200) {
//                System.out.println("错误");
//            }
//        }
//        return result;
//    }
//}
//
