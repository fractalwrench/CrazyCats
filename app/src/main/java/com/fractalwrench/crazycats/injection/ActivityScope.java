package com.fractalwrench.crazycats.injection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * This Scope identifies that the dependencies should only be retained for the lifecycle of an
 * activity
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope {}
