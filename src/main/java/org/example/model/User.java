package org.example.model;

import java.time.LocalDateTime;

public class User {
    private Long _id;
    private String _login;
    private String _password;
    private String _fullName;
    private String _role;
    private boolean _isBlocked;
    private int _failedAttempts;
    private LocalDateTime _lastLogin;
    private boolean _mustChangePassword;
    private LocalDateTime _createdAt;

    public User() {
    }

    public User(String login, String passvord, String fullName, String role) {
        _login = login;
        _password = passvord;
        _fullName = fullName;
        _role = role;
        _isBlocked = false;
        _failedAttempts = 0;
        _mustChangePassword = true;
        _createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return _id;
    }

    public void setId(Long id)
    {
        _id = id;
    }

    public String getLogin()
    {
        return _login;
    }

    public void setLogin(String login)
    {
        _login = login;
    }

    public String getPassvord()
    {
        return _password;
    }

    public void setPassvord(String passvord)
    {
        _password = passvord;
    }

    public String getFullName()
    {
        return _fullName;
    }

    public void setFullName(String fullName)
    {
        _fullName = fullName;
    }

    public String getRole()
    {
        return _role;
    }

    public void setRole(String role)
    {
        _role = role;
    }

    public boolean isBlocked()
    {
        return _isBlocked;
    }

    public void setIsBlocked(boolean blocked)
    {
        _isBlocked = blocked;
    }

    public int getFailedAttempts()
    {
        return _failedAttempts;
    }

    public void setFailedAttempts(int failedAttempts)
    {
        _failedAttempts = failedAttempts;
    }

    public LocalDateTime getLastLogin()
    {
        return _lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin)
    {
        _lastLogin = lastLogin;
    }

    public boolean isMustChangePassword()
    {
        return _mustChangePassword;
    }
    public void setMustChangePassword(boolean mustChangePassword)
    {
        _mustChangePassword = mustChangePassword;
    }

    public LocalDateTime getCreatedAt()
    {
        return _createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt)
    {
        _createdAt = createdAt;
    }

    public void incrementFailedAttempts() {
        _failedAttempts++;
        if (_failedAttempts >= 3) {
            _isBlocked = true;
        }
    }

    public void resetFailedAttempts() {
        _failedAttempts = 0;
    }

    @Override
    public String toString() {
        return String.format("User{id=%d, login='%s', fullName='%s', role='%s', blocked=%s}",
                _id, _login, _fullName, _role, _isBlocked);
    }
}
