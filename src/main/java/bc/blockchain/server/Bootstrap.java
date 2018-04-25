package bc.blockchain.server;

import io.netty.util.internal.StringUtil;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

import bc.blockchain.util.PcWifiMac;

public class Bootstrap extends BlockChainContext{
	
	public Bootstrap(String localMac) {
		this.setLocalMac(localMac);
	}

	public static void main(String[] args){
		
		Options options = new Options();
		Option opt = new Option("h", "help", false, "Print help");
		opt.setRequired(false);
		options.addOption(opt);

		opt = new Option("p", "printConfigItem", false, "Print all config item");
		opt.setRequired(false);
		options.addOption(opt);
		
		HelpFormatter hf = new HelpFormatter();
        hf.setWidth(110);
        CommandLine commandLine = null;
        CommandLineParser parser = new PosixParser();
        try {
            commandLine = parser.parse(options, args);
            if (commandLine.hasOption('h')) {
                // 打印使用帮助
                hf.printHelp("testApp", options, true);
            }

            // 打印opts的名称和值
            System.out.println("--------------------------------------");
            Option[] opts = commandLine.getOptions();
            if (opts != null) {
                for (Option opt1 : opts) {
                    String name = opt1.getLongOpt();
                    String value = commandLine.getOptionValue(name);
                    System.out.println(name + "=>" + value);
                }
            }
        }
        catch (ParseException e) {
            hf.printHelp("block chain server", options, true);
        }
		
        PcWifiMac mac=new PcWifiMac();
        String localmac=mac.getLocalMac();
        if(StringUtil.isNullOrEmpty(localmac)){
        	System.out.println("mac is null.");
        	return;
        }
		Bootstrap bs=new Bootstrap(localmac);
		bs.start();
		System.out.println("The server startup successful.");
	}
}
