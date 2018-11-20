package utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.bouncycastle.util.encoders.Hex;

public final class Hashing {


  //husk create time - tager fra databasen
  // TODO: You should add a salt and make this secure (FIXED)
  public static String md5(String rawString) {

    if (Config.getSalt()) {

      String key = Config.getSaltKey();

      try {

        // We load the hashing algoritm we wish to use.
        // byte[] key = Config.getSaltkey();
        MessageDigest md = MessageDigest.getInstance("MD5");
        rawString = rawString + "dsad";

        // We convert to byte array
        byte[] byteArray = md.digest(rawString.getBytes());

        // Initialize a string buffer
        StringBuffer sb = new StringBuffer();

        // Run through byteArray one element at a time and append the value to our stringBuffer
        for (int i = 0; i < byteArray.length; ++i) {
          sb.append(Integer.toHexString((byteArray[i] & 0xFF) | 0x100).substring(1, 3));
        }

        //Convert back to a single string and return
        return sb.toString();

      } catch (java.security.NoSuchAlgorithmException e) {

        //If somethings breaks
        System.out.println("Could not hash string");
      }

      return null;
    }

  // TODO: You should add a salt and make this secure (FIXED)
  public static String sha(String rawString, byte[] salt) {

    try {
      // We load the hashing algoritm we wish to use.
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      rawString = rawString + "Kmskde";

      digest.update(salt);
      // We convert to byte array
      byte[] hash = digest.digest(rawString.getBytes(StandardCharsets.UTF_8));

      // We create the hashed string
      String sha256hex = new String(Hex.encode(hash));

      // And return the string
      return sha256hex;

    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }

    return rawString;
  }


}

