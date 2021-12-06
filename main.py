from myEnv import pyramid
import numpy as np 
import pandas as pd 
import random

df = pd.DataFrame(columns=["teamWins1","teamWins2","teamWins3","teamWins4","teamLosses1","teamLosses2","teamLosses3","teamLosses4"
	,"teamStats00","teamStats10","teamStats20","teamStats30","teamStats01","teamStats11","teamStats21","teamStats31","teamStats02","teamStats12","teamStats22","teamStats32","teamStats03","teamStats13","teamStats23","teamStats33","teamStats04","teamStats14","teamStats24","teamStats34","teamStats05","teamStats15","teamStats25","teamStats35","teamStats06","teamStats16","teamStats26","teamStats36","teamStats07","teamStats17","teamStats27","teamStats37","teamStats08","teamStats18","teamStats28","teamStats38","teamStats09","teamStats19","teamStats29","teamStats39","choose_team"
	,"playerStats00","playerStats10","playerStats20","playerStats30","playerStats01","playerStats11","playerStats21","playerStats31","playerStats02","playerStats12","playerStats22","playerStats32","playerStats03","playerStats13","playerStats23","playerStats33","playerStats04","playerStats14","playerStats24","playerStats34","playerStats05","playerStats15","playerStats25","playerStats35","playerStats06","playerStats16","playerStats26","playerStats36","playerStats07","playerStats17","playerStats27","playerStats37","playerStats08","playerStats18","playerStats28","playerStats38","playerStats09","playerStats19","playerStats29","playerStats39","team_or_player","which_team","buy_item","money"])


env = pyramid()


"""
choose_team picks which team the agent will choose to bet on  {0,1,2,3}
team_or_player decides whether the agent will bet on the team or a player on the team {0:player,1:team}
which_player chooses a player to decide whether to buy a item for, and to bet on should it choose to bet on a player {0,1,2,3}
buy_item Choose whether to buy a item for a player, and if so what type of item {-1: no item, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9}
"""

ticker = 0

for x in range(256000000):
	test = True

	#choose_team
	env.step(random.randint(0,3))
	env.step(random.randint(0,1))
	env.step(random.randint(0,3))
	state, money, done = env.step(random.randint(-1,9))

	#if(done): test = False
	
	state[0].append(money)
	#print(state)
	series = pd.Series(state[0], index = df.columns)
	df = df.append(series, ignore_index = True)
	#print(df)
	env.reset()

#print(df)
	if(x%30000==0):
		df.to_csv(f'Output/pyramid{ticker}Output.csv')
		df = df[0:0]
