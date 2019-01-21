package jp.ac.uryukyu.ie.e185753;
import java.util.ArrayList;
import com.google.gson.*;
/**
 * ブロックチェーンクラス。
 *  ArrayList<Block> blockchain; //ブロックチェーンのリスト
 *  int difficultyLevel; //マイニングの難しさ。今回は3。
 * Created by e185753 on 2019/01/21.
 */
public class Blockchain {
    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficultyLevel = 3;
    public static void main(String[] args){//dataUserInput()

        blockchain.add(new Block(Block.setDataUserInput(),"0"));
        System.out.println("Trying to mine block ...");
        blockchain.get(0).mineBlock(difficultyLevel);
        for(int i = 1 ;i < 3;i++){
            //chain multiple blocks expect for genesis block.
            blockchain.add(new Block(Block.setDataUserInput(),blockchain.get(blockchain.size()-1).hash));
            System.out.println("Trying to mine block ...");
            blockchain.get(i).mineBlock(difficultyLevel);
        }
        System.out.println("\nBlockchain is valid:"+isChainValid());
        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        //Gson gson = new GsonBuilder().setPrettyPrinting().create();
        //String blockchainJson = gson.toJson(blockchain);
        System.out.println("\nthe blockchain:");
        System.out.println(blockchainJson);
    }
    /**
     * 最終的にblockchainが正しいか判定するメソッド。
     * 前後関係になっているブロック同士が正しいハッシュ値を持っているか確認する。
     * @return boolean
     */
    public static boolean isChainValid(){
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficultyLevel]).replace('\0', '0');

        for(int i = 1; i < blockchain.size(); i ++){
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);
            //compare registered hash and calculated hash
            if(!currentBlock.hash.equals(currentBlock.makeHash()) ){
                System.out.println("Current Hashes not equal");
                return false;
            }
            if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
            if(!currentBlock.hash.substring( 0, difficultyLevel).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true;
    }
}

