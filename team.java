import java.util.ArrayList;
import java.io.Serializable;
import java.util.HashMap;

public class team implements Serializable{

private ArrayList<player> members;
private String name;
private int wins;
private int losses;
private ArrayList<item> inventory;
private HashMap<String,pyramid> certificates;
private boolean inGame;



//serializing recreation
public team(ArrayList<player> members, String name, int wins, int losses, ArrayList<item> inventory,HashMap<String,pyramid> certificates,boolean inGame){
	this.members=members;
	this.name = name;
	this.wins = wins;
	this.losses = losses;
	this.inventory = inventory;
	this.certificates=certificates;
	this.inGame = inGame;
}

//new team creation
public team(String name,int playerCount){
	this.name = name;
	this.wins = 0;
	this.losses = 0;
	this.members = new ArrayList<player>();
	this.inventory = new ArrayList<item>();
	this.certificates = new HashMap<>();
	this.inGame = false;
	populateTeam(playerCount);
	
}

//Completed
private void populateTeam(int playerCount){
	for(int i=0;i<playerCount;i++){
		createPlayer();
	}
}

//individual player creation
private void createPlayer(){
	this.members.add(new player(this));
}

public void win(String cert,pyramid p){
	this.certificates.put(cert,p);
	this.wins++;
}

public ArrayList<player> getMembers(){
	return this.members;
}

public void lose(){
	
}

//return team name
public String getName(){
	return this.name;
}

public String toString(){
  return this.name;
}

public player[] getPartPlayers(){
  ArrayList<Integer> taken = new ArrayList<>();
  player[] returnList = new player[4];
  for(int i=0;i<4;i++){
	  boolean loop = true;
	  int caser = 0;
	  while(loop){
		  int testValue = generator.getRandomNumber(0, members.size()-1);
		  if(!taken.contains(testValue)){
			  taken.add(testValue);
			  returnList[i]=members.get(testValue);
			  loop = false;
		  }
		  caser++;
		  assert caser<1000:"Cannot find enough players for team";
	  }
  }
  return returnList;
}

  public void setInGame(boolean setter){
    this.inGame = setter;
  }

  public boolean getInGame(){
    return this.inGame;
  }


}