package data;

import org.apache.commons.io.input.TeeInputStream;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import sun.security.x509.UniqueIdentity;

import java.util.UUID;

public class RegisFormController extends SelectorComposer<Component> {
    @Wire
    private Textbox nameInput;
    @Wire
    private Textbox genderInput;
    @Wire
    private Textbox birthdayInput;
    @Wire
    private Textbox countryInput;

    private UserService userService = new UserServiceImpl();

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
    }

    @Listen("onClick = #submitButton")
    public void submitData(){
        System.out.println(nameInput.getValue());

        userService.add(new User(UUID.randomUUID().toString(),nameInput.getValue(), genderInput.getValue(),
                birthdayInput.getValue(), countryInput.getValue()));

        Executions.sendRedirect("playerSearch.zul");
    }
}
