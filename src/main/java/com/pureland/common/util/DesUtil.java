package com.pureland.common.util;
import com.thoughtworks.xstream.core.util.Base64Encoder;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 
 * @author qinpeirong
 *
 */
public class DesUtil {

    public static void main(String[] args) {
//        String encrypt = encrypt("100000101010010");
//        System.out.println("加密: " + encrypt);
    	String encrypt = "r96xz4hbpS0bMBrT7VtU4g==";
        String decrypt = decrypt(encrypt);
        System.out.println("解密："+ decrypt);
    }

    //创建动态密钥
    private static byte[] createDynKey(){
        SecureRandom sr = new SecureRandom();
        byte rawKeyData[] = null;
        try {
            KeyGenerator kg = KeyGenerator.getInstance( "DES" );
            kg.init( sr );
            SecretKey sk = kg.generateKey();
            rawKeyData = sk.getEncoded();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//        System.out.print("密钥：");
//        for (int i = 0; i < rawKeyData.length; i++) {
//            System.out.print(rawKeyData[i]+",");
//        }
//        System.out.println();
        return rawKeyData;
    }

    //创建固定密钥
    private static byte[] createKey(){
        byte[] psw="Cheater is an idiot".getBytes();
        SecretKey   key   =   new   SecretKeySpec(psw,"DES"); //固定密码
        //des.init(Cipher.ENCRYPT_MODE,   k);
        byte[] byteKey = key.getEncoded();

//        System.out.print("密钥：");
//        for (int i = 0; i < byteKey.length; i++) {
//            System.out.print(byteKey[i]+",");
//        }
//        System.out.println();
        return byteKey;
    }

    //加密数据
    public static String encrypt(String s){
        byte [] rawKeyData = null;
        try {
            DESKeySpec dks = new DESKeySpec(createKey());
            SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
            SecretKey sk = skf.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DES");
//          cipher.init(Cipher.ENCRYPT_MODE, sk, sr);
            cipher.init(Cipher.ENCRYPT_MODE, sk);
            byte[] data = s.getBytes();
            rawKeyData = cipher.doFinal(data);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
//        System.out.print("加密数据：");
//        for (int i = 0; i < rawKeyData.length; i++) {
//            System.out.print(rawKeyData[i]+",");
//        }
//        System.out.println();
        return new Base64Encoder().encode(rawKeyData);
    }

    //解密数据
    public static String decrypt(String rawKeyData){
        try {
            DESKeySpec dks = new DESKeySpec(createKey());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance( "DES" );
            SecretKey sk = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance( "DES" );
//            cipher.init( Cipher.DECRYPT_MODE, sk, sr );
            cipher.init( Cipher.DECRYPT_MODE, sk);
            byte encryptedData[] = new Base64Encoder().decode(rawKeyData);
            byte decryptedData[] = cipher.doFinal(encryptedData);
//          System.out.println("解密："+new String(decryptedData));
            return new String(decryptedData);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

}
