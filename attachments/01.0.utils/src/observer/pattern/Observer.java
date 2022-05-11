package observer.pattern;

/**
 * A class can implement the {@code Observer} interface when it
 * wants to be informed of changes in observable objects.

 * @param <E> Enumeration which denotes an Aspect which has changed
 * @param <T> the type of a value which has changed
 *
 * @see     observer.pattern.Observable
 * @version   1.0, 2022
 *
 */
public interface Observer<E extends Enum<E>, T> {

    /**
     * This method is called whenever the observed object is changed. An
     * application calls an {@code Observable} object's
     * {@code notifyObservers} method to have all the object's
     * observers notified of the change.
     * <p>
     * Typically, one would switch over the aspect which has changed and perform 
     * the fitting update with the help of the supplied data.
     *
     * @param aspect the aspect which has changed, this has to be an Enumeration type
     * @param data the data which has changed
     */
	void update(E aspect, T data);

}
