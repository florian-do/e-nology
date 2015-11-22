package com.enology.eip.e_nology.orm.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Lolo on 26/05/2015.
 */

@Table(name = "Domain")
public class Domain extends Model {

    public Domain()
    {
        super();
    }

    @Column(name = "Id")
    public  String  _id;

    @Column(name = "User")
    public  String  User;

    @Column(name = "Appellation")
    public  String  Appellation;

    @Column(name = "Created")
    public  String  Created;

    @Column(name = "Zipcode")
    public  int     Zipcode;

    @Column(name = "City")
    public  String  City;

    @Column(name = "Address")
    public  String  Address;

    @Column(name = "Owner")
    public  String  Owner;

    @Column(name = "Desc")
    public  String  Desc;

    @Column(name = "Name")
    public  String  Name;

}
