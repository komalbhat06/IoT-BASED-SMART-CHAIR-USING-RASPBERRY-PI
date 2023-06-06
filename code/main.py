import time
while True:
    f=open('dht.txt','r')
    dht=f.read()
    f.close()
    f=open('flex.txt','r')
    flex=f.read()
    f.close()
    t=dht.split('#')
    f=flex.split('#')
    print('temp',t[0],'humidity',t[1],'flex1',f[0],'flex2',f[1])
    time.sleep(1)
