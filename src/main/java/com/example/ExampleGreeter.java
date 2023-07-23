package com.example;

import com.rlrx.ClientThread;
import com.rlrx.GameStateChangedObservable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;

import javax.inject.Inject;

public class ExampleGreeter
	implements Disposable
{
	private final CompositeDisposable disposable;

	@Inject
	public ExampleGreeter(
		Client client,
		ExampleConfig config,
		GameStateChangedObservable gameState,
		@ClientThread Scheduler clientThread )
	{
		this.disposable = new CompositeDisposable();
		this.disposable.add(
			gameState
				.gameStateChanged( GameState.LOGGED_IN )
				.observeOn( clientThread )
				.subscribe( state -> client.addChatMessage(
					ChatMessageType.GAMEMESSAGE,
					"",
					"Example says " + config.greeting(),
					null ) )
		);
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
