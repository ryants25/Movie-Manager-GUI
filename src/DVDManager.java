import java.util.Scanner;

public class DVDManager {

	public static void main(String[] args) {
		
		DVDUserInterface dlInterface;
		DVDCollection dl = new DVDCollection();

		Scanner scan = new Scanner(System.in);

		System.out.println("Enter name of data file to load:");
		String filename = scan.nextLine();			
		dl.loadData(filename);
		
		scan.close();

		dlInterface = new DVDGUI(dl);
		dlInterface.processCommands();
		
	}

}
