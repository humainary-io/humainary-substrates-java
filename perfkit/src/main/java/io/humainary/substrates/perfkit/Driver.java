/*
 * Copyright © 2022 JINSPIRED B.V.
 */

package io.humainary.substrates.perfkit;

import io.humainary.devkit.perfkit.PerfKit;
import org.openjdk.jmh.annotations.*;

import java.lang.reflect.Method;

import static io.humainary.substrates.Substrates.*;
import static io.humainary.substrates.Substrates.Environment.EMPTY;
import static java.lang.Thread.State.BLOCKED;

/**
 * The PerfKit utility class for micro-benchmarking purposes.
 *
 * @author wlouth
 * @since 1.0
 */

@State ( Scope.Benchmark )
public class Driver
  implements PerfKit.Driver {

  private static final String                   FIRST              = "first";
  private static final String                   SECOND             = "second";
  private static final String                   THIRD              = "third";
  private static final String                   FIRST_SECOND_THIRD = "first.second.third";
  private static final Name                     FIRST_NAME         = name ( FIRST );
  private static final Float                    FLOAT_VALUE        = 1.0f;
  private static final Integer                  INTEGER_VALUE      = 1;
  private static final Long                     LONG_VALUE         = 1L;
  private static final Double                   DOUBLE_VALUE       = 1.0D;
  private static final Boolean                  BOOLEAN_VALUE      = Boolean.TRUE;
  private static final CharSequence             CHAR_SEQ_VALUE     = FIRST;
  private static final String                   STRING_VALUE       = FIRST;
  private static final Name                     NAME_VALUE         = FIRST_NAME;
  private static final Object                   OBJECT_VALUE       = new Object ();
  private static final Thread.State             ENUM_VALUE         = BLOCKED;
  private static final Class< Thread.State >    ENUM_CLASS         = Thread.State.class;
  private static final Variable< Integer >      VAR_INTEGER        = variable ( FIRST_NAME, INTEGER_VALUE );
  private static final Variable< Long >         VAR_LONG           = variable ( FIRST_NAME, LONG_VALUE );
  private static final Variable< Boolean >      VAR_BOOLEAN        = variable ( FIRST_NAME, BOOLEAN_VALUE );
  private static final Variable< Float >        VAR_FLOAT          = variable ( FIRST_NAME, FLOAT_VALUE );
  private static final Variable< Double >       VAR_DOUBLE         = variable ( FIRST_NAME, DOUBLE_VALUE );
  private static final Variable< Object >       VAR_OBJECT         = variable ( FIRST_NAME, OBJECT_VALUE );
  private static final Variable< String >       VAR_STRING         = variable ( FIRST_NAME, STRING_VALUE );
  private static final Variable< CharSequence > VAR_CHAR_SEQ       = variable ( FIRST_NAME, CHAR_SEQ_VALUE );
  private static final Variable< Name >         VAR_NAME           = variable ( FIRST_NAME, NAME_VALUE );
  private static final Variable< Thread.State > VAR_ENUM           = variable ( FIRST_NAME, ENUM_CLASS, ENUM_VALUE );
  private static final Environment              ENV_STRING_VALUE   = environment ( name ( FIRST ), FIRST );

  private Method method;
  private Name   name;


  @Setup ( Level.Trial )
  public final void setup ()
  throws NoSuchMethodException {

    method =
      getClass ().getMethod (
        "setup"
      );

    name =
      name (
        method
      );

  }

  /**
   * Parse a composite path.
   */

  @Benchmark
  public static Name name_parse () {

    return
      name (
        FIRST_SECOND_THIRD
      );

  }

  /**
   * Create of a root name.
   */

  @Benchmark
  public static Name name_root () {

    return
      name (
        FIRST
      );

  }

  /**
   * Extend an existing name.
   */

  @Benchmark
  public Name name_name () {

    return
      name.name (
        FIRST
      );

  }

  /**
   * Extend an existing name.
   */

  @Benchmark
  public Name name_enum () {

    return
      name.name (
        BLOCKED
      );

  }


  /**
   * Call {@code Name.toString}
   */

  @Benchmark
  public String name_to_string () {

    return
      name.toString ();

  }

  /**
   * Create a composite name using fluid calls.
   */

  @Benchmark
  public static Name name_chain () {

    return
      name (
        FIRST
      ).name (
        SECOND
      ).name (
        THIRD
      );

  }

  /**
   * Create a composite name using varargs.
   */

  @Benchmark
  public static Name name_name_string_string () {

    return
      name (
        FIRST
      ).name (
        SECOND,
        THIRD
      );

  }

  /**
   * Create a composite name from a class.
   */

  @Benchmark
  public Name name_class () {

    return
      name (
        getClass ()
      );

  }

  /**
   * Create a composite name from a class and simple string.
   */

  @Benchmark
  public Name name_class_string () {

    return
      name (
        getClass (),
        "member"
      );

  }

  /**
   * Create a composite name from a method.
   */

  @Benchmark
  public Name name_method () {

    return
      name (
        method
      );

  }

  /**
   * Calls {@code Substrates.environment(string,value)}
   */

  @Benchmark
  public static Environment substrates_environment_string_value_create () {

    return
      environment (
        name ( FIRST ),
        FIRST
      );

  }

  /**
   * Calls {@code Substrates.environment()}
   */

  @Benchmark
  public static Environment substrates_environment () {

    return
      environment ();

  }

  /**
   * Calls {@code Environment.getString(name,value)}.
   */

  @Benchmark
  public static String environment_single_get_string () {

    return
      ENV_STRING_VALUE.getString (
        FIRST_NAME,
        STRING_VALUE
      );

  }

  /**
   * Calls {@code Environment.getString(name,value)}.
   */

  @Benchmark
  public static String environment_empty_get_string () {

    return
      EMPTY.getString (
        FIRST_NAME,
        STRING_VALUE
      );

  }


  /**
   * Calls {@code Environment.getString(name,value)}.
   */

  @Benchmark
  public static CharSequence environment_empty_get_char_seq () {

    return
      EMPTY.getCharSequence (
        FIRST_NAME,
        CHAR_SEQ_VALUE
      );

  }

  /**
   * Calls {@code Environment.getName(name,value)}.
   */

  @Benchmark
  public static Name environment_empty_get_name () {

    return
      EMPTY.getName (
        FIRST_NAME,
        NAME_VALUE
      );

  }

  /**
   * Calls {@code Environment.getName(name,value)}.
   */

  @Benchmark
  public static Enum< Thread.State > environment_empty_get_enum () {

    return
      EMPTY.getEnum (
        FIRST_NAME,
        ENUM_CLASS,
        ENUM_VALUE
      );

  }

  /**
   * Calls {@code Environment.getInteger(name,value)}.
   */

  @Benchmark
  public static Integer environment_empty_get_integer () {

    return
      EMPTY.getInteger (
        FIRST_NAME,
        INTEGER_VALUE
      );

  }

  /**
   * Calls {@code Environment.getLong(name,value)}.
   */

  @Benchmark
  public static Long environment_empty_get_long () {

    return
      EMPTY.getLong (
        FIRST_NAME,
        LONG_VALUE
      );

  }

  /**
   * Calls {@code Environment.getFloat(name,value)}.
   */

  @Benchmark
  public static Float environment_empty_get_float () {

    return
      EMPTY.getFloat (
        FIRST_NAME,
        FLOAT_VALUE
      );

  }

  /**
   * Calls {@code Environment.getDouble(name,value)}.
   */

  @Benchmark
  public static Double environment_empty_get_double () {

    return
      EMPTY.getDouble (
        FIRST_NAME,
        DOUBLE_VALUE
      );

  }


  /**
   * Calls {@code Environment.getBoolean(name,value)}.
   */

  @Benchmark
  public static Boolean environment_empty_get_boolean () {

    return
      EMPTY.getBoolean (
        FIRST_NAME,
        BOOLEAN_VALUE
      );

  }

  /**
   * Calls {@code Environment.environment(environment)}.
   */

  @Benchmark
  public static Environment environment_environment_environment () {

    return
      ENV_STRING_VALUE.override (
        EMPTY
      );

  }

  /**
   * Calls {@code Environment.environment(name,value)}.
   */

  @Benchmark
  public static Environment environment_environment_name_value () {

    return
      ENV_STRING_VALUE.override (
        NAME_VALUE,
        STRING_VALUE
      );

  }


  /**
   * Calls {@code Environment.environment(name,supplier)}.
   */

  @Benchmark
  public static Environment environment_environment_name_supplier () {

    return
      ENV_STRING_VALUE.override (
        NAME_VALUE,
        () -> STRING_VALUE
      );

  }

  /**
   * Calls {@code Variable<Integer>.of(environment)}.
   */

  @Benchmark
  public static Integer variable_of_integer () {

    return
      VAR_INTEGER.of (
        EMPTY
      );

  }

  /**
   * Calls {@code Variable<Long>.of(environment)}.
   */

  @Benchmark
  public static Long variable_of_long () {

    return
      VAR_LONG.of (
        EMPTY
      );

  }

  /**
   * Calls {@code Variable<Boolean>.of(environment)}.
   */

  @Benchmark
  public static Boolean variable_of_boolean () {

    return
      VAR_BOOLEAN.of (
        EMPTY
      );

  }

  /**
   * Calls {@code Variable<Float>.of(environment)}.
   */

  @Benchmark
  public static Float variable_of_float () {

    return
      VAR_FLOAT.of (
        EMPTY
      );

  }

  /**
   * Calls {@code Variable<Double>.of(environment)}.
   */

  @Benchmark
  public static Double variable_of_double () {

    return
      VAR_DOUBLE.of (
        EMPTY
      );

  }

  /**
   * Calls {@code Variable<String>.of(environment)}.
   */

  @Benchmark
  public static String variable_of_string () {

    return
      VAR_STRING.of (
        EMPTY
      );

  }

  /**
   * Calls {@code Variable<CharSequence>.of(environment)}.
   */

  @Benchmark
  public static CharSequence variable_of_char_seq () {

    return
      VAR_CHAR_SEQ.of (
        EMPTY
      );

  }


  /**
   * Calls {@code Variable<Name>.of(environment)}.
   */

  @Benchmark
  public static Name variable_of_name () {

    return
      VAR_NAME.of (
        EMPTY
      );

  }

  /**
   * Calls {@code Variable<Enum>.of(environment)}.
   */

  @Benchmark
  public static Enum< Thread.State > variable_of_enum () {

    return
      VAR_ENUM.of (
        EMPTY
      );

  }


  /**
   * Calls {@code Variable<Object>.of(environment)}.
   */

  @Benchmark
  public static Object variable_of_object () {

    return
      VAR_OBJECT.of (
        EMPTY
      );

  }


  /**
   * Call {@code Substrates.variable(name, long)}.
   */

  @Benchmark
  public static Variable< Long > substrates_variable_long () {

    return
      variable (
        FIRST_NAME,
        LONG_VALUE
      );

  }

  /**
   * Call {@code Substrates.variable(name, long)}.
   */

  @Benchmark
  public static Variable< Integer > substrates_variable_integer () {

    return
      variable (
        FIRST_NAME,
        INTEGER_VALUE
      );

  }

  /**
   * Call {@code Substrates.variable(name, string)}.
   */

  @Benchmark
  public static Variable< String > substrates_variable_string () {

    return
      variable (
        FIRST_NAME,
        STRING_VALUE
      );

  }

  /**
   * Call {@code Substrates.variable(name, double)}.
   */

  @Benchmark
  public static Variable< Double > substrates_variable_double () {

    return
      variable (
        FIRST_NAME,
        DOUBLE_VALUE
      );

  }

  /**
   * Call {@code Substrates.variable(name, double)}.
   */

  @Benchmark
  public static Variable< Float > substrates_variable_float () {

    return
      variable (
        FIRST_NAME,
        FLOAT_VALUE
      );

  }

  /**
   * Call {@code Substrates.variable(name, boolean)}.
   */

  @Benchmark
  public static Variable< Boolean > substrates_variable_boolean () {

    return
      variable (
        FIRST_NAME,
        BOOLEAN_VALUE
      );

  }

  /**
   * Call {@code Substrates.variable(name, enum)}.
   */

  @Benchmark
  public static Variable< Thread.State > substrates_variable_enum () {

    return
      variable (
        FIRST_NAME,
        ENUM_CLASS,
        ENUM_VALUE
      );

  }

  /**
   * Call {@code Substrates.variable(name, char_sequence)}.
   */

  @Benchmark
  public static Variable< CharSequence > substrates_variable_char_seq () {

    return
      variable (
        FIRST_NAME,
        CHAR_SEQ_VALUE
      );

  }

  /**
   * Call {@code Substrates.variable(name, object)}.
   */

  @Benchmark
  public static Variable< Object > substrates_variable_object () {

    return
      variable (
        FIRST_NAME,
        OBJECT_VALUE
      );

  }

}