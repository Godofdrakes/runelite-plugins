package com.PluginRx.services;

import com.PluginRx.IPluginObservable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;
import net.runelite.client.plugins.Plugin;

public final class PluginSubject
	implements IPluginObservable
{
	private final Subject<Plugin> startUpSubject = PublishSubject.create();
	private final Subject<Plugin> shutDownSubject = PublishSubject.create();

	@Override
	public Observable<Plugin> startUp()
	{
		return Observable.wrap( startUpSubject );
	}

	@Override
	public Observable<Plugin> shutDown()
	{
		return Observable.wrap( shutDownSubject );
	}

	public void onStartUp( Plugin plugin )
	{
		startUpSubject.onNext( plugin );
	}

	public void onShutDown( Plugin plugin )
	{
		shutDownSubject.onNext( plugin );
	}
}
