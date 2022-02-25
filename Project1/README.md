# Project 1:  Simplified SSL (Secure Socket Layer)

### (A) Implement the RSA key generation and ciphering algorithm 
The class(es) should be able to generate public and private key pairs of arbitrary size and should be able to cipher and decipher short messages. 

In other words, I should be able to run your program to generate a private and public key pair and use it to cipher some data and decipher it.  

---

### (B) Implement the hash function and one-time key encryption 
The classes should be able to assemble the data into packets calculate the checksums. Implement one-time key generation, use it to encode and decode data. 
In other words, I should be able to run your program to verify that these modules work. 

---

###  (C) Implement the SSL layer using the classes from part A and part B and demonstrate it with a simple application described below 
Implement the handshake (key exchange) described above and transfer data hashed and encrypted with the one-time key. This entails opening a network socket connection to the server, exchanging the one-time key, authorizing the client and then transferring and receiving data via the socket. 

The application to demonstrate your work is very simple. The Client opens a connection to the Server. The handshake takes place, then the client sends data read from the keyboard and sends it encrypted to the server. The server examines the data and re-sends it encrypted to the client, but slightly modified: all upper case characters are converted to lower case, and all lower case characters are converted to upper case. Store the keys, the user info and the users profile in files, you are free to choose the format. I suggest you take a look at the java.util.Properties class (and its load method). 

---

# How to Run
