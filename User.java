import java.util.Objects;

public class User {
    private final String username;

    public User(String username) {
        this.username = Objects.requireNonNull(username, "Username cannot be null");
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "User[Username=" + username + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}