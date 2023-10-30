package com.PluginRx.services;

import com.PluginRx.IEventObservable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;
import net.runelite.client.eventbus.EventBus;

import javax.inject.Inject;

public class EventSubject
	implements IEventObservable
{
	private final EventBus eventBus;

	@Inject
	public EventSubject( EventBus eventBus )
	{
		this.eventBus = eventBus;
	}

	@Override
	public <T> Observable<T> onEvent( Class<T> eventClass, int priority )
	{
		final Subject<T> subject = PublishSubject.create();

		return Observable.using(
			() -> eventBus.register( eventClass, subject::onNext, priority ),
			subscriber -> subject,
			eventBus::unregister
		);
	}
}
