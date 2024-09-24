package data;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

import java.util.Set;

public class UserController extends SelectorComposer<Component> {

    @Wire
    private Textbox keywordBox;
    @Wire
    private Listbox userListBox;
    @Wire
    private Label idLabel;
    @Wire
    private Label nameLabel;
    @Wire
    private Label genderLabel;
    @Wire
    private Label birthdayLabel;
    @Wire
    private Label countryLabel;


    private ListModelList<User> dataModel = new ListModelList<>();

    private UserService userService = new UserServiceImpl();

    public UserController() {
        dataModel.addAll(userService.findAll());
    }

    @Override
    public void doAfterCompose(Component comp) throws Exception{
        super.doAfterCompose(comp);
        userListBox.setModel(dataModel);
    }

    @Listen("onClick = #deleteButton")
    public void deleteItem(){
        
    }

    @Listen("onSelect = #userListBox")
    public void showDetail(){
        Set<User> selection = dataModel.getSelection();
        User selected = selection.iterator().next();

        idLabel.setValue(selected.getId().toString());
        nameLabel.setValue(selected.getName());
        genderLabel.setValue(selected.getGender());
        birthdayLabel.setValue(selected.getBirthday());
        countryLabel.setValue(selected.getCountry());
    }




}
