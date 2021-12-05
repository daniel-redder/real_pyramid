import java.util.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
public class utility{
	


public utility(){

}

public static boolean containsGroup( int[][] grouping,int[] eleGroup){
	for(int[] group:grouping){
		if(arrayIntEquals(eleGroup, group)){
			return true;
		}
	}
	return false; 
}

public static boolean containsGrouper( int[][] grouping,int[] eleGroup){
	for(int[] group:grouping){
		if(eleGroup.equals(group)){
			return true;
		}
	}
	return false; 
}

public static boolean arrayIntEquals(int[] arr1, int[] arr2){
  boolean result = true;
  
  if(arr1.length != arr2.length) return false;
  
  for(int i=0; i<arr1.length; i++){
    if(arr1[i] != arr2[i]){
      result = false;
      break;
    }
  }
  
  return result;
}

public static void writeObject(Object serObj,String fileName) {
 
        try {
 
            FileOutputStream fileOut = new FileOutputStream("Data/"+fileName+".ser");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");
 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



  public static server ReadObjectFromFile(String filepath) {
 
        try {
 
            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
 
            server obj = (server)objectIn.readObject();
 
            System.out.println("The Object has been read from the file");
            objectIn.close();
            return obj;
 
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
  }

}