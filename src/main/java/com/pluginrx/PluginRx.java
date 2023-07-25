package com.pluginrx;

import com.google.inject.Binder;
import com.pluginrx.services.*;

import javax.annotation.Nonnull;

public final class PluginRx
{
	public static void configure( @Nonnull Binder binder )
	{
		binder.bind( IPluginSchedulers.class ).to( PluginSchedulers.class );
		binder.bind( IEventObservable.class ).to( EventSubject.class );
	}
}
