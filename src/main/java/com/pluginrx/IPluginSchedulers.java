package com.pluginrx;

import io.reactivex.rxjava3.core.Scheduler;

public interface IPluginSchedulers
{
	// If client thread run now, else run on next client thread update
	Scheduler clientThread();

	// Run on next client thread update
	Scheduler clientThreadDeferred();

	// Run on next client thread update, at end of frame
	Scheduler clientThreadEndOfFrame();

	// Run on the next gui thread update
	// Based on source examples this is for updating the RuneLite GUI specifically.
	Scheduler guiThread();
}

