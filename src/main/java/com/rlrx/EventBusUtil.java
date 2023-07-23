package com.rlrx;

import io.reactivex.rxjava3.disposables.Disposable;
import net.runelite.client.eventbus.EventBus;

import javax.annotation.Nonnull;

public class EventBusUtil
{
	@Nonnull
	public static <T> Disposable subscribe(
		@Nonnull EventBus eventBus,
		@Nonnull T subscriber )
	{
		final Disposable disposable = Disposable.fromAction( () ->
			eventBus.unregister( subscriber ) );

		eventBus.register( subscriber );

		return disposable;
	}
}
