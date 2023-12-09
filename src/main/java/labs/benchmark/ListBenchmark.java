/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package labs.benchmark;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
@Measurement(batchSize = 2000)
@BenchmarkMode(Mode.SingleShotTime)
public class ListBenchmark {
	
	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder().include(ListBenchmark.class.getSimpleName()).forks(1).build();

		new Runner(opt).run();
	}

	@Setup(Level.Trial)
	public void setup() {
		arrayList = new ArrayList<>();
		linkedList = new LinkedList<>();
		for (int i = 0; i < capacity; i++) {
			arrayList.add(testElement);
			linkedList.add(testElement);
		}
	}

	@Benchmark
	public void addLinked() {
		linkedList.add(testElement);
	}

	@Benchmark
	public void addArray() {
		arrayList.add(testElement);
	}

	@Benchmark
	public void deleteLinked() {
		linkedList.remove(testElement);
	}

	@Benchmark
	public void deleteArray() {
		arrayList.remove(testElement);
	}

	@Benchmark
	public void getLinked() {
		linkedList.get(capacity / 2);
	}

	@Benchmark
	public void getArray() {
		arrayList.get(capacity / 2);
	}

	private static int capacity = 10000000;
	private static int testElement = 0;

	private ArrayList<Integer> arrayList;
	private LinkedList<Integer> linkedList;
}
