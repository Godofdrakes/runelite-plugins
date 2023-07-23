package com.rlrx;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Predicate;
import net.runelite.api.Hitsplat;
import net.runelite.api.annotations.HitsplatType;

public interface HitSplatObservable
{
	Observable<Hitsplat> hitsplat();

	default Observable<Hitsplat> hitsplat( Predicate<Hitsplat> predicate )
	{
		return hitsplat().filter( predicate );
	}

	default Observable<Hitsplat> hitsplat( @HitsplatType int hitsplatType )
	{
		return hitsplat( hitsplat -> hitsplat.getHitsplatType() == hitsplatType );
	}
}
