package States;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HighScore {

	public void saveScore(int score) {
		if (score > getHighScore()) {
			try {
				FileWriter writer = new FileWriter("src/HighScore.txt");
				writer.write("");
				writer.write(Integer.toString(score));
				System.out.println(Integer.toString(score));
				writer.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}

	}
	
	public int getHighScore() {
		int highScore = 0;

		try {
			File myObj = new File("src/HighScore.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				highScore = Integer.parseInt(data);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		return highScore;
	}
	
}
