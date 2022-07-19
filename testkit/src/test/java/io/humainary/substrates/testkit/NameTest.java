/*
 * Copyright © 2022 JINSPIRED B.V.
 */

package io.humainary.substrates.testkit;

import io.humainary.substrates.Substrates.Name;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.ToIntFunction;

import static io.humainary.substrates.Substrates.name;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The test class for the {@link Name} interface.
 *
 * @author wlouth
 * @since 1.0
 */

final class NameTest {

  private static final String ROOT_VALUE = "root";
  private static final String ROOT_PATH  = ROOT_VALUE;
  private static final Name   ROOT_NAME  = name ( ROOT_PATH );
  private static final String DOT        = ".";
  private static final String NODE_VALUE = "node";
  private static final String NODE_PATH  = ROOT_PATH + DOT + NODE_VALUE;
  private static final Name   NODE_NAME  = name ( NODE_PATH );

  private final Function< Name, Integer > LENGTH =
    ( n ) -> n.value ().length ();

  private final ToIntFunction< Name > SUM =
    LENGTH::apply;

  @Test
  void root () {

    final var name =
      name (
        ROOT_PATH
      );

    assertEquals (
      ROOT_VALUE,
      name.value ()
    );

    assertFalse (
      name
        .enclosure ()
        .isPresent ()
    );

    assertSame (
      ROOT_NAME,
      name
    );

    assertEquals (
      ROOT_NAME,
      name
    );

    assertEquals (
      LENGTH.apply ( name ),
      name.foldFrom (
        LENGTH,
        ( v, n ) ->
          v + LENGTH.apply ( n )
      )
    );

    assertEquals (
      LENGTH.apply ( name ),
      (int) name.foldTo (
        n ->
          LENGTH.apply ( name ),
        ( v, n ) ->
          LENGTH.apply ( name ) + v
      )
    );

  }

  @Test
  void node () {

    final var name =
      name (
        NODE_PATH
      );

    assertEquals (
      NODE_VALUE,
      name.value ()
    );

    assertTrue (
      name
        .enclosure ()
        .isPresent ()
    );

    assertEquals (
      Optional.of (
        ROOT_NAME
      ),
      name.enclosure ()
    );

    assertSame (
      NODE_NAME,
      name
    );

    assertEquals (
      NODE_NAME,
      name
    );

    assertSame (
      name,
      ROOT_NAME.name (
        NODE_VALUE
      )
    );

    assertSame (
      name,
      name (
        ROOT_VALUE,
        NODE_VALUE
      )
    );

    assertSame (
      name.name (
        name
      ),
      name.name (
        name
      )
    );

    assertSame (
      name,
      name
        .name ( name )
        .enclosure ()
        .flatMap ( Name::enclosure )
        .orElseThrow ( AssertionError::new )
    );

    assertEquals (
      name
        .stream ()
        .mapToInt ( SUM )
        .sum (),
      name.foldFrom (
        LENGTH,
        ( v, n ) ->
          v + LENGTH.apply ( n )
      )
    );

    assertEquals (
      name
        .stream ()
        .mapToInt ( SUM )
        .sum (),
      (int) name.foldTo (
        n ->
          LENGTH.apply ( name ),
        ( v, n ) ->
          LENGTH.apply ( name ) + v
      )
    );

  }

}
