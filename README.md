# dvb4j

This is an highly experimental project of mine designed to parse DVB PSI Data from a Transport Stream within Java.

Currently only PATs and PMTs are supported.

## How to build?

Just do `mvn package install -DskipTests` - that's all.

## How to use?

I recommend you to look at the DvbTest. 
The basic strategy is to create a DvbContext, use setDvbReceiver to make it aware of your application and then call pushPacket for each DVB Packet you want to process.

## Why did you write this?

Coding for fun - also lockdown boredom.