package com.rlrx;

import com.google.inject.Inject;
import com.google.inject.Provider;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import net.runelite.client.eventbus.EventBus;

public class EventProvider
	implements Provider<EventSubject>
{
	@Inject
	private EventBus eventBus;

	@Inject
	@PluginShutdown
	private CompositeDisposable disposable;

	@Override
	public EventSubject get()
	{
		final EventSubject eventSubject = new EventSubject();

		// EventSubject avoids depending on EventBus in order to improve testability
		// Make that binding here instead
		eventBus.register( eventSubject );

		// Make sure to clean up binding when plugin is shut down
		disposable.add( Disposable.fromAction( () ->
			eventBus.unregister( eventSubject ) ) );

		return eventSubject;
	}
}
