import java.util.Arrays;
import java.util.Scanner;

public class Party{
    public Player[] players;
    public int nPlayers;
    int n;

    Party(Player[] players, int n){
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
            if (ok == true) return player;
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

    void play(){
        for (int nRound = 0; nRound < this.n; nRound++) {
            for (int nPlayer = 0; nPlayer < this.nPlayers; nPlayer++) {
                Player player = players[nPlayer];
                System.out.println("------------ Au tour de "+player.name+" ------------");
                player.printScore();
                for (int nDart = 0; nDart < 3; nDart++) {
                    Player winner = isWinner();
                    if (winner != null){
                        winner.printChampion();
                        System.exit(0);
                    }
                    Scanner scan = new Scanner(System.in);
                    System.out.println("----- Fléchette n°"+(nDart+1)+" -----");
                    System.out.println("Score ?");
                    int score = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Multiple ?");
                    int multiple = scan.nextInt();
                    System.out.println();
                    player.incResult(score, multiple);
                }
                System.out.println();
            }
        }
        Player winner = whoWin();
        winner.printChampion();
    }

}