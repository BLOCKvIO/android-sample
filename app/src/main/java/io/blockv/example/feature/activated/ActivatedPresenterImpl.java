package io.blockv.example.feature.activated;

import android.content.Intent;
import android.text.TextUtils;
import android.view.MenuItem;
import io.blockv.example.R;
import io.blockv.example.constants.Extras;
import io.blockv.example.feature.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import timber.log.Timber;


public class ActivatedPresenterImpl extends BasePresenter implements ActivatedPresenter {

  private final ActivatedScreen screen;

  private String vatomId = "";

  public ActivatedPresenterImpl(ActivatedScreen screen) {
    this.screen = screen;
  }

  @Override
  public void onCreate(Intent intent) {

    vatomId = intent.getExtras().getString(Extras.VATOM_ID);
    if (TextUtils.isEmpty(vatomId)) {
      screen.showToast(getString(R.string.vatom_page_no_vatom));
      screen.finish();
    }

    //get vatom by id
    collect(
      vatomManager.getVatoms(vatomId)
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe(val -> screen.showDialog(getString(R.string.vatom_page_loading)))
        .doFinally(screen::hideDialog)
        .filter(vatoms -> vatoms.size() > 0)
        .map(vatoms -> vatoms.get(0))
        .toFlowable()
        .doOnError(throwable -> {
          screen.showToast(throwable.getMessage());
          screen.finish();
        })
        .flatMap(screen::setVatom)
        .subscribe(vatom -> {
        }, Timber::e));
  }

  @Override
  public void onDestroy() {
    dispose();
  }


  @Override
  public void onOptionsItemSelected(MenuItem menuItem) {
    if (menuItem.getItemId() == android.R.id.home) {
      screen.finish();
    } else if (menuItem.getItemId() == R.id.details) {
      if (vatomId != null && vatomId.length() > 0) {
        screen.startVatomDetailsActivity(vatomId);
      }
    }

  }

}


