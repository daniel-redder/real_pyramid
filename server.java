import java.io.Serializable;
import java.io.*;
import java.util.ArrayList;


public class server implements Serializable{


private ArrayList<pyramid> currentGames;
private ArrayList<pyramid> previousGames;
private ArrayList<team> teams;
private ArrayList<player> players;
private ArrayList<agent> agents;
private ArrayList<pyramid> processingGames;
private int tick;
private boolean gameMarker;

private final int teamMemberNumber = 16;
private final int concurrentGames = 1;

private static int maxPlayerid;

//private ArrayList<item> itemCatalog;
//private ArrayList<monster> predefined monsters

//serializable function
public server(ArrayList<pyramid> currentGames, ArrayList<pyramid> previousGames, ArrayList<team> teams,int maxPlayerid){
	this.currentGames=currentGames;
	this.previousGames=previousGames;
	this.teams=teams;
	//this.players=players;
	this.tick=tick;
	
	this.maxPlayerid=maxPlayerid;
}

public server(String[] teamNames,ArrayList<agent> agents){
	this.teams = new ArrayList<>();
	for(String s: teamNames) this.teams.add(new team(s,teamMemberNumber));
	this.currentGames = new ArrayList<>();
	this.previousGames = new ArrayList<>();
	this.processingGames = new ArrayList<>();
	this.maxPlayerid=0;
	this.gameMarker = true;
	this.agents = agents;

	//Temporary Fix TODO
	this.gameMaker();
	//this.players = new ArrayList<>();
	//TOOD Add changing player values (so that it updates properly)
	//for(team t: this.teams) for(player p: t.getMembers()) this.players.add(p);
}

public static int getMaxID(){
	return maxPlayerid;
}

public void operateServer(){
	
	this.gameMaker();
	for(pyramid p: currentGames) p.operatePyramid();
	this.tick++;
	
	this.save();
}


//internal use only (devServerAutomata)
private void operateServer(boolean doSave){
	this.gameMaker();
	for(pyramid p: currentGames) if(!p.isComplete()) p.operatePyramid();
	this.tick++;
}


//runs a number of concurrentGames, runs them until they are complete, and stops. 
public void devServerAutomata(){
	while(this.processingGames.size()<this.concurrentGames){
		operateServer(true);
		this.tick++;
	}
	this.save();
}




private void gameMaker(){
	if(this.currentGames.size()<this.concurrentGames){
		for(int i=0;i<this.concurrentGames-this.currentGames.size();i++){
			team[] availableTeams = this.getAvailableTeams(4);
			ArrayList<player> availablePlayers = new ArrayList<>();
		
			for(team t: availableTeams){
		
				ArrayList<player> teamMembers = t.getMembers();
				ArrayList<Integer> takenIndex = new ArrayList<>();
		
				for(int z=0;z<4;z++){
					boolean looper = true;
					while(looper){
						int testIndex =  generator.getRandomNumber(0,teamMembers.size()-1);
		
						if(!takenIndex.contains(testIndex)){ 
							availablePlayers.add(teamMembers.get(testIndex)); 
							looper=false; 
							takenIndex.add(testIndex);
						}
					}
				}
			}
			this.currentGames.add(new pyramid(availablePlayers,availableTeams,this));
		}
	}
}


//public 



public team[] getAvailableTeams(int quant){
	team[] teamList = new team[quant];
	for(int i=0;i<quant;i++){
		boolean looper = true;
		
		while(looper){
			int test = generator.getRandomNumber(0, teams.size()-1);
			if(!teams.get(test).getInGame()){
				looper = false;
				teams.get(test).setInGame(true);;
				teamList[i]= teams.get(test);
			}
		}
	
	}
	return teamList;
}


public void save(){
	utility.writeObject(this,"server");
}

//used when all players are dead or have left. 
public void gameCompleted(pyramid pyra){
	this.processingGames.add(pyra);
	//this.currentGames.remove(pyra);
	this.gameMarker = true;
}

//used when a player completes the pyramid
public void gameCompleted(pyramid pyra,player p){
	this.processingGames.add(pyra);
	//this.currentGames.remove(pyra);
	this.gameMarker = true;
	//evaluate the bets of agents
	for(agent a: agents) a.checkBet(pyra, p);
}


//io functions

public ArrayList<pyramid> getCurrentGames(){
	return this.currentGames;
}

public ArrayList<pyramid> getProcessingGames(){
	return this.processingGames;
}

public ArrayList<agent> getAgents(){
	return this.agents;
}



//Used for AI processing 
public void processingCompleted(){
	this.previousGames.add(this.processingGames.get(0));
	this.processingGames.remove(0);
}


	
}