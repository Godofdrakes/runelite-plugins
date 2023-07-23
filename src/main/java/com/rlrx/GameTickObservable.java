package com.rlrx;

import io.reactivex.rxjava3.core.Observable;
import net.runelite.api.events.GameTick;

public interface GameTickObservable
{
	Observable<GameTick> gameTick();
}
