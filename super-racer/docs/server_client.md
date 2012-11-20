# Server-Client Communication Specification

### Overview
1. Server "handshakes" each client and sends them base files (map/time/score)
2. Loop ->	Server sends messages to all clients every 1/30th of a second
3. Loop -> Clients send server messages on each input frame
4. Server ends client connection with status message

### 1 Handshake
The Message format is simple
List of static values in the following form

	ID1,Xposition,Yposition
	ID2,Xposition,Yposition
	 .
	 .
	 .
	IDn,Xposition,Yposition

Client parses with following regex:
	
	<ID(/d{9})>,				# Look at Part 5 for ID Spec
	<X(/d{0-9}?(/./d{0-9})>,	# X Position
	<Y(/d{0-9}?(/./d{0-9})>	 	# Y Position
	


### 2 Server Messages
Server sends messages in the same format as the handshake.
However it only sends active objects so as to reduce network traffic.


### 3 Client Messages
Client sends messages of input and status only.
They will be in the form:

	Status
	Input1:Value
	Input2:Value
		.
		.
		.
	Inputn:Value


4
###  End Connection
Server will simply send one line status message
	A Won/B Won/ Etc


### 5 Object ID Specification
Objects are essentially messages
IDs of Objects are always 9 digits

	a-b-c-d-e-f-g-h-i  
		a - is object type
			0 -> System Events
			1 -> Sound
			2 -> Rendering
			3 -> Logic
		b -	BUFFER 
		c - BUFFER
		d - BUFFER
		e - Object State
			Could be something like frame of animation
			or even alive vs dead
		f -	Object State
		g - Object ID
		h - Object ID
		i - Object ID