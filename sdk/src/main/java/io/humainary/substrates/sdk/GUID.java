/*
 * Copyright © 2022 JINSPIRED B.V.
 */

package io.humainary.substrates.sdk;

import java.lang.invoke.VarHandle;
import java.util.function.Supplier;

import static java.lang.invoke.MethodHandles.lookup;
import static java.util.UUID.randomUUID;

public final class GUID {

  private GUID () {}

  private static final Supplier< String > GENERATOR =
    () -> randomUUID ().toString ();

  public static Supplier< String > generator () {

    return
      new State ();

  }

  public static String generate () {

    return
      GENERATOR.get ();

  }

  private static final class State implements Supplier< String > {

    private volatile String value;

    private static final VarHandle V;

    static {

      try {

        V =
          lookup ()
            .findVarHandle (
              State.class,
              "value",
              String.class
            );

      } catch (
        final Exception error
      ) {

        throw
          new ExceptionInInitializerError (
            error
          );

      }

    }

    @Override
    public String get () {

      final var uuid = (String)
        V.get (
          this
        );

      return
        uuid != null ?
        uuid :
        supply ();

    }

    private String supply () {

      var uuid = (String)
        V.getVolatile (
          this
        );

      if ( uuid != null )
        return uuid;

      final var result = (String)
        V.compareAndExchange (
          this,
          null,
          uuid = GENERATOR.get ()
        );

      return
        result == null ?
        uuid :
        result;

    }

  }

}
