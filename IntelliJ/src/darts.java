import java.util.Scanner;

public class darts {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Nombre de tours ? ");
        int n = scan.nextInt();
        scan.nextLine();
        System.out.println("Nombre de joueurs ? ");
        int nPlayers = scan.nextInt();
        scan.nextLine();
        Player players[] = new Player[nPlayers];
        for (int i = 0; i < nPlayers; i++) {
            System.out.println("Nom du joueur "+(i+1)+" ? ");
            String name = scan.next();
            players[i] = new Player(name);
            scan.nextLine();
        }
        Party party = new Party(players, n);
        party.play();
    }
}
