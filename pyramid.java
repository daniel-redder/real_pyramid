import java.io.Serializable;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;


public class pyramid implements Serializable{

private floor[] floorList;
private ArrayList<player> players;
private HashMap<team,int[][]> teamMap;
private ArrayList<player> deadPlayers;
private String name;
private team[] teams;
private boolean complete;
private player winner;
private server host;
private int[][] averageTeamStats;

//TODO new Pyramid creation
public pyramid(ArrayList<player> players,team[] teams,server host){
	this.players = players;
  System.out.println("Players selected.");
	this.teamMap = new HashMap<team,int[][]>();
	System.out.println("Creating floor...");
	this.floorList = createFloors();
	System.out.println("Floor created!");
	this.deadPlayers = new ArrayList<>(); 
	this.host = host;
	this.teams = teams;
	int[][] startingPositions = new int[floorList.length][];
	this.winner = null;
	this.complete = false;
//generates starting positions
	ArrayList<int[][]> holder = new ArrayList<>();
	for(floor f: this.floorList) holder.add(f.getStarts());

	for(int q=0; q<teams.length;q++){
		int[][] arr = new int[floorList.length][2];
		for(int z=0;z<floorList.length;z++){
			arr[z]=holder.get(z)[q];
		}
		teamMap.put(teams[q],arr);
	}
	

//	for(int q=0; q<teams.length;q++) this.teamMap.put(teams[q],startingPositions[q]);
	
	
	for(player p: players){ 
		int[] position_hold = p.getPosition();
		this.completeFloor(p);
	   System.out.println("Start :  "+position_hold[0]+" "+position_hold[1]+" end: "+p.getPosition()[0]+" "+p.getPosition()[1]+"  Team: "+p.getTeamName());
	}

	//calculating average team stats
	this.averageTeamStats = new int[4][10];
	for(int stat=0;stat<10;stat++){
		
		for(int i=0; i<this.teams.length;i++){
			int avgValue=0;
			for(int x=0;x<this.teams[i].getMembers().size();x++){
				avgValue+=this.teams[i].getMembers().get(x).getStat(stat);
			}
			this.averageTeamStats[i][stat]=Math.round(avgValue/4);
		}
		
	}
}
	

public pyramid(floor[] floorList, ArrayList<player> players,HashMap<team,int[][]> teamMap){

	this.floorList = floorList;
	this.players = players;
	this.teamMap = teamMap;

}



//TODO (after operateFloor is implemented)
//execution function that will trigger all floors in pyramid when time ticks
public void operatePyramid(){
	for(team t: this.teams) System.out.printf("\n Team: %s \n",t.getName());
	//all players are dead/left the pyramid check
	if(this.players.size() <1) pyramidComplete();
	for(player p: this.players) System.out.printf("\n%s: %s\n",p.getName(),p.getFloor());
	//Calls each floor to operate given a list of players
	for(floor x: this.floorList){ 
		System.out.printf("Now operating pyramid floor %s",x.toString());
		x.operateFloor(this.players,this);
	}
	//Updates the "alive" status of all players in the pyramid
	ArrayList<player> holder = new ArrayList<>();
	holder.addAll(this.players);
	for(player p: holder) if(!p.isAlive()) killPlayer(p);
}


public void killPlayer(player p){
	this.deadPlayers.add(p);
	this.players.remove(p);
	
	System.out.printf("%s, has died.",p.toString());
}


//TODO implement player leaving "courage problems"
public void playerLeft(){
	
}


//increment the floor of the player
// Triggers completion of pyramid (may need to be edited later but for now complete.)
public void completeFloor(player player){
	if(player.getFloor() <= this.floorList.length-1){

    player.incrementFloor(this.teamMap.get(player.getTeam())[player.getFloor()].clone());
  	}
}

public floor[] createFloors(){
	
	return new floor[]{new floor(new int[] {6,6},false,1),new floor(new int[] {4,4},false,2),new floor(new int[] {2,2},false,3),new floor(new int[] {0,0},true,4)};
}



public String winCert(team team){
	Calendar cal = Calendar.getInstance();
	cal.getTime();
	cal.add(Calendar.YEAR, -31);
	return "Management Hereby Certifies that on %s, team %s has completed the %s pyramid".format(cal.toString(),team,this.name);
}


//trigger pyramid's completion and archiving
public void pyramidComplete(player player){
	System.out.printf("%s has completed the pyramid",player.toString());
	player.getTeam().win(winCert(player.getTeam()),this);
	this.winner = player;
	this.complete = true;
	this.host.gameCompleted(this,player);
}

public void pyramidComplete(){
	assert this.players.size()==0: "Error: pyramidComplete() called but living players still in the pyramid!";
	this.complete = true;
	host.gameCompleted(this);
}

public team[] getTeams(){
	return this.teams;
}

public ArrayList<player> getPlayers(){
	return this.players;
}



public boolean isComplete(){
	return this.complete;
}

public player getWinner(){
	return this.winner;
}

}