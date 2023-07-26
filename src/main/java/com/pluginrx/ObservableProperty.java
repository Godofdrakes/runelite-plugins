package com.pluginrx;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;
import lombok.NonNull;

public final class ObservableProperty<T>
	implements Disposable
{
	public static <T> ObservableProperty<T> create( Observable<T> observable, T defaultValue, Scheduler scheduler )
	{
		return new ObservableProperty<>( observable, defaultValue, scheduler );
	}

	public static <T> ObservableProperty<T> create( Observable<T> observable, Scheduler scheduler )
	{
		return new ObservableProperty<>( observable, null, scheduler );
	}

	public static <T> ObservableProperty<T> create( Observable<T> observable, T defaultValue )
	{
		return new ObservableProperty<>( observable, defaultValue, null );
	}

	public static <T> ObservableProperty<T> create( Observable<T> observable )
	{
		return new ObservableProperty<>( observable, null, null );
	}

	private final CompositeDisposable disposable;
	private final Subject<Throwable> thrownExceptions;
	private final BehaviorSubject<T> subject;

	private ObservableProperty(
		@NonNull Observable<T> observable,
		T defaultValue,
		Scheduler scheduler )
	{
		disposable = new CompositeDisposable();

		thrownExceptions = PublishSubject.create();

		subject = BehaviorSubject.create();

		if (scheduler != null)
		{
			observable = observable.observeOn( scheduler );
		}

		if (defaultValue != null)
		{
			observable = observable.startWithItem( defaultValue );
		}

		observable.distinctUntilChanged().subscribe(
			subject::onNext,
			thrownExceptions::onNext,
			subject::onComplete,
			disposable
		);
	}

	public T getValue()
	{
		return subject.getValue();
	}

	public Observable<Throwable> thrownExceptions()
	{
		return Observable.wrap( thrownExceptions );
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
