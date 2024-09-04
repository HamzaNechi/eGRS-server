package com.orange.orangegrs.utils.email;

import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
public class EncryptionService {

    private static final String ALGORITHM = "AES";
    private String SECRET_KEY = "grs1234567890123";
    private SecretKey secretKey;

    public EncryptionService() throws Exception {
        this.secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
    }


    public String encrypt(String data) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encrypted = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

}
