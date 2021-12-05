import java.io.Serializable;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;


public class floor implements Serializable{

private int[] dimensions;
private monster[] monsters;
private trap[] traps;
private boolean isBoss;
private int cellCount;
private int[][] entrancePosits;
private int[] stairPosit;
private int floorNumber;

//serializing creation function
public floor(int[] dimensions,monster[] monsters, trap[] traps, boolean isBoss,int[][] entrancePosits, 
	int[] stairPosit,int cellCount,int floroNumber){

	this.floorNumber=floorNumber;
	this.dimensions = dimensions;
	this.monsters = monsters;
	this.traps = traps;
	this.isBoss = isBoss;
	this.stairPosit = stairPosit;
	this.entrancePosits = entrancePosits;
	this.cellCount = cellCount;
}


//create new floor
public floor(int[] dimensions,boolean isBoss,int floorNumber){
	this.dimensions = dimensions;
	this.floorNumber=floorNumber;
	this.isBoss = isBoss;
    this.entrancePosits = new int[][] {{0,0},{this.dimensions[0],0},{this.dimensions[0],this.dimensions[1]},{0,this.dimensions[1]}};
	this.cellCount = dimensions[0]*dimensions[1];
	
	this.stairPosit = new int[] {(int)Math.ceil(this.dimensions[0]/2),(int)Math.ceil(this.dimensions[1]/2)};
	System.out.println("Dimension Calculations complete");
	//if not boss level code
	if(!isBoss){
		
		buildTraps();
		System.out.println("Build Traps Complete");
		buildMonsters();
		System.out.println("Builld Monsters Complete.");
	}
	//if boss level code
	else{
		this.entrancePosits = new int[][] {{0,0},{0,0},{0,0},{0,0}};
		this.monsters= new monster[]{new monster(new int[]{0,0}, 2)};
		this.traps = new trap[]{};
	}
}
//returns the center of the pyramid
public int[] getCenter(){
	return this.stairPosit;
}

public int[] getDimen(){
	return this.dimensions;
}


public void operateBossFloor(ArrayList<player> floorPlayers,pyramid pyra){

	

	for(monster x: this.monsters){ 
		//x.move(this);
		
		//inverse of checkFight function
		for(player y: floorPlayers){
			fight.MonsterFight(x,y);
		} 
	}

	//kill all dead players
	for(player p: floorPlayers) if(!p.isAlive()) pyra.killPlayer(p);
	
	//check if the pyramid has been completed
	boolean isDone = true;
	for(monster m: this.monsters){ 
		System.out.printf("\nMonster posit:  %s,%s \n",m.getPosition()[0],m.getPosition()[1]);
		if(m.isAlive()) isDone = false;
	//isDone = false;
	}
	//if the pyramid has been completed pass the winner to the pyramid
	
	if(isDone){ 
		System.out.printf("\n |||==||\n %s \n ||==|| \n",isDone);
		pyra.pyramidComplete(this.monsters[0].getKiller());
	}
	
}




//In Prog
public void operateFloor(ArrayList<player> players, pyramid pyra){
	
	//generating list of players on floor
	ArrayList<player> floorPlayers = new ArrayList<>();
	for(player x: players) if(x.getFloor()==this.floorNumber && x.isAlive()) floorPlayers.add(x); 
	for(player x: floorPlayers) System.out.printf("Floor number:  %s,  player %s, %s,%s \n",x.getFloor(),x.getName(),x.getPosition()[0],x.getPosition()[1]);
	System.out.printf("\nThis is floor: %s, the center is: %s,%s\n",this.floorNumber,this.stairPosit[0],this.stairPosit[1]);

	for(player x: floorPlayers) x.setStamina(x.getStamina()+5);
	for(monster m: this.monsters) m.setStamina(m.getStamina()+5);
	//TODO
	//potential problem by sorting them like this it means higher initiative players will be hit by other player first in a situation where there
	// are more then 2 players in a room
	//sort players by initiative (here)
  Collections.sort(floorPlayers);

	//
	System.out.printf("\nBoss Detected: %s\n",this.isBoss);
	if(this.isBoss){ this.operateBossFloor(floorPlayers,pyra); return;}

	System.out.println("\n ||||| BEGIN NEW FLOOR PHASE !!! ||||||||||| \n \n \n \n ===================== Begin Player Phase =============== \n \n");
		//player movement 

		//String holder = "";
	//for(player p: floorPlayers) holder = holder + " "+p.toString();
	//System.out.printf("%s list of players on floor. ",holder);
	

	for(player x: floorPlayers){ 
		System.out.println(x.getPosition()[0]+" "+x.getPosition()[1]+"  Team: "+x.getTeamName());
		x.move(this);

		//TODO return values are irrelevent
		 checkFight(x, true, floorPlayers);
		 checkFight(x, false, floorPlayers);
	
	}

	//Traps Trigger

	System.out.println("\n================= Begin Trap Phase ========================\n");

	for(trap t: this.traps){
		for(player y: floorPlayers) if(utility.arrayIntEquals(y.getPosition(), t.getPosition())){
			t.trigger(y);
		}
	}


	System.out.println("\n================================== Begin Monster Phase ================================ \n");

	//monsters move
	for(monster x: this.monsters){ 
		x.move(this);
		
		//inverse of checkFight function
		for(player y: floorPlayers) if(utility.arrayIntEquals(y.getPosition(), x.getPosition())){
			fight.MonsterFight(x,y);
		} 


	}

	for(player p: floorPlayers) if(!p.isAlive()) pyra.killPlayer(p);
	for(player p: floorPlayers) if(utility.arrayIntEquals(p.getPosition(), this.stairPosit)) pyra.completeFloor(p);

}

private void checkFight(player p,boolean isPlayerFight,ArrayList<player> floorPlayers){
	if(isPlayerFight){
		
		for(player y: floorPlayers){ 
		//	System.out.printf("p:  %s    y: %s\n",p.toString(),y.toString());
			//System.out.printf("p posit %s    y posit %s\n",Arrays.toString(p.getPosition()),Arrays.toString(y.getPosition()));
			//System.out.print(p.getPosition().equals(y.getPosition())+"\n");
			if(utility.arrayIntEquals(p.getPosition(),y.getPosition()) && p.isAlive()){
				//System.out.printf("p:  %s    y: %s\n",p.toString(),y.toString());
				if(p.getTeam().equals(y.getTeam())){
					//System.out.printf("%s vs %s\n",p.getTeam().toString(),y.getTeam().toString());		 
				}
				else{ if(y.isAlive())  fight.playerFight(p,y);
				else System.out.printf("%s can see the Player %s dead on the floor.\n",p.toString(),y.toString());
				}
			}
		}
	}
  
	int val = -1;
	for(monster x: this.monsters){
		if(utility.arrayIntEquals(p.getPosition(),x.getPosition())){
			if(x.isAlive())	val = fight.MonsterFight(x, p);
			else System.out.printf("%s can see the Monster %s dead on the floor.\n",p.toString(),x.toString());
		}
	}
	
}



//generates traps given positions Finished
//may need modification once traps is finished
private void buildTraps(){
	int trapCount = (int)Math.ceil(this.cellCount*.8);
	//int trapCount = 2;
	this.traps = new trap[trapCount];
	int[][] takenPositions = new int[trapCount+5][2];
	//Adding some default no go positions for traps and monsters
	//TODO simplify this to a loop or something else
	takenPositions[0]=this.stairPosit;
	takenPositions[1]=this.entrancePosits[0];
	takenPositions[2]=this.entrancePosits[1];
	takenPositions[3]=this.entrancePosits[2];
	takenPositions[4]=this.entrancePosits[3];

	boolean badLoop = true;
	int[] testAr = new int[2];
	int loopcount;
	for(int i=0;i<trapCount;i++){
		
		loopcount = 0;
		//System.out.println("What the for loop doin?");
		badLoop = true;
		//why is the loop bad
		//look it has a infinite while eeeew
		while(badLoop){
			//System.out.printf("%d\n",loopcount);
			//ensures no infinite loops occur here
			assert loopcount<cellCount*3:"cannot find open spot for trap";
			
			//generates test case
			testAr[0]=generator.getRandomNumber(0,this.dimensions[0]);
			testAr[1]=generator.getRandomNumber(0,this.dimensions[1]);
			//System.out.printf("Test Position %s, Taken %s\n",Arrays.toString(testAr),Arrays.deepToString(takenPositions));
			//tests 
      loopcount+=1;
			if(!utility.containsGrouper(takenPositions, testAr)){
				badLoop=false;
				//System.out.printf("%s loop value\n",String.valueOf(i));
				takenPositions[i+5]=testAr.clone();
				this.traps[i]= new trap(testAr);
       }
    }

		}
	}



private void buildMonsters(){
	System.out.println("buildMonster");
	int monsterCount = (int)Math.ceil(this.cellCount*.2);
	this.monsters = new monster[monsterCount];
	int[][] takenPositions = new int[monsterCount+5][2];
	//Adding some default no go positions for traps and monsters
	//TODO simplify this to a loop or something else
	takenPositions[0]=this.stairPosit;
	takenPositions[1]=this.entrancePosits[0];
	takenPositions[2]=this.entrancePosits[1];
	takenPositions[3]=this.entrancePosits[2];
	takenPositions[4]=this.entrancePosits[3];

	boolean badLoop = true;
	int[] testAr = new int[2];
	int loopcount;
	for(int i=0;i<monsterCount;i++){
		
		loopcount = 0;

		badLoop = true;
		//why is the loop bad
		//look it has a infinite while eeeew
		while(badLoop){
			
			//ensures no infinite loops occur here
			assert loopcount<cellCount*3:"cannot find open spot for monster";
			
			//generates test case
			testAr[0]=generator.getRandomNumber(0,this.dimensions[0]);
			testAr[1]=generator.getRandomNumber(0,this.dimensions[1]);
			
			//tests 
			if(!utility.containsGrouper(takenPositions, testAr)){
				badLoop=false;
				takenPositions[i+5]=testAr.clone();
				this.monsters[i]= new monster(testAr,1);
			}
			loopcount+=1;
		
		}}
}


//returns the initial positions of teams to pyramid.java to pass to players
public int[][] getStarts(){
	return this.entrancePosits;
}

public String toString(){
	return String.valueOf(this.floorNumber);
}


}
