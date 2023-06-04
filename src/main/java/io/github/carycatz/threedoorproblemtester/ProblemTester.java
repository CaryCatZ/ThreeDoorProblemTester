/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2023. CaryCatZ <carycatz@outlook.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package io.github.carycatz.threedoorproblemtester;

import java.util.Arrays;
import java.util.Random;

public class ProblemTester {
	protected static final Random RANDOM = new Random();

	protected final ProblemDescriptor problemDescriptor;

	protected ProblemTestResult result = null;

	public ProblemTester(ProblemDescriptor problemDescriptor) {
		this.problemDescriptor = problemDescriptor;
	}

	@SuppressWarnings("ComparatorMethodParameterNotUsed")
	public void test(final long round) {
		Boolean[] choices = new Boolean[problemDescriptor.numChoice()];
		Arrays.fill(choices, false);
		choices[RANDOM.nextInt(choices.length)] = true;

		long tested = 0;
		ProblemTestResult result = new ProblemTestResult();
		while (++tested <= round) {
			Arrays.parallelSort(choices, (o1, o2) -> RANDOM.nextInt());

			Boolean[] copy = Arrays.copyOf(choices, choices.length);
			copy[RANDOM.nextInt(choices.length)] = false;
			Arrays.stream(copy).filter(b -> b).findFirst().ifPresentOrElse(b -> {
				result.round++;
				result.kept.failed();
				result.swapped.success();
			}, () -> {
				result.round++;
				result.kept.success();
				result.swapped.failed();
			});
		}

		this.result = result;
	}

	public ProblemTestResult getResult() {
		return result;
	}
}
