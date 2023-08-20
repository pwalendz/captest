package com.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Account implements Serializable {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)  // auto_increment
private int accno;
private String email;
private float amount;
public int getAccno() {
	return accno;
}
public void setAccno(int accno) {
	this.accno = accno;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public float getAmount() {
	return amount;
}
public void setAmount(float amount) {
	this.amount = amount;
}

}