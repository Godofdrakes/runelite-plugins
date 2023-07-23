package com.rlrx;

import com.google.inject.Inject;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;
import net.runelite.client.callback.ClientThread;

public class RuneLiteSchedulersImpl
	implements RuneLiteSchedulers
{
	private final ClientThread clientThread;

	@Inject
	public RuneLiteSchedulersImpl( ClientThread clientThread )
	{
		this.clientThread = clientThread;
	}

	@Override
	public Scheduler clientThreadScheduler()
	{
		return Schedulers.from( clientThread::invoke );
	}

	@Override
	public Scheduler clientThreadDeferredScheduler()
	{
		return Schedulers.from( clientThread::invokeLater );
	}

	@Override
	public Scheduler clientThreadEndOfFrameScheduler()
	{
		return Schedulers.from( clientThread::invokeAtTickEnd );
	}
}
