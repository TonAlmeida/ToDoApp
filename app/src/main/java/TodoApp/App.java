
package TodoApp;
import view.LoginScreen;

public class App {

    public static void main(String[] args) {
        try {
            LoginScreen log = new LoginScreen();
            log.setVisible(true);
        } catch (Exception e) {
            throw new RuntimeException("erro ao iniciar a tela principal  "
                    + e.getMessage(), e);
        }
    }
}
