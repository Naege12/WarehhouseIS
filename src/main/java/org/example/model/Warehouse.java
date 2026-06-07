package org.example.model;

public class Warehouse {
    private Long _id;
    private String _name;
    private String _address;

    public Warehouse(){}

    public Warehouse(String name, String address)
    {
        _name = name;
        _address = address;
    }

    public Long getId()
    {
        return _id;
    }

    public void setId(Long id)
    {
        _id = id;
    }

    public String getName()
    {
        return _name;
    }

    public void setName(String name)
    {
        _name = name;
    }

    public String getAddress()
    {
        return _address;
    }

    public void setAddress(String address)
    {
        _address = address;
    }

    @Override
    public String toString() {
        return String.format("Warehouse{id=%d, name='%s', address='%s'}", _id, _name, _address);
    }
}
