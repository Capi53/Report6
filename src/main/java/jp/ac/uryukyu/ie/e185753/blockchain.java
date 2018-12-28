package jp.ac.uryukyu.ie.e185753;

public class blockchain {
    public static void main(String[] args){
        Block genesisblock = new Block("first block","0");// genesis block has no previous block so I put "0" as previousHash
        System.out.println("this is genesis block`s hash : "+genesisblock.hash);

    }
}
