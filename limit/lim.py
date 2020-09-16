a=eval(input("Please input limit value: "))
p=int(input("Please input precision: "))
f=input("please input function: y=")
for i in range(p):
    x=eval(str(a-1)+"."+"9"*(i+1))
    print(eval(f),end="\tlimit\t")
    x=eval(str(a+1)+"."+"0"*(i+1)+"1")
    print(eval(f))