import time
import board
import busio
import adafruit_ads1x15.ads1115 as ADS
from adafruit_ads1x15.analog_in import AnalogIn

# Create the I2C bus
i2c = busio.I2C(board.SCL, board.SDA)

# Create the ADC object
ads = ADS.ADS1115(i2c)

# Create an analog input channel on the ADS1115
chan1 = AnalogIn(ads, ADS.P0)
chan2 = AnalogIn(ads, ADS.P1)

# Read the voltage on the analog input channel
while True:
    try:
        print('1: {}V'.format(chan1.value))
        print('2: {}V'.format(chan2.value))
        f=open('flex.txt','w')
        f.write(str(chan1.value)+"#"+str(chan2.value))
        f.close()
        time.sleep(1)

    except KeyboardInterrupt:
        break
