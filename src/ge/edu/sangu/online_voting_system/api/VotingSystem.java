package ge.edu.sangu.online_voting_system.api;

import ge.edu.sangu.online_voting_system.exception.NoVoterFoundException;
import ge.edu.sangu.online_voting_system.exception.VotingException;
import ge.edu.sangu.online_voting_system.model.Person;

import java.util.List;

public interface VotingSystem {

    /**
     * Creates unique voter keys for every voter
     * and also initializes candidates.
     *
     * @param voters
     * @param candidates
     */
    void init(List<Person> voters, List<Person> candidates, int legalFromAge);

    /**
     * Gives vote to candidate using someones voter key.
     *
     * @param voterKey
     * @param candidate
     * @throws NoVoterFoundException
     */
    void vote(String voterKey, Person candidate) throws VotingException;

    /**
     * Returns winners in a descending order.
     *
     * @return winners
     */
    List<Person> listWinners();
}
