package example.com.jiotwittercasestudy.mvp;

/**
 * Created by sureshs on 03-01-2018.
 */

public interface Presenter<V extends View> {
    void attachView(V view);

    void detachView();
}
