package com.RxRunelite;

import com.google.inject.Binder;
import com.RxRunelite.services.*;

import javax.annotation.Nonnull;

public final class RxPlugin
{
	public static void configure( @Nonnull Binder binder )
	{
		binder.bind( IPluginSchedulers.class ).to( PluginSchedulers.class );
		binder.bind( IEventObservable.class ).to( EventSubject.class );
	}
}
