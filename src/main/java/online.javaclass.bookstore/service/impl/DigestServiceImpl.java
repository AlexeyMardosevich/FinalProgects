package online.javaclass.bookstore.service.impl;

import online.javaclass.bookstore.service.DigestService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class DigestServiceImpl implements DigestService {

    @Override
    public String hash(String input) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            byte[] bytesInputs = input.getBytes();
            messageDigest.update(bytesInputs);
            byte[] bytesOut = messageDigest.digest();
            BigInteger bigInteger = new BigInteger(1, bytesOut);
            String hash = bigInteger.toString(16).toUpperCase();
            return hash;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
