/*
 * Copyright © 2022 JINSPIRED B.V.
 */

package io.humainary.substrates;

import io.humainary.spi.Providers;
import io.humainary.substrates.spi.SubstratesProvider;

import java.lang.reflect.Member;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static io.humainary.substrates.Substrates.Environment.EMPTY;
import static java.util.Objects.requireNonNull;
import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
import static java.util.Spliterator.*;
import static java.util.Spliterators.spliteratorUnknownSize;

/**
 * An open and extensible interface that includes several concepts common to many library interfaces discovered
 * in designing open interfaces related to observability, diagnostics, and profiling over the last 20 years.
 *
 * @author wlouth
 * @since 1.0
 */

public final class Substrates {

  private static final SubstratesProvider PROVIDER =
    Providers.create (
      "io.humainary.substrates.spi.factory",
      "io.substrates.spi.alpha.ProviderFactory",
      SubstratesProvider.class
    );

  private Substrates () {
  }


  /**
   * Creates a provider defined default {@link Environment}.
   *
   * @return An {@link Environment} that sources its property values based on a provider specific runtime inspection mechanism
   * @see SubstratesProvider#environment()
   */

  public static Environment environment () {

    return
      PROVIDER.environment ();

  }

  /**
   * Creates a {@link Environment} that sources property values using a provided {@code Function}
   *
   * @param func the function used for sourcing property values
   * @return An {@link Environment} that sources its property values from a lookup
   * @throws NullPointerException if the func parameter is {@code null}
   * @see SubstratesProvider#environment(Lookup)
   */

  public static Environment environment (
    final Function< ? super Name, Object > func
  ) {

    return
      PROVIDER.environment (
        func
      );

  }


  /**
   * Creates a {@link Environment} that sources property values using a provided {@link Lookup}
   *
   * @param lookup the lookup used for sourcing property values
   * @return An {@link Environment} that sources its property values from a lookup
   * @throws NullPointerException if the lookup parameter is {@code null}
   * @see SubstratesProvider#environment(Lookup)
   */

  public static Environment environment (
    final Lookup< Object > lookup
  ) {

    return
      PROVIDER.environment (
        lookup
      );

  }


  /**
   * Creates an {@link Environment} with a single {@link Name} and value pairing.
   *
   * @param <T>   The property class type
   * @param name  The property name
   * @param value The property value
   * @return A {@link Environment} containing a single property name-value pair.
   * @throws NullPointerException if the name parameter is {@code null}
   * @see SubstratesProvider#environment(Name, Object)
   */

  public static < T > Environment environment (
    final Name name,
    final T value
  ) {

    return
      PROVIDER.environment (
        name,
        value
      );

  }


  /**
   * Creates an {@link Environment} with a single {@link Name} and value pairing.
   *
   * @param name  The property path
   * @param value The property value
   * @return A {@link Environment} containing a single property name-value pair.
   * @throws NullPointerException if the name parameter is {@code null}
   * @see SubstratesProvider#environment(Name, int)
   */

  public static Environment environment (
    final Name name,
    final int value
  ) {

    return
      PROVIDER.environment (
        name,
        value
      );

  }


  /**
   * Creates an {@link Environment} with a single {@link Name} and value pairing.
   *
   * @param name  The property path
   * @param value The property value
   * @return A {@link Environment} containing a single property name-value pair.
   * @throws NullPointerException if the name parameter is {@code null}
   * @see SubstratesProvider#environment(Name, long)
   */

  public static Environment environment (
    final Name name,
    final long value
  ) {

    return
      PROVIDER.environment (
        name,
        value
      );

  }


  /**
   * Creates an {@link Environment} with a single {@link Name} and value pairing.
   *
   * @param name  The property name
   * @param value The property value
   * @return A {@link Environment} containing a single property name-value pair.
   * @throws NullPointerException if the name parameter is {@code null}
   * @see SubstratesProvider#environment(Name, float)
   */

  public static Environment environment (
    final Name name,
    final float value
  ) {

    return
      PROVIDER.environment (
        name,
        value
      );

  }


  /**
   * Creates an {@link Environment} with a single {@link Name} and value pairing.
   *
   * @param name  The property name
   * @param value The property value
   * @return A {@link Environment} containing a single property name-value pair.
   * @throws NullPointerException if the name parameter is {@code null}
   * @see SubstratesProvider#environment(Name, double)
   */

  public static Environment environment (
    final Name name,
    final double value
  ) {

    return
      PROVIDER.environment (
        name,
        value
      );

  }

  /**
   * Creates an {@link Environment} with a single {@link Name} and value pairing.
   *
   * @param name  The property name
   * @param value The property value
   * @return A {@link Environment} containing a single property name-value pair.
   * @throws NullPointerException if the name parameter is {@code null}
   * @see SubstratesProvider#environment(Name, double)
   */

  public static Environment environment (
    final Name name,
    final boolean value
  ) {

    return
      PROVIDER.environment (
        name,
        value
      );

  }

  /**
   * Creates an {@link Environment} with a single {@link Name} and value pairing.
   *
   * @param <T>      The property type
   * @param name     The property name
   * @param supplier The property value supplier
   * @return A {@link Environment} containing a single property name-value pair.
   * @throws NullPointerException if either of the name or supplier parameters are {@code null}
   * @see SubstratesProvider#environment(Name, Object)
   */

  public static < T > Environment environment (
    final Name name,
    final Supplier< T > supplier
  ) {

    return
      PROVIDER.environment (
        name,
        supplier
      );

  }


  /**
   * Returns a {@link Name} from a string path.
   *
   * @param path the string to be parsed and returned as a {@link Name}
   * @return A {@link Name} mapped to the path string
   * @throws NullPointerException     if the path parameter is {@code null}
   * @throws IllegalArgumentException if processing of path does not result in a name
   * @see Name#name(String)
   * @see SubstratesProvider#name(String)
   */

  public static Name name (
    final String path
  ) {

    return
      PROVIDER.name (
        path
      );

  }


  /**
   * Returns a {@link Name} from a concatenation of two string paths.
   *
   * @param prefix the first string path to be parsed into a {@link Name}
   * @param suffix the second string path to be parsed and appended to first
   * @return A {@link Name} mapped to a concatenation of both paths joined with a separator
   * @throws NullPointerException     if either argument is {@code null}
   * @throws IllegalArgumentException if processing of the arguments does not result in a name
   * @see Name#name(String, String)
   * @see SubstratesProvider#name(String, String)
   */

  public static Name name (
    final String prefix,
    final String suffix
  ) {

    return
      PROVIDER.name (
        prefix,
        suffix
      );

  }


  /**
   * Create a {@link Name} from iterating over a specified {@link Iterable} of {@link String} values.
   *
   * @param iterable the {@link Iterable} to be iterated over
   * @return A {@link Name} as a result of the latest iteration of appendage
   * @throws NullPointerException     if the {@link Iterable} is {@code null} or one of the values returned is {@code null}
   * @throws IllegalArgumentException if processing of one of the iterations does not result in a new name
   * @see Name#name(Iterable)
   */

  public static Name name (
    final Iterable< String > iterable
  ) {

    return
      PROVIDER.name (
        iterable
      );

  }


