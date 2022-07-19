/*
 * Copyright © 2020 Substrates Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package io.humainary.substrates.perfkit;

import io.humainary.devkit.perfkit.PerfKit.Target;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.humainary.devkit.perfkit.PerfKit.execute;
import static io.humainary.devkit.perfkit.PerfKit.target;

@TestMethodOrder (
  OrderAnnotation.class
)
final class DriverTest {

  private static final String PROFILE    = "spi";
  private static final String CONCURRENT = "name_";
  private static final String ALL        = "*";

  private static final Target TARGET =
    target (
      Driver.class,
      "substrates",
      "io.substrates.spi.alpha.ProviderFactory"
    );

  @Test
  @Order ( 1 )
  void one () {

    execute (
      TARGET,
      PROFILE,
      ALL,
      1,
      250.0,
      Assertions::fail
    );

  }

  @Test
  @Order ( 2 )
  void two () {

    execute (
      TARGET,
      PROFILE,
      CONCURRENT,
      2,
      500.0,
      Assertions::fail
    );

  }

  @Test
  @Order ( 4 )
  void four () {

    execute (
      TARGET,
      PROFILE,
      CONCURRENT,
      4,
      1000.0,
      Assertions::fail
    );

  }

}
