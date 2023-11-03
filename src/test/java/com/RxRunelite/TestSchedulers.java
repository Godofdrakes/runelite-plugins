package com.RxRunelite;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.TestScheduler;

public class TestSchedulers
	implements IPluginSchedulers
{
	public final TestScheduler scheduler = new TestScheduler();

	@Override
	public Scheduler clientThread()
	{
		return scheduler;
	}

	@Override
	public Scheduler clientThreadDeferred()
	{
		return scheduler;
	}

	@Override
	public Scheduler clientThreadEndOfFrame()
	{
		return scheduler;
	}
}