  /**
   * Create a {@link Name} from iterating over a specified {@link Iterable} and applying a transformation function.
   *
   * @param <T>       the type of each value iterated over
   * @param iterable  the {@link Iterable} to be iterated over
   * @param transform the function to be used to transform the type to a String type
   * @return A {@link Name} as a result of the latest iteration of appendage
   * @throws NullPointerException     if the {@link Iterable} is {@code null} or one of the values returned is {@code null}
   * @throws IllegalArgumentException if processing of one of the iterations does not result in a new name
   * @see Name#name(Iterable, Function)
   */

  public static < T > Name name (
    final Iterable< ? extends T > iterable,
    final Function< T, String > transform
  ) {

    return
      PROVIDER.name (
        iterable,
        transform
      );

  }


  /**
   * Create a {@link Name} from iterating over a specified {@link Iterator} of {@link String} values.
   *
   * @param iterator the {@link Iterator} to be iterated over
   * @return A {@link Name} as a result of the latest iteration of appendage
   * @throws NullPointerException     if the {@link Iterator} is {@code null} or one of the values returned is {@code null}
   * @throws IllegalArgumentException if processing of one of the iterations does not result in a new name
   * @see Name#name(Iterable)
   */

  public static Name name (
    final Iterator< String > iterator
  ) {

    return
      PROVIDER.name (
        iterator
      );

  }


  /**
   * Create a {@link Name} from iterating over a specified {@link Iterator} and applying a transformation function.
   *
   * @param <T>       the type of each value iterated over
   * @param iterator  the {@link Iterator} to be iterated over
   * @param transform the function to be used to transform the type to a String type
   * @return A {@link Name} as a result of the latest iteration of appendage
   * @throws NullPointerException     if the {@link Iterator} is {@code null} or one of the values returned is {@code null}
   * @throws IllegalArgumentException if processing of one of the iterations does not result in a new name
   * @see Name#name(Iterable, Function)
   */

  public static < T > Name name (
    final Iterator< ? extends T > iterator,
    final Function< T, String > transform
  ) {

    return
      PROVIDER.name (
        iterator,
        transform
      );

  }

  /**
   * Creates a {@link Name} from a {@link Class}.
   *
   * @param namespace the {@link Class} to be mapped to a {@link Name}
   * @return A {@link Name} where {@code name.toString().equals(cls.getName())}
   * @throws NullPointerException if the {@link Class} typed parameter is {@code null}
   * @see SubstratesProvider#name(Class)
   */

  public static Name name (
    final Class< ? > namespace
  ) {

    return
      PROVIDER.name (
        namespace
      );

  }


  /**
   * Creates a {@link Name} from a {@link Class} and additional path string.
   *
   * @param prefix the {@link Class} to be mapped to a {@link Name}
   * @return A {@link Name} where {@code name.toString().equals(cls.getName() + "." + path)}
   * @throws NullPointerException if either parameter is {@code null}
   * @see SubstratesProvider#name(Class, String)
   */

  public static Name name (
    final Class< ? > prefix,
    final String suffix
  ) {

    return
      PROVIDER.name (
        prefix,
        suffix
      );

  }


  /**
   * Creates a {@link Name} from a {@link Member}.
   *
   * @param namespace the {@link Member} to be mapped to a {@link Name}
   * @return A {@link Name} mapped to the {@link Member}
   * @throws NullPointerException if the {@link Member} typed parameter is {@code null}
   */

  public static Name name (
    final Member namespace
  ) {

    return
      PROVIDER.name (
        namespace
      );

  }


  /**
   * Creates a {@link Variable} of type {@code Object}.
   *
   * @param name     the property name to be sourced
   * @param defValue the value to be used if the variable is not present in an environment passed
   * @return A {@code Variable} of type {@code Object}.
   */

  public static Variable< Object > variable (
    final Name name,
    final Object defValue
  ) {

    return
      PROVIDER.variable (
        name,
        defValue
      );

  }


  /**
   * Creates a {@link Variable} of type {@code T}.
   *
   * @param name     the property name to be sourced
   * @param type     the class type of the value returned
   * @param defValue the value to be used if the variable is not present in an environment passed
   * @param <T>      the class type of the variable
   * @return A {@code Variable} of type {@code T}.
   */

  public static < T > Variable< T > variable (
    final Name name,
    final Class< ? extends T > type,
    final T defValue
  ) {

    return
      PROVIDER.variable (
        name,
        type,
        defValue
      );

  }


  /**
   * Creates a {@link Variable} of type {@code T}.
   *
   * @param name     the property name to be sourced
   * @param type     the type of the value returned
   * @param alt      an alternative class type to be used as the base for a mapping
   * @param mapper   the function used to transform the alternative type to the return type or {@code null}
   * @param defValue the value to be used if the variable is not present in an environment passed
   * @param <T>      the class of the return value type
   * @param <A>      the class of the alternative value type
   * @return A {@code Variable} of type {@code T}.
   * @see Environment#getObject(Name, Class, Class, Function)
   */

  public static < T, A > Variable< T > variable (
    final Name name,
    final Class< ? extends T > type,
    final Class< ? extends A > alt,
    final Function< ? super A, ? extends T > mapper,
    final T defValue
  ) {

    return
      PROVIDER.variable (
        name,
        type,
        alt,
        mapper,
        defValue
      );

  }


  /**
   * Creates a {@link Variable} of type {@code Enum}.
   *
   * @param name     the property name to be sourced
   * @param type     the class type of the returned
   * @param defValue the value to be used if the variable is not present in an environment passed
   * @param <T>      the class of the return value type
   * @return A {@code Variable} of type {@code T}.
   * @see Environment#getEnum(Name, Class, Enum)
   */

  public static < T extends Enum< T > > Variable< T > variable (
    final Name name,
    final Class< ? extends T > type,
    final T defValue
  ) {

    return
      PROVIDER.variable (
        name,
        type,
        defValue
      );

  }


  /**
   * Creates a {@link Variable} of type {@code Boolean}.
   *
   * @param name     the property name to be sourced
   * @param defValue the value to be used if the variable is not present in an environment passed
   * @return A {@code Variable} of type {@code Boolean}.
   * @see Environment#getBoolean(Name, boolean)
   */

  public static Variable< Boolean > variable (
    final Name name,
    final Boolean defValue
  ) {

    return
      PROVIDER.variable (
        name,
        defValue
      );

  }


  /**
   * Creates a {@link Variable} of type {@code Integer}.
   *
   * @param name     the property name to be sourced
   * @param defValue the value to be used if the variable is not present in an environment passed
   * @return A {@code Variable} of type {@code Integer}.
   * @see Environment#getInteger(Name, int)
   */

  public static Variable< Integer > variable (
    final Name name,
    final Integer defValue
  ) {

    return
      PROVIDER.variable (
        name,
        defValue
      );

  }


  /**
   * Creates a {@link Variable} of type {@code Long}.
   *
   * @param name     the property name to be sourced
   * @param defValue the value to be used if the variable is not present in an environment passed
   * @return A {@code Variable} of type {@code Long}.
   * @see Environment#getLong(Name, long)
   */

  public static Variable< Long > variable (
    final Name name,
    final Long defValue
  ) {

    return
      PROVIDER.variable (
        name,
        defValue
      );

  }


