from typing import Counter

class Compression:

    def __init__(self) :
        self.dictLetter={}
        self.counterValue=32
        self.compressed=''

    def compress(self, a: str) :
        
        determ=True
        while determ:

            list1=[]
            letter=[a[j:j+i] for i in range(2, len(a)) for j in range(0, len(a)-i+1) if a[j:j+i] not in a[0:j]]
            for interm in letter:
                i=len(interm)
                start=0
                check=True
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
                while chr(self.counterValue) in a:
                    self.counterValue+=1
                intermletter=chr(self.counterValue)
                self.dictLetter[intermletter]=keyValue[0]
                self.counterValue+=1
                a=a.replace(keyValue[0], intermletter)
            else :
                determ=False
        self.compressed=a