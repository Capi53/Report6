package jp.ac.uryukyu.ie.e185753;
import java.util.Date;
import java.util.Scanner;
/**
 * ブロッククラス。
 *  String hash; //ブロックのハッシュ値。
 *  String previousHash; //ブロックチェーンにおける、一つ前のブロックのハッシュ値。
 *  String data; //ブロックが持つデータ。
 *  long timestamps; //ブロックを作った時の時間。
 *  int nonce; //ブロックの要素を暗号化するときに使う一時的な変数。
 * Created by e185753 on 2019/01/21.
 */
public class Block {
    public String hash; // To make hash public, your hashed message will be able to identified by others.
    public String previousHash;
    private String data; //To make data private variable, data can be protected from others.
    private long timestamps; //record a log.
    private int nonce; //once-only code.

    //Block Constructor.
    /**
     * ブロッククラスのコンストラクタ。
     * data,previousHash,timestamps,hashを指定する。
     * @param data データを格納した変数。
     * @param previousHash このブロックの一つ前のブロックが持つハッシュ値。
     */
    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timestamps = new Date().getTime();
        this.hash = makeHash();
    }

    //this method calculates this block`s hash
    /**
     * ハッシュ化するメソッドを実行するメソッド。
     * ハッシュ化を担うエンクリプションクラスに必要な要素を受け渡す。
     * @return String
     */
    public String makeHash() {
        String calculateHash = Encryption.applySha256(previousHash + data + Long.toString(timestamps) + Integer.toString(nonce) );
        return calculateHash;
    }
    /**
     * 標準入力されたデータを受け取るメソッド。
     * 受け取った文字列データを一時的な変数Str_dataに保存する。
     * @return String
     */
    public static String setDataUserInput() {
        Scanner sc = new Scanner(System.in);
        System.out.print("you can input data here : ");
        String Str_data = sc.nextLine();
        return Str_data;
    }
    /**
     * 条件を満たすハッシュ値になるまで計算するメソッド。
     * 条件を満たすハッシュ値になるまで、繰り返し計算する。
     * @param difficultyLevel
     */
    public void mineBlock(int difficultyLevel){
        String target =  new String(new char[difficultyLevel]).replace('\0', '0'); //hash must be start with 0(dificulltyLevel times in a row)、「diffibultyLevelの分だけ先頭から0が連続で続く。」という条件。
        while(!hash.substring( 0, difficultyLevel).equals(target)) {
            nonce ++;
            hash = makeHash();
            //System.out.println(nonce);
        }
        System.out.println("Block Mined!!! : " + hash);
    }

    //for unit test
    /**
     * ユニットテストのためのメソッド。
     * ユニットテストする際に、データを参照することができる。
     * @return String ハッシュ値を計算するためのブロックから受け取る変数。
     */
    public String getData() {
        return data;
    }
    /**
     * ユニットテストのためのメソッド。
     * ユニットテストする際に、Nonceの値を更新することができる。
     * Nonceを更新しないとmibeBlockメソッドで一発で条件を満たせずループに入る時にmakeHashメソッドの返り値が変化しなくなる。つまり、無限ループに陥る。
     * @return String hash値を計算するためのブロックから受け取る変数。
     */
    public int setNonce(int nonce) {
        this.nonce = nonce;
        return nonce;
    }
    /**
     * ユニットテストのためのメソッド。
     * ユニットテストする際に、タイムスタンプを参照することができる。
     * @return String hash値を計算するためのブロックから受け取る変数。
     */
    public long getTimestamps() {
        return timestamps;
    }
}
