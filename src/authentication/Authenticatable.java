public interface Authenticatable {
    User authenticate(String username, String password);
    boolean register(User user);
}
