public class fight{



public fight(){

}

//Something like this 
//We have a fight controller (In here or main) and it will go through all players (n) times and check to see if they have enough stam to fight again
//So fighting is less of a singular event, and more of a thing that happens across the pyramid simultaniously
//MUH REALISM


public static int MonsterFight(monster m, player p){
	
	int maxHits = 30;

	if(!m.isAlive()) return 3;
	if(!p.isAlive()) return 4;
	if(m.getStamina()<5 && p.getStamina()<5) return 4;

	int currentHits = 0; 
	//combat code     

	//stamina = 6        lightningaffinity = 2
	while(currentHits<maxHits && p.getStamina()>=5 || m.getStamina()>=5){
		//System.out.printf("%s  %s   %s  %s \n",p.toString(),p.getStamina(),m.toString(),m.getStamina());
		if(p.getStat(2)>m.getDex()){
			//if the stamina is greater then 5 of the player then attack  (or run?)
			if(p.getStamina()>=5){
				if(isHit(p,m)){ 
					int dam = p.getDamage();
					m.setHealth(m.getHealth()-dam,p);
					System.out.printf("Player %s went first and hit %s for %s damage. %s has %s health remaining\n",p.toString(),m.toString(),dam,m.toString(),m.getHealth());
				}
				else System.out.printf("Player %s missed a attack against %s\n",p.toString(),m.toString());
				p.setStamina(p.getStamina()-5);
				currentHits++;
			}
			
			if(!m.isAlive()){ System.out.println("\n========== Combat Concluded ==========\n"); return 1;}
			
			if(m.getStamina()>=5){
				if(isHit(m,p)){
					int dam = m.getDamage(); 
					p.setHealth(p.getHealth()-dam);
					System.out.printf("Monster %s went second and hit %s for %s damage. %s has %s health remaining\n",m.toString(),p.toString(),dam,p.toString(),p.getHealth());
				}
				else System.out.printf("Monster %s missed a attack against %s\n",m.toString(),p.toString());
				m.setStamina(m.getStamina()-5);
				currentHits++;
			}
			if(!p.isAlive()){ System.out.println("\n========== Combat Concluded ==========\n"); return 2;}
		}
		else{
			if(m.getStamina()>=5){
				if(isHit(m,p)){ 
					int dam=m.getDamage();
					p.setHealth(p.getHealth()-dam);
					System.out.printf("Monster %s went first and hit %s for %s damage. %s has %s health remaining\n",m.toString(),p.toString(),dam,p.toString(),p.getHealth());
				}
				else System.out.printf("Monster %s missed a attack against %s\n",m.toString(),p.toString());
				m.setStamina(m.getStamina()-5);
				currentHits++;
			}
			if(!p.isAlive()){ System.out.println("\n========== Combat Concluded ==========\n"); return 2;}

			if(p.getStamina()>=5){
				if(isHit(p,m)){ 
					int dam = p.getDamage();
					m.setHealth(m.getHealth()-dam,p);
					System.out.printf("Player %s went Second and hit %s for %s damage. %s has %s health remaining.\n",p.toString(),m.toString(),dam,m.toString(),m.getHealth());
				}
				else System.out.printf("Player %s missed a attack against %s\n",p.toString(),m.toString());
				p.setStamina(p.getStamina()-5);
				currentHits++;
			}
			if(!m.isAlive()){ System.out.println("\n========== Combat Concluded ==========\n"); return 1;
			}
		}

	}
	System.out.println("\n========== Combat Concluded ==========\n");
	//cockbat code

	//assert false:"some godamn bullshit ie MonsterFight broke coderman";
	return 0;

	//return -1 if no fight happened
	//if returns 0 neither died
	//if return 1 mosnter died
	//if return 2 player died
	//if return 3 monster was already dead and fight is skipped
	//if return 4 player was already dead fight is skipped
}


public static int playerFight(player p, player m){
	int maxHits = 30;

	if(!m.isAlive()) return 3;
	if(!p.isAlive()) return 4;
	
	if(p.getStamina()<5 && m.getStamina()<5) return 4;
	int currentHits = 0; 
	//combat code     

	//stamina = 6        lightningaffinity = 2
	while(currentHits<maxHits  && p.getStamina()>=5 || m.getStamina()>=5){
		//System.out.printf("%s  %s   %s  %s \n",p.toString(),p.getStamina(),m.toString(),m.getStamina());
		if(p.getStat(2)>m.getStat(2)){
			//if the stamina is greater then 5 of the player then attack  (or run?)
			if(p.getStamina()>=5){
				if(isHit(p,m)){ 
					int dam = p.getDamage();
					m.setHealth(m.getHealth()-dam);
					System.out.printf("Player %s went Second and hit %s for %s damage. %s has %s health remaining.\n",p.toString(),m.toString(),dam,m.toString(),m.getHealth());
				}
				else System.out.printf("%s missed a attack against %s\n",p.toString(),m.toString());
				p.setStamina(p.getStamina()-5);
				currentHits++;
			}
			if(!m.isAlive()){ System.out.println("\n========== Combat Concluded ==========\n"); return 1;}
			
			if(m.getStamina()>=5){
				if(isHit(m,p)){ 
					int dam = m.getDamage();
					p.setHealth(p.getHealth()-dam);
					System.out.printf("Player %s went Second and hit %s for %s damage. %s has %s health remaining.\n",m.toString(),p.toString(),dam,p.toString(),p.getHealth());
					
				}
				else System.out.printf("Player %s missed a attack against %s\n",m.toString(),p.toString());
				m.setStamina(m.getStamina()-5);
				currentHits++;
			}
			if(!p.isAlive()){ System.out.println("\n========== Combat Concluded ==========\n"); return 2;}
		}
		else{
			if(m.getStamina()>=5){
				if(isHit(m,p)){
					int dam = m.getDamage();
					p.setHealth(p.getHealth()-dam);
					System.out.printf("Player %s went First and hit %s for %s damage. %s has %s health remaining.\n",m.toString(),p.toString(),dam,p.toString(),p.getHealth());
				}
				else System.out.printf("Player %s missed a attack against %s\n",m.toString(),p.toString());
				m.setStamina(m.getStamina()-5);
				currentHits++;
			}
			if(!p.isAlive()){ System.out.println("\n========== Combat Concluded ==========\n"); return 2;}

			if(p.getStamina()>=5){
				if(isHit(p,m)){ 
					int dam = p.getDamage();
					m.setHealth(m.getHealth()-dam);
					System.out.printf("Player %s went Second and hit %s for %s damage. %s has %s health remaining.\n",p.toString(),m.toString(),dam,m.toString(),m.getHealth());
				}
				else System.out.printf("Player %s missed a attack against %s\n",p.toString(),m.toString());
				p.setStamina(p.getStamina()-5);
				currentHits++;
			}
			if(!m.isAlive()){System.out.println("\n========== Combat Concluded ==========\n"); return 1;}
		}
	}
	System.out.println("\n========== Combat Concluded ==========\n");
	//assert false: "What the hell you are a failure ";
	return 0;
	//same as MonsterFight return vals
}


//TODO implement isHit to return true if (attacker hits defender)
private static boolean isHit(monster m, player p){
  boolean result = false;
  int monsterHit = m.getDex() + generator.dieRoll();
  int playerDef = p.getStat(2) + generator.dieRoll();

  if(monsterHit >= playerDef){
    result = true;
  }

	return result;
}

private static boolean isHit(player p, player pTwo){
  boolean result = false;
  int playerHit = p.getStat(2) + generator.dieRoll();
  int playerDef = pTwo.getStat(2) + generator.dieRoll();

  if(playerHit >= playerDef){
    result = true;
  }
	return result;
}

private static boolean isHit(player p, monster m){
  boolean result = false;
  int playerHit = p.getStat(2) + generator.dieRoll();
  int monsterDef = m.getDex() + generator.dieRoll();

  if(playerHit >= monsterDef){
    result = true;
  }
  
	return result;
}



}