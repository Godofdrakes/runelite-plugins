package com.rlrx;

import io.reactivex.rxjava3.core.Observable;
import net.runelite.api.GameState;

public interface GameStateChangedObservable
{
	Observable<GameState> gameStateChanged();

	default Observable<GameState> gameStateChanged( GameState gameState )
	{
		return gameStateChanged().filter( state -> state == gameState );
	}
}
