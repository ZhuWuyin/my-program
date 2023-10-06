from typing import Counter

fileIn=open('./content.in', 'r', encoding='UTF-8')
a=''.join(fileIn.readlines())
b=a

dictLetter={}
counterValue=32
compressed=''

determ=True
while determ:

    letter=[]
    list1=[]
    for i in range(2,len(a)):
        for j in range(0,len(a)-i+1):
            interm=a[j:j+i]
            if interm not in letter:
                letter.append(interm)
                check=True
                start=0
                while check:
                    position=a.find(interm, start)
                    if position!=-1:
                        list1.append(interm)
                        start=position+i
                    else :
                        check=False

    list1=Counter(list1)
    counter=[(i, list1[i]) for i in letter if list1[i]>1 and len(i)>2 or list1[i]>2]

    if counter:
        counter.sort(key=lambda x: x[1])
        keyValue=counter[-1]
        while chr(counterValue) in b:
            counterValue+=1
        intermletter=chr(counterValue)
        dictLetter[intermletter]=keyValue[0]
        counterValue+=1
        a=a.replace(keyValue[0], intermletter)
    else :
        determ=False
compressed=a

interm=dictLetter.items()
fileOut=open('./compressed.out', 'w', encoding='UTF-8')
fileOut.write(chr(0).join([i[0]+chr(1)+i[1] for i in list(interm)])+chr(2)+compressed)
