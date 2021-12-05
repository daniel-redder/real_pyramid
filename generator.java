import java.util.*;
import java.util.stream.*;

public class generator{

	public  generator(){
		
  }

	public static int getRandomNumber(int min, int max) {
    	return (int) ((Math.random() * (max - min + 1)) + min);
	}

  //Takes a name from the fNameList file, and one from the lNameList, and concatenates a full first and last name into a String.
	public static String getName(){
    
    String result = "";

    String firstName = fileReader.getRandomLine("static/aesthetic/fNameList.txt");
    String lastName = fileReader.getRandomLine("static/aesthetic/lNameList.txt");

    firstName = firstName.toLowerCase();
    lastName = lastName.toLowerCase();

    firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
    lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);

    result = firstName + " " + lastName;

    return result;
	}


//TODO: Remember, 0:Head, 1:Torso, 2:Legs, 3:Ring, 4:Weapon
	public static String getItemName(int rarity,int type){

    String selection;
    String prefix = fileReader.getRandomLine("static/itemInfo/adjectivium.txt");
    String suffix1 = fileReader.getRandomLine("static/itemInfo/suffix.txt");
    String suffix2 = fileReader.getRandomLine("static/itemInfo/adjectivium.txt");
    String result;

    switch(type){
    case 0: selection = "static/itemInfo/head.txt";
            break;
    case 1: selection = "static/itemInfo/torso.txt";
            break;
    case 2: selection = "static/itemInfo/legs.txt";
            break;
    case 3: selection = "static/itemInfo/ring.txt";
            break;
    case 4: selection = "static/itemInfo/weapon.txt";
            break;
    default: selection = "Invalid Item Type, please check for bugs.";
    }

    String itemName = fileReader.getRandomLine(selection);

    switch(rarity){
    case 0: result = itemName;
            break;
    case 1: result = prefix + " " + itemName;
            break;
    case 2: result = itemName + " of " + suffix1;
            break;
    case 3: result = itemName + " of " + suffix2 + " " + suffix1;
            break;
    case 4: result = prefix + " " + itemName + " of " + suffix2 + " " + suffix1;
            break;
    default: result = "Broken Beetle of Bug Betrayal";
    }

		return result;
	}

//TODO
//Items be like 0:Head, 1:Torso, 2:Legs, 3:Ring, 4:Weapon
//Rarity be like 0:common, 1:uncommon, 2:rare, 3:unique, 4:legendary
/*stats are in this order
	0:swolitude, 1:humongousness, 2:lightning affinity, 3:street smarts, 4:book smarts, 5:ocularity, 6:staminism, 7:chi energy, 8:failitude, 9:unfailitude
	
	statType is a integer corresponding to the index of a array of these stats
	*/

	public static int getItemStatType(){
    int result = 0;
    result = getRandomNumber(0, 9);
		return result;
	}


//TODO
	public static int getItemStatValue(int rarity){
    int result = getRandomNumber(1, 3) + (rarity * 2);
		return result;
	}

//TODO
	public static item getStarterWeapon(int[] stats){
		return new item(stats);
	}



	public static String getTrapName(){
		return "penus annihilator";
	}


  public static int statGenerator(){
    	int min = 1;
    	int max = 6;
		int val;
		int[] vals = new int[4];

		for(int i=0; i<4; i++){
			vals[i]= getRandomNumber(min,max);
		}
		val = getArraySum(vals)-getArrayMin(vals);

    return val;
	}


	public static int[] statListGeni(){
		/*stats are in this order
	swolitude, humongousness, lightning affinity, street smarts, book smarts, ocularity, staminism, chi energy, failitude, unfailitude
	*/
	int[] values = new int[10];

	for(int i=0; i<10;i++){
		values[i]=statGenerator();
	}
	return values;
	}

  public static int getArrayMin(int[] a){
		int value= Integer.MIN_VALUE;
		for(int i = 0; i<a.length; i++){
			if(a[i] > value){
        value = a[i];
      }
		}
		return value;
	}

  public static int getArrayMax(int[] a){
    int value = Integer.MAX_VALUE;
    for(int i = 0; i<a.length; i++){
      if(a[i] < value){
        value = a[i];
      }
	}
    return value;
    
  }

  public static int getArraySum(int[] a){
    int value = 0;
    for(int i = 0; i<a.length; i++){
      value += a[i];
    }
    return value;
  }

  public static String getMonsterName(){
    int nameLength = getRandomNumber(0, 5);
    String result = "";
    String temp = "";
    String temp2 = "";
	  for(int i=0; i<=nameLength;i++){
      temp += fileReader.getRandomLine("static/monstrousData/monstrousBase1.txt");
    }
    temp = temp.toLowerCase();
    result = fileReader.getRandomLine("static/monstrousData/monstrousBase1.txt");
    result += temp;
    temp2 = fileReader.getRandomLine("static/monstrousData/monstrousTitle1.txt");
    result += " the " + temp2;
    
    return result;
  }

  public static int dieRoll(){

  int result;

  int temp1 = generator.getRandomNumber(1, 20);
  int temp2 = generator.getRandomNumber(1, 20);
  int temp3 = generator.getRandomNumber(1, 20);
  int temp4 = generator.getRandomNumber(1, 3);

  int[] select = new int[]{temp1,temp2,temp3};
  result = select[temp4-1];
  
  return result;
}

}