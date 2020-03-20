package pl.britenet.profiling.demo.hardware;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@Fork(value = 1, warmups = 1, jvmArgsPrepend = "-XX:-RestrictContended")
@Warmup(iterations = 1, time = 1)
@Measurement(iterations = 1, time = 1)
@Threads(2)
public class FalseSharingJmh {
    @State(Scope.Group)
    public static class Unpadded {
        volatile long a;
        volatile long b;
    }

    @State(Scope.Group)
    public static class Padded {
        volatile long a;
        long padding1, padding2, padding3, padding4, padding5, padding6, padding7;
        volatile long b;
    }

    @State(Scope.Group)
    public static class PaddedByAnnotation {
        @jdk.internal.vm.annotation.Contended
        volatile long a;
        volatile long b;
    }

    @Group("Unpadded")
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public long updateUnpaddedA(Unpadded u) {
        return u.a++;
    }

    @Group("Unpadded")
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public long updateUnpaddedB(Unpadded u) {
        return u.b++;
    }

    @Group("Padded")
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public long updatePaddedA(Padded u) {
        return u.a++;
    }

    @Group("Padded")
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public long updatePaddedB(Padded u) {
        return u.b++;
    }

    @Group("PaddedByAnnotation")
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public long updatePaddedByAnnotationA(PaddedByAnnotation u) {
        return u.a++;
    }

    @Group("PaddedByAnnotation")
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public long updatePaddedByAnnotationB(PaddedByAnnotation u) {
        return u.b++;
    }

    public static void main(String[] args) throws IOException, RunnerException {
        Options opt = new OptionsBuilder()
                .include(FalseSharingJmh.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }

}