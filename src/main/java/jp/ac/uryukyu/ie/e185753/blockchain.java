package jp.ac.uryukyu.ie.e185753;
import java.util.ArrayList;
import com.google.gson.*;

public class Blockchain {
    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficultyLevel = 6;
    public static void main(String[] args){//dataUserInput()

        blockchain.add(new Block(Block.setDataUserInput(),"0"));
        blockchain.add(new Block(Block.setDataUserInput(),blockchain.get(blockchain.size()-1).hash));
        blockchain.add(new Block(Block.setDataUserInput(),blockchain.get(blockchain.size()-1).hash));
        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        //Gson gson = new GsonBuilder().setPrettyPrinting().create();
        //String blockchainJson = gson.toJson(blockchain);
        System.out.println(blockchainJson);
    }
    public static boolean isChainValid(){
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficultyLevel]).replace('\0', '0');

        for(int i = 0; i < blockchain.size(); i ++){
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

