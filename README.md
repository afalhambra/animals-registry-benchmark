# animals-registry-benchmark
Animals Registry micro-benchmark project

## Micro-benchmark test cases
To run the micro-benchmark automated test cases you should:

1. Open a terminal window and change directory to your Maven project. You should be in a directory that contains pom.xml file,
2. Run the below command

    ```
    mvn clean install
    ```
3. Once the project is installed you should be able to run it by running the below command
    ```
    java -jar target\benchmarks.jar
    ```
You should be able to see an outcome similar to this one below:

    
    Benchmark                                        Mode  Cnt     Score     Error  Units                                   
    AnimalsRegistryBenchmark.baseline                avgt    5   332.732 ±  67.707  ns/op                                   
    AnimalsRegistryBenchmark.benchMarkAverageWeight  avgt    5  2893.783 ± 410.234  ns/op
    
Above micro-benchmark is displayed in nanoseconds and measures the average time for each method call. 