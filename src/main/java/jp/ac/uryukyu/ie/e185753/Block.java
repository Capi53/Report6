package jp.ac.uryukyu.ie.e185753;
import java.util.Date;

public class Block {
    public String hash; // To be public, your hashed message will be able to identified by others.
    public String previousHash;
    private String data;//To make data private variable, data can protect from others.
    private long timestamps; //record a log.

    //Bloc Constructor.
    public Block(String data,String previousHash){
        this.data = data;
        this.previousHash = previousHash;
        this.timestamps = new Date().getTime();
    }
}
