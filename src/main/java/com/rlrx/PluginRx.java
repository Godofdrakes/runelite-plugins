package com.rlrx;

import com.google.inject.Binder;
import com.google.inject.Injector;
import com.google.inject.Scopes;
import com.google.inject.TypeLiteral;
import io.reactivex.rxjava3.core.Scheduler;
import net.runelite.client.eventbus.EventBus;

import javax.annotation.Nonnull;

public final class PluginRx
{
	public static void configure( @Nonnull Binder binder )
	{
		// Wrap ClientThread in an Rx Scheduler
		binder.bind( Scheduler.class )
			.annotatedWith( ClientThread.class )
			.toProvider( ClientSchedulerProvider.class )
			.in( Scopes.SINGLETON );
	}

	public static <T> void configureEventBus( @Nonnull EventBus eventBus )
	{

	}

	public static <T> void configureEvent( @Nonnull Binder binder, @Nonnull Class<T> clazz )
	{
		final var serviceType = new TypeLiteral<EventObservable<T>>() { };
		binder.bind( serviceType ).toInstance( new EventSubject<>( clazz ) );
	}
}
