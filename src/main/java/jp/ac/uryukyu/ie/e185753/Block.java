package jp.ac.uryukyu.ie.e185753;
import java.util.Date;
import java.util.Scanner;

public class Block {
    public String hash; // To make hash public, your hashed message will be able to identified by others.
    public String previousHash;
    private String data;//To make data private variable, data can be protected from others.
    private long timestamps; //record a log.
    private int nonce;//once-only code.

    //Block Constructor.
    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timestamps = new Date().getTime();
        this.hash = makeHash();
    }

    //this method calculates this block`s hash
    public String makeHash() {
        String calculateHash = Encryption.applySha256(previousHash + data + Long.toString(timestamps));
        return calculateHash;
    }

    public static String setDataUserInput() {
        Scanner sc = new Scanner(System.in);
        System.out.print("you can input data here : ");
        String Str_data = sc.nextLine();
        return Str_data;
    }

    public void mineBlock(int difficultyLevel){
        String target =  new String(new char[difficultyLevel]).replace('\0', '0'); //difficulty * "0"
        while(!hash.substring( 0, difficultyLevel).equals(target)) {
            nonce ++;
            hash = makeHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }
}
