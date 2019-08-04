package com.mibcxb.droid.core.util;

import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class HashUtils {
    public interface Algorithm {
        String MD5 = "MD5";
        String SHA1 = "SHA-1";
        String SHA256 = "SHA-256";
    }

    public static byte[] md5(byte[] data) {
        return hash(data, Algorithm.MD5);
    }

    public static byte[] sha1(byte[] data) {
        return hash(data, Algorithm.SHA1);
    }

    public static byte[] sha256(byte[] data) {
        return hash(data, Algorithm.SHA256);
    }

    public static byte[] hash(InputStream input, String algorithm) {
        byte[] data = IoUtils.read(input);
        return hash(data, algorithm);
    }

    public static byte[] hash(byte[] data, String algorithm) {
        byte[] hash = new byte[0];
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            hash = digest.digest(data);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash;
    }
}
