package shop.model.mobile;

import shop.model.observer.Aspect;
import shop.model.observer.Observer;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;

public class MobilePhone extends JFrame implements Observer<Aspect, String> {

    private final JTextArea text;

    public MobilePhone(String number) {
        super("Phone #" + number);
        text = new JTextArea();

        DefaultCaret caret = (DefaultCaret) text.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        this.add(new JScrollPane(text));

        text.setText("All messages on your mobile phone...\n");
        this.setPreferredSize(new Dimension(300, 400));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.setLocationByPlatform(true);
        this.pack();
    }

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
     * @param data   the data which has changed
     */
    @Override
    public void update(Aspect aspect, String data) {
        text.append(data + "\n");
    }
}
