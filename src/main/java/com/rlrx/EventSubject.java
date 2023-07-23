package com.rlrx;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.disposables.SerialDisposable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;
import lombok.RequiredArgsConstructor;
import net.runelite.client.eventbus.EventBus;

@RequiredArgsConstructor
public class EventSubject<T>
	implements EventObservable<T>, Disposable
{
	private final Subject<T> subject = PublishSubject.create();

	private final SerialDisposable disposable = new SerialDisposable();

	private final Class<T> clazz;

	public void subscribe( EventBus eventBus )
	{
		var subscriber = eventBus.register( clazz, subject::onNext, 0 );
		disposable.set( Disposable.fromAction( () -> eventBus.unregister( subscriber ) ) );
	}

	@Override
	public Observable<T> event()
	{
		return Observable.wrap( subject );
	}

	@Override
	public void dispose()
	{
		disposable.dispose();
	}

	@Override
	public boolean isDisposed()
	{
		return disposable.isDisposed();
	}
}
