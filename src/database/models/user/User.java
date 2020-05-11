package database.models.user;

import database.connection.HibernateUtil;
import database.models.Model;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Entity(name = "user")
public class User extends Model<User> implements Serializable {

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "username")
    private String username;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private UserTypeEnum type;

    public static User login(String nome, String senha) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query query = session.createQuery("SELECT u FROM user u WHERE u.name = :name AND u.password = :password");
        query.setParameter("name", nome);
        query.setParameter("password", senha);
        User user = (User) query.getSingleResult();
        session.close();

        return user;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserTypeEnum getType() {
        return type;
    }

    public void setType(UserTypeEnum type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String[] getResult() {
        return new String[]{getName(), getUsername(), getType().name()};
    }
}
