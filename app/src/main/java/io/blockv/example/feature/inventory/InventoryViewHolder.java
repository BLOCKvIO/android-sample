package io.blockv.example.feature.inventory;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import io.blockv.core.client.manager.VatomManager;
import io.blockv.core.model.Resource;
import io.blockv.core.model.Vatom;
import io.blockv.example.R;
import io.blockv.example.feature.activated.VatomActivity;
import timber.log.Timber;

public class InventoryViewHolder extends RecyclerView.ViewHolder {

  private Vatom vatom;
  final ImageView imageView;
  final Picasso picasso;
  final VatomManager vatomManager;

  public InventoryViewHolder(View itemView, VatomManager vatomManager, Picasso picasso) {
    super(itemView);
    imageView = itemView.findViewById(R.id.image);
    this.picasso = picasso;
    this.vatomManager = vatomManager;
  }

  public Vatom getVatom() {
    return vatom;
  }

  public void setVatom(Vatom vatom) {
    this.vatom = vatom;
    //each vatom should contain an activated image resource
    Resource resource = vatom
      .getProperty()
      .getResource("ActivatedImage");

    if(resource!=null)
    {
      //load activated image
      picasso
        .load(resource.getUrl())
        .placeholder(android.R.drawable.progress_indeterminate_horizontal)
        .error(R.drawable.ic_error)
        .into(imageView);

      imageView.setOnClickListener(view-> view.getContext().startActivity(VatomActivity.getIntent(view.getContext(),vatom.getId())));
    }

  }
}
