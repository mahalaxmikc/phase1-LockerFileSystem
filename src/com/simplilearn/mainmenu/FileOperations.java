package com.simplilearn.mainmenu;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.simplilearn.comparator.FilenameComparator;

public class FileOperations {

	private static String ROOT_DIRECTORY;
	//private static Integer ROOT_DIRECTORY_CREATION = 0;
	private static final String ROOT_DIRECTORY_PATH = "src/com/simplilearn/rootDirectory";
	public List<String> fileNames = new  ArrayList<String>(); //holds sorted filenames

	public  Scanner scanner  = new Scanner(System.in);

	public int flag=0; // 0 for main menu 1 for display 2 fiIO 3 add 4 delete 5 search
	public int menuOption= -1;
	public char fileIOChar;
	public boolean isRootDirectoryEmpty ;

	
	public  void showMainMenu() {
				System.out.println(" #WELCOME SCREEN##########################################################################################################");
			System.out.println("\t\t WELCOME  TO  LOCKER'S SCREEN PVT. LTD.");
			System.out.println("\t Applications Details : LockerScreen FileSytem Prototype (Standlone & UI through Command Line )");
			System.out.println("\t Developer Details : Mahalaxmi Chinchani (JAVA/JEE Developer)");
		
			System.out.println("\t User Interface for the Applications:");
			System.out.print("\t\t1.Display Root Directory Filenames in Lexicographical order \n\t\t2. File IO Operations \n\t\t3. Exit Application"
					+ "\nPlease Enter your choice:  ");
			System.out.println("\n ########################################################################################################################\n");
			
			int choice = menuOptions(); 
			menuOperations(choice);
		
	} 
	
	public void createRootDirectory() {
		File file1 = new File(ROOT_DIRECTORY_PATH);
	  ROOT_DIRECTORY = file1.getAbsolutePath();
		 
	}

	//*******************************Menu Selection & Operations **************************
	/*
	 * TODO : ENUM 
	 */
	private  int menuOptions() {

		//flag; //0 for main menu 1 for display 2 fiIO 3 add 4 delete 5 search

		int choice = scanner.nextInt(); // Reads User pick for math operation and
		// stores it into 'choice'


		switch (choice) { // Start of Switch
		case 1:

			break;
		case 2:

			break;
		case 3:

			break;

		case 10:

			break;
		case 0:

			break;

		default:
			System.out.println("Illegal Operation");

		} // end of Switch

		//	input.close();
		return choice;

	} // end of menuOptions()

	public   void menuOperations(int menuOption) { 


		if (menuOption == 1) {
			displayMenu();
		}else if (menuOption == 2) {
			fileIOOperations();
		}else if (menuOption == 3) {
			exitApplication();
		}else if (menuOption == 10) {
			showMainMenu(); //2 fiIO 3 add 4 delete 5 search
		}else if (menuOption==0 && flag == 2) {
			fileIOOperations();
		}else if (menuOption==0 && flag == 3) {
			addFile();
		}else if (menuOption==0 && flag == 4) {
			deleteFile();
		}else if (menuOption==0 && flag == 5) {
			searchFile();
		}else {
			System.out.println("Invalid option,directly back to mainmenu");
			showMainMenu();
		}
	} 

	//*******************************Menu Selection & Operations Ends **************


	//**********************Display*********************************************

	private  void displayMenu() {
		flag = 1;
		int mainMenu=0;
		


		fileNames = listFilesUsingJavaIO(ROOT_DIRECTORY);
		isRootDirectoryEmpty = fileNames.isEmpty();
		if(isRootDirectoryEmpty) {
			System.out.println("Root Directory is empty please add files to directory");
			addFile();
			isRootDirectoryEmpty=false;
		}
		
		else {
		try {
			lexicographicalSorting(fileNames);


			mainMenu= menuOptions();
			menuOperations(mainMenu);

		} catch (IOException e) {
			System.out.println("Error in sorting file");
		}
		}

	}

	private  List<String> lexicographicalSorting(List<String> fileNames) throws IOException{
		Collections.sort(fileNames, new FilenameComparator());

		if(flag==1)
			System.out.println("*********************Lexicographical Order************# 10.MainMenu #3.Exit*********");
		    System.out.println("Root Directory :"+ROOT_DIRECTORY);

		for (String fileName :fileNames) { 
			System.out.println(fileName); 
		}
		System.out.println("***************************************************************");


		return fileNames;
	}

	//list the files from root directory
	private  List<String> listFilesUsingJavaIO(String dir) {

		return Stream.of(new File(dir).listFiles())
				.filter(file -> !file.isDirectory())
				.map(File::getName)
				.collect(Collectors.toList());
	}

