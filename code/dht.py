
import adafruit_dht
from board import *
import time
# GPIO17
SENSOR_PIN = D23

dht11 = adafruit_dht.DHT11(SENSOR_PIN, use_pulseio=False)

while True:
	temperature = dht11.temperature
	humidity = dht11.humidity
	print(f"Humidity= {humidity:.2f}")
	print(f"Temperature= {temperature:.2f}°C")
	f=open('dht.txt','w')
	f.write(str(temperature)+"#"+str(humidity))
	f.close()
	time.sleep(3)
