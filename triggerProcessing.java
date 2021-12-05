import java.io.*;
import java.util.*;

// Triggers data moving  
class triggerProcessing{
  public static void main(String[] args) {

	


	
		 
		  server mainRun = utility.ReadObjectFromFile("Data/server.ser");
	      mainRun.processingCompleted();
		  utility.writeObject(mainRun,"Data/server.ser");

		  //mainRun.devServerAutomata();

		
	


//Old direct pyramid test case
/*
	  team red = new team("red",10);
	  team blue = new team("blue",10);
	  team green = new team("green",10);
	  team yellow = new team("yellow",10);
*/





/*
	  //Build team Array
	  team[] teams = new team[]{red,blue,green,yellow};
	  
	  //Builds Player ArrayList
	  ArrayList<player> players = new ArrayList<>();
	
	  for(team t: teams) for(player p: t.getPartPlayers()) players.add(p);

	 System.out.println("Making a Pyramid.");
	  pyramid test = new pyramid(players,teams);
	  System.out.println("Pyramid complete!");
    System.out.println();
	  test.operatePyramid();
*/

/* MonsterFight Test
    team guy = new team("peanut penis",1);
    int[] gig = new int[2];
      
  player man = guy.getMembers().get(0);
  monster billy = new monster(gig,0);
	
	System.out.println(fight.MonsterFight(billy, man));
*/

/*
//for(int i=0;i<100;i++) System.out.printf("Monster %s \n",new monster(new int[]{1,2},3).toString());
for(int i=0;i<100;i++) System.out.printf("%s \n",new item(3).toString());
*/

/*
  team guy = new team("peanut person",2);
  ArrayList<player> members = guy.getMembers();
  System.out.println(fight.playerFight(members.get(0), members.get(1)));
*/

}
}