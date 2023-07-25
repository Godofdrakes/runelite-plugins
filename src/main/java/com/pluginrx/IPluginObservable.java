package com.pluginrx;

import io.reactivex.rxjava3.core.Observable;
import net.runelite.client.plugins.Plugin;

public interface IPluginObservable
{
	Observable<Plugin> startUp();

	Observable<Plugin> shutDown();
}
