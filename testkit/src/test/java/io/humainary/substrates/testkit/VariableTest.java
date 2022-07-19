/*
 * Copyright © 2022 JINSPIRED B.V.
 */

package io.humainary.substrates.testkit;

import io.humainary.substrates.Substrates;
import org.junit.jupiter.api.Test;

import java.lang.Thread.State;
import java.util.function.BiFunction;

import static io.humainary.substrates.Substrates.Environment.EMPTY;
import static io.humainary.substrates.Substrates.*;
import static java.lang.Thread.State.BLOCKED;
import static java.lang.Thread.State.RUNNABLE;
import static org.junit.jupiter.api.Assertions.assertSame;

final class VariableTest {

  private static final Name NAME =
    name (
      Variable.class
    );

  private static final Name ALT_NAME =
    name (
      VariableTest.class
    );

  private static < T > void test (
    final T value,
    final T defVal,
    final BiFunction< ? super Name, T, ? extends Variable< T > > factory
  ) {

    final var variable =
      factory.apply (
        NAME,
        defVal
      );

    assertSame (
      value,
      variable.of (
        environment (
          NAME,
          value
        )
      )
    );

    assertSame (
      defVal,
      variable.of (
        EMPTY
      )
    );

  }

  @Test
  void object_of () {

    test (
      new Object (),
      new Object (),
      Substrates::variable
    );

  }

  @Test
  void string_of () {

    test (
      "value",
      "defValue",
      Substrates::variable
    );

  }

  @Test
  void float_of () {

    test (
      0.0f,
      0.1f,
      Substrates::variable
    );

  }

  @Test
  void double_of () {

    test (
      0.0d,
      0.1d,
      Substrates::variable
    );

  }

  @Test
  void integer_of () {

    test (
      0,
      1000,
      Substrates::variable
    );

  }

  @Test
  void long_of () {

    test (
      0L,
      1000L,
      Substrates::variable
    );

  }

  @Test
  void boolean_of () {

    test (
      true,
      false,
      Substrates::variable
    );

  }

  @Test
  void name_of () {

    test (
      NAME,
      ALT_NAME,
      Substrates::variable
    );

  }

  @Test
  void enum_of () {

    test (
      BLOCKED,
      RUNNABLE,
      ( name, defVal ) ->
        variable (
          name,
          State.class,
          defVal
        )
    );

  }

}
