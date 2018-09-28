package io.blockv.example.feature.inventory;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import io.blockv.common.model.Vatom;
import io.blockv.example.R;
import io.blockv.face.client.FaceManager;
import io.blockv.face.client.VatomView;

public class InventoryViewHolder extends RecyclerView.ViewHolder {

  private Vatom vatom;
  final VatomView vatomView;
  final FaceManager faceManager;
  final OnClickListener listener;

  public InventoryViewHolder(View itemView,
                             FaceManager faceManager,
                             OnClickListener listener) {
    super(itemView);
    this.faceManager = faceManager;
    this.vatomView = itemView.findViewById(R.id.vatom_view);
    this.listener = listener;
  }

  public Vatom getVatom() {
    return vatom;
  }

  public void setVatom(Vatom vatom) {
    this.vatom = vatom;
    faceManager
      .load(vatom)
      .setLoaderDelay(300)
      .into(vatomView)
      .call(success -> {

      },throwable->{

      });
    vatomView.setOnClickListener(view -> listener.onClick(view, vatom.getId()));
  }

  interface OnClickListener {
    void onClick(View view, String vatomId);
  }
}