  /**
   * Creates a {@link Variable} of type {@code Double}.
   *
   * @param name     the property name to be sourced
   * @param defValue the value to be used if the variable is not present in an environment passed
   * @return A {@code Variable} of type {@code Double}.
   * @see Environment#getDouble(Name, double)
   */

  public static Variable< Double > variable (
    final Name name,
    final Double defValue
  ) {

    return
      PROVIDER.variable (
        name,
        defValue
      );

  }


  /**
   * Creates a {@link Variable} of type {@code Float}.
   *
   * @param name     the property name to be sourced
   * @param defValue the value to be used if the variable is not present in an environment passed
   * @return A {@code Variable} of type {@code Float}.
   * @see Environment#getFloat(Name, float)
   */

  public static Variable< Float > variable (
    final Name name,
    final Float defValue
  ) {

    return
      PROVIDER.variable (
        name,
        defValue
      );

  }


  /**
   * Creates a {@link Variable} of type {@code String}.
   *
   * @param name     the property name to be sourced
   * @param defValue the value to be used if the variable is not present in an environment passed
   * @return A {@code Variable} of type {@code String}.
   * @see Environment#getString(Name, String)
   */

  public static Variable< String > variable (
    final Name name,
    final String defValue
  ) {

    return
      PROVIDER.variable (
        name,
        defValue
      );

  }


  /**
   * Creates a {@link Variable} of type {@code String}.
   *
   * @param name     the property name to be sourced
   * @param defValue the value to be used if the variable is not present in an environment passed
   * @return A {@code Variable} of type {@code CharSequence}.
   * @see Environment#getCharSequence(Name, CharSequence)
   */

  public static Variable< CharSequence > variable (
    final Name name,
    final CharSequence defValue
  ) {

    return
      PROVIDER.variable (
        name,
        defValue
      );

  }


  /**
   * Creates a {@link Variable} of type {@code Name}.
   *
   * @param name     the property name to be sourced
   * @param defValue the value to be used if the variable is not present in an environment passed
   * @return A {@code Variable} of type {@code String}.
   * @see Environment#getName(Name, Name)
   */

  public static Variable< Name > variable (
    final Name name,
    final Name defValue
  ) {

    return
      PROVIDER.variable (
        name,
        defValue
      );

  }


  /**
   * Creates a service provider interface (SPI) implementation of the {@link Hub} interface.
   *
   * @param <E> The class type of the emittance value
   * @return A service provider interface (SPI) implementation of the {@link Hub} interface
   */

  public static < E > Hub< E > hub () {

    return
      PROVIDER.hub ();

  }


  /**
   * Creates a service provider interface (SPI) implementation of the {@link Hub} interface.
   *
   * @param environment the environment used to configure the hub implementation
   * @param <E>         The class type of the emittance value
   * @return A service provider interface (SPI) implementation of the {@link Hub} interface
   */

  public static < E > Hub< E > hub (
    final Environment environment
  ) {

    return
      PROVIDER.hub (
        environment
      );

  }
  

  /**
   * A functional interface used for working around method overloading issues.
   *
   * @param <T> The return type of the {@link Fn#apply()}
   * @param <E> The exception type of the derived throwable
   */

  @FunctionalInterface
  public interface Fn< T, E extends Throwable > {

    /**
     * Use this function to force typing of an
     * overloaded method handle at compile time.
     *
     * @param fn  the function to be cast
     * @param <T> the return type of the function
     * @param <E> the exception type thrown by the function
     * @return A casting of the specified function
     */
    static < T, E extends Throwable > Fn< T, E > of (
      final Fn< T, E > fn
    ) {
      return fn;
    }


    /**
     * Invokes the underlying function.
     *
     * @return The result returned by the function
     * @throws E The derived throwable type thrown
     */

    T apply () throws E;

  }


  /**
   * An interface used for working around method overloading issues.
   *
   * @param <E> The exception type of the derived throwable
   */

  @FunctionalInterface
  public interface Op< E extends Throwable > {

    /**
     * Use this function to force typing of an
     * overloaded method handle at compile time.
     *
     * @param op  the operation to be cast
     * @param <E> the exception type thrown by the operation
     * @return A casting of the specified operation
     */

    static < E extends Throwable > Op< E > of (
      final Op< E > op
    ) {
      return op;
    }


    /**
     * Converts a {@link Fn} into an {@code Op}.
     *
     * @param fn  the {@link Fn} to be transformed
     * @param <T> the return type of the function
     * @param <E> the exception type thrown by the function
     * @return An operation that wraps the function
     */

    static < T, E extends Throwable > Op< E > from (
      final Fn< T, ? extends E > fn
    ) {
      return fn::apply;
    }


    /**
     * Invokes the underlying operation.
     *
     * @throws E The derived throwable type thrown
     */

    void apply () throws E;

  }


  /**
   * An extent is abstraction of a nested structure, where an extent (think extends) is enclosed with another extent.
   *
   * @param <T> The self referencing type class type
   * @see Name
   */

  public interface Extent<
    T extends Extent< T >
    > extends Iterable< T > {

    /**
     * The (parent/prefix) enclosing extent of this extent.
     *
     * @return An {@link Optional} holding a reference to the enclosing extent or {@link Optional#empty()}.
     */

    Optional< T > enclosure ();


    /**
     * Returns the extent instance that is referenced by this.
     *
     * @return A reference to this extent instance
     */

    T extent ();


    /**
     * Produces an accumulated value moving from left (root) to right (this) in the namespace.
     *
     * @param <R>         the return type of the accumulated value
     * @param initial     the function called to create the initial accumulated value
     * @param accumulator the function used to add a value to the accumulator
     * @return The accumulated result of performing the seed once and the accumulator.
     */

    default < R > R foldTo (
      final Function< ? super T, ? extends R > initial,
      final BiFunction< ? super R, ? super T, R > accumulator
    ) {

      return
        enclosure ().isPresent ()
        ? accumulator.apply ( foldTo ( initial, accumulator ), extent () )
        : initial.apply ( extent () );

    }


    /**
     * Produces an accumulated value moving from right (this) to left (root) in the extent.
     *
     * @param <R>         the return type of the accumulated value
     * @param initial     the function called to create the initial accumulated value
     * @param accumulator the function used to add a value to the accumulator
     * @return The accumulated result of performing the seed once and the accumulator.
     */

    default < R > R foldFrom (
      final Function< ? super T, ? extends R > initial,
      final BiFunction< ? super R, ? super T, R > accumulator
    ) {

      final var it =
        iterator ();

      var result =
        initial.apply (
          it.next ()
        );

      while ( it.hasNext () ) {

        result =
          accumulator.apply (
            result,
            it.next ()
          );

      }

      return
        result;

    }

    /**
     * Returns an iterator over the extents moving from right ({@code this}) to left ({@link #extremity()}).
     *
     * @return An iterator for traversing over the nested extents, starting with {@code this} extent instance.
     */

    @Override
    default Iterator< T > iterator () {

      //noinspection ReturnOfInnerClass
      return
        new Iterator<> () {

          private T extent =
            extent ();

          @Override
          public boolean hasNext () {

            return
              extent != null;

          }

          @Override
          public T next () {

            final var result = extent;

            if ( result == null )
              throw new NoSuchElementException ();

            extent =
              result
                .enclosure ()
                .orElse ( null );

            return
              result;

          }
        };

    }


    /**
     * Returns the depth of this extent within all enclosures.
     *
     * @return the depth of this extent within all enclosures.
     */

    default int depth () {

      return
        foldFrom (
          initial -> 1,
          ( depth, ignore ) -> depth + 1
        );

    }


    /**
     * Creates a {@link Spliterator} using the iterator returned from {@link #iterator()}.
     *
     * @return a {@code Spliterator} using the iterator returned from {@link #iterator()}.
     * @see #iterator()
     */

    @Override
    default Spliterator< T > spliterator () {

      return
        Spliterators.spliterator (
          iterator (),
          foldFrom (
            self -> 1,
            ( sum, name ) ->
              sum + 1
          ).longValue (),
          DISTINCT | NONNULL | IMMUTABLE
        );

    }


    /**
     * Returns a {@link Stream} containing each enclosing extent start with {@code this}.
     *
     * @return A {@code Stream} in which the first element is {@code this} extent instance,
     * and the last is the value returned from the {@link #extremity()} method.
     * @see #iterator()
     * @see #extremity()
     */

    default Stream< T > stream () {

      return
        StreamSupport.stream (
          spliterator (),
          false
        );

    }


    /**
     * Return the outermost (extreme) extent.
     *
     * @return The outermost extent.
     */

    default T extremity () {

      T prev;

      var current =
        extent ();

      do {

        prev =
          current;

        current =
          current
            .enclosure ()
            .orElse ( null );

      } while (
        current != null
      );

      return
        prev;

    }


    /**
     * Returns true if this {@link Extent} is directly or indirectly enclosed within the {@link Extent} parameter.
     *
     * @param enclosure the extent to be checked whether it enclosed this extent
     * @return true if the extent parameter encloses this extent, directly or indirectly
     */

    default boolean within (
      final Extent< T > enclosure
    ) {

      var current =
        extent ();

      do {

        current =
          current
            .enclosure ()
            .orElse ( null );

        if ( current == enclosure )
          return true;

      } while (
        current != null
      );

      return
        false;

    }

  }


