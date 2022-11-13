package View;

/**
 * Base class for MoviegoerView, StaffView
 */
public abstract class View {
    /** The {@code View} before this. */
    public View prevView;

    /** Method is called when transition using intent. */
    protected abstract void start();

    /** Destroys the current {@code View} by calling the start of previous {@code View}. */
    protected void destroy() {
        if (prevView == null) System.exit(1);
        else prevView.start();
    }

    /**
     * Transits from one {@code View} to another.
     * @param v the prevView of u
     * @param u the {@code View} to be started
     */
    protected void intent(View v, View u) {
        u.prevView = v;
        u.start();
    }

    /**
     * Get the previous {@code View}
     * @return the previous {@code View}
     */
    protected View getPrevView() {
        return prevView;
    }
}
