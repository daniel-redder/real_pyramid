import java.io.Serializable;


public  class trap implements Serializable{

private int[] position;
private int[] checkList;
private int[] valueList;
private String name;
private int damage;


//serializable generator
public trap(int[] position, int[] checkList, int[] valueList,String name){

	this.position = position;
	this.checkList = checkList;
	this.valueList = valueList;
}

public trap(int[] position){

	this.position = position;
	this.damage = generator.getRandomNumber(1, 4)+2;
	this.name = generator.getTrapName();

	int count = generator.getRandomNumber(1,10);

	this.checkList = new int[count];
	this.valueList = new int[count];

	for(int i=0;i<count;i++){
		this.checkList[i]=generator.getRandomNumber(0,9);
		this.valueList[i]=generator.getRandomNumber(10,20);
	}
}

public int[] getPosition(){
	return this.position;
}

public int trigger(player p){
	for(int i=0;i<checkList.length;i++){

		if(!p.isAlive()) return 0;
		//dc checker that accounts for equipment
		int roll = p.getFullStat(this.checkList[i])+generator.getRandomNumber(1,20);
		if(roll<valueList[i]) effect(p);
		else System.out.printf("%s avoided part %s of trap %s",p.toString(),String.valueOf(i+1),this.toString());

	}
	return 1;
}

public void effect(player p){
	p.setHealth(p.getHealth()-(this.damage));
	System.out.printf("%s triggered %s, and took %s damage",p.toString(),this.toString(),this.damage);
}

public String toString(){
	return this.name;
}



}