  /**
   * Represents one or more name parts, much like a namespace.
   * <p>
   * Note: An SPI implementation of this interface is free to override
   * the default methods implementation included here.
   */

  public interface Name
    extends Substrate,
            Extent< Name >,
            Comparable< Name > {


    /**
     * The value for this name node.
     *
     * @return A non {@code null} string value.
     */

    String value ();


    /**
     * Returns a new name that has this name as a direct or indirect prefix.
     *
     * @param path the string to be parsed and appended to this name
     * @return A new name with the path appended as one or more name parts.
     */

    Name name ( String path );


    /**
     * Returns {@code this} name instance.
     *
     * @return A reference to {@code this} name instance
     * @see Extent#extent()
     */

    @Override
    default Name extent () {

      return
        this;

    }


    /**
     * Returns a new name that has this name as a direct prefix and a value of the enum name.
     *
     * @param value the enum to be appended to this name
     * @return A new name with the enum name appended a name part.
     */

    default Name name (
      final Enum< ? > value
    ) {

      return
        name (
          requireNonNull (
            value
          ).name ()
        );

    }


    /**
     * Returns a new name that has this name as a direct or indirect prefix.
     *
     * @param first  the first path to be appended to this name
     * @param second the second path to be appended to the result of the first path
     * @return A new name with both paths appended.
     */

    default Name name (
      final String first,
      final String second
    ) {

      return
        name ( first )
          .name ( second );

    }


    /**
     * Returns a new name that has this name as a direct or indirect prefix.
     *
     * @param path the name to be appended to this name
     * @return A new name with the path appended.
     */

    default Name name (
      final Name path
    ) {

      return
        path.foldTo (
          initial ->
            name (
              initial.value ()
            ),
          ( name, part ) ->
            name.name (
              part.value ()
            )
        );

    }


    /**
     * Returns a new extension of this name from iterating over a specified {@link Iterable} of {@link String} values.
     *
     * @param it the {@link Iterable} to be iterated over
     * @return A name as a result of the latest iteration of appendage
     * @throws NullPointerException if the {@link Iterable} is {@code null} or one of the values return is {@code null}
     * @see Substrates#name(Iterable)
     */

    default Name name (
      final Iterable< String > it
    ) {

      return
        name (
          it,
          Function.identity ()
        );

    }


    /**
     * Returns a new extension of this name from iterating over a specified {@link Iterable} and applying a transformation function.
     *
     * @param <T> the type of each value iterated over
     * @param it  the {@link Iterable} to be iterated over
     * @param fn  the function to be used to transform the type to a String type
     * @return A name as a result of the latest iteration of appendage
     * @throws NullPointerException if the {@link Iterable} is {@code null} or one of the values return is {@code null}
     * @see Substrates#name(Iterable, Function)
     */

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


    /**
     * Returns a new extension of this name from iterating over a specified {@link Iterator} of {@link String} values.
     *
     * @param it the {@link Iterator} to be iterated over
     * @return A name as a result of the latest iteration of appendage
     * @throws NullPointerException if the {@link Iterable} is {@code null} or one of the values return is {@code null}
     * @see Substrates#name(Iterable)
     */

    default Name name (
      final Iterator< String > it
    ) {

      return
        name (
          it,
          Function.identity ()
        );

    }


    /**
     * Returns a new extension of this name from iterating over a specified {@link Iterator} and applying a transformation function.
     *
     * @param <T> the type of each value iterated over
     * @param it  the {@link Iterator} to be iterated over
     * @param fn  the function to be used to transform the type to a String type
     * @return A name as a result of the latest iteration of appendage
     * @throws NullPointerException if the {@link Iterator} is {@code null} or one of the values return is {@code null}
     * @see Substrates#name(Iterable, Function)
     */

    default < T > Name name (
      final Iterator< ? extends T > it,
      final Function< T, String > fn
    ) {

      var name =
        this;

      while ( it.hasNext () ) {

        name =
          name.name (
            fn.apply (
              it.next ()
            )
          );

      }

      return
        name;

    }


    /**
     * Returns a {@link String} representation of the name,
     * with each name part separated by a ".".
     *
     * @return A non-{@code null} {@link String} representation.
     */

    default CharSequence toPath () {

      return
        foldTo (
          first -> new StringBuilder ( 25 ).append ( first ),
          ( result, name ) -> result.append ( '.' ).append ( name.value () )
        );

    }


    /**
     * Compares this {@code Name} instance with another.
     */

    @Override
    default int compareTo (
      final Name name
    ) {

      return
        this == name
        ? 0
        : toString ().compareTo ( name.toString () );

    }


    /**
     * Returns the string representation returned from {@link #toPath()}.
     *
     * @return A non-{@code null} {@link String} representation.
     * @see #toPath()
     */

    @Override
    String toString ();


  }


  /**
   * An abstraction for interfacing with existing configuration libraries and stores.
   * <p>
   * Note: An SPI implementation of this interface is free to override the
   * default methods implementation included here.
   */

