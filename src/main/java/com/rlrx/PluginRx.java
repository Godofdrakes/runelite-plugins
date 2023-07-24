package com.rlrx;

import com.google.inject.Binder;

import javax.annotation.Nonnull;

public final class PluginRx
{
	public static void configure( @Nonnull Binder binder )
	{
		binder.bind( RuneLiteSchedulers.class ).to( RuneLiteSchedulersImpl.class );
		binder.bind( EventObservable.class ).to( EventSubject.class );
	}
}
