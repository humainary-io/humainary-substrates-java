/*
 * Copyright © 2022 JINSPIRED B.V.
 */

package io.humainary.substrates.sdk;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import static io.humainary.substrates.Substrates.*;
import static io.humainary.substrates.Substrates.Context.ID;
import static io.humainary.substrates.Substrates.Environment.EMPTY;

public abstract class AbstractContextProvider<
  I extends Instrument,
  T extends Context< ? extends I, ? >
  > {

  private static final Variable< String > CID =
    variable (
      ID,
      (String) null
    );

  private final ConcurrentHashMap< String, T > contexts =
    new ConcurrentHashMap<> ( 5 );

  private final Function< ? super Environment, ? extends T > factory;

  protected AbstractContextProvider (
    final Function< ? super Environment, ? extends T > factory
  ) {

    this.factory =
      factory;

  }


  public final T context () {

    // we do not need to store this
    // within the mapping of contexts

    return
      create (
        EMPTY
      );

  }

  public final T context (
    final Environment environment
  ) {

    // if there is an id then this
    // context should be mapped

    final var id =
      CID.of (
        environment
      );

    if ( id == null ) {

      return
        create (
          environment
        );

    } else {

      final var context =
        contexts.get (
          id
        );

      return
        context == null ?
        contexts.computeIfAbsent (
          id,
          __ ->
            create (
              environment
            )
        ) :
        context;

    }

  }

  private T create (
    final Environment environment
  ) {

    return
      factory.apply (
        environment
      );

  }

}
