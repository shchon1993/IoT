import RPi.GPIO as gpio
import time
import datetime
from firebase import firebase

input_pin = 13

input1 = 1
count = 0

gpio.setmode(gpio.BCM)
gpio.setup(input_pin, gpio.IN)

firebase = firebase.FirebaseApplication('https://iotproject-39405.firebaseio.com/',None)

def input():
    global input1
    global count
     # 1 == off
    if gpio.input(input_pin) == 1:
	count = count + 1
    else:
        input1 = 0
	count = 0
	
    if count > 10 :
	input1 = 1

    print("count ", count)
    if input1 == 1:
        print("1\n")
    else:
        print("0\n")

        
    if input1 == 0:
        input2 = 1
    else:
        input2 = 0
    firebase.patch('',{'message':str(input2+9)+"/20"})
    

 
try:
    while True:
        input()
       
        
except KeyboardInterrupt:
    gpio.cleanup()
