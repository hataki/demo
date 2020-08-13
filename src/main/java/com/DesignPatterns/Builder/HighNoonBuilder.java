package com.DesignPatterns.Builder;

/**
 * @Author: hataki
 * @Date: 2020/8/13
 * Time: 10:42
 * description:
 */
public interface HighNoonBuilder {
    HighNoonBuilder buildTarget();
    HighNoonBuilder buildVision();
    HighNoonBuilder buildScope();
    HighNoon build();
}
