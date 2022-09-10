import cv2
import numpy as np

def replace(list1: object, position: list, new: object):
    if len(position)==1:
        list1[position[0]]=new
    else :
        replace(list1[position[0]], position[1:], new)

def expand(code: list[list], magnification: int):
    Height=len(code)
    Width=len(code[0])
    outputList=[[[255 for i in range(3)] for j in range(Width*magnification)] for k in range(Height*magnification)]

    [[replace(outputList, [row+i, col+j], code[row//magnification][col//magnification]) for j in range(magnification)] for row in range(0, len(outputList), magnification) for col in range(0, len(outputList[0]), magnification) for i in range(magnification) if code[row//magnification][col//magnification]]
    return outputList

while True:
    imagePath=input("Image path (Drag the image here or type the path): ")
    outputName=input("Output image's name: ")
    outputPath=input("Target output path (Drag the document here or type the path): ")
    if outputPath[0]=='"' or outputPath[0]=="'":
        outputPath=outputPath[1:-1]
    if outputPath[-1]!="/":
        outputPath+="/"
    magnification=int(input("Please input magnification: "))

    image=cv2.imdecode(np.fromfile(imagePath, dtype=np.uint8), -1).tolist()
    cv2.imencode(outputName, np.array(expand(image, magnification), dtype=np.uint8))[1].tofile(outputPath+outputName)

    determ=input("Type 'c' to continue, 'e' to exit: ").lower()
    if determ=='e' or determ=='exit':
        break