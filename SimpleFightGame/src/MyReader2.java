import java.io.*;

public class MyReader2 {
	
	private final String PATH = "/Users/romanweisman/Private2/JavaTest/";
	private final String FILE_NAME = "test2";
	private final char CHAR_TO_SEARCH = 'A';
		
	public MyReader2() {
		writeToFile();
		readFromFileAndGetCharCount();
	}
	
	private void writeToFile() {
		File fileDirectory = new File(PATH);
		File file = new File(PATH + FILE_NAME);
		FileWriter fileWriter = null;
		BufferedWriter writer = null;
		try {
			fileDirectory.mkdirs();
			file.createNewFile();
			fileWriter = new FileWriter(file);
			writer = new BufferedWriter(fileWriter);
			writer.write("ABC");
			writer.write("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(writer != null)
					writer.close();
				if(fileWriter != null)
					fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private int readFromFileAndGetCharCount() {
		int counter = 0;
		FileReader fileReader = null;
		BufferedReader reader = null;
		String line;
		
		try {
			fileReader = new FileReader(PATH+FILE_NAME);
			reader = new BufferedReader(fileReader);
			
			while((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(reader != null)
					reader.close();
				if(fileReader != null)
					fileReader.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return counter;
	}

}
