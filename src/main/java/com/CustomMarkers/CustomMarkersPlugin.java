package com.CustomMarkers;

import com.CustomMarkers.View.MarkerConfigView;
import com.PluginRx.IPluginSchedulers;
import com.PluginRx.PluginRx;
import com.google.inject.Binder;
import com.google.inject.Inject;
import com.google.inject.Provides;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;

@Slf4j
@PluginDescriptor(
	name = "Custom Markers"
)
public class CustomMarkersPlugin
	extends Plugin
{
	private CompositeDisposable disposable;

	private MarkerConfigView panel;

	private NavigationButton navButton;

	@Inject
	private IPluginSchedulers schedulers;

	@Inject
	private ClientToolbar toolbar;

	@Provides
	static CustomMarkersConfig provideConfig( ConfigManager configManager )
	{
		return configManager.getConfig( CustomMarkersConfig.class );
	}

	@Override
	public void configure( Binder binder )
	{
		PluginRx.configure( binder );
	}

	@Override
	protected void startUp()
	{
		disposable = new CompositeDisposable();

		this.panel = new MarkerConfigView(schedulers);
		this.navButton = NavigationButton.builder()
			.tooltip( "Custom Markers" )
			.priority( 1 )
			.panel( this.panel )
			.build();
		this.toolbar.addNavigation( this.navButton );
	}

	@Override
	protected void shutDown()
	{
		disposable.dispose();
		disposable = null;
	}
}
