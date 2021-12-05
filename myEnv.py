import numpy as np 
import pandas as pd 
import javaobj as javaobj
import os.path,subprocess
import csv
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

	def __init__(self,seed=None,agents=1):
		
		#represents a starting point (not yet implemented for now randomness abounds)
		#TODO (low priority)  I think this would represent a .ser pyramid object that is saved or premade or some such 
		self.seed = seed

		#Steps used for running each pyramid
		
		self.steps = 0
		self.maxSteps = 4
		

		#Decisions
		self.choose_team = None
		self.team_or_player = None
		self.which_player = None
		self.buy_item = None


		#Input to SPMN
		self.teamStats = None
		self.teamlosses = None
		self.teamwins = None
		self.playerStats = None



		#begins simulating
		self.reset()


	#returns to original seeded state
	def reset(self):
		buildFiles = ["Main.java","mainNew.java","agentApplication.java","pyramid.java","team.java","trap.java","monster.java","player.java","agent.java"
					,"utility.java","fight.java","fileReader.java","fileWriter.java"]
		for x in buildFiles: subprocess.check_output(["javac",x])
		subprocess.check_output(["java","mainNew"])
		self.fetch_from_serv()


	def step(self, action):

		#incrementing step
		self.steps=self.steps+1

		
		#choose a team
		if self.steps==1:
			self.choose_team=action

		#choose whether to bet on a player of that team 
		if self.steps==2:
			self.team_or_player=action


		#choose a player
		if self.steps==3:
			self.which_player=action

		#choose whether to buy a item for that player
		if self.steps==4:
			self.buy_item=action
			self.sendActions()
			self.done = True
		
		self.fetch_from_serv()	

		#returning the output of the action
		return self.state(), self.money, self.done



	#this is run when all data points are submitted (the first "decision" only chooses which data the env passes rather than what is generated)
	def sendActions(self):
		with open("agentActions.csv","w+",newline="") as csfile:
			writer = csv.writer(csfile, delimiter=",")
			writer.writerow([self.choose_team, self.team_or_player, self.which_player, self.buy_item])
		
		subprocess.check_output(["java","agentApplication"])



	def fetch_from_serv(self):

		with open("Data/server.ser","rb") as ft:
			pobj = ft.read()
		pobj = javaobj.loads(pobj)

		"""with open("Data/server.ser","rb") as ft:
			pobj = javaobj.load(ft)
		"""
		#grabs data from the pyramid
		if len(pobj.processingGames)>0:
			pyramid = pobj.processingGames[0]

			#moves proccesed games to "previous games"
			subprocess.check_output(["java","triggerProcessing"])

		#runs if the game has not yet completed
		else:
			#change later to account for concurrent games TODO
			pyramid = pobj.currentGames[0]
			

		#TODO make teamstats and player stats
		#Needs to be implemented in java 
		self.teamStats=pyramid.averageTeamStats 

		
		# Defines player stats if a team has been choosen
		if   self.choose_team != None :
			print(self.choose_team)
			players = [p for p in pyramid.players if p.team==pyramid.teams[self.choose_team]]
			self.playerStats = []
			for i in range(len(players)):
				print(players[i].stats)
				self.playerStats.append(players[i].stats)
			

		#Builds teams wins and losses 
		self.teamWins = [x.wins for x in pyramid.teams]
		self.teamlosses = [x.losses for x in pyramid.teams]

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
			,self.team_or_player,self.which_player,self.buy_item]]



