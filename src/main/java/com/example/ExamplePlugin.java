package com.example;

import com.google.inject.Binder;
import com.google.inject.Provides;
import com.rlrx.PluginRx;
import com.rlrx.PluginSubject;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import javax.inject.Inject;

@Slf4j
@PluginDescriptor(
	name = "Example"
)
public class ExamplePlugin extends Plugin
{
	@Inject
	private PluginSubject pluginSubject;

	private final CompositeDisposable disposable = new CompositeDisposable();

	@Override
	protected void startUp() throws Exception
	{
		log.info( "Example started!" );

 		disposable.add( this.getInjector().getInstance( ExampleGreeter.class ) );

		pluginSubject.onStartUp( this );
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info( "Example stopped!" );

		disposable.dispose();

		pluginSubject.onShutDown( this );
	}

	@Override
	public void configure( Binder binder )
	{
		log.info( "Configuring Example!" );

		PluginRx.configure( binder );
	}

	@Provides
	static ExampleConfig provideConfig( ConfigManager configManager )
	{
		return configManager.getConfig( ExampleConfig.class );
	}
}
