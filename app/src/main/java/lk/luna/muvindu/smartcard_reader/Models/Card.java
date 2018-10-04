package lk.luna.muvindu.smartcard_reader.Models;

import java.util.Date;

public class Card {
    private String cardNumber;
    private String accountId;
    private String dateissued;
    private String expire;
    private int balance;

    public String getExpire() {
        return expire;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }



    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getDateissued() {
        return dateissued;
    }

    public void setDateissued(String dateissued) {
        this.dateissued = dateissued;
    }
}
