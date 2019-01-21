package jp.ac.uryukyu.ie.e185753;
import java.security.MessageDigest;
/**
 * 暗号化クラス。
 * Created by e185753 on 2019/01/21.
 */
public class Encryption {
    /**
     * ハッシュ化するメソッド。
     * 受け取った文字列を使ってハッシュコードを生成する。
     * @param input hash値を計算するためのブロックから受け取る変数。
     */
    public static String applySha256(String input){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(input.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
                //(hes.length()==1)? hexString.append('0'):hexString.append(hex);
            }
            return hexString.toString();
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}

