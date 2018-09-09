package io.blockv.example.feature.inventory;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.squareup.picasso.Picasso;
import io.blockv.common.model.Vatom;
import io.blockv.core.client.manager.ResourceManager;
import io.blockv.core.client.manager.VatomManager;
import io.blockv.example.R;

import java.util.ArrayList;
import java.util.List;


public class InventoryAdapter extends RecyclerView.Adapter<InventoryViewHolder> implements InventoryViewHolder.OnClickListener {

  List<Vatom> items = new ArrayList<Vatom>();

  final Picasso picasso;

  final VatomManager vatomManager;

  final ResourceManager resourceManager;

  private OnItemClickListener listener = null;

  public InventoryAdapter(VatomManager vatomManager,
                          ResourceManager resourceManager,
                          Picasso picasso) {
    this.picasso = picasso;
    this.vatomManager = vatomManager;
    this.resourceManager = resourceManager;
  }

  @Override
  public InventoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new InventoryViewHolder(
      LayoutInflater.from(parent.getContext()).inflate(R.layout.view_vatom_list_item, parent, false),
      vatomManager,
      resourceManager,
      picasso,
      this);
  }

  @Override
  public void onBindViewHolder(InventoryViewHolder holder, int position) {
    holder.setVatom(getItem(position));
  }

  @Override
  public int getItemCount() {
    return items.size();
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
