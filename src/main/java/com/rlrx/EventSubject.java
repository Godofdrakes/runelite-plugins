package com.rlrx;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import net.runelite.client.eventbus.EventBus;

import javax.inject.Inject;

public class EventSubject
	implements EventObservable
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
		final var subject = PublishSubject.<T>create();

		return Observable.using(
			() -> eventBus.register( eventClass, subject::onNext, priority ),
			subscriber -> subject,
			eventBus::unregister
		);
	}
}
