package jp.ac.uryukyu.ie.e185753;
import java.util.Scanner;

public class blockchain {
    public static void main(String[] args){
        Block genesisblock = new Block(dataUserInput(),"0");// genesis block has no previous block so I put "0" as previousHash
        System.out.println("this is genesis block`s hash : "+genesisblock.hash);

    }
    public static String dataUserInput(){
        Scanner sc = new Scanner(System.in);
        System.out.print("This is dataUserInput:");
        String Str_data = sc.nextLine();
        return Str_data;
    }
}
