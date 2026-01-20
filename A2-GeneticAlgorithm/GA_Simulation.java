import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class GA_Simulation {

  // Use the instructions to identify the class variables, constructors, and methods you need
  public static Random rng;
  private int n;
  private int k;
  private int r;
  private int c_0;
  private int c_max;
  private float m;
  private int g;
  public int roundNumber;
  ArrayList<Individual> population;

  /**
   * Constructor that assigns variables
   * @param n the number of individuals in each generation
   * @param k the number of winners (individuals allowed to reproduce) in each generation
   * @param r the number of rounds of evolution to run
   * @param c_0 the initial chromosome size
   * @param c_max the maximum chromosome size
   * @param m chance per round of a mutation in each gene
   * @param g number of states possible per gene
   */
  public GA_Simulation(int n, int k, int r, int c_0, int c_max, float m, int g){
    this.n= n;
    this.k= k;
    this.r= r;
    this.c_0= c_0;
    this.c_max = c_max;
    this.m = m;
    this.g = g;
  }

  /**
   * Creates an array list population of n indivduals
   * @return population, an array list of all individuals
   */
  public ArrayList<Individual> init(){
    this.population = new ArrayList<>(this.n);
    for(int i= 0; i <this.n; i++){
      this.population.add(new Individual(this.c_0, this.g, rng));
    }
    return this.population;
  }

  /**
   * Selects generational winners
   * @return population, an array list of new indivduals
   */
  public ArrayList<Individual> evolve(){
    ArrayList<Individual> winnerPopulation = new ArrayList<Individual>(this.k);
    for(int i = 0; i <this.k; i++){
      winnerPopulation.add(this.population.get(i));
    }
    for(int i = 0; i < this.n; i++){
      Random random = new Random();
      int parent1Index = random.nextInt(this.k);
      int parent2Index = random.nextInt(this.k);
      Individual parent1 = winnerPopulation.get(parent1Index);
      Individual parent2 = winnerPopulation.get(parent2Index);
      this.population.set(i, new Individual(parent1, parent2, this.c_max, this.m, this.g, rng));
    }
    return this.population;
  }

  /** Provided method that prints out summary statistics for a given
   * generation, based on the information provided
   * @param roundNumber Which round of evolution are we on, from 0 to n
   * @param bestFitness Fitness of top-ranked (most fit) individual
   * @param kthFitness Fitness of kth-ranked individual
   * @param leastFitness Fitness of lowest-ranked (least fit) individual
   * @param best Individual with highest fitness
   * @return Nothing, prints statistics to standard out
   */
  private void printGenInfo(int roundNumber, int bestFitness, int kthFitness, int leastFitness, Individual best) {
    System.out.println("Round " + roundNumber + ":");
    System.out.println("Best fitness: " + bestFitness);
    System.out.println("k-th (" + k + ") fitness: " + kthFitness);
    System.out.println("Least fit: " + leastFitness);
    System.out.println("Best chromosome: " + best);
    System.out.println(); // blank line to match the example format
  }

  /**
   * Prints out a description of the generation
   * @param roundNumber the round you are on
   */
  public void describeGeneration(int roundNumber){
    Individual bestInd = this.population.get(0);
    Individual kthInd = this.population.get(this.k-1);
    Individual leastInd = this.population.get(this.population.size()-1);
    int bestFitness = bestInd.getFitness();
    int kthFitness = kthInd.getFitness();
    int leastFitness = leastInd.getFitness();
    printGenInfo(roundNumber, bestFitness, kthFitness, leastFitness, bestInd);
  }

  /** Provided method that sorts population by fitness score, best first
   * @param pop ArrayList of Individuals in the current generation
   */
  public void rankPopulation(ArrayList<Individual> pop) {
    // sort population by fitness
    Comparator<Individual> ranker = new Comparator<>() {
      // this order will sort higher scores at the front
      public int compare(Individual c1, Individual c2) {
        return (int)Math.signum(c2.getFitness()-c1.getFitness());
      }
    };
    pop.sort(ranker);
  }

  public ArrayList<Individual> run(){
    init();
    rankPopulation(population);
    describeGeneration(0);
    roundNumber ++;
    for(int i = 0; i < r; i++){
      evolve();
      rankPopulation(population);
      describeGeneration(i);
      roundNumber++;
    }
    ArrayList<Individual> winnerPopulation = new ArrayList<Individual>(k);
    for(int i = 0; i <k; i++){
      winnerPopulation.add(population.get(i));
    }
    return winnerPopulation;

  }


  public static void main(String[] args) {
    // This first block of code establishes a random seed, which will make
    // it easier to test your code. The output should remain consistent if the
    // seed is the same. To run with a specific seed, you can run from the
    // command line like:
    //                    java GA_Simulation 24601

    long seed = System.currentTimeMillis(); // default
    if (args.length > 0) {
      try {
        seed = Long.parseLong(args[0]);
      } catch (NumberFormatException e) {
        System.err.println("Seed wasn't passed so using current time.");
      }
    }
    rng = new Random(seed);
    // Write your main below:
     GA_Simulation newSimulation = new GA_Simulation(100, 15, 100, 8, 20, 0.01f, 5);
     newSimulation.run();
    // GA_Simulation newSimulation = new GA_Simulation(50, 15, 50, 8, 8, 0.01f, 5);
    // newSimulation.run();
    // GA_Simulation newSimulation = new GA_Simulation(100, 15, 100, 8, 8, 0.01f, 5);
    // newSimulation.run();
    // GA_Simulation newSimulation = new GA_Simulation(100, 15, 100, 8, 20, 0.02f, 5);
    // newSimulation.run();
    // GA_Simulation newSimulation = new GA_Simulation(100, 15, 100, 8, 20, 0.01f, 3);
    // newSimulation.run();
    // GA_Simulation newSimulation = new GA_Simulation(100, 15, 100, 5, 20, 0.01f, 5);
    // newSimulation.run();
  }

}
