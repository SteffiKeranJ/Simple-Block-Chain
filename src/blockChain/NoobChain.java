package blockChain;

import java.util.ArrayList;
import com.google.gson.GsonBuilder;
import blockChain.Block;

public class NoobChain {
	// Add all our blocks to Array List
	public static ArrayList<Block> blockChain = new ArrayList<Block>(); 
	public static int difficulty = 0;

	public static void main(String[] args) {
		 Block genesisBlock = new Block ("Hello, I am the beginning. In other words, I am the genesis block", "0");
		 System.out.println("Hash for Block 1:" + genesisBlock.hash);
		 blockChain.add(genesisBlock);
		 
		 System.out.println("Mining the genesis block...........");
		 blockChain.get(0).miner(difficulty);
		 
		 Block secondBlock = new Block("Here comes the second block!", blockChain.get(blockChain.size()-1).hash);
		 System.out.println("Hash for the second Block: " + secondBlock.hash);
		 blockChain.add(secondBlock);
		 
		 System.out.println("Mining the second block...........");
		 blockChain.get(1).miner(difficulty);

		 Block thirdBlock = new Block("Here comes the third block!", blockChain.get(blockChain.size()-1).hash);
		 System.out.println("Hash for the third Block: " + thirdBlock.hash);
		 blockChain.add(thirdBlock);

		 System.out.println("Mining the third block...........");
		 blockChain.get(2).miner(difficulty);
		 
		 Block fourthBlock = new Block("Here comes the fourth block!", blockChain.get(blockChain.size()-1).hash);
		 System.out.println("Hash for the fourth Block: " + fourthBlock.hash);
		 blockChain.add(fourthBlock);

		 System.out.println("Mining the fourth block...........");
		 blockChain.get(3).miner(difficulty);
		 
		 System.out.println("\nBlockchain is Valid: " + isIntegrity());

		 
		 String blockChainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockChain);
		 System.out.println("\n The Block Chain is: \n");
		 System.out.println(blockChainJson);

	}
	
	// method to check the integrity of the block chain
	// the current block's previous hash has to be equal to the hash of the previous block
	public static Boolean isIntegrity() {
		Block currentBlock;
		Block previousBlock;
		String target = new String(new char[difficulty]).replace('\0', '0');

		// loop through the Block Chain to check the hashes
		for (int i = 1; i<blockChain.size(); i++) {
			currentBlock = blockChain.get(i);
			previousBlock = blockChain.get(i-1);
			
			if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
				System.out.println("Mismatch of hashes for the current Block");
				return false;
			}
			
			if (!previousBlock.hash.equals(currentBlock.previousHash)) {
				System.out.println("Previous Hash not equal");
				return false;
			}
			
			if (!currentBlock.hash.substring(0, difficulty).equals(target)) {
				System.out.println("This block hasn't been mined");
				return false;
			}
		}
		return true;
		
	}
}
