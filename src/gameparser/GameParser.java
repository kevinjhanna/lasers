package gameparser;

import java.io.*;
import java.util.Scanner;

public class GameParser {
	private File f;
	private Scanner stream;
	
	public GameParser(File f) throws IOException {
		if (f.exists() && f.isFile()){
			this.f = f;
						
		}else{
			throw new FileNotFoundException();
		}
	}
	
	public void parse() throws IOException, InvalidLoadedBoardException{
		try {
			stream = new Scanner(f);
			int width = 0;
			int height = 0;
			
			boolean gotSize = false;
			
			while (stream.hasNextLine()) {
					String aLine = stream.nextLine().replaceFirst("#.*", ""); // dejamos de lado los comentarios
					if(!gotSize) {
						gotSize = processSize(aLine);
					}else{
						processTile(aLine);
					}
			}
			
		}
		catch(Exception e){
			System.out.println(e);
		}
		finally {
			if (stream != null){
				stream.close();
			}
		}
	}
	

	private boolean processSize(String aLine) throws InvalidLoadedBoardException{
		
		if (!aLine.matches("\\s*\\d*\\s*,\\s*\\d*\\s*")){
			throw new InvalidLoadedBoardException();
		}
		
		Scanner lineScanner = new Scanner(aLine);
		lineScanner.useDelimiter(",");
		
		System.out.println(Integer.parseInt(lineScanner.next().replaceAll(" ", "")));
		
		return true;
	}
	
	private void processTile(String line){
		
	}
}
