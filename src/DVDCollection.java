import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DVDCollection {
	
	/* The current number of DVDs in the array */
	private int numdvds;
	/* The array to contain the DVDs */
	private DVD[] dvdArray;
	
	public DVDCollection() {
		numdvds = 0;
		dvdArray = new DVD[7];
	}
	
	public String toString() {
		String line = "";
		for(int i = 0; i < numdvds; i++)
		{ 
			line += dvdArray[i].toString();
		}
		return line;
	}

	public void addOrModifyDVD(String title, String rating, String runningTime) {
		boolean check = false;
		if (numdvds >= 7)
		{
			System.out.println("Collection is full!");
		}
		else
		{
			int index = 0;
			while(index < numdvds)
			{
				if(title.compareTo(dvdArray[index].getTitle()) == 0)
				{
					check = true;
					break;
				}
				else
				{
					check = false;
				}
				index++;
			}
			
			if(check) // just modify
			{
				dvdArray[index].setRating(rating);
				dvdArray[index].setRunningTime(Integer.parseInt(runningTime));
			}
			else // add
			{
				DVD add = new DVD(title,rating,Integer.parseInt(runningTime));
				dvdArray[numdvds] = add;
				numdvds++;	
			}
			
			DVD temp;
			for (int i = 0; i < (numdvds-1) ; i++)
			{
				for (int j = 0; j < (numdvds-i-1); j++)
				{
					char x = dvdArray[j].getTitle().charAt(0);
					char y = dvdArray[j+1].getTitle().charAt(0);
					int diff = x - y;
					if(diff > 0)
					{
						temp = dvdArray[j];
						dvdArray[j] = dvdArray[j+1];
						dvdArray[j+1] = temp;
					}
				}
			}
			
		}	
	}
	
	public void removeDVD(String title) { 
		boolean found = false;
		int i = 0;
		while(i < numdvds)
		{
			if(title.compareTo(dvdArray[i].getTitle()) == 0)
			{
				found = true;
				break;
			}
			else
			{
				found = false;
			}
			i++;
		}
		if(found == true)
		{
			for(int index = i; index < numdvds; index++)
			{
				dvdArray[index] = dvdArray[index+1];
			}
			numdvds--;
		}
		else
			System.out.println("DVD not in collection!");
		}
	
	public String getDVDsByRating(String rating) {
		String total = "";
		for(int i = 0; i < numdvds; i++)
		{
			if(rating.compareTo(dvdArray[i].getRating()) == 0)
			{
				total += dvdArray[i].toString();
			}
		}
		return total;
	}

	public int getTotalRunningTime() {	
		
		int total = 0;
		for(int i = 0; i < numdvds; i++)
		{
			total += dvdArray[i].getRunningTime();
		}
		return total;
	}

	
	public void loadData(String filename) { 
		
		 BufferedReader file = null;
	       try {
	           file = new BufferedReader(new FileReader(filename));
	           
	           String line;
	           while ((line = file.readLine()) != null) 
	           {
	               String[] arr = line.split(",");         
	               addOrModifyDVD(arr[0].toUpperCase(), arr[1],(arr[2]));
	           }
	       } 
	       catch (IOException e) 
	       {
	           e.printStackTrace();
	       } 
	       finally {
	    	   int i = 0;
	    	   while (i < numdvds)
	    	   {
	    		   i++;
	    	   }
	    	   try {
	               if (file != null) 
	               {
	                   file.close();
	               }
	           } 
	    	   catch (IOException ex) 
	    	   {
	               ex.printStackTrace();
	           }
	       }
	}
	
	public void save() {
		try {
			File file = new File("DVDTestFile2.txt");
			FileWriter stream = new FileWriter(file, false);
			for(int i = 0;i < numdvds;i++)
			{
				stream.write(dvdArray[i].toString());
			}
			stream.close();
		}
		catch(IOException error)
		{
			error.printStackTrace();
		}
	}
}
