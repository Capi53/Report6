package jp.ac.uryukyu.ie.e185753;
import java.util.ArrayList;
import com.google.gson.*;

public class Blockchain {
    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static void main(String[] args){//dataUserInput()

        blockchain.add(new Block(Block.setDataUserInput(),"0"));
        blockchain.add(new Block(Block.setDataUserInput(),blockchain.get(blockchain.size()-1).hash));
        blockchain.add(new Block(Block.setDataUserInput(),blockchain.get(blockchain.size()-1).hash));

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        //Gson gson = new GsonBuilder().setPrettyPrinting().create();
        //String blockchainJson = gson.toJson(blockchain);
        System.out.println(blockchainJson);
    }
}

