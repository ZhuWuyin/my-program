from random import randint
from collections import Counter
from timeit import timeit

def r2():
    list1=[1,2,3,4,5,6,7,8,9,10,11,12]
    list2=[1,1,1,1,1,1,1,1,1,1,1,1]
    counter=[]

    for count in range(1000000):
        # start=input("Start: ")
        # if start=='end':
        #     exit(1)
        # print("list2", list2)

        length=sum(list2)
        listIndex=randint(1, length)

        index=0
        for index in range(0, len(list2)):
            listIndex-=list2[index]
            if listIndex<=0:
                list2[index]=0
                break
            else :
                list2[index]+=100

        for i in range(index+1, len(list2)):
            list2[i]+=100
        
        counter.append(list1[index])

    print(Counter(counter))

print(timeit("r2()", "from __main__ import r2", number=1))
