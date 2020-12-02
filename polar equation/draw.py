import turtle
from math import sin, cos, tan, radians

turtle.speed(0)
turtle.hideturtle()
turtle.goto(400,0)
turtle.goto(-400,0)
turtle.goto(0,0)
turtle.goto(0,400)
turtle.goto(0,-400)
turtle.goto(0,0)

while True:
    function=input("r=")
    precision=float(input("precision: "))
    degree_list=[a/10 for a in [i for i in range(int(360/precision+1))]]
    turtle.up()
    x=degree_list[0]
    x=radians(x)
    try :
        r=eval(function)
        turtle.goto(-r*cos(x), -r*sin(x))
    except ZeroDivisionError:
        pass
    turtle.down()
    for x in degree_list:
        x=radians(x)
        try :
            r=eval(function)
            turtle.goto(-r*cos(x), -r*sin(x))
        except ZeroDivisionError:
            pass

turtle.done()