
import adafruit_dht
from board import *
import time
import board
import busio
import adafruit_ads1x15.ads1115 as ADS
from adafruit_ads1x15.analog_in import AnalogIn
import requests
url = "http://192.168.0.116:8080/Chair_Server/"
# GPIO17
SENSOR_PIN = D23
# Create the I2C bus
i2c = busio.I2C(board.SCL, board.SDA)

# Create the ADC object
ads = ADS.ADS1115(i2c)

# Create an analog input channel on the ADS1115
chan1 = AnalogIn(ads, ADS.P0)
chan2 = AnalogIn(ads, ADS.P1)
chan3 = AnalogIn(ads, ADS.P2)

dht11 = adafruit_dht.DHT11(SENSOR_PIN, use_pulseio=False)
def temp():
    dht11 = adafruit_dht.DHT11(SENSOR_PIN, use_pulseio=False)
    temperature = dht11.temperature
    humidity = dht11.humidity
    data="Temp: "+str(temperature)+" Humi: "+str(humidity)
    print(data)
    return str(temperature)+"##"+str(humidity)
def flex():
    flexData="sensor 1: "+str(chan1.value)+" Sensor 2 : "+str(chan2.value)+" Sensor 3: "+str(chan3.value)
    print(flexData)
    return str(chan1.value)+"##"+str(chan2.value)+"##"+str(chan3.value)
while True:
    try:
        data=temp()+"##"+flex()
        print(data)
        ploads = {'data':data}
        r = requests.get(url+'get_sensor_data',params=ploads)
        print(r.text)
        time.sleep(5)
    except:
        pass
