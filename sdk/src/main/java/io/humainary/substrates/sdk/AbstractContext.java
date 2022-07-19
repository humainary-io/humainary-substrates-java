/*
 * Copyright © 2022 JINSPIRED B.V.
 */

package io.humainary.substrates.sdk;

import io.humainary.substrates.Substrates;

import java.util.concurrent.ConcurrentHashMap;

import static io.humainary.substrates.Substrates.*;
import static io.humainary.substrates.sdk.GUID.generator;

public abstract class AbstractContext<
  I extends Instrument,
  E
  >
  extends AbstractContainer< I >
  implements Context< I, E > {

  // lazily generated...but still thread safe
  private static final Environment CONTEXT =
    Substrates.environment (
      UUID,
      generator ()
    );

  private final Producer< ? extends I, E > producer;
  private final Hub< E >                   hub;


  @FunctionalInterface
  public interface Producer<
    I extends Instrument,
    E
    > {

    I produce (
      Inlet< E > inlet
    );

  }

  protected AbstractContext (
    final Environment environment,
    final Producer< ? extends I, E > producer
  ) {

    this (
      environment,
      producer,
      hub ()
    );

  }

  protected AbstractContext (
    final Environment environment,
    final Producer< ? extends I, E > producer,
    final Hub< E > hub
  ) {

    super (
      CONTEXT.override (
        environment
      ),
      new ConcurrentHashMap<> ( 50 )
    );

    this.producer =
      producer;

    this.hub =
      hub;

  }


  @Override
  public final Subscription subscribe (
    final Subscriber< E > subscriber
  ) {

    return
      hub.subscribe (
        subscriber
      );

  }


  @Override
  public final Subscription consume (
    final Outlet< E > outlet
  ) {

    return
      hub.consume (
        outlet
      );

  }


  protected final I instrument (
    final Name name
  ) {

    final var instrument =
      map.get (
        name
      );

    return
      instrument != null
      ? instrument
      : map.computeIfAbsent (
        name,
        this::create
      );

  }

  protected abstract Type type ();

  private I create (
    final Name name
  ) {

    return
      producer.produce (
        hub.inlet (
          reference (
            type (),
            name,
            environment
          )
        )
      );

  }

}
