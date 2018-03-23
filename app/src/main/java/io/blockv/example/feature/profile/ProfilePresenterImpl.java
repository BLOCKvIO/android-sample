package io.blockv.example.feature.profile;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.support.media.ExifInterface;
import android.view.MenuItem;
import android.view.View;
import io.blockv.core.client.builder.UpdateUserBuilder;
import io.blockv.core.util.Observable;
import io.blockv.example.R;
import io.blockv.example.feature.BasePresenter;
import org.jetbrains.annotations.Nullable;
import timber.log.Timber;

import java.io.IOException;
import java.io.InputStream;


public class ProfilePresenterImpl extends BasePresenter implements ProfilePresenter {

  private static final int PHOTO_REQUEST_CODE = 1000;
  private final ProfileScreen screen;
  private final ContentResolver contentResolver;

  public ProfilePresenterImpl(ProfileScreen screen,
                              ContentResolver contentResolver) {
    this.screen = screen;
    this.contentResolver = contentResolver;

  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    reload();
  }

  void reload()
  {

    screen.showDialog(getString(R.string.profile_page_loading));
    //fetch the current logged in user's details
    collect(userManager
      .getCurrentUser()
      .subscribe(
        user -> {
          if (user != null) {
            screen.setUserId(user.getId());
            screen.setFirstName(user.getFirstName());
            screen.setLastName(user.getLastName());
            screen.setBirthday(user.getBirthday());
            screen.setAvatar(user.getAvatarUri());
            //fetch the current user's tokens
            collect(
              userManager.getCurrentUserTokens()
                .subscribe(tokens -> {
                  screen.setTokens(tokens);
                  screen.hideDialog();
                }, throwable -> {
                  screen.hideDialog();
                  screen.showToast(throwable.getMessage());
                }));
          }
        },
        throwable -> {
          screen.hideDialog();
          screen.showToast(throwable.getMessage());
        }));

  }
  @Override
  public void onDestroy() {
    dispose();
  }


  @Override
  public void onOptionsItemSelected(MenuItem menuItem) {
    if (menuItem.getItemId() == android.R.id.home) {
      screen.finish();
    }

  }

  @Override
  public void onSaveDetailsClick(View view, String firstName, String lastName, String birthday) {
    screen.showDialog(getString(R.string.profile_page_saving));
    //update the current user's details
    collect(userManager.updateCurrentUser(
      new UpdateUserBuilder()
        .setFirstName(firstName)
        .setLastName(lastName)
        .setBirthday(birthday)
        .build()
    ).subscribe(user -> {
      screen.hideDialog();
      screen.setUserId(user.getId());
      screen.setFirstName(user.getFirstName());
      screen.setLastName(user.getLastName());
      screen.setBirthday(user.getBirthday());
    }, throwable -> {
      screen.showToast(throwable.getMessage());
      screen.hideDialog();
    }));
  }

  @Override
  public void onSavePasswordClick(View view, String password) {
    screen.showDialog(getString(R.string.profile_page_saving));
    //update current user's password
    collect(userManager.updateCurrentUser(new UpdateUserBuilder()
      .setPassword(password)
      .build()
    ).subscribe(user -> screen.hideDialog(), throwable -> {
      screen.showToast(throwable.getMessage());
      screen.hideDialog();
    }));
  }

  @Override
  public void onLogOutClick(View view) {

    onDestroy();
    screen.showDialog(getString(R.string.profile_page_logging_out));

    //log the current user out
    userManager
      .logout()
      .subscribe(ok -> {
        screen.hideDialog();
        screen.restartApp();
      }, throwable -> {
        screen.showToast(throwable.getMessage());
        screen.hideDialog();
        screen.restartApp();
      });
  }

  @Override
  public void onAvatarClick(View view) {
    screen.startSelectPhotoActivity(PHOTO_REQUEST_CODE);
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    try {
      if (resultCode == Activity.RESULT_OK) {
        if (requestCode == PHOTO_REQUEST_CODE) {
          Uri selectedImage = data.getData();
          screen.showDialog(getString(R.string.profile_page_uploading));
          collect(
            loadAvatar(selectedImage)
              .subscribe(avatar -> {
                //update the current user's avatar
                collect(userManager.uploadAvatar(avatar)
                  .subscribe(
                    Void -> {
                      screen.hideDialog();
                      reload();

                    },
                    throwable -> {
                      screen.hideDialog();
                      screen.showToast(throwable.getMessage());
                    }));
              }, throwable -> {
                screen.hideDialog();
                screen.showToast(throwable.getMessage());
              }));
        }
      }
    } catch (Exception e) {
      Timber.e(e, e.getMessage());
    }
  }

  /**
   * Loads an image from file and scales it down and crops it
   * @param uri is the uri an image file
   */
  Observable<Bitmap> loadAvatar(Uri uri) {

    return new Observable<Bitmap>() {
      @Nullable
      @Override
      public Bitmap getResult() throws IOException {
        Matrix matrix = new Matrix();
        try {
          InputStream inputStream = contentResolver.openInputStream(uri);
          ExifInterface exif = new ExifInterface(inputStream);
          int rotation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
          int rotationInDegrees = rotation == ExifInterface.ORIENTATION_ROTATE_90 ?
            90 : rotation == ExifInterface.ORIENTATION_ROTATE_180 ?
            180 : rotation == ExifInterface.ORIENTATION_ROTATE_270 ?
            270 :
            0;
          if (rotation != 0f) {
            matrix.preRotate(rotationInDegrees);
          }
        } catch (Exception e) {
          Timber.e(e.getMessage());
        }

        InputStream input = contentResolver.openInputStream(uri);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(input, null, options);
        input.close();
        // Now load a subsampled image - that's no larger than the dimensions of the screen
        options.inJustDecodeBounds = false;
        options.inSampleSize = calculateInSampleSize(options, 500, 500);
        //should return the image but sampled down
        input = contentResolver.openInputStream(uri);
        Bitmap orignal = BitmapFactory.decodeStream(input, null, options);
        input.close();
        Bitmap out;
        if (orignal.getWidth() >= orignal.getHeight()) {
          out = Bitmap.createBitmap(
            orignal,
            orignal.getWidth() / 2 - orignal.getHeight() / 2,
            0,
            orignal.getHeight(),
            orignal.getHeight(),
            matrix,
            true
          );

        } else {
          out = Bitmap.createBitmap(
            orignal,
            0,
            orignal.getHeight() / 2 - orignal.getWidth() / 2,
            orignal.getWidth(),
            orignal.getWidth(),
            matrix,
            true
          );
        }

        return out;
      }
    };
  }

  public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
    int height = options.outHeight;
    int width = options.outWidth;
    int inSampleSize = 1;
    if (height > reqHeight || width > reqWidth) {
      int halfHeight = height / 2;

      for (int halfWidth = width / 2; halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth; inSampleSize *= 2) {
        ;
      }
    }

    return inSampleSize;
  }
}


