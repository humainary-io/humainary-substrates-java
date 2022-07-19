/*
 * Copyright © 2022 JINSPIRED B.V.
 */

package io.humainary.substrates.testkit;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static io.humainary.substrates.Substrates.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The test class for the {@link Environment} interface.
 *
 * @author wlouth
 * @since 1.0
 */

final class EnvironmentTest {

  private static final Name  FLOAT_PATH     = name ( "float.1" );
  private static final Name  FLOAT_ALT_PATH = name ( "float.2" );
  private static final float FLOAT_VALUE    = 1.0F;
  private static final float FLOAT_DEFAULT  = 2.0F;

  private static void get_float (
    final Environment environment
  ) {

    assertEquals (
      FLOAT_VALUE,
      environment.getFloat (
        FLOAT_PATH,
        FLOAT_DEFAULT
      )
    );

    assertEquals (
      FLOAT_DEFAULT,
      environment.getFloat (
        FLOAT_ALT_PATH,
        FLOAT_DEFAULT
      )
    );

  }


  @Test
  void environment_function () {

    final var map =
      new HashMap< Name, Float > ( 5 );

    map.put (
      FLOAT_PATH,
      FLOAT_VALUE
    );

    get_float (
      environment (
        lookup (
          map::get
        )
      )
    );

  }

  @Test
  void environment_string_object () {

    get_float (
      environment (
        FLOAT_PATH,
        FLOAT_VALUE
      )
    );

  }

  @Test
  void environment_predicate_function () {

    get_float (
      environment (
        lookup (
          name ->
            FLOAT_VALUE
        )
      ).filter (
        FLOAT_PATH::equals
      )
    );

  }

  @Test
  void environment_override_environment () {

    get_float (
      environment (
        FLOAT_PATH,
        FLOAT_DEFAULT
      ).override (
        environment (
          FLOAT_PATH,
          FLOAT_VALUE
        )
      )
    );

  }

  @Test
  void environment_override_name_value () {

    get_float (
      environment (
        FLOAT_PATH,
        FLOAT_DEFAULT
      ).override (
        FLOAT_PATH,
        FLOAT_VALUE
      )
    );

  }

}
