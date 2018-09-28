Blockv Android Example
======================

Android example application demoing features of the Blockv platform

<h2>Contents</h2>

This example demos the following features of the BLOCKv Android Sdk

* **Checking if a user is logged in**. [Code](./app/src/main/java/io/blockv/example/feature/landing/LandingPresenterImpl.java#L17)

* **Logging in via email**. [Code](./app/src/main/java/io/blockv/example/feature/login/email/LoginEmailPresenterImpl.java#L22) [Docs](https://developer.blockv.io/docs/reference/v1/android/user/login)

* **Logging in via phone number**. [Code](./app/src/main/java/io/blockv/example/feature/login/phone/LoginPhonePresenterImpl.java#L21) [Docs](https://developer.blockv.io/docs/reference/v1/android/user/login)

* **Resting password using email**. [Code](./app/src/main/java/io/blockv/example/feature/login/email/LoginEmailPresenterImpl.java#L41) [Docs](https://developer.blockv.io/docs/reference/v1/android/user/reset-token)

* **Resting password using phone number**. [Code](./app/src/main/java/io/blockv/example/feature/login/phone/LoginPhonePresenterImpl.java#L39) [Docs](https://developer.blockv.io/docs/reference/v1/android/user/reset-token)

* **Registration**. [Code](./app/src/main/java/io/blockv/example/feature/register/RegisterPresenterImpl.java#L29) [Docs](https://developer.blockv.io/docs/reference/v1/android/user/register)

* **Verifying email address**. [Code](./app/src/main/java/io/blockv/example/feature/verify/email/VerifyEmailPresenterImpl.java#L30) [Docs](https://developer.blockv.io/docs/reference/v1/android/user/verify-token)

* **Verifying phone number**. [Code](./app/src/main/java/io/blockv/example/feature/verify/phone/VerifyPhonePresenterImpl.java#L31) [Docs](https://developer.blockv.io/docs/reference/v1/android/user/verify-token)

* **Resending email verification**. [Code](./app/src/main/java/io/blockv/example/feature/verify/email/VerifyEmailPresenterImpl.java#L46) [Docs](https://developer.blockv.io/docs/reference/v1/android/user/reset-user-token-verification)

* **Resending phone number verification**. [Code](./app/src/main/java/io/blockv/example/feature/verify/phone/VerifyPhonePresenterImpl.java#L47) [Docs](https://developer.blockv.io/docs/reference/v1/android/user/reset-user-token-verification)

* **Fetching current user's profile**. [Code](./app/src/main/java/io/blockv/example/feature/profile/ProfilePresenterImpl.java#L46) [Docs](https://developer.blockv.io/docs/reference/v1/android/user/get-profile)

* **Fetching the user's Tokens**. [Code](./app/src/main/java/io/blockv/example/feature/profile/ProfilePresenterImpl.java#L57) [Docs](https://developer.blockv.io/docs/reference/v1/android/user/get-user-tokens)

* **Updating current user's profile**. [Code](./app/src/main/java/io/blockv/example/feature/profile/ProfilePresenterImpl.java#L93) [Docs](https://developer.blockv.io/docs/reference/v1/android/user/update-profile)

* **Uploading a avatar**. [Code](./app/src/main/java/io/blockv/example/feature/profile/ProfilePresenterImpl.java#L161) [Docs](https://developer.blockv.io/docs/reference/v1/android/user/upload-avatar)

* **Logging out**. [Code](./app/src/main/java/io/blockv/example/feature/profile/ProfilePresenterImpl.java#L129) [Docs](https://developer.blockv.io/docs/reference/v1/android/user/logout)

* **Fetching user inventory**. [Code](./app/src/main/java/io/blockv/example/feature/inventory/InventoryPresenterImpl.java#L59) [Docs](https://developer.blockv.io/docs/reference/v1/android/user/vatom/inventory)

* **Fetching vAtoms by id**. [Code](./app/src/main/java/io/blockv/example/feature/activated/ActivatedPresenterImpl.java#L33) [Docs](https://developer.blockv.io/docs/reference/v1/android/user/vatom/get-by-id)

* **Adding VatomView to a layout**. [Code](./app/src/main/res/layout/activity_vatom_activated.xml#L53)

* **Loading a VatomView**. [Code](./app/src/main/java/io/blockv/example/feature/activated/ActivatedScreenImpl.java#L52)

* **Customising a VatomView loader**. [Code](./app/src/main/java/io/blockv/example/feature/activated/ActivatedScreenImpl.java#L66)

* **Customising a VatomView error**. [Code](./app/src/main/java/io/blockv/example/feature/activated/ActivatedScreenImpl.java#L67)

* **Customising the face selection procedure**. [Code](./app/src/main/java/io/blockv/example/feature/activated/ActivatedScreenImpl.java#L82)

* **Using a VatomView in a RecyclerView**.  [Code Part 1](./app/src/main/java/io/blockv/example/feature/inventory/InventoryAdapter.java#L55) [Code Part 2](./app/src/main/java/io/blockv/example/feature/inventory/InventoryViewHolder.java#L36)

* **Creating a custom FaceView**. [Code](./app/src/main/java/io/blockv/example/utils/ImageFaceView.java)

* **Registering a FaceView**. [Code](./app/src/main/java/io/blockv/example/BlockvModule.java#L30)


<h3>Building using Android Studio...</h3>

1. Open Android Studio and select *Open an existing Android Studio project*
1. Select the **blockv-android-example** directory.

<h3>Modify IDs, compile and run</h3>

To set up the example:

1. Change [*replace-with-your-app-id*](./app/src/main/java/io/blockv/example/BlockvModule.java#L28) in the BlockvModule.java file to your own **App ID**. See [FAQ](https://developer.blockv.io/docs/faq)
1. Compile and run.

<h3>Building</h3>
To build the sample after you have applied the changes above, use the build/run option in Android Studio.

<h3>Dependencies</h3>

1. [Dagger 2](https://github.com/google/dagger) for singleton management
