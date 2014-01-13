import re
import os
import string
import numpy as np
import matplotlib.pyplot as plt

def analyse_data(subfolder, prefix):
	statisticsList = []

	#collect data
	indir = '/home/adrian/workspace/regularLanguages/doc/statistics/'+subfolder
	print indir
	for root, dirs, filenames in os.walk(indir):
	    for f in filenames:
	    	print f
	    	if string.find(f, prefix) != -1:
				log = open(os.path.join(root, f),'r')
				statObj = EAStatistics()
				data = log.read()
				data = data.split('\n')

				#remove timestamp #Fri Dec 27 00:46:40 CET 2013: Problem Count: 50
				data = [re.sub(r'^\w{3} \w{3} \d{2} \d{2}:\d{2}:\d{2} \w{3} \d{4}:', '', d) for d in data]
				for d in data:
					dl = d.split(':')
					dl = [n.strip() for n in dl]

					if dl[0] == 'Problem Count':
						statObj.noProblems=int(dl[1])
					elif dl[0] == 'CandidatesCount':
						statObj.noCandidates=int(dl[1])
					elif dl[0] == 'Solution Found':
						statObj.foundPercentage=int(dl[1])
					elif dl[0] == 'Avg cycles':
						statObj.avgCycles=int(dl[1])
				statisticsList.append(statObj)
				print str(statObj)


	#plot data
	
	#compress data
	last=None
	percSum=0.0
	avgSum=0.0
	count=0.0

	statisticsList.sort(statistics_compare)

	compressedList = []
	for e in statisticsList:
		if not last is None:
			if int(e.noCandidates) == int(last.noCandidates) and int(e.noProblems) == int(last.noProblems):
				percSum+=e.foundPercentage
				avgSum+=e.avgCycles
				count+=1.0
				last=e
			else:
				newObj = EAStatistics()
				newObj.noCandidates=last.noCandidates
				newObj.noProblems=last.noProblems
				newObj.foundPercentage=percSum/count
				newObj.avgCycles=avgSum/count
				compressedList.append(newObj)

				percSum=e.foundPercentage
				avgSum =e.avgCycles
				count=1.0

				last=e
		else:
			percSum=e.foundPercentage
			avgSum =e.avgCycles
			count=1.0
			last=e
	#append the last object
	newObj = EAStatistics()
	newObj.noCandidates=last.noCandidates
	newObj.noProblems=last.noProblems
	newObj.foundPercentage=percSum/count
	newObj.avgCycles=avgSum/count
	compressedList.append(newObj)

	compressedList.sort(statistics_compare)

	xaxis = range(len(compressedList))
	
	bars = [x.foundPercentage for x in compressedList]
	barsAvg = [x.avgCycles for x in compressedList]

	print barsAvg

	labels = ["C:"+str(x.noCandidates)+"/P:"+str(x.noProblems) for x in compressedList]

	plt.figure(figsize=(12,10))

	plt.ylim([0,100])
	plt.xticks(xaxis, labels, rotation='vertical', size='small', ha='center')
	plt.bar(xaxis, bars, 0.5, alpha=0.4, color='b', align="center")
	plt.savefig(prefix+subfolder+"_solved.svg")

	plt.clf()

	plt.figure(figsize=(12,10))
	plt.ylim([0,100])
	plt.xticks(xaxis, labels, rotation='vertical', size='small', ha='center')
	plt.bar(xaxis, barsAvg, 0.5, alpha=0.4, color='b', align="center")
	plt.savefig(prefix+subfolder+"_cycles.svg")


class EAStatistics:
 	noCandidates=0
	noProblems=0
	foundPercentage=0
	avgCycles=0
	__len__=1

	def __str__(self):
		return "Cand:"+str(self.noCandidates)+", Probs:"+str(self.noProblems)+", foundPercentage:" + str(self.foundPercentage)+", AvgCycles:"+str(self.avgCycles)

def statistics_compare(x, y):
	if x.noCandidates > y.noCandidates:
		return 1
	elif x.noCandidates == y.noCandidates:
		if x.noProblems > y.noProblems:
			return 1
		elif x.noProblems == y.noProblems:
			return 0
		else:
			return -1
	else:
		return -1

analyse_data('abab', 'E_G_')
analyse_data('abab', 'E_L_')
analyse_data('abab', 'C_G_')
analyse_data('div3', 'E_G_')
analyse_data('div3', 'E_L_')
analyse_data('div3', 'C_G_')
