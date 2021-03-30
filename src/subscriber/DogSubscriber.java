package subscriber;

import models.Dog;

import java.util.concurrent.Flow;

public class DogSubscriber implements Flow.Subscriber<Dog> {
    int counter =0 ;
    private Flow.Subscription subscription;
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        System.out.println("subscribed");
        this.subscription  = subscription;

        // ask for one data in the stream
        this.subscription.request(1);

        System.out.println("onSubscribe requested 1 item");
    }

    @Override
    public void onNext(Dog dog) {
        System.out.println(" Animal => "+dog);
        //increment the counter
        counter++;

        // ask for one data in the stream
        this.subscription.request(1);
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("An error occured ");
    }

    @Override
    public void onComplete() {
        System.out.println("process completed");
    }
}