  public interface Environment
    extends Lookup< Object >,
            Substrate {

    Environment EMPTY = ( name, defVal ) -> defVal;

    /**
     * Returns a typed value mapped to a name.
     *
     * @param name the property name to be sourced
     * @param type the class type of the value returned
     * @param <T>  the class of the return type and default value
     * @return An {@link Optional} holding the value matched or {@link Optional#empty()}.
     */

    default < T > Optional< T > getObject (
      final Name name,
      final Class< ? extends T > type
    ) {

      //noinspection unchecked
      return
        get ( name )
          .flatMap (
            object ->
              type.isInstance ( object )
              ? Optional.of ( (T) object )
              : empty ()
          );

    }


    /**
     * Returns a typed value mapped to a name.
     *
     * @param name     the property name to be sourced
     * @param type     the class type of the value returned
     * @param <T>      the class of the return type and default value
     * @param defValue the default value to return if not matched
     * @return The value matched, or the provided default value.
     */

    default < T > T getObject (
      final Name name,
      final Class< T > type,
      final T defValue
    ) {

      return
        getObject ( name, type )
          .orElse ( defValue );

    }


    /**
     * Returns a typed value mapped to a name.
     *
     * @param name   the property name to be sourced
     * @param type   the type of the value returned
     * @param alt    an alternative class type to be used as the base for a mapping
     * @param mapper the function used to transform the alternative type to the return type or null
     * @param <T>    the class of the return value type
     * @param <A>    the class of the alternative value type
     * @return An {@link Optional} holding the value matched or {@link Optional#empty()}.
     */

    default < T, A > Optional< T > getObject (
      final Name name,
      final Class< ? extends T > type,
      final Class< ? extends A > alt,
      final Function< ? super A, ? extends T > mapper
    ) {

      return
        get ( name )
          .flatMap (
            value -> {

              if ( type.isInstance ( value ) ) {

                //noinspection unchecked
                return
                  Optional.of (
                    (T) value
                  );

              } else if ( alt.isInstance ( value ) ) {

                //noinspection unchecked
                return
                  ofNullable (
                    mapper.apply (
                      (A) value
                    )
                  );

              } else {

                return
                  empty ();

              }

            }
          );

    }


    /**
     * Returns a {@link String} value mapped to a name.
     *
     * @param name the property name to be sourced
     * @return An {@link Optional} holding the value matched or {@link Optional#empty()}.
     */

    default Optional< String > getString (
      final Name name
    ) {

      return
        getObject (
          name,
          String.class,
          CharSequence.class,
          CharSequence::toString
        );

    }


    /**
     * Returns a {@link String} value mapped to a name.
     *
     * @param name     the property name to be sourced
     * @param defValue the default value to return if not matched
     * @return The value matched, or the provided default value.
     */

    default String getString (
      final Name name,
      final String defValue
    ) {

      return
        getString ( name )
          .orElse ( defValue );

    }


    /**
     * Returns a {@link CharSequence} value mapped to a name.
     *
     * @param name the property name to be sourced
     * @return An {@link Optional} holding the value matched or {@link Optional#empty()}.
     */

    default Optional< CharSequence > getCharSequence (
      final Name name
    ) {

      return
        getObject (
          name,
          CharSequence.class
        );

    }


    /**
     * Returns a {@link CharSequence} value mapped to a name.
     *
     * @param name     the property name to be sourced
     * @param defValue the default value to return if not matched
     * @return The value matched, or the provided default value.
     */

    default CharSequence getCharSequence (
      final Name name,
      final CharSequence defValue
    ) {

      return
        getCharSequence ( name )
          .orElse ( defValue );

    }


    /**
     * Returns a {@link Boolean} value mapped to a name.
     *
     * @param name the property name to be sourced
     * @return An {@link Optional} holding the value matched or {@link Optional#empty()}.
     */

    default Optional< Boolean > getBoolean (
      final Name name
    ) {

      return
        getObject (
          name,
          Boolean.class,
          String.class,
          Boolean::parseBoolean
        );

    }


    /**
     * Returns a {@link Boolean} value mapped to a name.
     *
     * @param name     the property name to be sourced
     * @param defValue the default value to return if not matched
     * @return The value matched, or the provided default value.
     */

    default Boolean getBoolean (
      final Name name,
      final Boolean defValue
    ) {

      return
        getBoolean ( name )
          .orElse ( defValue );

    }


    /**
     * Returns a boolean value mapped to a name.
     *
     * @param name     the property name to be sourced
     * @param defValue the default value to return if not matched
     * @return The value matched, or the provided default value.
     */

    default boolean getBoolean (
      final Name name,
      final boolean defValue
    ) {

      return
        getBoolean ( name )
          .orElse ( defValue );

    }


    /**
     * Returns a {@link Long} value mapped to a name.
     *
     * @param name the property name to be sourced
     * @return An {@link Optional} holding the value matched, or {@link Optional#empty()}.
     */

    default Optional< Long > getLong (
      final Name name
    ) {

      return
        getObject (
          name,
          Long.class,
          String.class,
          Long::parseLong
        );

    }


    /**
     * Returns a long value mapped to a name.
     *
     * @param name     the property name to be sourced
     * @param defValue the default value to return if not matched
     * @return The value matched, or the provided default value.
     */

    default long getLong (
      final Name name,
      final long defValue
    ) {

      return
        getLong ( name )
          .orElse ( defValue );

    }


    /**
     * Returns a {@link Long} value mapped to a name.
     *
     * @param name     the property name to be sourced
     * @param defValue the default value to return if not matched
     * @return The value matched, or the provided default value.
     */

    default Long getLong (
      final Name name,
      final Long defValue
    ) {

      return
        getLong ( name )
          .orElse ( defValue );

    }


    /**
     * Returns a {@link Integer} value mapped to a name.
     *
     * @param name the property name to be sourced
     * @return An {@link Optional} holding the value matched or {@link Optional#empty()}.
     */

    default Optional< Integer > getInteger (
      final Name name
    ) {

      return
        getObject (
          name,
          Integer.class,
          String.class,
          Integer::parseInt
        );

    }


    /**
     * Returns an integer value mapped to a name.
     *
     * @param name     the property name to be sourced
     * @param defValue the default value to return if not matched
     * @return The value matched, or the provided default value.
     */

    default int getInteger (
      final Name name,
      final int defValue
    ) {

      return
        getInteger ( name )
          .orElse ( defValue );

    }


    /**
     * Returns an integer value mapped to a name.
     *
     * @param name     the property name to be sourced
     * @param defValue the default value to return if not matched
     * @return The value matched, or the provided default value.
     */

    default Integer getInteger (
      final Name name,
      final Integer defValue
    ) {

      return
        getInteger ( name )
          .orElse ( defValue );

    }


    /**
     * Returns a {@link Double} value mapped to a name.
     *
     * @param name the property name to be sourced
     * @return An {@link Optional} holding the value matched or {@link Optional#empty()}.
     */

    default Optional< Double > getDouble (
      final Name name
    ) {

      return
        getObject (
          name,
          Double.class,
          String.class,
          Double::parseDouble
        );

    }


    /**
     * Returns a {@link Double} value mapped to a name.
     *
     * @param name     the property name to be sourced
     * @param defValue the default value to return if not matched
     * @return The value matched, or the provided default value.
     */

    default Double getDouble (
      final Name name,
      final Double defValue
    ) {

      return
        getDouble ( name )
          .orElse ( defValue );

    }


    /**
     * Returns a double value mapped to a name.
     *
     * @param name     the property name to be sourced
     * @param defValue the default value to return if not matched
     * @return The value matched, or the provided default value.
     */

    default double getDouble (
      final Name name,
      final double defValue
    ) {

      return
        getDouble ( name )
          .orElse ( defValue );

    }


    /**
     * Returns a {@link Float} value mapped to a name.
     *
     * @param name the property name to be sourced
     * @return An {@link Optional} holding the value matched or {@link Optional#empty()}.
     */

    default Optional< Float > getFloat (
      final Name name
    ) {

      return
        getObject (
          name,
          Float.class,
          String.class,
          Float::parseFloat
        );

    }


    /**
     * Returns a {@link Float} value mapped to a name.
     *
     * @param name     the property name to be sourced
     * @param defValue the default value to return if not matched
     * @return The value matched, or the provided default value.
     */

    default float getFloat (
      final Name name,
      final Float defValue
    ) {

      return
        getFloat ( name )
          .orElse ( defValue );

    }


    /**
     * Returns a float value mapped to a name.
     *
     * @param name     the property name to be sourced
     * @param defValue the default value to return if not matched
     * @return The value matched, or the provided default value.
     */

    default float getFloat (
      final Name name,
      final float defValue
    ) {

      return
        getFloat ( name )
          .orElse ( defValue );

    }


    /**
     * Returns an {@link Enum} value mapped to a name.
     *
     * @param name the property name to be sourced
     * @param type the enum class type of the value returned
     * @param <T>  the enum class of the return type and default value
     * @return An {@link Optional} holding the enum value matched or {@link Optional#empty()}.
     */

    default < T extends Enum< T > > Optional< T > getEnum (
      final Name name,
      final Class< T > type
    ) {

      return
        getObject (
          name,
          type,
          String.class,
          value ->
            Enum.valueOf (
              type,
              value
            )
        );

    }


    /**
     * Returns an {@link Enum} value mapped to a name.
     *
     * @param name     the property name to be sourced
     * @param type     the enum class type of the value returned
     * @param <T>      the enum class of the return type and default value
     * @param defValue the default value to return if not matched
     * @return The {@link Enum} value matched, or the provided default value.
     */

    default < T extends Enum< T > > T getEnum (
      final Name name,
      final Class< T > type,
      final T defValue
    ) {

      return
        getEnum ( name, type )
          .orElse ( defValue );

    }


    /**
     * Returns an {@link Name} value mapped to a name.
     *
     * @param name the property name to be sourced
     * @return An {@link Optional} holding the enum value matched or {@link Optional#empty()}.
     */

    default Optional< Name > getName (
      final Name name
    ) {

      return
        getObject (
          name,
          Name.class,
          String.class,
          Substrates::name
        );

    }


    /**
     * Returns an {@link Name} value mapped to a name.
     *
     * @param name     the property name to be sourced
     * @param defValue the default value to return if not matched
     * @return The value matched, or the provided default value.
     */

    default Name getName (
      final Name name,
      final Name defValue
    ) {

      return
        getName ( name )
          .orElse ( defValue );

    }


    /**
     * Returns a {@code Environment} that overrides (or supplies) the value for a particular path within an environment.
     *
     * @param name  the property name to be sourced
     * @param value the value returned on match
     * @return A {@code Environment} that overrides (or supplies) the value for a particular name within an environment.
     */

    default Environment override (
      final Name name,
      final Object value
    ) {

      return
        ( lookup, defVal ) ->
          name == lookup
          ? value
          : get ( lookup, defVal );

    }


    /**
     * Returns a {@code Environment} that overrides (or supplies) the value for a particular path within an environment.
     *
     * @param name     the property name to be sourced
     * @param supplier the supplier used to provide a value on match
     * @return A {@code Environment} that overrides (or supplies) the value for a particular path within an environment.
     */

    default Environment override (
      final Name name,
      final Supplier< Object > supplier
    ) {

      return
        ( lookup, defVal ) ->
          name == lookup
          ? ofNullable ( supplier.get () ).orElse ( defVal )
          : get ( lookup, defVal );

    }


    /**
     * Returns a {@code Environment} that first performs a property lookup in the specified
     * {@code Environment} and then falls back to this {@code Environment}.
     *
     * @param primary the {@code Environment} used as the initial lookup target
     * @return A {@code Environment} that chains {@code this} environment, and the provided environment together.
     */

    default Environment override (
      final Environment primary
    ) {

      return
        ( lookup, defVal ) ->
          primary.get (
            lookup
          ).orElseGet (
            () ->
              get (
                lookup,
                defVal
              )
          );

    }


    /**
     * Returns a {@code Environment} that caches lookup results across calls
     *
     * @return A {@code Environment} that is fronted by a cache containing name value mappings.
     */

    default Environment memorize () {

      final Map< Name, Object > map =
        new ConcurrentHashMap<> ();

      return
        ( lookup, defVal ) ->
          ofNullable (
            map.computeIfAbsent (
              lookup,
              key ->
                get (
                  lookup,
                  null
                )
            )
          ).orElse (
            defVal
          );

    }


    /**
     * Returns a {@code Environment} that applies a function to the name parameter before being calls onto this {@code Environment}
     *
     * @param mapper A function that returns an alternative name or the same name passed
     * @return A {@code Environment} that allows a remapping of the name before being looked up in this {@code Environment}
     */

    default Environment remap (
      final Function< ? super Name, ? extends Name > mapper
    ) {

      return
        ( name, defVal ) ->
          get (
            mapper.apply (
              name
            ),
            defVal
          );

    }


    /**
     * Returns a {@code Environment} that tests a predicate to decide whether to pass the call onto this {@code Environment}
     *
     * @param filter the predicate to be tested before calling onto this {@code Environment}
     * @return A {@code Environment} that allows a remapping of the name before being looked up in this {@code Environment}
     */

    default Environment filter (
      final Predicate< ? super Name > filter
    ) {

      return
        ( name, defVal ) ->
          filter.test ( name ) ?
          get ( name, defVal ) :
          null;

    }

  }


