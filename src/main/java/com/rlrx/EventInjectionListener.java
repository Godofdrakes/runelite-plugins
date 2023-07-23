package com.rlrx;

import com.google.inject.spi.InjectionListener;
import lombok.RequiredArgsConstructor;
import net.runelite.client.eventbus.EventBus;

@RequiredArgsConstructor
public class EventInjectionListener
	implements InjectionListener<EventSubject<?>>
{
	private final EventBus eventBus;

	@Override
	public void afterInjection( EventSubject<?> injectee )
	{

	}
}
