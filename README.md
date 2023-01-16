# Kotlin SMS API App

This is the App version of my project to automate sending sms messages.

Once you install the **.apk** for this project, whenever the app is running on your Android Phone you are able to ping your phone with a POST request that contains a message, a number and an authentication token.

## How it works

After you setup this app and the Python code for POST requests, you are able to Ping your phone to send SMS messages.

So on my computer, I would send a POST request like this using Python

```python
url = f"http://{phone_ip}:{port}"
data = {"number": str(number), "message": str(message), "Authorization": str(auth_token)}
headers = {'Content-type': 'application/json'}
response = requests.post(url, json=data, headers=headers) # sends the POST request to your Android Phone
```

Where the variables are equal to...

```python
phone_ip = "IP_ADDRESS Of your Phone Running this App" # Note, the Android app will show you your IP Address
port = "9000" # by default this is set to 9000. If you want to change this, change it in MainActivity.kt
number = "123456" # the phone number you want to send an SMS to
message = "This is an example text message" # the message you want sent to the number
auth_token = "123456789" # by default it's set to this number. You should change the auth token to something more secure. Instructions below
```

## Why Use This App

The main advantage to this program I have created is that it is sending a SMS message from your personal phone number. 

Most other methods will send a message from some other address, but with this app when the recipient receives your text it will display as your contact.


## Setup

1. Clone this repository

```sh
git clone https://github.com/nolanwinsman/KotlinSMSAPIApp.git
```

2. Change the value of validAuthToken on **MainActivity.kt** By default it is set to **123456789** This is not a secure password so you will want to change it to a secure password. Currently this value is on line **73**

```kotlin
val validAuthToken = "123456789"
```

3. Build the **.apk** and install it on your phone. 

For instructions on how to build an apk on Android studio, view [**this document**](https://developer.android.com/studio/run)


4. Run the app on your Android decive and accept the requested permissions.

5. Currently, the App only works if it is open so do not fully close it.

6. Your app is now setup. Read the docs below for how to send a **POST** request to send an SMS. 

## How to send POST request

Make sure to view [this repository](https://github.com/nolanwinsman/AndroidTextAPI) for instructions and example code on sending an SMS to your phone from your computer.

That repository is designed for automating texts with crontab or another scheduler. 


## TODO

- Make app run in background

## Contact

Nolan Winsman - [@Github](https://github.com/nolanwinsman) - nolanwinsman@gmail.com

Project Link: [https://github.com/nolanwinsman/KotlinSMSAPIApp.git](https://github.com/nolanwinsman/KotlinSMSAPIApp.git)

## Contributers
- nolanwinsman

## Files

- /KotlinSMSAPIApp/app/src/main/java/com/example/birthdayscheduler/MainActivity.kt : The main file for this program. This is where you should make any changes
- README.md : this file

## Todo
- App Home Screen displays more info. Info like Port number, Auth token
- Ability to set Auth token on App
- Add some kind of logging