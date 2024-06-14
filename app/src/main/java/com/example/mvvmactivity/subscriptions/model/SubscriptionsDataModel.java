package com.example.mvvmactivity.subscriptions.model;

public class SubscriptionsDataModel {

    String subscription_id,subscription_code,subscription_name,subscription_type;

    public SubscriptionsDataModel(String subscription_id, String subscription_code, String subscription_name, String subscription_type) {
        this.subscription_id = subscription_id;
        this.subscription_code = subscription_code;
        this.subscription_name = subscription_name;
        this.subscription_type = subscription_type;
    }

    public String getSubscription_id() {
        return subscription_id;
    }

    public void setSubscription_id(String subscription_id) {
        this.subscription_id = subscription_id;
    }

    public String getSubscription_code() {
        return subscription_code;
    }

    public void setSubscription_code(String subscription_code) {
        this.subscription_code = subscription_code;
    }

    public String getSubscription_name() {
        return subscription_name;
    }

    public void setSubscription_name(String subscription_name) {
        this.subscription_name = subscription_name;
    }

    public String getSubscription_type() {
        return subscription_type;
    }

    public void setSubscription_type(String subscription_type) {
        this.subscription_type = subscription_type;
    }
}
