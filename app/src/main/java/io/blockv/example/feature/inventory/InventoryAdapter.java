package io.blockv.example.feature.inventory;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import io.blockv.common.model.Vatom;
import io.blockv.core.client.manager.VatomManager;
import io.blockv.example.R;
import io.blockv.face.client.FaceManager;

import java.util.ArrayList;
import java.util.List;


public class InventoryAdapter extends RecyclerView.Adapter<InventoryViewHolder> implements InventoryViewHolder.OnClickListener {

  List<Vatom> items = new ArrayList<Vatom>();

  final VatomManager vatomManager;

  final FaceManager faceManager;

  private OnItemClickListener listener = null;

  public InventoryAdapter(VatomManager vatomManager,
                          FaceManager faceManager) {
    this.vatomManager = vatomManager;
    this.faceManager = faceManager;
  }

  @Override
  public InventoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new InventoryViewHolder(
      LayoutInflater.from(parent.getContext()).inflate(R.layout.view_vatom_list_item, parent, false),
      faceManager,
      this);
  }

  @Override
  public void onBindViewHolder(InventoryViewHolder holder, int position) {
    holder.setVatom(getItem(position))
      .subscribe(() -> {
      }, throwable -> {
      });
  }

  @Override
  public int getItemCount() {
    return items.size();
  }

  @Override
  public int getItemViewType(int position) {
    //recycle views based on vatom template variation id
    return items.get(position).getProperty().getTemplateVariationId().hashCode();
  }


  public Vatom getItem(int pos) {
    return items.get(pos);
  }

  public void setItems(List<Vatom> items) {
    this.items = items;
    notifyDataSetChanged();
  }

  public synchronized void setItemClickListener(OnItemClickListener listener) {
    this.listener = listener;
  }

  @Override
  public synchronized void onClick(View view, String vatomId) {
    if (listener != null) {
      listener.onClick(view, vatomId);
    }
  }

  interface OnItemClickListener {
    void onClick(View view, String vatomId);
  }

}
