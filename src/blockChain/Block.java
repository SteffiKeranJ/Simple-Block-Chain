package blockChain;

import java.util.Date;
import blockChain.StringUtil;

public class Block {

	public String hash; // our digital signature
	public String previousHash; // previous block's digital signature
	private String data; // our data
	private long timeStamp;
	private int nonce;
	
	//Block Constructor
	public Block (String data, String previousHash) {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash();
	}
	
	// method to calculate hash
	// Call the stringUtil class for applying sha-256
	public String calculateHash() {
		String calculatedHash = StringUtil.applySha256(
				previousHash + 
				Long.toString(timeStamp) + 
				data);
		return calculatedHash;
	}
	
	// Method for mining the block
	public void miner(int difficulty) {
		String target = new String(new char[difficulty]).replace('\0', '0');
		while(!hash.substring( 0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
		System.out.println("Block Mined!!! : " + hash);
	}
}
