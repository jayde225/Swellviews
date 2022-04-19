package MainMVC;

public class MainController {
    private MainModel model;
    private MainView view;

    public MainController(MainModel model, MainView view) {
        this.model = model;
        this.view = view;
    }
}