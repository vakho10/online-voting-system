package ge.edu.sangu.online_voting_system.exception;

public class AlreadyVotedException extends VotingException {

    // XXX Should have message "Voter with voterKey: ${voterKey} has already voted! Call the cops :|"
    public AlreadyVotedException(String voterKey) {
        super("Voter with voterKey: " + voterKey + " has already voted! Call the cops :|");
    }
}
