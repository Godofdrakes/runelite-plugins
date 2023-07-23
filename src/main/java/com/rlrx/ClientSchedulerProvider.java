package com.rlrx;

import com.google.inject.Inject;
import com.google.inject.Provider;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;
import net.runelite.client.callback.ClientThread;

public class ClientSchedulerProvider
	implements Provider<Scheduler>
{
	private final ClientThread clientThread;

	@Inject
	public ClientSchedulerProvider(ClientThread clientThread)
	{
		this.clientThread = clientThread;
	}

	@Override
	public Scheduler get()
	{
		return Schedulers.from( clientThread::invokeAtTickEnd );
	}
}
