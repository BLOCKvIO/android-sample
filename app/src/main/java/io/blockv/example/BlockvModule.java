package io.blockv.example;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import io.blockv.core.client.Blockv;
import io.blockv.core.client.manager.*;

import javax.inject.Singleton;

/**
 * Dagger Module wrapping the Blockv SDK
 */
@Module
public class BlockvModule {

  private Context context;

  public BlockvModule(Context context) {
    this.context = context;
  }

  @Singleton
  @Provides
  public Blockv provideBlockv() {
    return new Blockv(context, "<replace-with-app-id>");//creates the blockv singleton
  }

  @Singleton
  @Provides
  public UserManager provideUserManager(Blockv blockv) {
    return blockv.getUserManager();
  }

  @Singleton
  @Provides
  public VatomManager provideVatomManager(Blockv blockv) {
    return blockv.getVatomManager();
  }

  @Singleton
  @Provides
  public EventManager provideEventManager(Blockv blockv) {
    return blockv.getEventManager();
  }

  @Singleton
  @Provides
  public ResourceManager provideResourceManager(Blockv blockv) {
    return blockv.getResourceManager();
  }

  @Singleton
  @Provides
  public ActivityManager provideActivityManager(Blockv blockv) {
    return blockv.getActivityManager();
  }

}
