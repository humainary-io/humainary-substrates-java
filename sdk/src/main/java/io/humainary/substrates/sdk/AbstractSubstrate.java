/*
 * Copyright © 2022 JINSPIRED B.V.
 */

package io.humainary.substrates.sdk;

import io.humainary.substrates.Substrates;
import io.humainary.substrates.Substrates.Environment;

import static io.humainary.substrates.Substrates.Environment.EMPTY;

public abstract class AbstractSubstrate
  implements Substrates.Substrate {

  protected final Environment environment;

  protected AbstractSubstrate () {

    this (
      EMPTY
    );

  }

  protected AbstractSubstrate (
    final Environment environment
  ) {

    this.environment =
      environment;

  }


  @Override
  public final Environment environment () {

    return
      environment;

  }

}
