lizt=""

for x in [0,1,2,3,4,5,6,7,8,9]:
	for y in [0,1,2,3]:
		lizt=lizt+","+f'self.teamStats[{y}][{x}]'

print(lizt)