package model;

public class LoginModel {
    private long Id;
    private String Name;
    private String Username;
    private String Password;
    private Role role;

    public enum Role {
        ADMIN, USER
    }

    public LoginModel(){
    }

    public LoginModel(long Id, String Name, String Username, String Password,Role role) {
        this.Id = Id;
        this.Name = Name;
        this.Username = Username;
        this.Password = Password;
        this.role = role;
    }

	public long getId() {
		return Id;
	}

	public void setId(long l) {
		Id = l;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}


}