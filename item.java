import java.io.Serializable;

public class item implements Serializable{
	
	private String name;
  private int type; // 0:Head, 1:Torso, 2:Legs, 3:Ring, 4:Weapon
	private int statMod; //NEVER: 0:swolitude, 1:humongousness, 2:lightning affinity, 3:street smarts, 4:book smarts, 5:ocularity, 6:staminism, 7:chi energy, 8:failitude, 9:unfailitude
	private int modValue; //1d4 + Item rarity *2 or something
	private int rarity; //range 0-4  TOOD change later 


//de serializing function
public item(int statMod, int modValue, String name, int type){

	this.statMod=statMod;
	this.modValue=modValue;
	this.name=name;
	this.rarity=rarity;
  this.type=type;
}


//new item creator
public item(int rarity){
	this.rarity = rarity;
	this.name = generator.getItemName(this.rarity, this.type);
	this.statMod = generator.getItemStatType();
	this.modValue = generator.getItemStatValue(this.rarity);

}

//starter weapon
public item(int[] stats){
	this.rarity = 0;
	this.name="rusty weapon";
	this.type = 4;
	
}


public int getDamage(int[] stats){
	return 5;
}

//Returns mod type as string
public String modToString(){
  String statModToString;
  int temp = this.statMod;

  switch(temp){
    case 0: statModToString = "Swolitude";
      break;
    case 1: statModToString = "Humongousness";
      break;
    case 2: statModToString = "Lightning Affinity";
      break;
    case 3: statModToString = "Street Smarts";
      break;
    case 4: statModToString = "Book Smarts";
      break;
    case 5: statModToString = "Ocularity";
      break;
    case 6: statModToString = "Stamanism";
      break;
    case 7: statModToString = "Chi Energy";
      break;
    case 8: statModToString = "Failitude";
      break;
    case 9: statModToString = "unfailitude";
      break;
    default: statModToString = "Why is there no stat? Fix! NOW!";
  }
  return statModToString;
}

//Returns type of item as string
public String typeToString(){

    int temp = this.type;
    String itemTypeToString;
    switch(temp){
      case 0: itemTypeToString = "Headwear";
        break;
      case 1: itemTypeToString = "Torso Piece";
        break;
      case 2: itemTypeToString = "Leg Coverings";
        break;
      case 3: itemTypeToString = "Ring";
        break;
      case 4: itemTypeToString = "Weapon";
        break;
      default: itemTypeToString = "There is no type! Fix this! NOW!";
    }

    return itemTypeToString;
}

//returns rarity of the item as a string
  public String itemRarityToString(){
    int temp = this.rarity;
    String rarityToString;

    switch(temp){
      case 0: rarityToString = "Common";
        break;
      case 1: rarityToString = "Uncommon";
        break;
      case 2: rarityToString = "Rare";
        break;
      case 3: rarityToString = "Unique";
        break;
      case 4: rarityToString = "Legendary";
        break;
      default: rarityToString = "There is no rarity! It's all messed up! FIXX!";
    }
    return rarityToString;
  }

//returns entire description of item as string
public String itemToString(){
  String result;

    result = "The " + this.name + " is a " + itemRarityToString() + " " + typeToString() + " that provides " + modToString() + ": " + modValue; 

   return result;
}

public String toString(){
String result;

    result = "The " + this.name + " is a " + itemRarityToString() + " " + typeToString() + " that provides " + modToString() + ": " + modValue; 

   return result;
}

public int getStatMod(){
  return this.statMod;
}

public int getModValue(){
  return this.modValue;
}

}