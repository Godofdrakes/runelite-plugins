package com.example;

import com.pluginrx.IEventObservable;
import com.pluginrx.IPluginSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Predicate;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;

import javax.inject.Inject;

public class ExampleGreeter
	implements Disposable
{
	private final CompositeDisposable disposable;

	@Inject
	public ExampleGreeter(
		Client client,
		ExampleConfig config,
		IEventObservable events,
		IPluginSchedulers schedulers )
	{
		final Predicate<GameStateChanged> onLoggedIn = event ->
			event.getGameState() == GameState.LOGGED_IN;

		disposable = new CompositeDisposable();
		disposable.add(
			events.onEvent( GameStateChanged.class, onLoggedIn )
				.observeOn( schedulers.clientThreadEndOfFrameScheduler() )
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
