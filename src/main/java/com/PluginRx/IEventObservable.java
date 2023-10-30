package com.PluginRx;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Predicate;
import net.runelite.api.events.VarbitChanged;

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

	default Observable<Integer> onVarbitChanged( int varbitId )
	{
		return onEvent( VarbitChanged.class, event -> event.getVarbitId() == varbitId )
				.map( VarbitChanged::getValue );
	}

	default Observable<VarbitChanged> onVarpChanged( int varpId )
	{
		return onEvent( VarbitChanged.class, event -> event.getVarpId() == varpId );
	}
}
