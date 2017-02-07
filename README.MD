RSA Encryptor/Decryptor
==============

<h2 id="key-generation">1. Key pair generation</h2>

The first thing you will need to do in order to use the application is to generate a pair of RSA keys. 
This can be performed by executing the appropriate commands in the terminal (if you are using Unix-based OS). In case you are using Windows OS you will need Cygwin with OpenSSL pre-installed.
Open the Cygwin terminal and type the following commands:

```
openssl genrsa -out keypair.pem 2048
openssl rsa -in keypair.pem -outform DER -pubout -out path/to/public.der
openssl pkcs8 -topk8 -nocrypt -in keypair.pem -outform DER -out path/to/private.der
```

The first command generates a pair of RSA keys (i.e. public and private) that have 2048 bytes length.
The other two commands store the generated public and private keys at the *public.der* and *private.der*
files respectivelly. 

These two files should be placed inside the working directory of the application in order to be used for encryption/decryption purposes.

---------------
**Attention:**



   a. The RSA-Encryptor uses the public key to encrypt a message and the private key to decrypt it. Hence, this application
    is designed for personal use only (e.g. for keeping your passwords of different accounts in a single document in an encrypted form).
	
   b. In any case keep the private key in a safe place, which is accessible only to you (e.g. inside a personal flash drive).

---------------


<h2 id="installation">2. Installation</h2>


  - Download or clone the source code locally.
  - Compile the source code in order to create a single executable .jar file.
  - Place the .jar file inside a desired folder.
  - Copy the previously generated *public.der* and *private.der* files and paste them inside the same folder.
  - Start the application by double clicking on the .jar file or by executing the command *"java -jar application-name.jar"* in the terminal.
  - Have fun.

<h2 id="operation">3. Operation</h3>

Start the application and type the message you would like to encrypt. After typing the desired message click on the *Encrypt* button.
The message will be automatically encrypted and the content of the text area will be replaced with a single string that corresponds to 
the encrypted message.	

A similar procedure should be followed for decrypting a message previously encrypted using this application. Just copy the encrypted 
message and paste it in the text area of the application and click the *Decrypt* button. The application decrypts the message and 
presents the original text in the application text area.

The two following figures present the previously mentioned steps.

 <p align="center" width="128" height="73">
	<img align="center" src="/images/fig1.png" />
 </p>
 
 <p align="center" width="128" height="73">
	<img align="center" src="/images/fig2.png" />
 </p>

