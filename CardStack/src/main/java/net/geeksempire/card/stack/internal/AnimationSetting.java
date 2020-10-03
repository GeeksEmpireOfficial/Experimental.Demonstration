package net.geeksempire.card.stack.internal;

import android.view.animation.Interpolator;

import net.geeksempire.card.stack.Direction;

public interface AnimationSetting {
    Direction getDirection();
    int getDuration();
    Interpolator getInterpolator();
}
