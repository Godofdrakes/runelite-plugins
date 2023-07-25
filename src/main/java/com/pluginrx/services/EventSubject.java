package com.pluginrx.services;

import com.pluginrx.IEventObservable;
import com.pluginrx.IPluginSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import net.runelite.client.eventbus.EventBus;

import javax.inject.Inject;

public class EventSubject
	implements IEventObservable
{
	private final IPluginSchedulers schedulers;
	private final EventBus eventBus;

	@Inject
	public EventSubject(
		IPluginSchedulers schedulers,
		EventBus eventBus)
	{
		this.schedulers = schedulers;
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
