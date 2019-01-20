package jp.ac.uryukyu.ie.e185753;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BlockTest {
    Block genesisBlockTest = new Block("hoge","0");
    private int nonce = 0;
    @Test
    public String makeHash() {
        Encryption enc = new Encryption();
        String calculateHash = enc.applySha256(genesisBlockTest.previousHash + genesisBlockTest.getData() + Long.toString(genesisBlockTest.getTimestamps()) + Integer.toString(genesisBlockTest.setNonce(nonce)));
        return calculateHash;
    }
    @Test
    void mineBlock(){
        String target = new String(new char[1]).replace('\0', '0'); //difficulty level is 1.
        this.nonce = genesisBlockTest.setNonce(nonce);
        while (!genesisBlockTest.hash.substring(0, 1).equals(target)) {
            if(nonce>=1){
                System.out.println("hash:"+genesisBlockTest.hash);
                System.out.println("this is not encryption that begin with `0`");
            }
            this.nonce++;
            genesisBlockTest.hash = makeHash();
            System.out.println("-------------------------------");
        }
        System.out.println("hash:"+genesisBlockTest.hash);
        assertEquals("0",genesisBlockTest.hash.substring(0,1));
        System.out.println("Test pass");
    }
    
}
