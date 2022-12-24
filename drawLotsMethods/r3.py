from random import randint
from collections import Counter
from timeit import timeit

def r1():
    list1=[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22]
    list1Len=len(list1)
    bound=list1Len-1
    counter=[]

    for i in range(1000000):
        listIndex=randint(0, bound)
        counter.append(list1[listIndex])
        temp=list1[bound]
        list1[bound]=list1[listIndex]
        list1[listIndex]=temp
        bound-=1
        if bound==0:
            counter.append(list1[0])
            bound=list1Len-1

    print(Counter(counter))

print(timeit("r1()", "from __main__ import r1", number=1))