/*
 * Copyright 2015-2018 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v20.html
 */

package org.junit.jupiter.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Assume;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.test.event.ExecutionEventRecorder;

/**
 * Integration tests that verify support for failed assumptions in the
 * {@link JupiterTestEngine}.
 *
 * @since 5.4
 */
class FailedAssumptionsTests extends AbstractJupiterTestEngineTests {

	@Test
	void testAbortedExceptionInBeforeAll() {
		ExecutionEventRecorder eventRecorder = executeTestsForClass(TestAbortedExceptionInBeforeAllTestCase.class);

		assertEquals(1, eventRecorder.getContainerAbortedCount(), "# containers aborted");
		assertEquals(0, eventRecorder.getTestStartedCount(), "# tests started");
	}

	@Test
	void assumptionViolatedExceptionInBeforeAll() {
		ExecutionEventRecorder eventRecorder = executeTestsForClass(
			AssumptionViolatedExceptionInBeforeAllTestCase.class);

		assertEquals(1, eventRecorder.getContainerAbortedCount(), "# containers aborted");
		assertEquals(0, eventRecorder.getTestStartedCount(), "# tests started");
	}

	// -------------------------------------------------------------------

	static class TestAbortedExceptionInBeforeAllTestCase {

		@BeforeAll
		static void beforeAll() {
			Assumptions.assumeTrue(false);
		}

		@Test
		void test() {
		}
	}

	static class AssumptionViolatedExceptionInBeforeAllTestCase {

		@BeforeAll
		static void beforeAll() {
			Assume.assumeTrue(false);
		}

		@Test
		void test() {
		}
	}

}
