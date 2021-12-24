package eu.pierrebeitz.aoc._2020;

import eu.pierrebeitz.aoc.utils.AocUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class Day8Puzzle2 {

    public static void main(String[] args) throws IOException {
        try (var reader = AocUtils.loadInputForDay(2020, 8)) {
            loadAndExecute(reader);
        }
    }

    static int loadAndExecute(BufferedReader reader) {
        var runtime = new Day8Puzzle1.Runtime(reader);
        var forkJoinPool = ForkJoinPool.commonPool();
        int result = forkJoinPool.invoke(new RuntimeExecutor(runtime));
        System.out.println(result);
        return result;
    }

    private static class RuntimeExecutor extends RecursiveTask<Integer> {
        private final Day8Puzzle1.Runtime runtime;

        RuntimeExecutor(Day8Puzzle1.Runtime runtime) {
            this.runtime = runtime;
        }

        // this approach actually allows to solve a more complex version of this problem where we would mutate any instruction
        // and not only one
        @Override
        protected Integer compute() {
            var sum = 0;
            while (runtime.hasNext()) {
                var maybeMutatedRuntime = runtime.mutate();
                runtime.executeNext();
                sum += maybeMutatedRuntime
                      .map(RuntimeExecutor::new)
                      .map(RecursiveTask::fork)
                      .stream().mapToInt(ForkJoinTask::join).sum();

            }
            if (runtime.isComplete()) {
                return runtime.getAccumulator();
            }
            return sum;
        }
    }

}
