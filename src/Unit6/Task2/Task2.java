package Unit6.Task2;

import org.openjdk.jmh.annotations.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;


@Fork(value=1)
@Warmup(iterations = 1)
@Measurement(iterations = 1, time = 1)
public class Task2 {

    @State(Scope.Benchmark)
    public static class BenchmarkState {
        @Param({ "50000", "500000", "1000000"})
        public int value;
        public int randomIndex;
        Random r = new Random();

        ArrayList<Integer> al;
        LinkedList<Integer> ll;

        @Setup(Level.Trial)
        public void setup() {
            System.out.println("setup");
            al = new ArrayList<>();
            for (int i = 0; i < value; i++)
                al.add(i);
            ll = new LinkedList<>(al);
        }

        @Setup(Level.Invocation)
        public void setRandom()
        {
            randomIndex = r.nextInt(value-1);
        }



        @TearDown(Level.Invocation)
        public void reset() {
            if (al.size() > value)
                al.remove(value);
            else if (al.size() < value)
                al.add(randomIndex);
            if (ll.size() > value)
                ll.remove(value);
            else if (ll.size() < value)
                ll.add(randomIndex);
        }
    }

    public static void main(String[] args) {
        try {
            org.openjdk.jmh.Main.main(args);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Benchmark
    public boolean arraylistAdd(BenchmarkState state) { return state.al.add(38); }

    @Benchmark
    public boolean linkedlistAdd(BenchmarkState state) { return state.ll.add(38); }

    @Benchmark
    public void arraylistAddByIndex(BenchmarkState state) { state.al.add(state.value/2, 38); }

    @Benchmark
    public void linkedlistAddByIndex(BenchmarkState state) { state.ll.add(state.value/2, 38); }

    @Benchmark
    public Integer arraylistGet(BenchmarkState state) { return state.al.get(state.randomIndex); }

    @Benchmark
    public Integer linkedlistGet(BenchmarkState state) { return state.ll.get(state.randomIndex); }

    @Benchmark
    public boolean arraylistContains(BenchmarkState state) { return state.al.contains(state.randomIndex); }

    @Benchmark
    public boolean linkedlistContains(BenchmarkState state) { return state.ll.contains(state.randomIndex); }

    @Benchmark
    public boolean arraylistRemove(BenchmarkState state) { return state.al.remove((Integer) state.randomIndex); }

    @Benchmark
    public boolean linkedlistRemove(BenchmarkState state) { return state.ll.remove((Integer) state.randomIndex); }

    @Benchmark
    public Integer arraylistRemoveByIndex(BenchmarkState state) { return state.al.remove(state.randomIndex); }

    @Benchmark
    public Integer linkedlistRemoveByIndex(BenchmarkState state) { return state.ll.remove(state.randomIndex); }


}
