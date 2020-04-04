package ge.edu.sangu.online_voting_system.impl;

import ge.edu.sangu.online_voting_system.api.VotingSystem;
import ge.edu.sangu.online_voting_system.exception.AlreadyVotedException;
import ge.edu.sangu.online_voting_system.exception.InfantVotedException;
import ge.edu.sangu.online_voting_system.exception.NoVoterFoundException;
import ge.edu.sangu.online_voting_system.model.Vote;
import ge.edu.sangu.online_voting_system.model.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        // TODO Initialize candidates with votes starting at 0!
    }

    @Override
    public void vote(String voterKey, Person candidate) throws NoVoterFoundException {
        Person voter = VOTERS.get(voterKey);
        if (voter == null) {
            throw new NoVoterFoundException(voterKey);
        }
        if (voterIsInfant) { // TODO Check if voter is in legal voting age!
            throw new InfantVotedException(legalFromAge);
        }
        if (voteExists) { // TODO One candidate may only vote once! Check if vote exists!
            throw new AlreadyVotedException(voterKey);
        }
        Vote vote = // TODO Vote object should have voter, candidate and voting time (current datetime)!
        VOTES.add(vote);
    }

    @Override
    public List<Person> listWinners() {
        // TODO Return winners by descending order!
        return null;
    }

    // XXX Super secret backdoor method! (O_o) be quiet!
    public Map<String, Person> getVoters() {
        return VOTERS;
    }
}
