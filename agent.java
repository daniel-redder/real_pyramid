import java.util.HashMap;
import java.io.Serializable;


public class agent implements Serializable{

private int money;
private HashMap<pyramid,player> playerBets;
private HashMap<pyramid,team> teamBets;
private HashMap<pyramid,Integer> betMap;


public agent(){
	this.money = 10;
	this.playerBets = new HashMap<>();
	this.teamBets = new HashMap<>();
	this.betMap = new HashMap<>();
}

public boolean spend(int value){
	return this.setMoney(value-this.money);
}


private boolean setMoney(int value){
	if(value<0) return false;
	else this.money = value; return true; 
}

//Only functions for up to two bets per pyramid per agent (one player, one team / they don't have to be the same.)
public void checkBet(pyramid pyra, player p){

	//If a player bet exists for this pyramid
	if(this.playerBets.containsKey(pyra)){
		
		//if the player matches the winning player
		if(this.playerBets.get(pyra).equals(p)){
			this.money += this.betMap.get(pyra)*2;
		}
	}
	//if a team bet exists for this pyramid
	if(this.playerBets.containsKey(pyra)){

		//if the team matches the team the bet was placed on award money
		if(this.playerBets.get(pyra).equals(p.getTeam())){
			this.money += this.betMap.get(pyra)*2;
		}
	}
}



//bet on a player for value money
public boolean bet(pyramid pyra, player p,int value){
	
	//doesn't have enough money to make bet
	if(!setMoney(this.money - value)) return false;

	this.playerBets.put(pyra,p);
	return true;
}

//bet on a team for money
public boolean bet(pyramid pyra, team t, int value){
	
	if(!setMoney(this.money - value)) return false;
	
	this.teamBets.put(pyra,t);
	return true;
}











	
}