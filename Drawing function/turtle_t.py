import turtle as t
from math import sin, cos

#三角函数以弧度制度量(trigonometric function measured in radians)

def plus_point(b):
    global point
    point+=b
    return point

t.hideturtle()
t.speed(0)
t.goto(400,0)
t.goto(-400,0)
t.goto(0,0)
t.goto(0,400)
t.goto(0,-400)
t.goto(0,0)
while True:
    point=-100.0
    b=float(input('精度 (precision)：'))
    point_list=[plus_point(b) for i in range(int(-100/b),int(100/b+1))]
    x_axis=float(input('x轴放大倍数 (magnification of x axis)：'))
    y_axis=float(input('y轴放大倍数 (magnification of y axis)：'))
    t.color('red')
    equation=input('y=')
    t.up()
    x=-101
    y=eval(equation.replace("x", "("+str(x)+")"))
    if type(y)!=complex:
        t.goto(x*x_axis,y*y_axis)
    t.down()
    for i in point_list:
        x=i
        y=eval(equation.replace("x", "("+str(x)+")"))
        if type(y)!=complex:
            t.goto(x*x_axis,y*y_axis)
    t.up()
t.done()