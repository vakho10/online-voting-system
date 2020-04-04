package ge.edu.sangu.online_voting_system.exception;

public class NoVoterFoundException extends VotingException {

    // XXX Should have message "No voter with voterKey: ${voterKey} found!"
    public NoVoterFoundException(String voterKey) {
        super("No voter with voterKey: " + voterKey + " found!");
    }
}
