package com.pluginrx.services;

import com.google.inject.Inject;
import com.pluginrx.IPluginSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;
import net.runelite.client.callback.ClientThread;

import javax.swing.*;

public class PluginSchedulers
	implements IPluginSchedulers
{
	private final ClientThread clientThread;

	@Inject
	public PluginSchedulers( ClientThread clientThread )
	{
		this.clientThread = clientThread;
	}

	@Override
	public Scheduler clientThread()
	{
		return Schedulers.from( clientThread::invoke );
	}

	@Override
	public Scheduler clientThreadDeferred()
	{
		return Schedulers.from( clientThread::invokeLater );
	}

	@Override
	public Scheduler clientThreadEndOfFrame()
	{
		return Schedulers.from( clientThread::invokeAtTickEnd );
	}

	@Override
	public Scheduler guiThread()
	{
		return Schedulers.from( SwingUtilities::invokeLater );
	}
}
