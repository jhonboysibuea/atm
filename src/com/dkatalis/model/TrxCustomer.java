package com.dkatalis.model;

public class TrxCustomer {
    private Customer customerId;
    private String method;
    private int amount;
    private Customer receiverId;
    private String owedTo;
    private String owedFrom;
    private int valOwedTo;
    private int valOwedFrom;

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Customer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Customer receiverId) {
        this.receiverId = receiverId;
    }

    public String getOwedTo() {
        return owedTo;
    }

    public void setOwedTo(String owedTo) {
        this.owedTo = owedTo;
    }

    public String getOwedFrom() {
        return owedFrom;
    }

    public void setOwedFrom(String owedFrom) {
        this.owedFrom = owedFrom;
    }

    public int getValOwedTo() {
        return valOwedTo;
    }

    public void setValOwedTo(int valOwedTo) {
        this.valOwedTo = valOwedTo;
    }

    public int getValOwedFrom() {
        return valOwedFrom;
    }

    public void setValOwedFrom(int valOwedFrom) {
        this.valOwedFrom = valOwedFrom;
    }
}
