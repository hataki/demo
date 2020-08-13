package com.DesignPatterns.Builder;

/**
 * @Author: hataki
 * @Date: 2020/8/13
 * Time: 10:50
 * description:
 */
public class HighNoonImpBuilder implements HighNoonBuilder{

    HighNoon highNoon = new HighNoon() ;

    @Override
    public HighNoonBuilder buildTarget() {
        highNoon.target = new Target(6,true);
        return this;

    }

    @Override
    public HighNoonBuilder buildVision() {
        highNoon.vision = new Vision(100,100,100,100);
        return this;
    }

    @Override
    public HighNoonBuilder buildScope() {
        highNoon.scope = new Scope(5,5,5,5);
        return this;
    }

    @Override
    public HighNoon build() {
        return highNoon;
    }
}
