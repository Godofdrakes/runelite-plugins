package com.rlrx;

import io.reactivex.rxjava3.core.Observable;
import net.runelite.client.plugins.Plugin;

public interface PluginObservables
{
	Observable<Plugin> startUp();
	Observable<Plugin> shutDown();
}
