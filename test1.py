
mport RPi.GPIO as gpio
import time
import datetime
 
input_pin = 13

input = 0
count = 0

gpio.setmode(gpio.BCM)
gpio.setup(input_pin, gpio.IN)
 
def input():
    global input, count
 
    if gpio.input(echo_pin) == 0:
	count = count + 1
    else input = 1
        pulse_end = time.time()
	count = 0
	
    if count % 60 == 0:
	input = 0

    if input == 1:
        print("1\n")
    else
        print("0\n")
 
try:
    while True:
        input()
        time.sleep(1)
        
except KeyboardInterrupt:
gpio.cleanup()

