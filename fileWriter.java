import java.io.*;


public class fileWriter{

	public fileWriter(){
		
	}

	//fileWriter for Serialized Objects
	public static <T> void saveFile(String fileName, String path,T obj){

		try{
			FileOutputStream fileout = new FileOutputStream(path+"/"+fileName+".ser");
			ObjectOutputStream out = new ObjectOutputStream(fileout);

			out.writeObject(obj);
			out.close();
			fileout.close();
			System.out.printf("Data saved at %s\n",path+"/"+fileName+".ser");
		}catch(IOException i){
			i.printStackTrace();
		}


	}

	
}