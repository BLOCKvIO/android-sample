package io.blockv.example.feature.inventory;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import io.blockv.common.model.Face;
import io.blockv.common.model.Vatom;
import io.blockv.example.R;
import io.blockv.face.client.FaceManager;
import io.blockv.face.client.VatomView;
import io.reactivex.Completable;

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

  /** This FSP excludes "heavy" faces from the inventory view. */
  FaceManager.FaceSelectionProcedure noHeavyFaces = (vatom, set) -> {

    // Pick icon face
    Face face = FaceManager.EmbeddedProcedure.ICON.getProcedure().select(vatom, set);

    // Exclude heavy faces
    if (face != null && face.getProperty().getDisplayUrl().equalsIgnoreCase("native://generic-3d")) return null;

    // Not heavy, it's good
    return face;

  };

  public Completable setVatom(Vatom vatom) {
    this.vatom = vatom;
    vatomView.setOnClickListener(view -> listener.onClick(view, vatom.getId()));

    //load the vatomview
    return
      Completable.fromSingle(
        faceManager
          .load(vatom)
          .setFaceSelectionProcedure(noHeavyFaces)
          .setLoaderDelay(200)//use loader delay to prevent loaders flicking when scrolling fast
          .into(vatomView));

  }

  interface OnClickListener {
    void onClick(View view, String vatomId);
  }
}
