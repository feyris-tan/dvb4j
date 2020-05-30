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

## Useful resources:

The information needed to write this software were gathered from:

- DVB Identifiers: https://www.dvbservices.com/identifiers/index.php
- ISO 13818-1
- ETSI EN 300 468 
- ITU-T H.222.0 Amendment 3
- ITU-T H.222.0 Amendment 5
- Descriptor list from https://en.wikipedia.org/wiki/Program-specific_information#Program_and_Elementary_Stream_Descriptor_Tags
- ETSI TS 102 809
- Packet header from https://en.wikipedia.org/wiki/MPEG_transport_stream#Packet
- ITU-T Rec. H.222.0 (03/2017) 