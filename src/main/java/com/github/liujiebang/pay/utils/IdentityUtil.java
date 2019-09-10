package com.github.liujiebang.pay.utils;

import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;


public class IdentityUtil {

    private static final String SYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final Random RANDOM = new SecureRandom();

    public static final String SIGN_TYPE_MD5 = "MD5";

    public static final String SIGN_TYPE_SHA256 = "HMAC-SHA256";

    public static final String SIGN = "sign";


    public static boolean inspectionSign(String xmlStr, String key, String signType) throws Exception {
        Map<String, String> data = XMLUtil.xmlToMap(xmlStr);
        if (!data.containsKey(SIGN)) {
            return false;
        }
        String sign = data.get(SIGN);
        return generateSignature(data, key, signType).equals(sign);
    }

    public static boolean inspectionSign(Map<String, String> data, String key, String signType) throws Exception {
        if (!data.containsKey(SIGN)) {
            return false;
        }
        String sign = data.get(SIGN);
        return generateSignature(data, key, signType).equals(sign);
    }

    public static String generateSignature(final Map<String, String> data, String key, String signType) throws Exception {
        Set<String> keySet = data.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for (String k : keyArray) {
            if (k.equals(SIGN)) {
                continue;
            }
            if (data.get(k).trim().length() > 0) // 参数值为空，则不参与签名
                sb.append(k).append("=").append(data.get(k).trim()).append("&");
        }
        sb.append("key=").append(key);
        if (SIGN_TYPE_MD5.equals(signType)) {
            return DigestUtils.md5Hex(sb.toString()).toUpperCase();
        } else if (SIGN_TYPE_SHA256.equals(signType)) {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            return byteArrayToHexString(sha256_HMAC.doFinal(sb.toString().getBytes("utf-8"))).toUpperCase();
        } else {
            throw new Exception(String.format("Invalid sign_type: %s", signType));
        }
    }

    public static String uuid() {
        String uuid = String.valueOf(UUID.randomUUID()).replace("-", "");
        uuid = uuid.substring(7);
        return uuid;
    }

    public static String getLocalhostIp() {
        String ip = "";
        try {
            ip = InetAddress.getLocalHost().toString();
            ip = ip.substring(ip.indexOf("/") + 1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ip;
    }

    /**
     * sha-1 类型签名
     *
     * @param str
     * @return
     */
    public static String shaSign(String str) {
        MessageDigest crypt = null;
        String signature = null;
        try {
            crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(str.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return signature;
    }

    public static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }


    public static String generateNonceStr() {
        char[] nonceChars = new char[32];
        for (int index = 0; index < nonceChars.length; ++index) {
            nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
        }
        return new String(nonceChars);
    }

    public static String getMoeny(double price) {
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(price));
        BigDecimal moeny = bigDecimal.multiply(new BigDecimal("100"));
        return moeny.setScale(0, BigDecimal.ROUND_DOWN).toString();
    }

    public static String getTimeStamp() {
        return String.valueOf(Calendar.getInstance().getTimeInMillis() / 1000);
    }

    /**
     * HmacSHA256类型签名
     *
     * @param map
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public static String createSign(Map<String, String> map, String key, String signType) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, PayException {
        Map<String, String> params = new TreeMap<String, String>();
        Set<String> set = map.keySet();
        for (String string : set) {
            if (map.get(string) != null && !map.get(string).equals("")) {
                params.put(string, String.valueOf(map.get(string)));
            }
        }
        String string = createSign(params);
        String stringSignTemp = string + "&key=" + key;
        if (SIGN_TYPE_MD5.equals(signType)) {
            return DigestUtils.md5Hex(stringSignTemp).toUpperCase();
        } else if (SIGN_TYPE_SHA256.equals(signType)) {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            return byteArrayToHexString(sha256_HMAC.doFinal(stringSignTemp.getBytes("utf-8"))).toUpperCase();
        } else {
            throw new PayException(String.format("Invalid sign_type: %s", signType));
        }

    }

    /**
     * 将加密后的字节数组转换成字符串
     *
     * @param b 字节数组
     * @return 字符串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b != null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1)
                hs.append('0');
            hs.append(stmp);
        }
        return hs.toString().toLowerCase();
    }

    /**
     * 构造package
     *
     * @param params
     * @return
     */
    public static String createSign(Map<String, String> params) {
        Set<String> keysSet = params.keySet();
        Object[] keys = keysSet.toArray();
        Arrays.sort(keys);
        StringBuffer temp = new StringBuffer();
        boolean first = true;
        for (Object key : keys) {
            if (first) {
                first = false;
            } else {
                temp.append("&");
            }
            temp.append(key.toString()).append("=");
            Object value = params.get(key);
            String valueString = "";
            if (null != value) {
                valueString = value.toString();
            }
            temp.append(valueString);
        }
        return temp.toString();
    }

}
