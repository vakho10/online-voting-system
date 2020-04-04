package ge.edu.sangu.online_voting_system.exception;

public class InfantVotedException extends VotingException {

    // XXX Should have message "Voter should not be less than the age: ${voterAge}! Call their parents :|"
    public InfantVotedException(int legalFromAge) {
        super("Voter should not be less than the age: " + legalFromAge + "! Call their parents :|");
    }
}
