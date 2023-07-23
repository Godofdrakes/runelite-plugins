package com.rlrx;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Predicate;

public interface EventObservable<T>
{
	Observable<T> event();
}
