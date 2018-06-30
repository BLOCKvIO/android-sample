Blockv Android Example
======================

Android example application demoing features of the Blockv platform

<h2>Contents</h2>

This example demos the following features of the BLOCKv Android Sdk

* **Checking if a user is logged in**. [Code](https://github.com/BLOCKvIO/android-example/blob/master/app/src/main/java/io/blockv/example/feature/landing/LandingPresenterImpl.java#L17)

* **Logging in via email**. [Code](https://github.com/BLOCKvIO/android-example/blob/master/app/src/main/java/io/blockv/example/feature/login/email/LoginEmailPresenterImpl.java#L22) [Docs](https://developer.blockv.io/docs/reference/v1/android/user/login)

* **Logging in via phone number**. [Code](https://github.com/BLOCKvIO/android-example/blob/master/app/src/main/java/io/blockv/example/feature/login/phone/LoginPhonePresenterImpl.java#L21) [Docs](https://developer.blockv.io/docs/reference/v1/android/user/login)

* **Resting password using email**. [Code](https://github.com/BLOCKvIO/android-example/blob/master/app/src/main/java/io/blockv/example/feature/login/email/LoginEmailPresenterImpl.java#L42) [Docs](https://developer.blockv.io/docs/reference/v1/android/user/reset-token)

* **Resting password using phone number**. [Code](https://github.com/BLOCKvIO/android-example/blob/master/app/src/main/java/io/blockv/example/feature/login/phone/LoginPhonePresenterImpl.java#L38) [Docs](https://developer.blockv.io/docs/reference/v1/android/user/reset-token)

* **Registration**. [Code](https://github.com/BLOCKvIO/android-example/blob/master/app/src/main/java/io/blockv/example/feature/register/RegisterPresenterImpl.java#L28) [Docs](https://developer.blockv.io/docs/reference/v1/android/user/register)

* **Verifying email address**. [Code](https://github.com/BLOCKvIO/android-example/blob/master/app/src/main/java/io/blockv/example/feature/verify/email/VerifyEmailPresenterImpl.java#L29) [Docs](https://developer.blockv.io/docs/reference/v1/android/user/verify-token)

* **Verifying phone number**. [Code](https://github.com/BLOCKvIO/android-example/blob/master/app/src/main/java/io/blockv/example/feature/verify/phone/VerifyPhonePresenterImpl.java#L31) [Docs](https://developer.blockv.io/docs/reference/v1/android/user/verify-token)

* **Resending email verification**. [Code](https://github.com/BLOCKvIO/android-example/blob/master/app/src/main/java/io/blockv/example/feature/verify/email/VerifyEmailPresenterImpl.java#L46) [Docs](https://developer.blockv.io/docs/reference/v1/android/user/reset-user-token-verification)

* **Resending phone number verification**. [Code](https://github.com/BLOCKvIO/android-example/blob/master/app/src/main/java/io/blockv/example/feature/verify/phone/VerifyPhonePresenterImpl.java#L47) [Docs](https://developer.blockv.io/docs/reference/v1/android/user/reset-user-token-verification)

* **Fetching current user's profile**. [Code](https://github.com/BLOCKvIO/android-example/blob/master/app/src/main/java/io/blockv/example/feature/profile/ProfilePresenterImpl.java#L43) [Docs](https://developer.blockv.io/docs/reference/v1/android/user/get-profile)

* **Fetching the user's Tokens**. [Code](https://github.com/BLOCKvIO/android-example/blob/master/app/src/main/java/io/blockv/example/feature/profile/ProfilePresenterImpl.java#L55)

* **Updating current user's profile**. [Code](https://github.com/BLOCKvIO/android-example/blob/master/app/src/main/java/io/blockv/example/feature/profile/ProfilePresenterImpl.java#L91) [Docs](https://developer.blockv.io/docs/reference/v1/android/user/update-profile)

* **Uploading a avatar**. [Code](https://github.com/BLOCKvIO/android-example/blob/master/app/src/main/java/io/blockv/example/feature/profile/ProfilePresenterImpl.java#L157)

* **Logging out**. [Code](https://github.com/BLOCKvIO/android-example/blob/master/app/src/main/java/io/blockv/example/feature/profile/ProfilePresenterImpl.java#L129) [Docs](https://developer.blockv.io/docs/reference/v1/android/user/logout)

* **Fetching user inventory**. [Code](https://github.com/BLOCKvIO/android-example/blob/master/app/src/main/java/io/blockv/example/feature/inventory/InventoryPresenterImpl.java#L25)

* **Fetching vAtoms by id**. [Code](https://github.com/BLOCKvIO/android-example/blob/master/app/src/main/java/io/blockv/example/feature/activated/VatomPresenterImpl.java#L31)

<h3>Building using Android Studio...</h3>

1. Open Android Studio and select *Open an existing Android Studio project*
1. Select the **blockv-android-example** directory.

<h3>Modify IDs, compile and run</h3>

To set up the example:

1. Change [*replace-with-your-app-id*](https://github.com/BLOCKvIO/android-example/blob/master/app/src/main/java/io/blockv/example/BlockvModule.java#L27) in the BlockvModule.java file to your own **App ID**. See [FAQ](https://developer.blockv.io/docs/faq)
1. Compile and run.

<h3>Building</h3>
To build the sample after you have applied the changes above, use the build/run option in Android Studio.

<h3>Dependencies</h3>

1. [Dagger 2](https://github.com/google/dagger) for singleton management
