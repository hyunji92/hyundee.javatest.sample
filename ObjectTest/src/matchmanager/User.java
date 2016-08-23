package matchmanager;

import java.util.Set;

/**
 * Created by hyunji on 2016. 8. 21..
 */
public class User {
    private long lineNumber;
    private Set<String> hobbies;
    private Set<Long> matchedUsers;
    private long similiarity;

    public long getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(long lineNumber) {
        this.lineNumber = lineNumber;
    }

    public Set<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(Set<String> hobbies) {
        this.hobbies = hobbies;
    }

    public long getSimiliarity() {
        return similiarity;
    }

    public void setSimiliarity(long similiarity) {
        this.similiarity = similiarity;
    }

    public Set<Long> getMatchedUsers() {
        return matchedUsers;
    }

    public void setMatchedUsers(Set<Long> matchedUsers) {
        this.matchedUsers = matchedUsers;
    }
}
