package ge.edu.sangu.online_voting_system;

import ge.edu.sangu.online_voting_system.exception.NoVoterFoundException;
import ge.edu.sangu.online_voting_system.impl.OnlineVotingSystem;
import ge.edu.sangu.online_voting_system.model.Gender;
import ge.edu.sangu.online_voting_system.model.Race;
import ge.edu.sangu.online_voting_system.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        OnlineVotingSystem votingSystem = new OnlineVotingSystem();

        // Call init
        List<Person> candidates = generateCandidates(5);
        votingSystem.init(generateVoters(20), candidates, 18);

        // Super shady method call...
        Map<String, Person> voters = votingSystem.getVoters();
        Random rnd = new Random();
        for (String voterKey : voters.keySet()) {
            final int randomCandidateIndex = rnd.nextInt(candidates.size());
            try {
                votingSystem.vote(voterKey, candidates.get(randomCandidateIndex));
            } catch (NoVoterFoundException e) {
                System.out.println("Oh lord! (X_x)");
                e.printStackTrace();
                System.exit(-1); // Kill this hideous application!
            }
        }

        // Say who won (top 3)
        System.out.println("Top 3 winners are :)...");
        List<Person> winners = votingSystem.listWinners();
        for (int i = 1; i <= 3; i++) {
            Person winner = winners.get(i - 1);
            System.out.println(i + " => " + winner.getFirstName() + " " + winner.getLastName());
        }
        System.out.println();
    }

    private static List<Person> generateVoters(int amount) {
        Random rnd = new Random();
        List<Person> voters = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            // Race choosing :) (rigged one)
            Race race = Race.CAUCASIAN;
            double raceRnd = rnd.nextDouble();
            if (raceRnd < 0.50) {
                if (raceRnd < 0.25) {
                    race = Race.NEGROID;
                } else {
                    race = Race.MONGOLIAN;
                }
            }
            if (rnd.nextInt(2) == 0) {
                voters.add(new Person("Girly_" + i, "Surname_" + i, 16 + rnd.nextInt(50), Gender.FEMALE, race));
            } else {
                voters.add(new Person("Manly_" + i, "Surname_" + i, 16 + rnd.nextInt(50), Gender.MALE, race));
            }
        }
        return voters;
    }

    private static List<Person> generateCandidates(int amount) {
        return generateVoters(amount);
    }
}
