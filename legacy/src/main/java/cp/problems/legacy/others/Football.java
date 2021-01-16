package cp.problems.legacy.others;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Team implements Comparable<Team> {
    String name;
    int points = 0;
    int goalDifferences = 0;
    int wins = 0;
    int ties = 0;
    int losses = 0;
    int gamesPlayed = 0;
    int goalsScored = 0;
    int goalAgainst = 0;

    @Override
    public int compareTo(Team o1) {
        Team o2 = this;
        if (o1.points > o2.points) {
            return 1;
        } else if (o1.points < o2.points) {
            return -1;
        } else if (o1.wins > o2.wins) {
            return 1;
        } else if (o1.wins < o2.wins) {
            return -1;
        } else if (o1.goalDifferences > o2.goalDifferences) {
            return 1;
        } else if (o1.goalDifferences < o2.goalDifferences) {
            return -1;
        } else if (o1.goalsScored > o2.goalsScored) {
            return 1;
        } else if (o1.goalsScored < o2.goalsScored) {
            return -1;
        } else if (o1.gamesPlayed < o2.gamesPlayed) {
            return 1;
        } else if (o1.gamesPlayed > o2.gamesPlayed) {
            return -1;
        } else {
            return o2.name.toLowerCase().compareTo(o1.name.toLowerCase());
        }
    }
}

public class Football {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in, "ISO-8859-1");
        PrintWriter cout = new PrintWriter(new OutputStreamWriter(System.out, StandardCharsets.ISO_8859_1));
        int len = Integer.parseInt(s.nextLine());
        for (int i = 0; i < len; i++) {
            String tour = s.nextLine();
            int numOfTeams = Integer.parseInt(s.nextLine());

            ArrayList<Team> teams = new ArrayList<>(numOfTeams);

            for (int j = 0; j < numOfTeams; j++) {
                Team current = new Team();
                current.name = s.nextLine();
                teams.add(current);
            }

            int matches = Integer.parseInt(s.nextLine());
            for (int j = 0; j < matches; j++) {
                String line = s.nextLine();
                int hash1 = line.indexOf('#');
                String firstTeamName = line.substring(0, hash1);
                int hash2 = line.indexOf('#', hash1 + 1);
                String secondTeamName = line.substring(hash2 + 1);

                int goal1 = Integer.parseInt(line.substring(hash1 + 1, line.indexOf('@')));
                int goal2 = Integer.parseInt(line.substring(line.indexOf('@') + 1, hash2));

                Team team1 = withName(teams, firstTeamName);
                Team team2 = withName(teams, secondTeamName);

                assert team1 != null;
                assert team2 != null;

                team1.gamesPlayed++;
                team2.gamesPlayed++;

                if (goal1 > goal2) {
                    team1.wins++;
                    team2.losses++;
                    team1.points += 3;
                } else if (goal1 < goal2) {
                    team1.losses++;
                    team2.wins++;
                    team2.points += 3;
                } else {
                    team1.ties++;
                    team2.ties++;
                    team1.points++;
                    team2.points++;
                }

                team1.goalsScored += goal1;
                team2.goalsScored += goal2;

                team1.goalAgainst += goal2;
                team2.goalAgainst += goal1;


                team1.goalDifferences = team1.goalsScored - team1.goalAgainst;
                team2.goalDifferences = team2.goalsScored - team2.goalAgainst;
            }

            Collections.sort(teams);

            cout.println(tour);
            int x = 1;
            for (Team t : teams) {
                cout.printf("%d) %s %dp, %dg (%d-%d-%d), %dgd (%d-%d)\n", x, t.name, t.points, t.gamesPlayed, t.wins, t.ties, t.losses, t.goalDifferences, t.goalsScored, t.goalAgainst);
                x++;
            }

            if (i < len - 1)
                cout.println();

        }

        cout.flush();
    }

    private static Team withName(ArrayList<Team> array, String name) {
        for (Team anArray : array) {
            if (name.equals(anArray.name)) {
                return anArray;
            }
        }
        return null;
    }
}