  /**
   * An interface for service provider produced object types exposing configuration and runtime state for inspection purposes.
   */

  public interface Substrate {

    /**
     * Returns the {@link Environment} associated with this object for the purpose of state inspection
     *
     * @return A non-{@code null} reference to the {@link Environment} associated with context
     */

    default Environment environment () {

      return
        EMPTY;

    }

  }


  /**
   * An interface used to identify and track a referable {@link Substrate} across
   * event processing without providing direct access to the emitting {@link Substrate}.
   */

  public interface Reference
    extends Substrate {

    /**
     * Returns the {@link Name} associated with the {@link Referent}.
     *
     * @return A non-null {@link Name} associated with a {@link Referent}
     */

    Name name ();

    /**
     * Returns the {@link Type} identifying the interface type of {@link Referent}.
     *
     * @return A non-null {@link Type} associated with a {@link Referent} interface type
     */

    Type type ();

  }

  /**
   * A {@link Referent} is a recurrent {@link Substrate} that can be
   * identified and partially inspected via a {@link Reference}.
   */

  public interface Referent
    extends Substrate {

    /**
     * Returns the {@link Reference} identifying the {@link Substrate} within a {@link Context}
     * along with an {@link Environment} offering additional environment information particular
     * to the {@link Referent} and {@link Reference}.
     *
     * @return A non-null reference identifying this substrate instance
     */

    Reference reference ();

  }


