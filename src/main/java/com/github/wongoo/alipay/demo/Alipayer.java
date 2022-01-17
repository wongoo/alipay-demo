package com.github.wongoo.alipay.demo;

import com.alipay.api.AlipayClient;
import com.alipay.api.CertAlipayRequest;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.file.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author wongoo
 */
public class Alipayer {

    private Alipayer() {
    }

    private static final String CONFIG_DIR = System.getProperty("user.home") + "/alipay_demo";
    private static final Properties PROPERTIES = initialProperties();

    private static Properties initialProperties() {
        try {
            Properties properties = new Properties();
            File configFile = new File(CONFIG_DIR + "/alipay.properties");
            FileInputStream configInputStream = FileUtils.openInputStream(configFile);
            properties.load(configInputStream);
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final AlipayClient CLIENT = initialAlipayClient();

    public static AlipayClient client() {
        return CLIENT;
    }

    public static Properties properties() {
        return PROPERTIES;
    }

    private static AlipayClient initialAlipayClient() {
        try {
            // 1. 创建AlipayClient实例
            return new DefaultAlipayClient(getClientParams());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static CertAlipayRequest getClientParams() throws Exception {

        CertAlipayRequest certParams = new CertAlipayRequest();
        certParams.setServerUrl("https://openapi.alipay.com/gateway.do");

        String appId = PROPERTIES.getProperty("alipay.appid");
        //请更换为您的AppId
        certParams.setAppId(appId);
        //请更换为您的PKCS8格式的应用私钥
        certParams.setPrivateKey(PROPERTIES.getProperty("alipay.private-key"));
        //请更换为您使用的字符集编码，推荐采用utf-8
        certParams.setCharset("utf-8");
        certParams.setFormat("json");
        certParams.setSignType("RSA2");
        //请更换为您的应用公钥证书文件路径
        certParams.setCertPath(CONFIG_DIR + "/appCertPublicKey_" + appId + ".crt");
        //请更换您的支付宝公钥证书文件路径
        certParams.setAlipayPublicCertPath(CONFIG_DIR + "/alipayCertPublicKey_RSA2.crt");
        //更换为支付宝根证书文件路径
        certParams.setRootCertPath(CONFIG_DIR + "/alipayRootCert.crt");
        return certParams;
    }

}
