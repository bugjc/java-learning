package com.bugjc.java.basics.enums;

import lombok.Data;


@Data
public class Pizza {

    private PizzaStatus status;
    public enum PizzaStatus {
        ORDERED (5){
            @Override
            public boolean isOrdered() {
                return true;
            }
        },
        READY (2){
            @Override
            public boolean isReady() {
                return true;
            }
        },
        DELIVERED (0){
            @Override
            public boolean isDelivered() {
                return true;
            }
        };

        private int timeToDelivery;

        public boolean isOrdered() {return false;}

        public boolean isReady() {return false;}

        public boolean isDelivered(){return false;}

        public int getTimeToDelivery() {
            return timeToDelivery;
        }

        PizzaStatus (int timeToDelivery) {
            this.timeToDelivery = timeToDelivery;
        }
    }

    public boolean isDeliverable() {
        return this.status.isReady();
    }

    public void printTimeToDeliver() {
        System.out.println("Time to delivery is " + this.getStatus().getTimeToDelivery());
    }

    public static void main(String[] args) {
        System.out.println(Pizza.PizzaStatus.READY.isReady());
        System.out.println(Pizza.PizzaStatus.ORDERED.isReady());

        System.out.println(Pizza.PizzaStatus.READY.isOrdered());
        System.out.println(Pizza.PizzaStatus.ORDERED.isOrdered());
    }
}

