/*
 * Copyright © 2022 JINSPIRED B.V.
 */

package io.humainary.substrates.sdk;

import io.humainary.substrates.Substrates.Environment;
import io.humainary.substrates.Substrates.Inlet;
import io.humainary.substrates.Substrates.Instrument;
import io.humainary.substrates.Substrates.Reference;

public abstract class AbstractInstrument< E >
  implements Instrument {

  protected final Inlet< E > inlet;

  protected AbstractInstrument (
    final Inlet< E > inlet
  ) {

    this.inlet =
      inlet;

  }

  @Override
  public Environment environment () {

    return
      inlet.environment ();

  }

  @Override
  public Reference reference () {

    return
      inlet.reference ();

  }


}
