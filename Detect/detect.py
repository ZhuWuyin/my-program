import cv2, pyautogui, time, keyboard

def getVelocity():
    start=pyautogui.position()
    time.sleep(0.01)
    end=pyautogui.position()

    Vx=(start[0]-end[0])/0.01
    Vy=(start[1]-end[1])/0.01

    return (Vx, Vy)

def check_keys_except(excluded_keys):
    all_keys = set([chr(i) for i in range(32, 127) if chr(i) not in excluded_keys]) | keyboard.all_modifiers

    for key in all_keys:
        if keyboard.is_pressed(key):
            return True
        
    return False

capture=cv2.VideoCapture(0)
start=False
add=False

video_start=0
countdown_time=5

file_index=0
fourcc=cv2.VideoWriter_fourcc(*"mp4v")
out=cv2.VideoWriter("video/video{0}.mp4".format(file_index), fourcc, 20.0, (640, 480))

excluded_keys=['1','2']

print("ok")
while True:
    Vcurr=getVelocity()

    if keyboard.is_pressed("1") and not start:
        start=True
    if keyboard.is_pressed("2") and start:
        start=False

    if start and ((Vcurr[0]!=0 or Vcurr[1]!=0) or check_keys_except(excluded_keys)):
        video_start=int(time.time())
        add=True
    
    if time.time()<=video_start+countdown_time:
        ret, frame=capture.read()
        if not ret:
            continue
        frame=cv2.flip(frame, 1)
        out.write(frame)
    elif add:
        file_index+=1
        add=False
        out=cv2.VideoWriter("video/video{0}.mp4".format(file_index), fourcc, 20.0, (640, 480))