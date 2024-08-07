package carsystem;

class rental 
{
	    private car car;
	    private customer customer;
	    private int days;

	    public rental(car car, customer customer, int days) {
	        this.car = car;
	        this.customer = customer;
	        this.days = days;
	    }

	    public car getCar() {
	        return car;
	    }

	    public customer getCustomer() {
	        return customer;
	    }

	    public int getDays() {
	        return days;
	    }
}
