alphabet=[["A", "B", "C", "D", "E"], ["F", "G", "H", "I", "J"], ["K", "L", "M", "N", "O"], ["P", "Q", "R", "S", "T"], ["U", "V", "W", "X", "Y"], ["Z", "", "", "", ""]]
print('\n'.join([', '.join(k) for k in [[j for j in i if j] for i in alphabet]]))
newAlphabet=[[l[k] for l in alphabet] for k in [int(i) for i in input("\nPlease input the colum number from zero\n").split()]]
print('\n'.join([', '.join(k) for k in [[j for j in i if j] for i in newAlphabet]]))
rowList=[int(i) for i in input("\nPlease input the colum number from zero\n").split()]
print("\nAnswer is: "+''.join([newAlphabet[i][rowList[i]] for i in range(len(rowList))]))