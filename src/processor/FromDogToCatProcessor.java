package processor;

import models.Cat;
import models.Dog;
import publisher.AnimalPublisher;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.function.Function;

public class FromDogToCatProcessor extends SubmissionPublisher<Cat> implements Flow.Processor<Dog, Cat> {

    Function<Dog,Cat> funtion;
    Flow.Subscription subscription;

    public FromDogToCatProcessor(Function<Dog,Cat> function){
        super();
        this.funtion = function;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public void onNext(Dog dog) {
        // after the transformation we send to the subscriber
        submit((Cat) funtion.apply(dog));
        this.subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("an error occured during Cat Processor ");
    }

    @Override
    public void onComplete() {
        System.out.println("Processed Completed ");
    }
}
