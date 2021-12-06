import java.io.*;
import java.util.*;
import java.io.FileNotFoundException;
// creates a new server if one does not exist. 
class agentApplication {
  public static void main(String[] args) {

	 // int value = 1;

			server mainRun = utility.ReadObjectFromFile("Data/server.ser");
		String[] values = new String[4];
		//https://www.baeldung.com/java-csv-file-array
			List<List<String>> records = new ArrayList<>();
			try (BufferedReader br = new BufferedReader(new FileReader("agentActions.csv"))){
    			String line;
    			while ((line = br.readLine()) != null) {
        			values = line.split(",");
        			records.add(Arrays.asList(values));
    		}}catch(Exception e){

			}

			// values    [0] = self.choose_team,  [1]= self.team_or_player,  [2]=  self.which_player, [3]= self.buy_item
			
			pyramid game = mainRun.getCurrentGames().get(0);

			//handling team choice 
			team selectedTeam = game.getTeams()[Integer.parseInt(values[0])];

			//TODO modify for multi agent situation
			agent currAgent = mainRun.getAgents().get(0);

			ArrayList<player> selectedPlayers = game.getPlayers();
			


			//Selecting player
			for(int i=0;i<selectedPlayers.size();i++){
				if(!selectedPlayers.get(i).getTeam().equals(selectedTeam)) selectedPlayers.remove(i);
			}

			player choosenPlayer = selectedPlayers.get(Integer.parseInt(values[2]));


			//Applying bet 
			if(Integer.parseInt(values[1])==1){
				currAgent.bet(game, selectedTeam, 1);
			}
			else{
				currAgent.bet(game, choosenPlayer, 1);
			}

			
			//Buying items for player

			int buy_item = Integer.parseInt(values[3]);

			if(buy_item != -1){

				currAgent.spend(1);
				choosenPlayer.addToInventory(new item(buy_item,5,"agent Item", 4),4);
			}


		  	utility.writeObject(mainRun,"server");


}
}