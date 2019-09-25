import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MyReader{
	private final String FILE_PATH = "/Users/romanweisman/Private/JavaTest/";
	private final String FILE_NAME = "test";
	private final char RAND_CHAR = '%';
	private final String STR = "st";
	
	public void input() {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter any string: ");
		String fromUser = input.nextLine();
		System.out.println(fromUser);
		input.close();
	}
	
	public void folderFun() {
		if(createFolderandFile()) {
			writeToFileWithRandom();
			countHowManyCharsAreInTheFile();
		}
	}
	
	private boolean createFolderandFile() {
		File directory = new File(FILE_PATH);
		File file = new File(FILE_PATH + FILE_NAME);

		try {
			directory.mkdir();
			
			file.createNewFile();
			return true;
			
		} 	catch (IOException e) {
				e.printStackTrace();
		}
		return false;
	}
	
	private void writeToFileWithRandom() {
		System.out.println("Wrtiing to the file");
		File myFile = null;
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		try {
			myFile = new File(FILE_PATH+FILE_NAME);
			fileWriter = new FileWriter(myFile);
			bufferedWriter = new BufferedWriter(fileWriter);
			
			for(int i=0 ; i<1000; i++) {
				if(i%100==0) {
					bufferedWriter.write("\n");
				}
				double rand = Math.random();
				if(rand <=0.1345) {
					bufferedWriter.append(RAND_CHAR);
				} else {
					bufferedWriter.write(STR);
				}
			}
			
			
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(bufferedWriter!=null)
					bufferedWriter.close();
				if(fileWriter!=null)
					fileWriter.close();
				
				System.out.println("Closing the file");
			} catch(IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	private void countHowManyCharsAreInTheFile() {
		File myFile = null;
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		String fileLine = null;
		List<String> aList = null;
		long count = 0;
		long count2 = 0;
		long count3 = 0;
		
		try {
			myFile = new File(FILE_PATH + FILE_NAME);
			fileReader = new FileReader(myFile);
			bufferedReader = new BufferedReader(fileReader);
			
			while((fileLine = bufferedReader.readLine()) != null) {
				count += fileLine.chars().filter(ch->ch == RAND_CHAR).count();
				
				count2 += countOccurrencesOfACharInAString_Recursive(fileLine);
			}
			
			aList = Files.lines(Paths.get(FILE_PATH + FILE_NAME)).filter(line -> line.contains("%")).collect(Collectors.toList());
			
			
		} catch (IOException e) {
			
		} finally {
			try {
				bufferedReader.close();
				fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("count: " + count);
		System.out.println("count2: " + count2);
		System.out.println("count3: " + aList.size());
	}
	
	private int countOccurrencesOfACharInAString_Recursive(String s) {

		return countOccurrencesOfACharInAString_RecursiveHelper(s,0);
	}
	
	private int countOccurrencesOfACharInAString_RecursiveHelper(String s, int i) {
		int count = 0;
		if(i == s.length()) {
			return 0;
		}
		if(s.charAt(i) == RAND_CHAR) {
			count=1;
		}
		return count + countOccurrencesOfACharInAString_RecursiveHelper(s,i+1);
	}
}
