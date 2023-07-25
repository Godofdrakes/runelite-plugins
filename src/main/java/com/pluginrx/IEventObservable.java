package com.pluginrx;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Predicate;

public interface IEventObservable
{
	int PRIORITY_DEFAULT = 0;

	<T> Observable<T> onEvent( Class<T> eventClass, int priority );

	default <T> Observable<T> onEvent( Class<T> eventClass )
	{
		return onEvent( eventClass, PRIORITY_DEFAULT );
	}

	default <T> Observable<T> onEvent( Class<T> eventClass, Predicate<T> predicate )
	{
		return onEvent( eventClass ).filter( predicate );
	}
}
