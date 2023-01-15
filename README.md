# Kotlin SMS API App

This is the App version of my project to automate sending sms messages.

Once you install the **.apk** for this project, whenever the app is running on your Android Phone you are able to ping your phone with a POST request that contains a message, a number and an authentication token.

## Setup

1. Clone this repository

```sh
git clone https://github.com/nolanwinsman/KotlinSMSAPIApp.git
```

2. Change the value of validAuthToken on **MainActivity.kt** By default it is set to **123456789** This is not a secure password so you will want to change it to a secure password. Currently this value is on line **73**

```sh
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
