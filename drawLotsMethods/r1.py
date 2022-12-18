from random import randint
from collections import Counter
from timeit import timeit

def r1():
    list1=[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30]
    list2=[]
    counter=[]
    switch=True

    for i in range(1000000):
        # start=input("\nStart: ")
        # if start=='end':
        #     exit(1)

        if switch:
            listIndex=randint(0, len(list1)-1)
            counter.append(list1[listIndex])
            list2.append(list1.pop(listIndex))
            if not list1:
                switch=False
        else :
            listIndex=randint(0, len(list2)-1)
            counter.append(list2[listIndex])
            list1.append(list2.pop(listIndex))
            if not list2:
                switch=True

    print(Counter(counter))

print(timeit("r1()", "from __main__ import r1", number=1))