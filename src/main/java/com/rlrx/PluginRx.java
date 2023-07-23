package com.rlrx;

import com.google.inject.Binder;
import com.google.inject.Scopes;
import io.reactivex.rxjava3.core.Scheduler;

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

		// In order to keep EventSubject testable it does not bind to the EventBus
		// Instead we use a provider to handle that
		binder.bind( EventSubject.class )
			.toProvider( EventProvider.class )
			.in( Scopes.SINGLETON );

		// Implement all event observables using EventSubject
		binder.bind( GameStateObservable.class ).to( EventSubject.class );
		binder.bind( GameTickObservable.class ).to( EventSubject.class );
	}
}
