package com.RxRunelite;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;

import java.util.HashMap;
import java.util.Map;

public class TestEventSubject
	implements IEventObservable
{
	private final Map<Class<?>, Subject<?>> eventMap = new HashMap<>();

	@SuppressWarnings("unchecked")
	private <T> Subject<T> getSubject( Class<T> eventClass )
	{
		return (Subject<T>)eventMap.computeIfAbsent( eventClass, k -> PublishSubject.<T>create() );
	}

	public <T> Observer<T> getObserver( Class<T> eventClass )
	{
		return getSubject( eventClass );
	}

	@Override
	public <T> Observable<T> onEvent( Class<T> eventClass, int priority )
	{
		return getSubject( eventClass );
	}
}
