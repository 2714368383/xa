package com.base.net.xa.utils;

import sun.misc.BASE64Encoder;

import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: zhaikaixuan
 * @Date: 2019-08-24 11:01
 * @Description: 加密解密工具
 */
public class EnDeUtil {

    /**
     * RSA工具
     */
    public static final String KEY_ALGORITHM = "RSA";
    public static final Integer iterationNum = 1024;


    /**
     * 获取公钥
     *
     * @param keyMap
     * @return
     */
    public static String getPublicKey(Map<String, Object> keyMap) {
        Key publicKey = (Key) keyMap.get("PUBLIC_KEY");
        String encode = encryBASE64(publicKey);
        return encode;
    }


    /**
     * 获取私钥
     *
     * @param keyMap
     * @return
     */
    public static String getPrivateKey(Map<String, Object> keyMap) {
        Key publicKey = (Key) keyMap.get("PRIVATE_KEY");
        String encode = encryBASE64(publicKey);
        return encode;
    }

    private static String encryBASE64(Key publicKey) {
        byte[] encoded = publicKey.getEncoded();
        return new BASE64Encoder().encode(encoded);
    }


    /**
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @Author zhaikaixuan
     * @Description 初始化公密钥
     * @Date 2019-08-24 11:04
     * @Param []
     **/
    public static Map<String, Object> initialKey() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(iterationNum);

        KeyPair keyPair = keyPairGen.generateKeyPair();

        PrivateKey aPrivate = keyPair.getPrivate();
        PublicKey aPublic = keyPair.getPublic();
        Map<String, Object> keyMap = new HashMap<>(2);
        keyMap.put("PRIVATE_KEY", aPrivate);
        keyMap.put("PUBLIC_KEY", aPublic);
        return keyMap;
    }

    /**
     * MD5工具
     */


    public static void main(String[] args) throws NoSuchAlgorithmException {
        Map<String, Object> map = initialKey();
        String privateKey = getPrivateKey(map);
        String publicKey  = getPublicKey(map);
        System.out.println(privateKey);
        System.out.println("=======");
        System.out.println(publicKey);
    }

}
