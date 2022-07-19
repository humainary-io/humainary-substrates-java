/*
 * Copyright © 2022 JINSPIRED B.V.
 */

package io.humainary.substrates.sdk;

import io.humainary.substrates.Substrates.Container;
import io.humainary.substrates.Substrates.Environment;
import io.humainary.substrates.Substrates.Name;
import io.humainary.substrates.Substrates.Referent;

import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

import static java.util.Optional.empty;

public abstract class AbstractContainer< O extends Referent >
  extends AbstractSubstrate
  implements Container< O > {

  final Map< Name, O > map;

  protected AbstractContainer (
    final Environment environment,
    final Map< Name, O > map
  ) {

    super (
      environment
    );

    this.map =
      map;

  }

  @Override
  public final O get (
    final Name name,
    final O defValue
  ) {

    return
      map.getOrDefault (
        name,
        defValue
      );

  }

  @Override
  public final Optional< O > get (
    final Name name
  ) {

    final O value =
      map.get (
        name
      );

    return
      value != null
      ? Optional.of ( value )
      : empty ();

  }


  @Override
  public final Iterator< O > iterator () {

    return
      map.values ().iterator ();

  }

}