  /**
   * A marker interface for observability instruments - interfaces that measure and/or emit some phenomenon
   */

  public interface Instrument
    extends Referent {

  }


  /**
   * A functional interface that abstracts the means for looking up values mapped to a {@link Name}
   *
   * @param <T> The class type of the mapped value
   * @see Container
   * @see Environment
   */

  @FunctionalInterface
  public interface Lookup< T > {

    /**
     * Returns an optional holding the named value or an empty optional
     *
     * @param name a non-{@code null} name referenced looked up within the map
     * @return An optional holding the named value or an empty optional
     */

    default Optional< T > get (
      final Name name
    ) {

      return
        ofNullable (
          get (
            name,
            null
          )
        );

    }


    /**
     * Returns the {@link Object} value mapped to a name.
     *
     * @param name     the property name to be sourced
     * @param defValue the default value to return if not matched
     * @return The value matched, or the provided default value.
     */

    T get (
      final Name name,
      final T defValue
    );

  }


  /**
   * An interface for mapping a {@link Name} to a (named) value
   *
   * @param <T> the class type of the (named) values created on-demand
   */

  public interface Container<
    T extends Referent
    > extends Lookup< T >,
              Iterable< T >,
              Substrate {


    /**
     * Returns a stream of referents contained within the container.
     *
     * @return A stream of referents contained with the container.
     */

    default Stream< T > stream () {

      return
        StreamSupport.stream (
          spliteratorUnknownSize (
            iterator (),
            DISTINCT | NONNULL
          ),
          false
        );

    }


  }


  /**
   * An interface used to emit an emittance value via a {@link  Conduit} that wraps the publication within an {@link Event}.
   *
   * @param <T> the class type of the emitted value
   */

  public interface Inlet< T >
    extends Referent {

    /**
     * A publication method that will wrap the provided value within an {@link Event} before delivery to a registered {@link Outlet}
     *
     * @param value the value to be wrapped within an {@link Event}
     */

    void emit (
      T value
    );


    /**
     * A publication method that allows for the value to be materialized only when there is a registered {@link Outlet}
     *
     * @param supplier the supplier of the value to be wrapped within an {@link Event}
     */

    default void emit (
      final Supplier< ? extends T > supplier
    ) {

      emit (
        supplier.get ()
      );

    }

  }


  /**
   * An interface that creates and manages {@link Inlet} instances mapped to a {@link Reference}.
   *
   * @param <T> the class type of the emitted value
   */

  public interface Conduit< T >
    extends Substrate {

    /**
     * Returns the {@link Inlet} mapped to the {@link Reference} provided.
     *
     * @param reference the {@code Reference} used by the {@code Inlet} when publishing emittances
     * @return An {@code Inlet} instance for publishing emittances produced by the {@code Reference}
     */

    Inlet< T > inlet (
      Reference reference
    );

  }


  /**
   * An interface that is a composition of both {@link Source} and {@link Conduit} interfaces.
   *
   * @param <T> the class type of emitted value
   */

  public interface Hub< T >
    extends Source< T >,
            Conduit< T > {

  }


  /**
   * A marker interface for observability contexts - interfaces that create instruments for an environment and relay events.
   *
   * @param <I> the class type of the instrument
   * @param <T> the class type of emitted value
   */

  public interface Context<
    I extends Instrument, T
    > extends Source< T >,
              Container< I > {

    /**
     * The {@link Name} to be used for looking up the ID within the environment, supplied or otherwise.
     * This need only be supplied if the context is to be shared across multiple caller sites and threads.
     */

    Name ID = name ( "io.humainary.substrates.context" ).name ( "id" );

    /**
     * The {@link Name} to be used for looking up the UUID within the environment, supplied or otherwise.
     * This is expected to be generated on-demand for those callers interested in additional tracking.
     */

    Name UUID = name ( "io.humainary.substrates.context" ).name ( "uuid" );

  }


  /**
   * Represents an envelope enclosing a value by emitted by a {@link Source}.
   *
   * @param <T> the class type of emitted value
   */

  public interface Event< T >
    extends Substrate {

    /**
     * The {@link Reference} identifying the emitting {@link Referent}.
     *
     * @return The {@code Reference} identifying the emitting {@code Referent}.
     */

    Reference emitter ();


    /**
     * Returns the value that was emitted by the {@link Reference}
     *
     * @return The value emitted
     */

    T emittance ();


    /**
     * Returns a new {@code Event} instance with a transformed value using the transformer provided.
     *
     * @param transformer the function used to transform the emittance value from one type to another
     * @param <R>         the class type of the new event emittance value
     * @return A new {@code Event} instance containing the result of the emittance transformation
     */

    < R > Event< R > map (
      Function< ? super T, ? extends R > transformer
    );

  }


  /**
   * An interface for subscribing to source events.
   *
   * @param <T> the class type of emitted value
   */

  public interface Source< T >
    extends Substrate {

    /**
     * Adds a {@link Subscriber} to receive events from a source with the ability to
     * dynamically select and filter by named event.
     *
     * @param subscriber the subscriber to be registered
     * @return The subscription used to control future delivery of messages.
     */

    Subscription subscribe (
      Subscriber< T > subscriber
    );


    /**
     * Attaches a {@link Outlet} to the source *without* selective abilities
     *
     * @param outlet the outlet to be used to this receive all events
     */

    default Subscription consume (
      final Outlet< T > outlet
    ) {

      // implementations are free to override and optimize
      return
        subscribe (
          subscriber (
            outlet
          )
        );

    }


    /**
     * Returns a source that transforms event emittance of type {@link T} to type {@link R}.
     *
     * @param func the mapping function to be applied to the (original) event of type {@link T} before being relayed on
     * @param <R>  the class type of the sourced events dispatched from the returned source
     * @return A source that transforms event emittance of type {@link T} to type {@link R}
     */

    default < R > Source< R > source (
      final Function< ? super T, ? extends R > func
    ) {

      return
        subscriber ->
          subscribe (
            ( reference, registrar ) ->
              subscriber.accept (
                reference,
                outlet ->
                  registrar.register (
                    outlet.compose (
                      event ->
                        event.map (
                          func
                        )
                    )
                  )
              )
          );

    }

  }


  /**
   * A utility interface used for efficiently retrieving a value from an {@link Environment} or {@link Substrate}.
   *
   * @param <T> the class type of the value extracted from the target {@link Environment}
   */

  public interface Variable< T >
    extends Substrate {

    /**
     * Returns a value from the environment.
     *
     * @param environment the environment used for sourcing a value
     * @return A value sourced from the environment, or the variable's default value.
     */

    T of (
      final Environment environment
    );


    /**
     * Returns a value from the environment of the context provided.
     *
     * @param substrate the environmental instance to be introspected
     * @return A value sourced from the environment, or the variable's default value.
     */

    default T of (
      final Substrate substrate
    ) {

      return
        of (
          substrate
            .environment ()
        );

    }

  }


  /**
   * An interface used for registering a {@link Outlet} with a {@link Referent} for the purpose of event consumption.
   *
   * @param <T> the class type of the emitted value
   */

  public interface Registrar< T >
    extends Substrate {

    /**
     * Registers a {@link Outlet} with a {@link Registrar} associated with a {@link Referent}.
     *
     * @param outlet The {@link Outlet} to be associated with the {@link Referent}
     */

    void register (
      Outlet< T > outlet
    );

  }

