package com.rlrx;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.disposables.SerialDisposable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;
import lombok.RequiredArgsConstructor;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.eventbus.Subscribe;

import javax.inject.Inject;

public class GameStateChangedSubject
	implements GameStateChangedObservable, Disposable
{
	private final Subject<GameStateChanged> subject;
	private final Disposable disposable;

	@Inject
	public GameStateChangedSubject( EventBus eventBus )
	{
		subject = PublishSubject.create();
		disposable = Disposable.fromAction( () -> eventBus.unregister( this ) );
		eventBus.register( this );
	}

	@Subscribe
	private void onGameStateChanged( GameStateChanged event )
	{
		subject.onNext( event );
	}

	@Override
	public Observable<GameState> gameStateChanged()
	{
		return null;
	}

	@Override
	public Observable<GameState> gameStateChanged( GameState gameState )
	{
		return GameStateChangedObservable.super.gameStateChanged( gameState );
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
