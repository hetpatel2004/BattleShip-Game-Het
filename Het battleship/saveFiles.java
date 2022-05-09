import java.io.*;
import java.util.*;

public class saveFiles {
	private String filePath;
	private ArrayList <ArrayList<String>> nameScore = new ArrayList<>();

	public saveFiles(){
		filePath = System.getProperty("user.dir");
		for (int i = 0; i < 10; i++){
			nameScore.add(new ArrayList<String>(Arrays.asList("0", "Player")));
		}
		try{
			readFromFile();
		}
		catch (FileNotFoundException e){

		}
	}

	public Boolean checkScore(int score){
		if(score > Integer.parseInt (nameScore.get (nameScore.size() - 1).get(0))){
			return true;
		}
		return false;
	}

	public void writeToFile (String name, int score) throws IOException{
		FileWriter fWriter = new FileWriter(getFilePath(), false);
		BufferedWriter bWriter = new BufferedWriter(fWriter);
		nameScore.add(new ArrayList<String>(Arrays.asList(String.valueOf(score), name)));
		Collections.sort(nameScore, new Comparator<ArrayList<String>>(){
			@Override
			public int compare(ArrayList<String> ol, ArrayList<String> o2){
				return ol.get(0). compareTo(o2.get(0));
			}
		});
		Collections.reverse(nameScore);
			for (int i = nameScore.size(); i > 10; i--){
				nameScore.remove(i -1);
			}
			for (ArrayList <String> s : nameScore){
				bWriter.write(s.get(1) + ":");
				bWriter.write(s.get(0));
				bWriter.write("\r\n");
				bWriter.flush();
			}
		
	}

	public void showHighScore(){
		for(ArrayList<String> s : nameScore){
			System.out.printf("%s : %s\n", s.get(1), s.get(0));
		}
	}
			


	public void readFromFile() throws FileNotFoundException{
		FileReader fReader = new FileReader(getFilePath());
		BufferedReader bReader = new BufferedReader(fReader);	
		Scanner read = new Scanner(bReader);
		while(read.hasNextLine()) {
			String Line = read.nextLine();
			String[] lines = Line.split(":");
			nameScore.add(new ArrayList<String>(Arrays.asList(lines[1], lines[0])));
		}
	}

	private String getFilePath() {
		return filePath + "/topScore.txt";
	}
}
	
