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

public class TestReporter {
	protected final ProblemTestResult result;

	public TestReporter(ProblemTestResult result) {
		this.result = result;
	}

	public String report() {
		long round = result.round;
		long swp_suc = result.swapped.getSuccess();
		long swp_fld = result.swapped.getFailed();
		double swp_prb = (double)swp_suc / round * 100;
		long kpt_suc = result.kept.getSuccess();
		long kpt_fld = result.kept.getFailed();
		double kpt_prb = (double)kpt_suc / round * 100;

		return """
				======== Test Report ========
				- Test Round: %s
				
				- Swapped: %s ->
					success: %s, failed: %s, probability: %s%%.
				
				- Kept: %s ->
					success: %s, failed: %s, probability: %s%%.
					
				Probability Of Swapped / Probability Of Kept:
					result: %s
				""".formatted(
						round,
				round, swp_suc, swp_fld, swp_prb * 100,
				round, kpt_suc, kpt_fld, kpt_prb * 100,
				swp_prb / kpt_prb
		);
	}

	public static String report(ProblemTestResult result) {
		return new TestReporter(result).report();
	}
}
