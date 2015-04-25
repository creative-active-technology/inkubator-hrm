package com.inkubator.hrm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author rizkykojek
 * from http://www.codejava.net/coding/file-encryption-and-decryption-simple-example
 */
public class CryptoUtils {
 
    public static void encrypt(String cryptoAlgo, byte[] key, String inputPathFile, String outputPathFile) throws Exception {
    	File inputFile = new File(inputPathFile);
    	File outputFile = new File(outputPathFile);
    	encrypt(cryptoAlgo, key, inputFile, outputFile);
    }

    public static void encrypt(String cryptoAlgo, byte[] key, File inputFile, File outputFile) throws Exception {    	
        doCrypto(Cipher.ENCRYPT_MODE, cryptoAlgo, key, inputFile, outputFile);
    }
 
    public static void decrypt(String cryptoAlgo, byte[] key, String inputPathFile, String outputPathFile) throws Exception {
    	File inputFile = new File(inputPathFile);
    	File outputFile = new File(outputPathFile);
    	decrypt(cryptoAlgo, key, inputFile, outputFile);
    }

    public static void decrypt(String cryptoAlgo, byte[] key, File inputFile, File outputFile) throws Exception {    	
        doCrypto(Cipher.DECRYPT_MODE, cryptoAlgo, key, inputFile, outputFile);
    }
    
    private static void doCrypto(int cipherMode, String cryptoAlgo, byte[] key, File inputFile, File outputFile) throws Exception {
        try {
            Key secretKey = new SecretKeySpec(key, cryptoAlgo);
            Cipher cipher = Cipher.getInstance(cryptoAlgo);
            cipher.init(cipherMode, secretKey);
             
            FileOutputStream outputStream;
            try (FileInputStream inputStream = new FileInputStream(inputFile)) {
                byte[] inputBytes = new byte[(int) inputFile.length()];
                inputStream.read(inputBytes);
                byte[] outputBytes = cipher.doFinal(inputBytes);
                outputStream = new FileOutputStream(outputFile);
                outputStream.write(outputBytes);
            }
            outputStream.close();
             
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IOException | IllegalBlockSizeException | BadPaddingException e) {
            throw new Exception("Error encrypting/decrypting file", e);
        }
    }
}
