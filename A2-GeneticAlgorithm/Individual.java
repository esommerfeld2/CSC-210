import java.util.ArrayList;
import java.util.Random;

import javax.management.relation.InvalidRoleValueException;

public class Individual {

    /**
     * Chromosome stores the individual's genetic data as an ArrayList of characters
     * Each character represents a gene
     */
    ArrayList<Character> chromosome;

    /**
     * Inital constructor to generate initial population members
     * @param c_0 The initial chromosome size
     * @param num_letters The number of letters available to choose from
     */
    public Individual(int c_0, int num_letters, Random rng) {
        chromosome = new ArrayList<>(c_0);
        for(int i = 0; i < c_0; i++){
            chromosome.add(randomLetter(num_letters, rng));
        }
    }

    /**
     * Second constructor to create parents and offspring chromosomes
     * @param parent1 The first parent chromosome
     * @param parent2 The second parent chromosome
     * @param c_max The maximum chromosome size
     * @param m The chances per round of mutation in each gene
     */
    public Individual(Individual parent1, Individual parent2, int c_max, float m, int num_letters, Random rng) {
        Random random = new Random();
        int prefixLength = random.nextInt(parent1.chromosome.size() - 1) + 1;
        int suffixLength = random.nextInt(parent2.chromosome.size() - 1) + 1;
        chromosome = new ArrayList<>(prefixLength + suffixLength);
        ArrayList<Character> prefix = new ArrayList<>();
        ArrayList<Character> suffix = new ArrayList<>();
        for(int i= 0; i < prefixLength; i++){
            prefix.add(parent1.chromosome.get(i));
        }
        for (int i = parent2.chromosome.size() - suffixLength; i < parent2.chromosome.size(); i++) {
        suffix.add(parent2.chromosome.get(i));
        }
        chromosome.addAll(prefix);
        chromosome.addAll(suffix);
        if(chromosome.size() > c_max){
            for(int i= chromosome.size()-1; i > c_max-1; i --){
                chromosome.remove(i);
            }
        }
        for(int i = 0; i < chromosome.size(); i++){
            boolean mutate = doesMutate(m, rng);
            if(mutate){
                chromosome.set(i,randomLetter(num_letters, rng));
            }
        }
        
    }

    /**
     * Provided method to choose a letter at random, in the range from A to the number of states indicated
     * @param num_letters The number of letters available to choose from (number of possible states)
     * @param rng The random number generator being used for the current run
     * @return The letter as a Character
     */
    private Character randomLetter(int num_letters, Random rng) {
        return Character.valueOf((char)(65 + rng.nextInt(num_letters)));
    }

    /**
     * Provided method to determine whether a given gene will mutate based on the parameter ***m***
     * @param m the mutation rate
     * @param rng The random number generator being used for the current run
     * @return true if a number randomly chosen between 0 and 1 is less than ***m***, else false
     */
    private Boolean doesMutate(float m, Random rng) {
        float randomNum = rng.nextInt(100) / 100f;
        return randomNum < m;
    }

    /**
     * Calculates the fitness of an individual
     * @return the fitness
     */
    public int getFitness(){
        int fitness= 0;
        int largeIndex= chromosome.size()-1;
        for(int i = 0; i < chromosome.size()/2; i++){
            if(chromosome.get(i).equals(chromosome.get(largeIndex))){
                fitness++;
            }else{
                fitness --;
            }
            largeIndex--;
        }
        if(chromosome.size() % 2 != 0){
            fitness++;
        }
        for(int i = 0; i < chromosome.size()-1; i++){
            if(chromosome.get(i).equals(chromosome.get(i+1))){
                fitness--;
            }
        }
        return fitness;

    }

    /**
     * Expresses the individual's chromosome as a String, for display purposes
     * @return The chromosome as a String
     */
    public String toString() {
        StringBuilder builder = new StringBuilder(chromosome.size());
        for(Character ch: chromosome) {
            builder.append(ch);
        }
        return builder.toString();
    }




    public static void main(String[] args) {
        // This code will set a random seed when you're testing Individual (i.e., running it without GA_Simulation)
        Random rng = new Random(System.currentTimeMillis());

        // You can pass rng, as defined above, to your constructors.
        Individual i = new Individual(8, 4, rng);
        Individual t = new Individual(8, 4, rng);
        Individual it = new Individual(i, t, 8, 0.01f, 5, rng);
        System.out.println(it);
        System.out.println(it.getFitness());
        System.out.println(i);
        System.out.println(i.getFitness());

    }

}
