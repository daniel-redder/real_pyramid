import numpy as np 
import pandas as pd 
import javaobj.v2 as javaobj
import os.path,subprocess

'''
Variables and possible values:
---------------------------------------
team(x)wins:0-4

x={0,1,2,3}

this represents how many games a specific team has "won"
---------------------------------------
team(x)losses:0-4

x={0,1,2,3}

this represents how many games a specific team has "lost"
----------------------------------------
teamStats[x][y]

x={0,1,2,3}
y={0,1,2,3,4,5,6,7,8,9}

This represents the average stat, y, across all selected members of a team, x. 
-----------------------------------------
playerStats[x][y]

x = {0,1,2,3}
y={0,1,2,3,4,5,6,7,8,9}

This represents each stat for each player on the selected team. 
'''



class pyramid:

	def __init__(self,seed=None,iterations=1,agents=1):
		
		#represents a starting point (not yet implemented for now randomness abounds)
		#TODO (low priority)  I think this would represent a .ser pyramid object that is saved or premade or some such 
		self.seed = seed

		#Steps used for running each pyramid
		
		self.inIteration = True
		#iterations used for how many consecutive pyramids are generated by the environment
		self.iterations = iterations
		self.curr_iter = 0
		self.steps = 0
		self.maxSteps = 4
		
		#begins simulating
		self.reset()


	#returns to original seeded state
	def reset(self):
		buildFiles = ["Main.java","mainNew.java"]
		for x in buildFiles: subprocess.check_output(["javac",x])
		subprocess.check_output(["java","mainNew.java"])
		self.fetch_from_serv()


	def step(self, action):

		#incrementing step
		self.step++

		

		
		


		 


		self.fetch_from_serv()


		#step counting
		if self.step==self.maxSteps:
			self.curr_iter++
			self.steps=0
			self.inIteration=False
			

		#If all pyramids parsed through
		if self.curr_iter >= self.iterations: 
			self.done = True


		#returning the output of the action
		return self.state(), self.money, self.done, self.inIteration


	def fetch_from_serv(self):
		
		with open("server.ser","rb") as ft:
			pobj = javaobj.load(ft)


		#TODO make teamstats and player stats
		self.teamStats = [][]
		self.playerStats = [][]
		#TODO make teamWins and losses 
		#these would change afterwards does that matter?
		#(not really) I think
		self.teamWins = []
		self.teamlosses = []
		#TODO modify to work with many agents (gets agents current money)
		self.money = pobj.agents[0].money
		


	def state(self):


		#returns the current state of the model in this order
		# teamWins, teamLosses, averageTeamStats, decision1 (choose a team), all player stats for that team, decision2 (choose whether to bet on a player or team)
		#, decision3 (choose a player), decision4 (decide whether to invest in a item for that player), utility value (money)
		return [[self.teamwins[0],self.teamwins[1],self.teamwins[2],self.teamwins[3],self.teamlosses[0],self.teamlosses[1],self.teamlosses[2],self.teamlosses[3]
			,self.teamStats[0][0],self.teamStats[1][0],self.teamStats[2][0],self.teamStats[3][0],self.teamStats[0][1],self.teamStats[1][1],self.teamStats[2][1],self.teamStats[3][1],self.teamStats[0][2],self.teamStats[1][2],self.teamStats[2][2],self.teamStats[3][2],self.teamStats[0][3],self.teamStats[1][3],self.teamStats[2][3],self.teamStats[3][3],self.teamStats[0][4],self.teamStats[1][4],self.teamStats[2][4],self.teamStats[3][4],self.teamStats[0][5],self.teamStats[1][5],self.teamStats[2][5],self.teamStats[3][5],self.teamStats[0][6],self.teamStats[1][6],self.teamStats[2][6],self.teamStats[3][6],self.teamStats[0][7],self.teamStats[1][7],self.teamStats[2][7],self.teamStats[3][7],self.teamStats[0][8],self.teamStats[1][8],self.teamStats[2][8],self.teamStats[3][8],self.teamStats[0][9],self.teamStats[1][9],self.teamStats[2][9],self.teamStats[3][9]
			,self.choose_team,
			self.playerStats[0][0],self.playerStats[1][0],self.playerStats[2][0],self.playerStats[3][0],self.playerStats[0][1],self.playerStats[1][1],self.playerStats[2][1],self.playerStats[3][1],self.playerStats[0][2],self.playerStats[1][2],self.playerStats[2][2],self.playerStats[3][2],self.playerStats[0][3],self.playerStats[1][3],self.playerStats[2][3],self.playerStats[3][3],self.playerStats[0][4],self.playerStats[1][4],self.playerStats[2][4],self.playerStats[3][4],self.playerStats[0][5],self.playerStats[1][5],self.playerStats[2][5],self.playerStats[3][5],self.playerStats[0][6],self.playerStats[1][6],self.playerStats[2][6],self.playerStats[3][6],self.playerStats[0][7],self.playerStats[1][7],self.playerStats[2][7],self.playerStats[3][7],self.playerStats[0][8],self.playerStats[1][8],self.playerStats[2][8],self.playerStats[3][8],self.playerStats[0][9],self.playerStats[1][9],self.playerStats[2][9],self.playerStats[3][9]
			,self.team_or_player,self.which_player,self.buy_item,self.money]]


