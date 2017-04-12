package com.vararg.moviessample.app.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by vararg on 10.04.2017.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface Main {}
