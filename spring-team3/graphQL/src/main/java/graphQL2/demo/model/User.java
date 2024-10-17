package graphQL2.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "APP_USER")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private String hobby;
    private String favoriteColor;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getHobby() { return hobby; }
    public void setHobby(String hobby) { this.hobby = hobby; }
    public String getFavoriteColor() { return favoriteColor; }
    public void setFavoriteColor(String favoriteColor) { this.favoriteColor = favoriteColor; }
//    public List<User> getFriends() { return friends; }
//    public void setFriends(List<User> friends) { this.friends = friends; }
}