  /**
   * A functional interface for registering interest in receiving events from a named object within a source.
   *
   * @param <T> the class type of the emitted value
   */

  @FunctionalInterface
  public interface Subscriber< T > {

    /**
     * @param reference the reference for which the event relates to within a source
     * @param registrar a registrar used for registering an outlet to capture sourced events
     */

    void accept (
      Reference reference,
      Registrar< T > registrar
    );

  }

  /**
   * An interface used for unregistering interest in receiving subscribed events.
   */

  public interface Subscription
    extends AutoCloseable,
            Substrate {

    /**
     * Cancels the subscription, relinquishing any underlying registrations.
     * This method is invoked automatically on objects managed by the
     * {@code try}-with-resources statement.
     *
     * @see #cancel()
     */

    @Override
    default void close () {

      cancel ();

    }

    /**
     * Cancels the subscription, relinquishing any underlying registrations.
     *
     * @see #close()
     */

    void cancel ();

  }


  /**
   * An interface used for capturing/collecting sourced events.
   *
   * @param <T> the class type of the emitted value
   */

  @FunctionalInterface
  public interface Outlet< T > {

    Outlet< ? > EMPTY =
      event -> {
      };


    /**
     * Return an outlet that discards the event passed to it.
     *
     * @param <E> the class type of the event parameter
     * @return An outlet that discards events passed to it
     */

    static < E > Outlet< E > empty () {

      //noinspection unchecked
      return
        (Outlet< E >) EMPTY;

    }


    /**
     * Accepts a sourced event from a referent.
     *
     * @param event the emittance published by the referent
     */

    void accept (
      Event< T > event
    );


    /**
     * Returns an outlet that dispatches first to this outlet and then to the provided outlet.
     *
     * @param after a non-{@code null} outlet reference
     * @return An outlet that dispatches first to this outlet and then to the provided outlet
     */

    default Outlet< T > andThen (
      final Outlet< T > after
    ) {

      requireNonNull (
        after
      );

      return
        event -> {

          accept (
            event
          );

          after.accept (
            event
          );

        };

    }


    /**
     * Returns an outlet that accepts an emittance type of the provided function input and used
     * the function to transform from the new emittance type to the emittance type of this outlet.
     *
     * @param func the function used to transform the new input type to this input type
     * @param <I>  the class type of the new (input) event type
     * @return An outlet that processes input of a different type before relaying onto this outlet.
     */

    default < I > Outlet< I > compose (
      final Function< ? super Event< I >, ? extends Event< T > > func
    ) {

      return
        event ->
          accept (
            func.apply (
              event
            )
          );

    }

  }


  /**
   * An interface representing the interface type for a {@link Referent}.
   *
   * @see Reference#type()
   * @see #name(Class)
   * @see #reference(Type, Name)
   * @see #reference(Type, Name, Environment)
   */

  public interface Type
    extends Substrate {

    /**
     * The {@link Name} identifying the {@link Referent} interface type.
     *
     * @return A non-null {@link Name} instance identifying the interface type
     */

    Name name ();


  }


  /**
   * A utility method for transforming a @{@code Function} into a {@link Lookup}.
   *
   * @param fn  the function to be transformed
   * @param <T> the return type of the function
   * @return A lookup that returns an Optional around the returned result
   */

  public static < T > Lookup< T > lookup (
    final Function< ? super Name, ? extends T > fn
  ) {

    return
      PROVIDER.lookup (
        fn
      );

  }


  /**
   * Transform a {@link Lookup} into a {@code Function} that calls the {@link Lookup#get(Name, Object)} with {@code null} as the default value.
   *
   * @param lookup a non-null lookup reference that is converted to a single argument (name) function
   * @return A function that calls the lookup method with the name provide and a default value of {@code null}
   */

  public static < T > Function< Name, T > function (
    final Lookup< T > lookup
  ) {

    return
      PROVIDER.function (
        lookup
      );

  }


  /**
   * Transform a {@link Lookup} into a {@code Function} that calls the {@link Lookup#get(Name, Object)} with a specified default value.
   *
   * @param lookup a non-null lookup reference that is converted to a single argument (name) function
   * @param defVal the default value supplied to the lookup when the function is invoked
   * @return A function that calls the lookup method with the name provide and a default value
   */

  public static < T > Function< Name, T > function (
    final Lookup< T > lookup,
    final T defVal
  ) {

    return
      PROVIDER.function (
        lookup,
        defVal
      );

  }


  /**
   * Transform a {@link Lookup} into a {@code Function} that calls the {@link Lookup#get(Name, Object)} with a {@code Supplier}.
   *
   * @param lookup   a non-null lookup reference that is converted to a single argument (name) function
   * @param supplier the function to be called when the lookup returns a {@code null}
   * @return A function that calls the lookup method with the name provide and a default value
   */

  public static < T > Function< Name, T > function (
    final Lookup< T > lookup,
    final Supplier< ? extends T > supplier
  ) {

    return
      PROVIDER.function (
        lookup,
        supplier
      );

  }


  /**
   * Returns a {@link Subscriber} that uses the provided {@link Outlet} for all {@link Reference} registrations.
   *
   * @param outlet the {@link Outlet} to be registered with each and every {@link Reference} of a {@link Source}
   * @param <T>    the class type of the {@link Event} emittance value
   * @return A {@link Subscriber} that uses the provided {@code Outlet} for all {@code Reference} registrations.
   */

  public static < T > Subscriber< T > subscriber (
    final Outlet< T > outlet
  ) {

    return
      PROVIDER.subscriber (
        outlet
      );

  }


  /**
   * Returns an {@link Outlet} that forwards on incoming events to a {@link Conduit}.
   *
   * @param conduit the {@link Conduit} to which events should be forwarded to
   * @param <T>     the class type of the {@link Event} emittance value
   * @return An {@link Outlet} that forwards events onto the specified conduit
   */

  public static < T > Outlet< T > outlet (
    final Conduit< ? super T > conduit
  ) {

    return
      PROVIDER.outlet (
        conduit
      );

  }


  /**
   * Returns the {@link Type} mapped to the {@link Referent} interface class.
   *
   * @param type the interface class of the {@link Referent}
   * @return A non-null {@link Type} mapped to the specified interface class
   * @see #name(Class)
   */

  public static Type type (
    final Class< ? extends Referent > type
  ) {

    return
      PROVIDER.type (
        type
      );

  }


  /**
   * Returns a {@link Reference} created with the provided parameters.
   *
   * @param type the class type of the {@link Referent}
   * @param name the name identifying the {@link Referent} within some scope
   * @return A non-null {@link Reference} instance with the provided parameters set as fields
   */


  public static Reference reference (
    final Type type,
    final Name name
  ) {

    return
      PROVIDER.reference (
        type,
        name
      );

  }


  /**
   * Returns a {@link Reference} created with the provided parameters.
   *
   * @param type        the class type of the {@link Referent}
   * @param name        the name identifying the {@link Referent} within some scope
   * @param environment the environment providing additional properties
   * @return A non-null {@link Reference} instance with the provided parameters set as fields
   */

  public static Reference reference (
    final Type type,
    final Name name,
    final Environment environment
  ) {

    return
      PROVIDER.reference (
        type,
        name,
        environment
      );

  }

}