	//**********************End of Display**************************************************



	//**********************File IO Operation*********************************************

	public  void fileIOOperations() {



		System.out.println("*********************File IO Operation************# 10.MainMenu #3.Exit*********");
		System.out.println("\t File IO Operation for the Applications:");
		System.out.print("\t\t A.ADD  \n\t\t D.DELETE \n\t\t S.SEARCH"
				+ "\n Please Enter your choice: \n ");
		System.out.println("********************************************************************************");

		//  Operation choice storage

		String fileIOOperationChoice = selectFileIO(); 
		fileIOOperationOption(fileIOOperationChoice);

	} 

	public  String selectFileIO() {
		String choice = scanner.next();
		
	try {
		menuOperations(Integer.parseInt(choice));
		
	}catch (Exception e) {
		switch (choice) { // Start of Switch
		case "A":

			break;
		case "D":

			break;
		case "S":

			break;
		/*
		 * default: System.out.println("Illegal Operation");
		 */


		} 
	
	}

		
			
		return choice;
	} 


	public   void fileIOOperationOption(String choice) {
		if (choice.equalsIgnoreCase("A")) {
			addFile();
		}else if (choice.equalsIgnoreCase("D")) {
			deleteFile();
		}else if(choice.equalsIgnoreCase("S"))
			searchFile();
	} 

	public void addFile() {
		flag=3;
		System.out.println("*********************Add File*********************");
		System.out.println("Enter the file to add root directory");
		String filename= scanner.next();
		File file = new File(ROOT_DIRECTORY+"\\"+filename);
		try {
			boolean value = file.createNewFile();
			if (value) {
				System.out.println("The new file is created.");
				System.out.println("********************Lexicographical Order**********************0. BACK > 10. MAINMENU > 3. EXIT APPLICATION*");
				lexicographicalSorting(listFilesUsingJavaIO(ROOT_DIRECTORY));
				int choice = menuOptions(); 
				menuOperations(choice);
			}
			else {
				System.out.println("The file already exists.");
				System.out.println("Enter your choice :0. BACK > 10. MAINMENU > 3. EXIT APPLICATION");
				int choice = menuOptions(); 
				menuOperations(choice);
			}
		}
		catch(Exception e) {
			e.getStackTrace();
		}


		System.out.println("***************************************************************");
	}

	public void deleteFile() {
		flag=4;
		System.out.println("*********************Delete File*********************");
		System.out.println("Enter the file to delet from root directory");
		String filename= scanner.next();
		File file = new File(ROOT_DIRECTORY+"\\"+filename);
		try {
			boolean value = file.delete();
			if (value) {
				System.out.println("The  file is deleted.");
				System.out.println("Enter your choice :0. BACK > 10. MAINMENU > 3. EXIT APPLICATION");
				int choice = menuOptions(); 
				menuOperations(choice);
			}
			else {
				System.out.println("Failed to delete the file || File not found ");
				System.out.println("Enter your choice :0. BACK > 10. MAINMENU > 3. EXIT APPLICATION");
				int choice = menuOptions(); 
				menuOperations(choice);
			}
		}
		catch(Exception e) {
			e.getStackTrace();
		}

		System.out.println("***************************************************************");
	}
	public void searchFile() {
		flag=5;
		System.out.println("*********************Search File*********************");
		System.out.println("Enter the file to search from root directory");
		String filename= scanner.next();
		try {
			List<String> filenames= listFilesUsingJavaIO(ROOT_DIRECTORY);
			Collections.sort(filenames, new FilenameComparator());

			int index = Collections.binarySearch(filenames, filename); 
			if(index > 0) {
				filenames.get(index);
				System.out.println("File Found :"+filenames.get(index));
				System.out.println("Enter your choice :0. BACK > 10. MAINMENU > 3. EXIT APPLICATION");
				int choice = menuOptions(); 
				menuOperations(choice);
			}
			else {
				System.out.println("Unsuccessful Search || File not found ");
				System.out.println("Enter your choice :0. BACK > 10. MAINMENU > 3. EXIT APPLICATION");
				int choice = menuOptions(); 
				menuOperations(choice);
			}
		}

		catch(Exception e) {
			e.getStackTrace();
		}

		System.out.println("***************************************************************");
	}
	//**********************End of File IO Operation**************************************************


	//**********************Exit Application*********************************************
	public  void exitApplication() {
		System.out.println("Exiting the Application..."); 
		System.exit(0); 
	} 

	//**********************Exit**************************************************


}
