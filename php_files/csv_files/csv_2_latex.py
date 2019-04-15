import csv
with open("file.csv", 'r') as f:
	reader = csv.reader(f)
	latex_str = "\\begin{document}"

	latex_str += "\\begin{center} \n"
	latex_str += "\\begin{tabular}{| l | r | l | l | l | l | l |} \n"
	latex_str += "\\hline \n"

	latex_str += "Name & Student Number	& Course	& Date	& description	& start 	& end  \\\\ \n"
	latex_str += "\\hline \n"
	count = 0
	list_reader = list(reader)
	print(len(list_reader))
	for row in list_reader:
		print(row)
		for x in range(1,len(row)-2):
			latex_str += row[x] + "	" + "& "
		latex_str += row[-2] 
		latex_str += "\\\\ \n"
		latex_str +="\\hline \n"


	latex_str += "\\end{tabular}  \n"
	latex_str += "\\end{center} \n"
	latex_str += "\\end{document}"


with open("file.tex",'w+') as file:
	file.write(latex_str)


"""
\begin{center}
\begin{tabular}{ |c|c|c| } 
 \hline
 cell1 & cell2 & cell3 \\ 
 cell4 & cell5 & cell6 \\ 
 cell7 & cell8 & cell9 \\ 
 \hline
\end{tabular}
\end{center}

"""


