import java.util.Arrays;
import java.util.Scanner;

public class Party{
    private boolean[] doneScores;
    public Player[] players;
    public int nPlayers;
    int n;

    Party(Player[] players, int n){
        doneScores = new boolean[7];
        this.players = players;
        this.nPlayers = players.length;
        this.n = n;
    }

    Player isWinner(){
        for (int nPlayer = 0; nPlayer < this.nPlayers; nPlayer++) {
            Player player = players[nPlayer];
            int nScore;
            boolean ok = true;
            for (nScore = 0; nScore < player.score.length && ok==true; nScore++) {
                if(player.score[nScore] != 3) ok = false;
            }
            if (ok == true && player.points >= whoWin().points) return player;
        }
        return null;
    }

    Player whoWin(){
        int maxScore = 0;
        Player winner = null;
        for (int nPlayer = 0; nPlayer < this.nPlayers; nPlayer++) {
            Player player = players[nPlayer];
            if( player.points > maxScore ){
                maxScore = player.points;
                winner = player;
            }
        }
        return winner;
    }

    void computeDoneScores(int score){
        if (players[0].isCorrectScore(score)) {
            int index = score-15;
            for (int nPlayer = 0; nPlayer < this.nPlayers; nPlayer++) {
                if (players[nPlayer].score[index] != 3) return;
            }
            doneScores[index] = true;
        }
    }

    public void incResult(Player player, int score, int multiple){
        if(player.isCorrectScore(score)){
            int index = score - 15;
                boolean allDone = false;
                for (int i = 0; i < multiple; i++) {
                    if (player.score[index] == 3){
                        if(!this.doneScores[index]) {
                            player.points += score;
                        }
                    } else {
                        player.score[index] ++;
                    }
                    computeDoneScores(score);
                }
        }

    }


    void play(){
        for (int nRound = 1; nRound <= this.n; nRound++) {
            for (int nPlayer = 0; nPlayer < this.nPlayers; nPlayer++) {
                Player player = players[nPlayer];
                System.out.println("------------ Au tour de "+player.name+" ------------     Tour : "+nRound);
                player.printScore();
                for (int nDart = 0; nDart < 3; nDart++) {
                    int multiple, score;
                    Player winner = isWinner();
                    if (winner != null){
                        winner.printChampion();
                        System.exit(0);
                    }
                    Scanner scan = new Scanner(System.in);
                    System.out.println("----- Fléchette n°"+(nDart+1)+" -----");
                    System.out.println("Score ?");
                    try{score = scan.nextInt();}
                    catch(Exception e){
                        nDart -= 1;
                        continue;
                    }
                    if(score == 0) break;
                    scan.nextLine();
                    System.out.println("Multiple ?");
                    try{multiple = scan.nextInt();}
                    catch(Exception e){
                        nDart -= 1;
                        continue;
                    }
                    System.out.println();
                    incResult(player, score, multiple);

                }
                System.out.println();
            }
        }
        Player winner = whoWin();
        winner.printChampion();
    }

}
