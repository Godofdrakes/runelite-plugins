package com.AreaMarkers;

import com.PluginRx.PluginRx;
import com.google.inject.Binder;
import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = "Area Markers"
)
public class AreaMarkersPlugin
	extends Plugin
{
	@Provides
	static AreaMarkersConfig provideConfig( ConfigManager configManager )
	{
		return configManager.getConfig( AreaMarkersConfig.class );
	}

	@Override
	public void configure( Binder binder )
	{
		PluginRx.configure( binder );
	}

	@Override
	protected void startUp()
	{

	}

	@Override
	protected void shutDown()
	{

	}
}
