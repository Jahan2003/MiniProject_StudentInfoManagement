package dao;

import model.LoginModel;

public interface StudentInterface {
   public LoginModel getByUsername(String username) throws ClassNotFoundException ;
}
