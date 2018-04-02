package bc.blockchain.server;

public class Bootstrap extends BlockChainContext{
	
	public static void main(String[] args){
		Bootstrap bs=new Bootstrap();
		bs.start();
		System.out.println("The server startup successful.");
	}
}
