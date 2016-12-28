package tealist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Handles parsing of options and conversion between different files 
 * containing information about tea.
 * 
 * @author Thomas Ejnefjäll
 */
public class TeaList 
{	
	/**
	 * Constructs a TeaList
	 */
	public TeaList() { }
	/**
	 * Process a request containing one or more options. For information
	 * on valid options see the readme file.
	 * 
	 * @param args Options
	 */
	public void processRequest(String[] args) 
	{
		Map<Options, String> request = this.parseRequest(args);
		request = this.validateRequest(request);
		
		if(request.containsKey(Options.ERROR))
		{
			this.showError(request.get(Options.ERROR));			
		}
		else
		{
			if(request.containsKey(Options.LIST_FILE_FORMATS))
			{
				this.showFileFormats();
			}
			else if(request.containsKey(Options.HELP))
			{
				this.showHelp();
			}
			else if(request.containsKey(Options.FROM_FILE_FORMAT))
			{
				try {
					this.convertFile(request);
				} catch (Exception e) {
					this.showError(e.getMessage());					
				}
			}
		}
	}
	/**
	 * Parse the request. 
	 * 
	 * @param args The options given to the program
	 * @return a map with the options and there values when needed
	 */
	private Map<Options, String> parseRequest(String[] args) 
	{
		Map<Options, String> request = new HashMap<Options, String>();
		
		for(int i = 0; i < args.length; i ++) 
		{
			if(Options.HELP.equals(args[i])) 
			{
				request.put(Options.HELP, null);
			}
			else if(Options.LIST_FILE_FORMATS.equals(args[i])) 
			{
				request.put(Options.LIST_FILE_FORMATS, null);
			}
			else if(Options.FROM_FILE_FORMAT.equals(args[i])) 
			{
				if(request.containsKey(Options.FROM_FILE_FORMAT))
				{
					request.put(Options.ERROR, "-f can only be used once");
				}
				else if(args.length > i + 1)
				{
					request.put(Options.FROM_FILE_FORMAT, args[++i]);
				}
				else
				{
					request.put(Options.ERROR, "-f must be followed by the file format");
				}
			}
			else if(Options.TO_FILE_FORMAT.equals(args[i])) 
			{
				if(request.containsKey(Options.TO_FILE_FORMAT))
				{
					request.put(Options.ERROR, "-t can only be used once");
				}
				else if(args.length > i + 1)
				{
					request.put(Options.TO_FILE_FORMAT, args[++i]);
				}
				else
				{
					request.put(Options.ERROR, "-t must be followed by the file format");
				}
			}
			else if(Options.INPUT_FILE.equals(args[i])) 
			{
				if(request.containsKey(Options.INPUT_FILE))
				{
					request.put(Options.ERROR, "-i can only be used once");
				}
				else if(args.length > i + 1)
				{
					request.put(Options.INPUT_FILE, args[++i]);
				}
				else
				{
					request.put(Options.ERROR, "-i must be followed by the name of the input file");
				}
			}
			else if(Options.OUTPUT_FILE.equals(args[i])) 
			{
				if(request.containsKey(Options.OUTPUT_FILE))
				{
					request.put(Options.ERROR, "-o can only be used once");
				}
				else if(args.length > i + 1 && !args[i + 1].startsWith("-"))
				{
					request.put(Options.OUTPUT_FILE, args[++i]);
				}
				else
				{
					request.put(Options.OUTPUT_FILE, null);
				}
			}
			else
			{
				request.put(Options.ERROR, "Invalid option: " + args[i]);
			}
		}
		return request;
	}
	/**
	 * Checks if a request is valid. If there is an error an error value is 
	 * added to the map with information about the error.
	 * 
	 * @param request The parsed options given to the program
	 * @return a map of the options
	 */
	private Map<Options, String> validateRequest(Map<Options, String> request) 
	{
		if(!request.containsKey(Options.ERROR))
		{
			Set<Options> params = request.keySet();
			
			boolean fileConversionExists = false;
			Options[] fileConversion = {Options.FROM_FILE_FORMAT, Options.TO_FILE_FORMAT, Options.INPUT_FILE};
			
			for(Options param : params)
			{
				for(Options fileParam : fileConversion) 
				{
					if(param.equals(fileParam))
					{
						fileConversionExists = true;
					}
				}
			}
			if(fileConversionExists) 
			{
				if(!params.containsAll(Arrays.asList(fileConversion)))
				{
					request.put(Options.ERROR, "-f -t and -i must all be used when converting files");					
				}
			}
		}		
		return request;		
	}
	/**
	 * Prints the help to standard out.
	 */
	private void showHelp()
	{
		System.out.println("Usage: java -jar TeaList.jar [OPTION][VALUE]");
		System.out.println("Options can be given in any order");
		System.out.println("");
		System.out.println("Input/output format");
		System.out.println("-f from file format (requires value after)");
		System.out.println("-t to file format (require value after)");
		System.out.println("");
		System.out.println("Input/output file");
		System.out.println("-i input file (requires value after)");
		System.out.println("-o output file (if no value is given standard output will be used)");
		System.out.println("");
		System.out.println("Information");
		System.out.println("-l list avaliable file formats");
		System.out.println("-h print help");
		System.out.println("");
		System.out.println("Example of valid options and values");
		System.out.println("java -jar TeaList.jar -h");
		System.out.println("(prints help)");
		System.out.println("java -jar TeaList.jar -f text -t xml -i tea.txt");
		System.out.println("(reads a tealist in text format in tea.txt and writes it as xml to standard output)");
		System.out.println("java -jar TeaList.jar -o tea.txt -i tea.xml -t txt -f xml");
		System.out.println("(reads a tealist in xml format in tea.xml and writes it as text to tea.txt)");		
	}
	/**
	 * Prints valid file formats to standard out.
	 */
	private void showFileFormats()
	{
		System.out.println("TeaList 0.1 supports the following file formats");
		System.out.println("text - Text (txt) file where fields are separated with ;");
		System.out.println("xml - Xml (xml) file");
	}
	/**
	 * Prints any errors to standard out.
	 * 
	 * @param error the error that happened
	 */
	private void showError(String error)
	{
		System.out.println("Error: " + error);
		this.showHelp();		
	}
	/**
	 * Converts tea data from one file to an other. 
	 * 
	 * @param options any options given for the conversion
	 * @throws Exception if there was an error while converting
	 */
	private void convertFile(Map<Options, String> options) throws Exception 
	{
		FileIO fileIO = new FileIO();
		List<Tea> teaList = new ArrayList<Tea>();		
		
		String inFileName = options.get(Options.INPUT_FILE);
		String inFileFormat = options.get(Options.FROM_FILE_FORMAT);
		String outFileFormat = options.get(Options.TO_FILE_FORMAT);
		String outFileName = options.get(Options.OUTPUT_FILE);
		
		if(FileFormats.TEXT.equals(inFileFormat))
		{
			teaList = fileIO.readTextFile(inFileName);
		}
		else if(FileFormats.XML.equals(inFileFormat))
		{
			teaList = fileIO.readXmlFile(inFileName);
		}
		else
		{
			throw new Exception("Unknown input file format: " + inFileFormat);
		}
		
		if(FileFormats.TEXT.equals(outFileFormat))
		{
			fileIO.writeTextFile(teaList, outFileName);
		}
		else if(FileFormats.XML.equals(outFileFormat))
		{
			fileIO.writeXmlFile(teaList, outFileName);
		}
		else
		{
			throw new Exception("Unknown output file format: " + outFileFormat);
		}
	}	
}