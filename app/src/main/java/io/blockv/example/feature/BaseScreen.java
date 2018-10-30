package io.blockv.example.feature;

import android.app.Dialog;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import io.blockv.core.client.manager.EventManager;
import io.blockv.core.client.manager.ResourceManager;
import io.blockv.core.client.manager.VatomManager;
import io.blockv.example.Injector;
import io.blockv.example.R;
import io.blockv.face.client.FaceManager;

import javax.inject.Inject;

public class BaseScreen {

  protected BaseActivity activity;

  protected Fragment fragment;

  @Inject
  protected Picasso picasso;

  @Inject
  protected VatomManager vatomManager;

  @Inject
  protected ResourceManager resourceManager;

  @Inject
  protected FaceManager faceManager;

  @Inject
  protected EventManager eventManager;

  private volatile Dialog progress;

  public BaseScreen(BaseActivity activity) {
    this.activity = activity;
    Injector.$.inject(this);
  }

  public BaseScreen(Fragment fragment) {
    this.fragment = fragment;
    this.activity = (BaseActivity) fragment.getActivity();
    Injector.$.inject(this);
  }


  public void showToast(String message) {
    Toast.makeText(activity.getApplicationContext(), message, Toast.LENGTH_LONG).show();
  }


  public void showDialog(String text) {
    if (progress != null) {
      progress.dismiss();
    }

    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
    View view = activity.getLayoutInflater().inflate(R.layout.view_progress_dialog, null);
    TextView textView = view.findViewById(R.id.loading_msg);
    textView.setText(text);
    Dialog dialog =
      builder
        .setView(view)
        .create();
    progress = dialog;
    dialog.show();
  }

  public void hideDialog() {
    if (progress != null) {
      progress.dismiss();
    }
  }

  public void finish() {
    activity.finish();
  }

  public void goBack() {
    activity.onBackPressed();
  }
}
