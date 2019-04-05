package org.secondKill.java_learning.security;


import java.io.FileOutputStream;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;

public class SecurityExample2 {

    /**
     * 生成密钥对
     *
     * @return
     * @throws Exception
     */
    public static KeyPair generateKey() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(512);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        return keyPair;
    }

    /**
     * 保存到文件
     *
     * @param path
     * @param y
     * @throws Exception
     */
    public static void saveFile(String path,String key)throws Exception{
        FileOutputStream fos = new FileOutputStream(path);
        fos.write(key.getBytes());
        fos.flush();
        fos.close();
    }


    public static byte[] encrypt(PrivateKey privateKey, String message) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);

        return cipher.doFinal(message.getBytes());
    }

    public static byte[] decrypt(PublicKey publicKey, byte [] encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);

        return cipher.doFinal(encrypted);
    }

    /**
     * 得到私钥
     * @param key 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = (new BASE64Decoder()).decodeBuffer(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }




    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        KeyPair keyPair = generateKey();
        // 私钥
        PrivateKey privateKey = keyPair.getPrivate();
        byte[] privateBT = privateKey.getEncoded();
        // base64
        saveFile("src/main/java/org/secondKill/java_learning/security/privateKey.txt", Base64.encodeBase64String(privateBT));
        //============================================//
        // 公钥
        PublicKey publicKey = keyPair.getPublic();
        byte[] publicBT = publicKey.getEncoded();
        // base64
        saveFile("src/main/java/org/secondKill/java_learning/security/publicKey.txt", Base64.encodeBase64String(publicBT));

        //验证密钥



    }

}
