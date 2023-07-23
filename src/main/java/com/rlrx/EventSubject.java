package com.rlrx;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.client.eventbus.Subscribe;

public final class EventSubject
	implements GameStateObservable,
		GameTickObservable
{
	private final Subject<GameStateChanged> gameStateChangedSubject = PublishSubject.create();
	private final Subject<GameTick> gameTickSubject = PublishSubject.create();

	@Subscribe
	public void onGameStateChanged( GameStateChanged event )
	{
		gameStateChangedSubject.onNext( event );
	}

	@Subscribe
	public void onGameTick( GameTick event )
	{
		gameTickSubject.onNext( event );
	}

	@Override
	public Observable<GameState> gameStateChanged()
	{
		return gameStateChangedSubject.map( GameStateChanged::getGameState );
	}

	@Override
	public Observable<GameTick> gameTick()
	{
		return null;
	}
}
