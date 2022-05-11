package shop.model.observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents an observable object, or "data"
 * in the model-view paradigm. It can be implemented to represent an
 * object that the application wants to have observed.
 * <p>
 * An observable object can have one or more observers. An observer
 * may be any object that implements interface {@code Observer}. After an
 * observable instance changes, an application calling the
 * {@code Observable}'s {@code notifyObservers} method
 * causes all of its observers to be notified of the change by a call
 * to their {@code update} method.
 * <p>
 * The order in which notifications will be delivered is unspecified.
 * The default implementation provided in the Observable class will
 * notify Observers in the order in which they registered interest, but
 * subclasses may change this order, use no guaranteed order, deliver
 * notifications on separate threads, or may guarantee that their
 * subclass follows this order, as they choose.
 * <p>
 * The methods are realized as default-methods with some tricky
 * static data structure to hold the observers and should not be implemented.
 * <p>
 * Note that this notification mechanism has nothing to do with threads
 * and is completely separate from the {@code wait} and {@code notify}
 * mechanism of class {@code Object}.
 * <p>
 * When an observable object is newly created, its set of observers is
 * empty. Two observers are considered the same if and only if the
 * {@code equals} method returns true for them.
 *
 * @param <E> Enumeration which denotes an Aspect which has changed
 * @param <T> the type of value which has changed
 *
 * @see		Observable#notifyObservers(Enum, Object)
 * @see     Observer#update(Enum, Object)
 * @version   1.0, 2022
 * 
 */
// see: https://stackoverflow.com/questions/13362636/a-generic-observer-pattern-in-java
public interface Observable<E extends Enum<E>, T> {

	class Observers {
		// Just to make sure, this is not accessed outside
		private static final Map<Observable<?, ?>, List<Observer<?,?>>> OBSERVERS = new HashMap<>();
	}

	private List<Observer<?,?>> observers() {
		return Observers.OBSERVERS.computeIfAbsent(this, k -> new ArrayList<>());
	}

    /**
     * Adds an observer to the set of observers for this object, provided
     * that it is not the same as some observer already in the set.
     * The order in which notifications will be delivered to multiple
     * observers is not specified. See the class comment.
     *
     * @param   obs   an observer to be added.
     * @throws IllegalArgumentException   if the parameter obs is null.
     */
	default void addObserver(Observer<E,?> obs) {
		if (obs == null)
			throw new IllegalArgumentException("Tried to add a null observer");

		synchronized (obs) {
			if (!observers().contains(obs)){
				observers().add(obs);
			}
		}
	}

    /**
     * Deletes an observer from the set of observers of this object.
     * Passing {@code null} to this method will have no effect.
     * @param   obs   the observer to be deleted.
     */
	default void deleteObserver(Observer<E,?> obs) {
		if (obs == null)
			return;

		synchronized (obs) {
			observers().remove(obs);
		}
	}

    /**
     * Notify all of its observers that this object has changed.
     * <p>
     * Each observer has its {@code update} method called with two
     * arguments: the aspect which has changed and the data which
     * has changed.
     *
     * @param aspect the aspect which has changed, this has to be an Enumeration type
     * @param data the data which has changed

     * @see     Observer#update(E aspect, T data)
     */
	@SuppressWarnings("unchecked")
	default void notifyObservers(E aspect, T data) {
		List<Observer<E,T>> observerCopy = new ArrayList<>();
		synchronized (this) {
//			observerCopy.addAll((Collection<? extends Observer<E, T>>) observers()); // gives maven compilation error
			for(Observer<?, ?> o : observers())
				observerCopy.add((Observer<E, T>) o);
		}

		for (Observer<E,T> obs : observerCopy) {
			obs.update(aspect, data);
		}
	}

}
