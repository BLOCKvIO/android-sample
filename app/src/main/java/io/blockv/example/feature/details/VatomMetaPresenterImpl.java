package io.blockv.example.feature.details;

import android.content.Intent;
import android.text.TextUtils;
import android.view.MenuItem;
import io.blockv.example.R;
import io.blockv.example.constants.Extras;
import io.blockv.example.feature.BasePresenter;


public class VatomMetaPresenterImpl extends BasePresenter implements VatomMetaPresenter {

  private final VatomMetaScreen screen;

  public VatomMetaPresenterImpl(VatomMetaScreen screen) {
    this.screen = screen;
  }

  @Override
  public void onCreate(Intent intent) {

    String vatomId =intent.getExtras().getString(Extras.VATOM_ID);
    if(TextUtils.isEmpty(vatomId))
    {
      screen.showToast(getString(R.string.vatom_details_page_no_vatom));
      screen.finish();
    }

    screen.showDialog(getString(R.string.vatom_details_page_loading));
    //get vatom by id
    collect(vatomManager
      .getVatoms(vatomId)
      .subscribe(vatoms->{
        screen.hideDialog();
        if (vatoms != null && vatoms.size() > 0) {
          screen.setVatom(vatoms.get(0));
        }
      },throwable->{
        screen.hideDialog();
        screen.showToast(throwable.getMessage());
        screen.finish();
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

}


