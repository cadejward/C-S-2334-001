import java.util.Random;

public class RandomPlayer extends Player{
	
	private Random generator;
	
	public RandomPlayer(String name)
	{
		super(name);
		this.generator = new Random();
	}
	
	public int[] getMove(int[] pileSizes)
	{
		int pileIndex = generator.nextInt(pileSizes.length);
        int numObjects = 1 + generator.nextInt(pileSizes[pileIndex]);

        return new int[]{pileIndex, numObjects};
	}

}
