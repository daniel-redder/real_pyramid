from myEnv import pyramid
import numpy as np 
import pandas as pd 


df = pd.DataFrame(columns=["teamWins1","teamWins2","teamWins3","teamWins4","teamLosses1","teamLosses2","teamLosses3","teamLosses4"
	,"teamStats00","teamStats10","teamStats20","teamStats30","teamStats01","teamStats11","teamStats21","teamStats31","teamStats02","teamStats12","teamStats22","teamStats32","teamStats03","teamStats13","teamStats23","teamStats33","teamStats04","teamStats14","teamStats24","teamStats34","teamStats05","teamStats15","teamStats25","teamStats35","teamStats06","teamStats16","teamStats26","teamStats36","teamStats07","teamStats17","teamStats27","teamStats37","teamStats08","teamStats18","teamStats28","teamStats38","teamStats09","teamStats19","teamStats29","teamStats39","choose_team"
	,"playerStats00","playerStats10","playerStats20","playerStats30","playerStats01","playerStats11","playerStats21","playerStats31","playerStats02","playerStats12","playerStats22","playerStats32","playerStats03","playerStats13","playerStats23","playerStats33","playerStats04","playerStats14","playerStats24","playerStats34","playerStats05","playerStats15","playerStats25","playerStats35","playerStats06","playerStats16","playerStats26","playerStats36","playerStats07","playerStats17","playerStats27","playerStats37","playerStats08","playerStats18","playerStats28","playerStats38","playerStats09","playerStats19","playerStats29","playerStats39","team_or_player","which_team","buy_item"])


env = pyramid()


for x in range(3):
	test = True
	while(test):
		state, money, done = env.step(2)

		if(done): test = False
	
	state.append(money)
	
	series = pd.Series(state, index = df.columns)
	df.append(series, ignore_index = True)
	env.reset()



print(df)