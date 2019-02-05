package io.blockv.example.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import io.blockv.common.model.Face;
import io.blockv.common.model.Resource;
import io.blockv.common.model.Vatom;
import io.blockv.example.R;
import io.blockv.face.client.FaceBridge;
import io.blockv.face.client.FaceView;
import io.blockv.face.client.ViewFactory;
import io.reactivex.android.schedulers.AndroidSchedulers;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.List;

public class ImageFaceView extends FaceView {

  private ImageView imageView;

  //Factory to emit this custom faceview, this is used to register the faceview.
  public final static ViewFactory factory = new ViewFactory() {
    @NotNull
    @Override
    public String getDisplayUrl() {
      return "native://image"; //display url for this face
    }

    @NotNull
    @Override
    public FaceView emit(Vatom vatom, Face face, FaceBridge faceBridge) {
      return new ImageFaceView(vatom, face, faceBridge);
    }
  };

  public ImageFaceView(Vatom vatom, Face face, FaceBridge bridge) {
    super(vatom, face, bridge);
  }

  @NotNull
  @Override
  public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
    imageView = (ImageView) layoutInflater.inflate(R.layout.view_custom_image_face, viewGroup, false);
    return imageView;
  }

  @Override
  public void onLoad(LoadHandler handler) {

    Config config = new Config(getFace());

    if (config.scale.equalsIgnoreCase("fill")) {
      imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
    } else {
      imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
    }

    Resource resource = getVatom().getProperty().getResource(config.image);

    if (resource != null) {
      //add your Cancelables to the handler, this allows them to be cancel when loading is canceled.
      handler.collect(
        getBridge()
          .getResourceManager()
          .getBitmap(resource, imageView.getWidth(), imageView.getHeight())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(image -> {
            imageView.setImageBitmap(image);
            handler.onComplete();//call once load completes successfully
          }, handler::onError));

    } else
      handler.onError(new

        NullPointerException("No image resource found"));
  }


  /**
   * Class to handle accessing properties we want from the face config
   **/

  class Config {

    final String image;
    final String scale;

    public Config(Face face) {

      JSONObject config = face.getProperty().getConfig();
      List<String> resources = face.getProperty().getResources();

      image = config.optString("image", (resources.size() > 0 ? resources.get(0) : "ActivatedImage"));
      scale = config.optString("scale", "fit");

    }
  }
}
