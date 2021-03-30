import dao.Database;
import models.Cat;
import models.Dog;
import processor.FromDogToCatProcessor;
import publisher.AnimalPublisher;
import subscriber.CatSubscriber;
import subscriber.DogSubscriber;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // animal publisher
        AnimalPublisher animalPublisher = new AnimalPublisher();

        // animal subscriber
        DogSubscriber dogSubscriber = new DogSubscriber();

        CatSubscriber catSubscriber = new CatSubscriber();

        FromDogToCatProcessor catProcessor = new FromDogToCatProcessor(
                s->{
                    return (Cat) new Dog("","");
        });

        // add a subscriber to the publisher
        animalPublisher.subscribe(dogSubscriber);

        catProcessor.subscribe(catSubscriber);

        animalPublisher.subscribe(catProcessor);

        // the Stream of data are now publish by the publisher
        new Database().getAllAnimal().stream().forEach(
                animal -> {
                    animalPublisher.submit(animal);
                }
        );

        // while the number of item processed is different than the number of items we wait
        while (new Database().getAllAnimal().size() != dogSubscriber.getCounter()) {
            Thread.sleep(10);
        }
        // close the Publisher
        animalPublisher.close();
        catProcessor.close();

        System.out.println("Exiting the app");

    }
}
