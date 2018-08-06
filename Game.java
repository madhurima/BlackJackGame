/**
 * 
 */
package org.blackjack.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @author Padmini
 *
 */
@Entity
@Table(name = "game")
public class Game implements Serializable {
    
    private Long id;
    private String username;
    private String result;
    private String playerCredits;
    private String playerBet;
    private String dealerHandValue;
    private String splithand;
    private String lefthandresult;
    private String righthandresult;
    private Date createddate;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getPlayerCredits() {
		return playerCredits;
	}
	public void setPlayerCredits(String playerCredits) {
		this.playerCredits = playerCredits;
	}
	public String getPlayerBet() {
		return playerBet;
	}
	public void setPlayerBet(String playerBet) {
		this.playerBet = playerBet;
	}
	public String getDealerHandValue() {
		return dealerHandValue;
	}
	public void setDealerHandValue(String dealerHandValue) {
		this.dealerHandValue = dealerHandValue;
	}
	public String getSplithand() {
		return splithand;
	}
	public void setSplithand(String splithand) {
		this.splithand = splithand;
	}
	public String getLefthandresult() {
		return lefthandresult;
	}
	public void setLefthandresult(String lefthandresult) {
		this.lefthandresult = lefthandresult;
	}
	public String getRighthandresult() {
		return righthandresult;
	}
	public void setRighthandresult(String righthandresult) {
		this.righthandresult = righthandresult;
	}
	public Date getCreateddate() {
		return createddate;
	}
	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}
    
}
