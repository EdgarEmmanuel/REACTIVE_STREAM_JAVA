package subscriber;

import models.Cat;

import java.util.concurrent.Flow;

public class CatSubscriber implements Flow.Subscriber<Cat> {
    int counter = 0;
    Flow.Subscription subscription;
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription=subscription;
        this.subscription.request(1);
        System.out.println(" one cat processed ");
    }

    public int getCounter() {
        return counter;
    }

    @Override
    public void onNext(Cat cat) {
        System.out.println(" Cat => "+cat);
        counter++;
        this.subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("An error on the cat Subscriber ");
    }

    @Override
    public void onComplete() {
        System.out.println(" Cat processed finish ");
    }
}
