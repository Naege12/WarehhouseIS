package controllers;


public class Controller {

    public boolean checkAccept(String login, String password)
    {
        return !login.isEmpty() && !password.isEmpty();
    }

}
