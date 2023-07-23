package com.rlrx;

import io.reactivex.rxjava3.core.Scheduler;

public interface RuneLiteSchedulers
{
	// If client thread run now, else run on next client thread update
	Scheduler clientThreadScheduler();

	// Run on next client thread update
	Scheduler clientThreadDeferredScheduler();

	// Run on next client thread update, at end of frame
	Scheduler clientThreadEndOfFrameScheduler();
}

