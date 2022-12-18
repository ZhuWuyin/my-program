from random import randint
from collections import Counter
from timeit import timeit

def r():
    list1=[1,2,3,4,5,6,7,8,9,10,11,12]
    counter=[]

    for i in range(1000000):
        index=randint(0, len(list1)-1)
        counter.append(list1[index])

    print(Counter(counter))

print(timeit("r()", "from __main__ import r", number=1))