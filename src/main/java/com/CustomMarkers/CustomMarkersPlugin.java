package com.CustomMarkers;

import com.CustomMarkers.View.MarkerConfigView;
import com.RxRunelite.RxPlugin;
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
	private ClientToolbar toolbar;

	@Provides
	static CustomMarkersConfig provideConfig( ConfigManager configManager )
	{
		return configManager.getConfig( CustomMarkersConfig.class );
	}

	@Override
	public void configure( Binder binder )
	{
		RxPlugin.configure( binder );
	}

	@Override
	protected void startUp()
	{
		disposable = new CompositeDisposable();

		this.panel = new MarkerConfigView();
		// @todo: must have an icon
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
