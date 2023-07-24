package com.rlrx;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Predicate;

public interface EventObservable
{
	<T> Observable<T> onEvent( Class<T> eventClass, int priority );

	default <T> Observable<T> onEvent( Class<T> eventClass )
	{
		return onEvent( eventClass, 0 );
	}

	default <T> Observable<T> onEvent( Class<T> eventClass, Predicate<T> predicate )
	{
		return onEvent( eventClass, 0 ).filter( predicate );
	}
}
