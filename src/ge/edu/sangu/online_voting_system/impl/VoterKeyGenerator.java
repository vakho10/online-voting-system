package ge.edu.sangu.online_voting_system.impl;

import ge.edu.sangu.online_voting_system.model.Gender;
import ge.edu.sangu.online_voting_system.model.Person;
import ge.edu.sangu.online_voting_system.model.Race;

import java.util.Random;

public class VoterKeyGenerator {

    private static final int STEPS = 3;

    String generateFor(Person voter) {
        // XXX Should generate and return unique key for voter!
        //  Same voter should have same key!
        //  Key should be hard to crack :|
        String fullName = voter.getFirstName() + voter.getLastName();
        char[] chars = fullName.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (chars[i] + STEPS);
        }
        return new String(chars) + "_" + new Random().nextInt(200);
    }

    public static void main(String[] args) {
        VoterKeyGenerator generator = new VoterKeyGenerator();
        String result = generator.generateFor(new Person("John", "Smith", 35, Gender.MALE, Race.CAUCASIAN));
        System.out.println(result);
    }
}
