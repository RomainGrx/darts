import java.util.Arrays;

public class Player{
    public int[] correctScore = {15, 16, 17, 18, 19, 20, 21};
    public String name;
    public int score[];
    public int points;

    Player(String name){
        this.name = name;
        this.score = new int[7];
        this.points = 0;
    }

    void printChampion(){
        System.out.println("WAOUAW WAOUAW WAOUAW WAOUAW WAOUAW WAOUAW WAOUAW WAOUAW");
        System.out.println("Nous avons un grand gagnant!");
        System.out.println("BRAVO A TOI CHAMPION");
        System.out.println("================  "+this.name+"  ================");
        System.out.println("WAOUAW WAOUAW WAOUAW WAOUAW WAOUAW WAOUAW WAOUAW WAOUAW");
    }

    void printScore(){
        System.out.println(" --------------------------------------");
        System.out.println(" | 15 | 16 | 17 | 18 | 19 | 20 | bull |");
        System.out.println(" --------------------------------------");
        for (int i = 0; i < this.score.length; i++) {
            System.out.print(" | "+this.score[i]+" ");
        }
        System.out.println("   |   Score : "+this.points);
        System.out.println(" --------------------------------------");
    }

    public boolean isCorrectScore(int score){

        for (int i = 0; i < correctScore.length; i++) {
            if(correctScore[i] == score) return true;
        }
        return false;
    }


}