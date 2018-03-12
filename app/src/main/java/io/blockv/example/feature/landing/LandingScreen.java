package io.blockv.example.feature.landing;

/**
 * LandingScreen handles screen layout and navigation for LandingActivity
 * @see LandingActivity
 */
public interface LandingScreen
{
  void registerEvents(LandingPresenter presenter);

  void startLoginActivity();

  void startRegisterActivity();

  void startInventoryActivity();
}
