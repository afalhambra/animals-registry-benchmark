package org.mycompany.animals;

import org.mycompany.animals.dogs.DogRegistry;
import org.mycompany.animals.dogs.domain.DogBreed;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import javax.xml.bind.JAXBException;
import java.util.EnumMap;
import java.util.concurrent.TimeUnit;

public class AnimalsRegistryBenchmark {

    @State(Scope.Benchmark)
    public static class BenchmarkState {
        int a = 1;
        int b = 2;
        static final String dogsFile = "src/resources/dogs.xml";
        static AnimalFactory animalFactory;
        static DogRegistry dogRegistry;

        @Setup(Level.Trial)
        public void setUp() {
            animalFactory = FactoryProvider.getFactory(AnimalType.DOG);
            try {
                dogRegistry = (DogRegistry) animalFactory.load(dogsFile);
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }
    }

    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 1)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Benchmark
    public void baseline(BenchmarkState state, Blackhole blackhole) {
        double avgWeight = state.dogRegistry.averageWeight(DogBreed.GREYHOUND);
        blackhole.consume(avgWeight);
    }

    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 1)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Benchmark
    public void benchMarkAverageWeight(BenchmarkState state, Blackhole blackhole){
        EnumMap<DogBreed, Double> enumMap = state.dogRegistry.averageWeightPerBreed();
        blackhole.consume(enumMap);
    }
}
