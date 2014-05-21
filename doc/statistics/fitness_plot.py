import random
import os
import numpy as np
import matplotlib.pyplot as plt

#for i in range(20):
xaxis = range(25)
bars = [5, 5, 5, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 3, 3, 3, 3]

#for x in range(10):
#	bars.append(random.random()*70)

plt.figure(figsize=(8,5))

#plt.ylim([0,100])
#plt.xticks(xaxis, xaxis)
#barlist = plt.bar(xaxis, bars, 0.5, alpha=0.4, color='b', align="center")

#for x2 in [1, 3, 5, 7, 9]:
#	x1 = x2-1

#	if(bars[x1] > bars[x2]):
#		barlist[x1].set_color('g')
#		barlist[x2].set_color('r')
#	else:
#		barlist[x1].set_color('r')
#		barlist[x2].set_color('g')

#plt.plot(xaxis, bars, 'r')
#plt.savefig("bars_random_"+str(i)+".svg")

#plt.clf()

plt.ylim([0,100])
plt.ylabel('Fitness')
plt.xlabel('Kandidaten')
plt.xticks(xaxis, xaxis)
plt.bar(xaxis, bars, 0.5, alpha=0.4, color='b', align="center")

#for x in range(10):
#	if x < 5:
#		barlist[x].set_color('g')
#	else:
#		barlist[x].set_color('r')

plt.savefig("bars_fitness.svg")