package io.blockv.example.feature.details;

import android.widget.ListView;
import android.widget.SimpleAdapter;
import io.blockv.common.model.Vatom;
import io.blockv.example.R;
import io.blockv.example.feature.BaseScreen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VatomMetaScreenImpl extends BaseScreen implements VatomMetaScreen {


  final android.support.v7.widget.Toolbar toolbar;
  final ListView details;


  public VatomMetaScreenImpl(VatomMetaActivity activity) {
    super(activity);

    toolbar = activity.findViewById(R.id.toolbar);
    details = activity.findViewById(R.id.details);
    activity.setSupportActionBar(toolbar);
    if (activity.getSupportActionBar() != null) {
      activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

  }

  @Override
  public void registerEvents(VatomMetaPresenter presenter) {

  }

  @Override
  public void setVatom(Vatom vatom) {

    List<Map<String, String>> detail = new ArrayList<>();
    String[] from = {"title", "content"};
    int[] to = {R.id.title, R.id.content};

    Map<String, String> map = new HashMap<>();
    map.put("title", "Vatom Id");
    map.put("content", vatom.getId());
    detail.add(map);
    map = new HashMap<>();
    map.put("title", "Created Date");
    map.put("content", vatom.getWhenCreated());
    detail.add(map);
    map = new HashMap<>();
    map.put("title", "Modified Date");
    map.put("content", vatom.getWhenModified());
    detail.add(map);
    map = new HashMap<>();
    map.put("title", "Template Id");
    map.put("content", vatom.getProperty().getTemplateId());
    detail.add(map);
    map = new HashMap<>();
    map.put("title", "Template Variation Id");
    map.put("content", vatom.getProperty().getTemplateVariationId());
    detail.add(map);
    map = new HashMap<>();
    map.put("title", "Title");
    map.put("content", vatom.getProperty().getTitle());
    detail.add(map);
    map = new HashMap<>();
    map.put("title", "Description");
    map.put("content", vatom.getProperty().getDescription());
    detail.add(map);
    map = new HashMap<>();
    map.put("title", "Author");
    map.put("content", vatom.getProperty().getAuthor());
    detail.add(map);
    map = new HashMap<>();
    map.put("title", "Category");
    map.put("content", vatom.getProperty().getCategory());
    detail.add(map);
    map = new HashMap<>();
    map.put("title", "Acquirable");
    map.put("content", ""+vatom.getProperty().isAcquireable());
    detail.add(map);
    map = new HashMap<>();
    map.put("title", "Redeemable");
    map.put("content", ""+vatom.getProperty().isRedeemable());
    detail.add(map);
    map = new HashMap<>();
    map.put("title", "Tradeable");
    map.put("content", ""+vatom.getProperty().isTradeable());
    detail.add(map);
    map = new HashMap<>();
    map.put("title", "Transferable");
    map.put("content", ""+vatom.getProperty().isTransferable());
    detail.add(map);
    map = new HashMap<>();
    map.put("title", "Dropped");
    map.put("content", ""+vatom.getProperty().isDropped());
    detail.add(map);
    map = new HashMap<>();
    map.put("title", "In Contract");
    map.put("content", ""+vatom.getProperty().isInContract());
    detail.add(map);



    details.setAdapter(new SimpleAdapter(activity, detail, R.layout.view_property_list_item, from, to));


  }
}

