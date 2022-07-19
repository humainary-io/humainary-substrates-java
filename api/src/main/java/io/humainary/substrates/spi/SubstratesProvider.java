/*
 * Copyright © 2022 JINSPIRED B.V.
 */

package io.humainary.substrates.spi;

import io.humainary.spi.Providers.Provider;
import io.humainary.substrates.Substrates.*;

import java.lang.reflect.Member;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;
import static java.util.Optional.*;
import static java.util.function.Function.identity;

/**
 * The service provider interface for the substrates runtime.
 * <p>
 * Note: An SPI implementation of this interface is free to override
 * the default methods implementation included here.
 *
 * @author wlouth
 * @since 1.0
 */

public interface SubstratesProvider
  extends Provider {

  Name name (
    String path
  );


  default Name name (
    final String first,
    final String second
  ) {

    return
      name ( first )
        .name ( second );

  }


  default Name name (
    final Class< ? > cls,
    final String path
  ) {

    return
      name ( cls )
        .name ( path );

  }


  default Name name (
    final Class< ? > cls
  ) {

    return
      name (
        cls.getName ()
      );

  }


  default Name name (
    final Member member
  ) {

    return
      name (
        member.getDeclaringClass ().getName ()
      ).name (
        member.getName ()
      );

  }


  default Name name (
    final Iterable< String > it
  ) {

    return
      name (
        it,
        identity ()
      );

  }


  default < T > Name name (
    final Iterable< ? extends T > it,
    final Function< T, String > fn
  ) {

    return
      name (
        it.iterator (),
        fn
      );

  }


  default Name name (
    final Iterator< String > it
  ) {

    return
      name (
        it,
        identity ()
      );

  }


  default < T > Name name (
    final Iterator< ? extends T > it,
    final Function< T, String > fn
  ) {

    if ( !it.hasNext () )
      throw new IllegalArgumentException ();

    Name name;

    do {

      name =
        name (
          fn.apply (
            it.next ()
          )
        );

    } while ( it.hasNext () );

    return
      name;

  }


  default Environment environment () {

    return
      Environment.EMPTY;

  }


  default Environment environment (
    final Function< ? super Name, Object > fn
  ) {

    requireNonNull (
      fn
    );

    return
      environment (
        lookup ( fn )
      );

  }


  default Environment environment (
    final Lookup< Object > lookup
  ) {

    requireNonNull (
      lookup
    );

    return
      ( name, defVal ) -> {
        try {

          return
            lookup.get (
              name,
              defVal
            );

        } catch (
          final Throwable error
        ) {

          return
            defVal;

        }
      };

  }


  default < T > Environment environment (
    final Name path,
    final T value
  ) {

    requireNonNull (
      path
    );

    return
      environment (
        name ->
          path == name
          ? ofNullable ( value )
          : empty ()
      );

  }


  default Environment environment (
    final Name path,
    final int value
  ) {

    return
      environment (
        path,
        Integer.valueOf (
          value
        )
      );

  }


  default Environment environment (
    final Name path,
    final long value
  ) {

    return
      environment (
        path,
        Long.valueOf (
          value
        )
      );

  }


  default Environment environment (
    final Name path,
    final float value
  ) {

    return
      environment (
        path,
        Float.valueOf (
          value
        )
      );

  }


  default Environment environment (
    final Name path,
    final double value
  ) {

    return
      environment (
        path,
        Double.valueOf (
          value
        )
      );

  }


  default Environment environment (
    final Name path,
    final boolean value
  ) {

    return
      environment (
        path,
        Boolean.valueOf (
          value
        )
      );

  }


  default < T > Environment environment (
    final Name path,
    final Supplier< T > supplier
  ) {

    requireNonNull (
      path
    );

    requireNonNull (
      supplier
    );

    return
      environment (
        name ->
          path == name
          ? of ( supplier.get () )
          : empty ()
      );

  }


  default Variable< Object > variable (
    final Name name,
    final Object defValue
  ) {

    return
      environment ->
        environment
          .get (
            name,
            defValue
          );

  }


  default < T > Variable< T > variable (
    final Name name,
    final Class< ? extends T > type,
    final T defValue
  ) {

    return
      environment ->
        environment.< T > getObject ( name, type )
          .orElse ( defValue );


  }


  default < T, A > Variable< T > variable (
    final Name name,
    final Class< ? extends T > type,
    final Class< ? extends A > alt,
    final Function< ? super A, ? extends T > mapper,
    final T defValue
  ) {

    return
      environment ->
        environment.getObject (
          name,
          type,
          alt,
          mapper
        ).orElse (
          defValue
        );

  }


  default < T extends Enum< T > > Variable< T > variable (
    final Name name,
    final Class< T > type,
    final T defValue
  ) {

    return
      environment ->
        environment.getEnum (
          name,
          type,
          defValue
        );

  }


  default Variable< Boolean > variable (
    final Name name,
    final Boolean defValue
  ) {

    return
      environment ->
        environment.getBoolean (
          name,
          defValue
        );

  }


  default Variable< Integer > variable (
    final Name name,
    final Integer defValue
  ) {

    return
      environment ->
        environment.getInteger (
          name,
          defValue
        );

  }


  default Variable< Long > variable (
    final Name name,
    final Long defValue
  ) {

    return
      env ->
        env.getLong (
          name,
          defValue
        );

  }


  default Variable< Double > variable (
    final Name name,
    final Double defValue
  ) {

    return
      environment ->
        environment.getDouble (
          name,
          defValue
        );

  }


  default Variable< Float > variable (
    final Name name,
    final Float defValue
  ) {

    return
      environment ->
        environment.getFloat (
          name,
          defValue
        );

  }


  default Variable< String > variable (
    final Name name,
    final String defValue
  ) {

    return
      environment ->
        environment.getString (
          name,
          defValue
        );

  }


  default Variable< CharSequence > variable (
    final Name name,
    final CharSequence defValue
  ) {

    return
      environment ->
        environment.getCharSequence (
          name,
          defValue
        );

  }


  default Variable< Name > variable (
    final Name name,
    final Name defValue
  ) {

    return
      environment ->
        environment.getName (
          name,
          defValue
        );

  }


  < E > Hub< E > hub ();


  < E > Hub< E > hub (
    Environment environment
  );


  < T > Lookup< T > lookup (
    final Function< ? super Name, ? extends T > fn
  );


  default < T > Function< Name, T > function (
    final Lookup< T > lookup
  ) {

    return
      function (
        lookup,
        (T) null
      );

  }


  default < T > Function< Name, T > function (
    final Lookup< T > lookup,
    final T defVal
  ) {

    return
      name ->
        lookup.get (
          name,
          defVal
        );

  }


  default < T > Function< Name, T > function (
    final Lookup< T > lookup,
    final Supplier< ? extends T > supplier
  ) {

    return
      name ->
        lookup.get (
          name
        ).orElseGet (
          supplier
        );

  }


  default < T > Subscriber< T > subscriber (
    final Outlet< T > outlet
  ) {

    return
      ( reference, registrar ) ->
        registrar.register (
          outlet
        );

  }


  default < T > Outlet< T > outlet (
    final Conduit< ? super T > conduit
  ) {

    return
      event ->
        conduit.inlet (
          event.emitter ()
        ).emit (
          event.emittance ()
        );

  }


  default Reference reference (
    final Type type,
    final Name name
  ) {

    return
      reference (
        type,
        name,
        environment ()
      );

  }


  Reference reference (
    Type type,
    Name name,
    Environment environment
  );


  Type type (
    Class< ? extends Referent > type
  );

}