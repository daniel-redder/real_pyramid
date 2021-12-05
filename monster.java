import java.io.Serializable;

public class monster implements Serializable{

private static String monsterTypes = "static/monstrousData/monstrousType.txt";
private static String monsterTraits = "static/monstrousData/monstrousTrait.txt";

private int[] position;
private String name;
private boolean alive = true;
private int surfaceArea;
private int rarity;
private int job;
private int health;
private int attack;
private int stamina;
private int dex;
private player killer;

//serializable generator
//Rarity: 0:Common, 1:Special, 2:Miniboss
public monster(int[] position,int rarity){
  this.rarity = rarity;
  this.position = position;
  String[] newMonster = getType(monsterTypes);
  String[] rareMonster = getType(monsterTraits);
  this.name = "";
  this.killer = null;
  int[] statLine = statParser(newMonster);
  int[] rareStatLine = statParser(rareMonster);

  if(rarity >=1){
    this.name += rareMonster[0];
    this.name += " ";
    for(int i=0; i<rareStatLine.length; i++){
      statLine[i] += rareStatLine[i];
    }}
  
    this.name += newMonster[0];
    this.name += " ";
    this.name += generator.getMonsterName();

    if(rarity == 2){
      statLine[0] *= 2;
      this.name += " " + establishRoyalty("static/monstrousData/monstrousTitle2.txt");
    }

    this.health = statLine[0];
    this.attack = statLine[1];
    this.dex = statLine[2];
    this.stamina = statLine[3];
    this.surfaceArea = statLine[4];

}

//TODO implement movement
public void move(floor level){
System.out.printf("%s monster is shmovin\n",this.toString());
  int[] dimensions = level.getDimen();
  int maxY = dimensions[1];
  int maxX = dimensions[0];
  
  if(maxY != 0){
  boolean spotCheck = true;
  while(spotCheck){
    int temp = generator.getRandomNumber(1, 4);
    if(temp == 1 && position[0]-1 >= 0){
      position[0] -= 1;
      spotCheck = false;
    }else if(temp == 2 && position[1]+1 <= maxY){
      position[1] += 1;
      spotCheck = false;
    }else if(temp == 3 && position[0]+1 <= maxX){
      position[0] += 1;
      spotCheck = false;
    }else if(temp == 4 && position[1]-1 >= 0){
      position[1] -= 1;
      spotCheck = false;
    }
    System.out.println(this.name + " is thinking...");
  }
    System.out.println(this.name + " has moved to (" + this.position[0] + ", " + this.position[1] + ")!");
    System.out.println();
  } 
}

public void setHealth(int val,player fighter){
	this.health = val;
	if(this.health<1){ 
		this.alive = false;
		this.killer = fighter;
	}
}


public player getKiller(){
	return this.killer;
}

public boolean isAlive(){
  return this.alive;
}

public int[] getPosition(){
	return this.position;
}

public String getName(){
  return this.name;
}

public int getJob(){
  return this.job;
}

public int getRarity(){
  return this.rarity;
}

public int getSurfaceArea(){
  return this.surfaceArea;
}

public int getDex(){
  return this.dex;
}

public int getStamina(){
  return this.stamina;
}

public void setStamina(int value){
	this.stamina = value;
}

public int getHealth(){
  return this.health;
}

public int getDamage(){
  return this.attack;
}

public String toString(){
  return this.name;
}

public static int[] statParser(String[] arr){

  int[] result = new int[arr.length-1];

  for(int i=1; i<arr.length-1; i++){
    result[i-1] = Integer.valueOf(arr[i]);
  }

  return result;
}

//Monster generation stuff from here on out
public static String[] getType(String path){
  String temp = fileReader.getRandomLine(path);
  String[] result = temp.split(",");
  return result;
}

public static String establishRoyalty(String path){
  String result = fileReader.getRandomLine(path);
  return result;
}

	
}