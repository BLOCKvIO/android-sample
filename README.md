Blockv Android Example
======================

Android example application demoing features of the Blockv platform

<h2>Contents</h2>

This example demos the following features of the BLOCKv Android Sdk

* **Checking if a user is logged in**. [Code](./app/src/main/java/io/blockv/example/feature/landing/LandingPresenterImpl.java#L18)

* **Logging in via email**. [Code](./app/src/main/java/io/blockv/example/feature/login/email/LoginEmailPresenterImpl.java#L22) [Docs](https://developer.blockv.io/docs/reference/v1/android/user/login)

* **Logging in via phone number**. [Code](./app/src/main/java/io/blockv/example/feature/login/phone/LoginPhonePresenterImpl.java#L21) [Docs](https://developer.blockv.io/docs/reference/v1/android/user/login)

* **Resting password using email**. [Code](./app/src/main/java/io/blockv/example/feature/login/email/LoginEmailPresenterImpl.java#L40) [Docs](https://developer.blockv.io/docs/reference/v1/android/user/reset-token)

* **Resting password using phone number**. [Code](./app/src/main/java/io/blockv/example/feature/login/phone/LoginPhonePresenterImpl.java#L38) [Docs](https://developer.blockv.io/docs/reference/v1/android/user/reset-token)

* **Registration**. [Code](./app/src/main/java/io/blockv/example/feature/register/RegisterPresenterImpl.java#L27) [Docs](https://developer.blockv.io/docs/reference/v1/android/user/register)

* **Verifying email address**. [Code](./app/src/main/java/io/blockv/example/feature/verify/email/VerifyEmailPresenterImpl.java#L30) [Docs](https://developer.blockv.io/docs/reference/v1/android/user/verify-token)

* **Verifying phone number**. [Code](./app/src/main/java/io/blockv/example/feature/verify/phone/VerifyPhonePresenterImpl.java#L29) [Docs](https://developer.blockv.io/docs/reference/v1/android/user/verify-token)

* **Resending email verification**. [Code](./app/src/main/java/io/blockv/example/feature/verify/email/VerifyEmailPresenterImpl.java#L46) [Docs](https://developer.blockv.io/docs/reference/v1/android/user/reset-user-token-verification)

* **Resending phone number verification**. [Code](./app/src/main/java/io/blockv/example/feature/verify/phone/VerifyPhonePresenterImpl.java#L45) [Docs](https://developer.blockv.io/docs/reference/v1/android/user/reset-user-token-verification)

* **Fetching current user's profile**. [Code](./app/src/main/java/io/blockv/example/feature/profile/ProfilePresenterImpl.java#L47) [Docs](https://developer.blockv.io/docs/reference/v1/android/user/get-profile)

* **Fetching the user's Tokens**. [Code](./app/src/main/java/io/blockv/example/feature/profile/ProfilePresenterImpl.java#L52) [Docs](https://developer.blockv.io/docs/reference/v1/android/user/get-user-tokens)

* **Updating current user's profile**. [Code](./app/src/main/java/io/blockv/example/feature/profile/ProfilePresenterImpl.java#L87) [Docs](https://developer.blockv.io/docs/reference/v1/android/user/update-profile)

* **Uploading a avatar**. [Code](./app/src/main/java/io/blockv/example/feature/profile/ProfilePresenterImpl.java#L157) [Docs](https://developer.blockv.io/docs/reference/v1/android/user/upload-avatar)

* **Logging out**. [Code](./app/src/main/java/io/blockv/example/feature/profile/ProfilePresenterImpl.java#L127) [Docs](https://developer.blockv.io/docs/reference/v1/android/user/logout)

* **Fetching user inventory**. [Code](./app/src/main/java/io/blockv/example/feature/inventory/InventoryPresenterImpl.java#L61) [Docs](https://developer.blockv.io/docs/reference/v1/android/user/vatom/inventory)

* **Fetching vAtoms by id**. [Code](./app/src/main/java/io/blockv/example/feature/activated/ActivatedPresenterImpl.java#L34) [Docs](https://developer.blockv.io/docs/reference/v1/android/user/vatom/get-by-id)

* **Adding VatomView to a layout**. [Code](./app/src/main/res/layout/activity_vatom_activated.xml#L53)

* **Loading a VatomView**. [Code](./app/src/main/java/io/blockv/example/support/LiveVatomView.java#L84)

* **Customising a VatomView loader**. [Code](./app/src/main/java/io/blockv/example/support/LiveVatomView.java#L86)

* **Customising a VatomView error**. [Code](./app/src/main/java/io/blockv/example/support/LiveVatomView.java#L87)

* **Using a VatomView in a RecyclerView**.  [Code Part 1](./app/src/main/java/io/blockv/example/feature/inventory/InventoryAdapter.java#L56) [Code Part 2](./app/src/main/java/io/blockv/example/feature/inventory/InventoryViewHolder.java#L36)

* **Creating a custom FaceView**. [Code](./app/src/main/java/io/blockv/example/utils/ImageFaceView.java)

* **Registering a FaceView**. [Code](./app/src/main/java/io/blockv/example/BlockvModule.java#L31)


<h3>Building using Android Studio...</h3>

1. Open Android Studio and select *Open an existing Android Studio project*
1. Select the **blockv-android-example** directory.

<h3>Modify IDs, compile and run</h3>

To set up the example:

1. Change [*replace-with-your-app-id*](./app/src/main/java/io/blockv/example/BlockvModule.java#L30) in the BlockvModule.java file to your own **App ID**. See [FAQ](https://developer.blockv.io/docs/faq)
1. Compile and run.

<h3>Building</h3>
To build the sample after you have applied the changes above, use the build/run option in Android Studio.

<h3>Dependencies</h3>

1. [Dagger 2](https://github.com/google/dagger) for singleton management
