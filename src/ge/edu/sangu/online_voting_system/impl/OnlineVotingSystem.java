package ge.edu.sangu.online_voting_system.impl;

import ge.edu.sangu.online_voting_system.api.VotingSystem;
import ge.edu.sangu.online_voting_system.exception.AlreadyVotedException;
import ge.edu.sangu.online_voting_system.exception.InfantVotedException;
import ge.edu.sangu.online_voting_system.exception.NoVoterFoundException;
import ge.edu.sangu.online_voting_system.exception.VotingException;
import ge.edu.sangu.online_voting_system.model.Person;
import ge.edu.sangu.online_voting_system.model.Vote;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class OnlineVotingSystem implements VotingSystem {

    private static final Map<String, Person> VOTERS = new HashMap<>();
    private static final List<Vote> VOTES = new ArrayList<>();

    private final VoterKeyGenerator keyGenerator;

    private int legalFromAge;

    public OnlineVotingSystem() {
        keyGenerator = new VoterKeyGenerator();
    }

    @Override
    public void init(List<Person> voters, List<Person> candidates, int legalFromAge) {

        // Save legal age from...
        this.legalFromAge = legalFromAge;

        // Generate unique voter keys for voters
        for (Person voter : voters) {
            final String voterKey = keyGenerator.generateFor(voter);
            VOTERS.put(voterKey, voter);
        }
    }

    @Override
    public void vote(String voterKey, Person candidate) throws VotingException {
        Person voter = VOTERS.get(voterKey);
        if (voter == null) {
            throw new NoVoterFoundException(voterKey);
        }
        if (voter.getAge() < legalFromAge) { // XXX Check if voter is in legal voting age!
            throw new InfantVotedException(legalFromAge);
        }
        // XXX Vote object should have voter, candidate and voting time (current datetime)!
        Vote vote = new Vote(voter, candidate, LocalDateTime.now());
        if (VOTES.contains(vote)) { // XXX One candidate may only vote once! Check if vote exists!
            throw new AlreadyVotedException(voterKey);
        }
        VOTES.add(vote);
    }

    @Override
    public List<Person> listWinners() {
        // XXX Return winners by descending order!
        Map<Person, Integer> tmpVotes = new HashMap<>();
        for (Vote vote : VOTES) {
            Person candidate = vote.getTo();
            Integer votes = tmpVotes.get(candidate) == null ? 0 : tmpVotes.get(candidate);
            tmpVotes.put(candidate, votes + 1);
        }
        Set<Map.Entry<Person, Integer>> entries = tmpVotes.entrySet();
        return entries.stream()
                .sorted(new Comparator<Map.Entry<Person, Integer>>() {
                    @Override
                    public int compare(Map.Entry<Person, Integer> a, Map.Entry<Person, Integer> b) {
                        Integer aVotes = a.getValue();
                        Integer bVotes = b.getValue();
                        if (aVotes > bVotes) {
                            return -1;
                        }
                        if (aVotes < bVotes) {
                            return 1;
                        }
                        return 0;
                    }
                })
                .peek(entry -> {
                    System.out.println(entry.getKey() + " has " + entry.getValue() + " votes.");
                })
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());
    }

    // XXX Super secret backdoor method! (O_o) be quiet!
    public Map<String, Person> getVoters() {
        return VOTERS;
    }
}
