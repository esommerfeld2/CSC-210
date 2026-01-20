# A2 Genetic Algorithms

Your readme should include the following information. Each student needs to submit all of this information themselves, even when pair programming. 

Group Members: N/A

Any peers and/or TAs/Tutors you collaborated with: Jade

Would you like to give "kudos" to anyone who was particularly supportive or helpful?
Jade
Cite any references used:
https://stackoverflow.com/questions/20748848/why-removerange-method-of-arraylist-class-is-not-working 
https://www.baeldung.com/java-generating-random-numbers-in-range

If you used AI, please describe how you used it and what the experienc taught you:
I used AI remind me of basic syntax and help me debug. It taught me how to track down why my roundNumber was being funky, taught me how to use random.nextInt() and helped me check my work. Ie pointing out that I should be doing this.n = n not the other way around. It did need very specfic instructions for what I was trying to do so it was a good practice in articulating what I though was the problem and how I wanted to solve it!

## Questions

Please briefly describe what you observed about the "winners" produced by your genetic algorithm. Did changing the parameter values have any effect on what you observed?
At first there are a few strings but eventually it becomes one long string that always has the bets fitness. If you change the max to be smaller the most fit will still always be the biggest odd number and repeat the same string over and over again as the winner. If you increase the mutation rate the string that wins is more likley to not always the same round to round. Reducing the number of letters chosen to 3 gets a patern like BCBCBCB. And reducing the intial starting size doesn't change much.

## Reflection

Please provide a reflection on your experience with this assignment-- what was interesting? what was hard? what do you feel like you learned?
This was a lot better than last assignment. I actually was able to code through it and understand my errors and what was happening. I found it a very interesting and creative assingment. Particulary the ways in which we combined the two parents genomes to make a new child. It was hard to understand evolve, I had a lot of errors there and then tracking down my errors in other methods it called. But I feel like I learned more about how to use array lists, working through code, indeces and how to use and track them, and gained more comfortability with doing a decreasing loop. 