package io.blockv.example.feature.profile;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.*;
import com.jakewharton.processphoenix.ProcessPhoenix;
import io.blockv.common.internal.net.rest.auth.ResourceEncoder;
import io.blockv.common.model.Token;
import io.blockv.example.R;
import io.blockv.example.feature.BaseScreen;
import io.blockv.example.feature.landing.LandingActivity;
import timber.log.Timber;

import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;

public class ProfileScreenImpl extends BaseScreen implements ProfileScreen {

  final android.support.v7.widget.Toolbar toolbar;
  final ImageView avatar;
  final TextView userId;
  final EditText firstName;
  final EditText lastName;
  final EditText birthday;
  final EditText password;
  final Button saveDetails;
  final Button savePassword;
  final Button logout;
  final LinearLayout tokenList;
  final Handler handler;


  public ProfileScreenImpl(ProfileActivity activity) {
    super(activity);

    this.handler = new Handler(Looper.getMainLooper());
    toolbar = activity.findViewById(R.id.toolbar);
    activity.setSupportActionBar(toolbar);
    if (activity.getSupportActionBar() != null) {
      activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    avatar = activity.findViewById(R.id.avatar);
    firstName = activity.findViewById(R.id.first_name);
    lastName = activity.findViewById(R.id.last_name);
    birthday = activity.findViewById(R.id.birthday);
    password = activity.findViewById(R.id.password);
    saveDetails = activity.findViewById(R.id.save);
    savePassword = activity.findViewById(R.id.save_password);
    logout = activity.findViewById(R.id.logout);
    userId = activity.findViewById(R.id.user_id);
    tokenList = activity.findViewById(R.id.token_list);
  }

  @Override
  public void registerEvents(ProfilePresenter presenter) {
    saveDetails.setOnClickListener(v -> presenter.onSaveDetailsClick(v,
      firstName.getText().toString(),
      lastName.getText().toString(),
      birthday.getText().toString()
    ));
    savePassword.setOnClickListener(v -> presenter.onSavePasswordClick(v, password.getText().toString()));

    logout.setOnClickListener(presenter::onLogOutClick);
    avatar.setOnClickListener(presenter::onAvatarClick);
  }

  @Override
  public void setUserId(String id) {
    this.userId.setText(id);
  }

  @Override
  public void setFirstName(String firstName) {
    this.firstName.setText(firstName);
  }

  @Override
  public void setLastName(String lastName) {
    this.lastName.setText(lastName);
  }

  @Override
  public void setBirthday(String birthday) {
    this.birthday.setText(birthday);
  }

  @Override
  public void setAvatar(String url) {
    Timber.e(url);
    if (url != null) {
      try {
        //add asset provider credentials
        url = resourceManager.encodeUrl(url);
      } catch (ResourceEncoder.MissingAssetProviderException e) {
        Timber.w(e.getMessage());
      }
      picasso
        .load(url)
        .placeholder(R.drawable.ic_add_grey_back)
        .error(R.drawable.ic_add_grey_back)
        .into(avatar);
    }

  }

  @Override
  public void enableSaveDetails(boolean enable) {
    saveDetails.setEnabled(enable);
  }

  @Override
  public void enableSavePassword(boolean enable) {
    savePassword.setEnabled(enable);
  }

  @Override
  public void startSelectPhotoActivity(int code) {
    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    activity.startActivityForResult(intent, code);
  }


  @Override
  public void restartApp() {
    showToast(activity.getResources().getString(R.string.profile_page_restarting));
    handler.postDelayed(() -> {
      Intent landing = new Intent(activity, LandingActivity.class);
      landing.setFlags(FLAG_ACTIVITY_CLEAR_TASK);
      ProcessPhoenix.triggerRebirth(activity, landing);
    }, 1500);
  }

  @Override
  public void setTokens(List<Token> tokens) {
    tokenList.removeAllViews();
    for (Token token : tokens) {
      View view = activity.getLayoutInflater().inflate(R.layout.view_token_list_item, null);
      TextView tokenText = view.findViewById(R.id.token);
      tokenText.setText(token.getToken());
      tokenList.addView(view);
    }
  }
}

