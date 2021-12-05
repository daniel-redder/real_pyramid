import java.io.Serializable;

public class player implements Serializable, Comparable<player> {

	private int[] stats;
	private item[] inventory;
	private item weapon;
	private int floor;
	private String name;
	private team team;
	private int[] position;
	private boolean Alive;
	private int damage;
	private int health;
  private int stamina;
  private int id;

	// for recreating already made players
	public player(int[] stats, boolean Alive, item[] inventory, int floor, String name, team team, int[] position) {
		this.stats = stats;
		this.Alive = Alive;
		this.inventory = inventory;
		this.floor = floor;
		this.name = name;
		this.team = team;
		this.damage = damage;
		this.position = position;
		this.health=health;
    this.stamina=stamina;
	}

	// new player creation
	public player(team team) {
		/*
		 * stats are in this order swolitude, humongousness, lightning affinity, street
		 * smarts, book smarts, ocularity, staminism, chi energy, failitude, unfailitude
		 */
		this.team = team;
		this.position = new int[2];
		this.inventory = new item[4];
		this.Alive = true;
		this.floor = 0;
		
		this.stats = generator.statListGeni();
		this.name = generator.getName();
		this.health = this.stats[1]*5+1000;
    	this.stamina = this.stats[6]*5;
		// assign default weapon based on highest stat combo TODO
		this.weapon = generator.getStarterWeapon(this.stats);
		this.damage = this.weapon.getDamage(this.stats);
	}

	
	public void move(floor level) {
		System.out.println("Starting Position "+this.position[0]+" "+this.position[1]);
		int[] stairs = level.getCenter();
		int[] dimensions = level.getDimen();
    int maxY = dimensions[1];
    int maxX = dimensions[0];
    int temp = generator.getRandomNumber(0,1);
    //this.position;
    //fuck you it navigates graphs
    if(this.position[temp] > stairs[temp]){
      this.position[temp] -= 1;
    }else if(this.position[temp] < stairs[temp]){
      this.position[temp] += 1;
    }else if(this.position[1-temp] < stairs[1-temp]){
      this.position[1-temp] += 1;
    }else if(this.position[1-temp] > stairs[1-temp]){
      this.position[1-temp] -= 1;
    }else{
      System.out.println(this.name + "'s move logic isn't working!");
    }
    System.out.println(this.name + " got " + temp + " for temp!");
    System.out.println(this.name + " has moved to (" + this.position[0] + ", " + this.position[1] + ")!");
  }

  public void setHealth(int value){
		this.health = value;
		if(this.health<1) this.Alive=false;
	}

  public void setStamina(int value){
    this.stamina = value;
  }

	public int getHealth(){
		return this.health;
	}

  public int getStamina(){
    return this.stamina;
  }

	public int getDamage(){
		return this.damage;
	}


	public String getName(){
		return this.name;
	}

	public void setStat(int stat, int value){
		this.stats[stat]=value;
	}

	public boolean isAlive() {
		return this.Alive;
	}

	public int incrementFloor(int[] position) {
		this.floor++;
		this.position = position;

		//TODO change this only temporary for now
		this.stamina =  this.stats[6]*5;
		return this.floor;
	}

	public void setFloor(int val) {
		this.floor = val;
	}

	public team getTeam(){
		return this.team;
	}

	public int getFloor() {
		return this.floor;
	}

	public int[] getPosition() {
		return this.position;
	}

	public String getTeamName() {
		return this.team.getName();
	}

	public String toString() {
		return String.format("%s, from team: %s", this.name, this.team);
	}

	// swolitude, humongousness, lightning affinity, street smarts, book smarts,
	// ocularity, staminism, chi energy, failitude, unfailitude
	public int getStat(int stat) {
		return stats[stat];
	}

  //gets the base stat as well as items that add to the stat value
  public int getFullStat(int stat){
    int temp = getStat(stat);
    
    for(int i=0; i<inventory.length; i++){
      if(inventory[i] != null){
        if(inventory[i].getStatMod() == stat){
          temp += inventory[i].getModValue();
        }
      }
    }
    
    return temp;
  }

  public int compareTo(player compareInit) {
		int compareInt = ((player) compareInit).getStat(2);
		return this.stats[2] - compareInt;
	}


	public void addToInventory(item i, int type){
		this.inventory[type] = i;
	}


}