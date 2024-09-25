//package data;
//
//import org.zkoss.zk.ui.Component;
//import org.zkoss.zk.ui.select.SelectorComposer;
//import org.zkoss.zk.ui.select.annotation.Listen;
//import org.zkoss.zk.ui.select.annotation.Wire;
//import org.zkoss.zul.Label;
//import org.zkoss.zul.ListModelList;
//import org.zkoss.zul.Listbox;
//import org.zkoss.zul.Textbox;
//import org.zkoss.zul.Messagebox;
//
//import java.util.List;
//import java.util.Set;
//
//public class UserController extends SelectorComposer<Component> {
//
//    @Wire
//    private Textbox keywordBox; // For search functionality
//    @Wire
//    private Listbox userListBox; // Listbox for displaying users
//    @Wire
//    private Label idLabel;
//    @Wire
//    private Label nameLabel;
//    @Wire
//    private Label genderLabel;
//    @Wire
//    private Label birthdayLabel;
//    @Wire
//    private Label countryLabel;
//
//    // Data model to manage user list
//    private ListModelList<User> dataModel = new ListModelList<>();
//    private UserService userService = new UserServiceImpl(); // UserService to manage user data
//
//    public UserController() {
//        dataModel.addAll(userService.findAll()); // Load all users initially
//    }
//
//    @Override
//    public void doAfterCompose(Component comp) throws Exception {
//        super.doAfterCompose(comp);
//        userListBox.setModel(dataModel); // Set the data model to the listbox
//    }
//
//    // Show user details when a user is selected in the listbox
//    @Listen("onSelect = #userListBox")
//    public void showDetail() {
//        Set<User> selection = dataModel.getSelection();
//        if (!selection.isEmpty()) {
//            User selected = selection.iterator().next(); // Get the selected user
//            idLabel.setValue(selected.getId().toString());
//            nameLabel.setValue(selected.getName());
//            genderLabel.setValue(selected.getGender());
//            birthdayLabel.setValue(selected.getBirthday());
//            countryLabel.setValue(selected.getCountry());
//        }
//    }
//
//    // Search users by keyword entered in the textbox
//    @Listen("onClick = #searchButton")
//    public void searchUser() {
//        String keyword = keywordBox.getValue(); // Get search keyword
//        List<User> searchResult = userService.search(keyword); // Fetch users by keyword
//        dataModel.clear();
//        dataModel.addAll(searchResult); // Update the listbox with search results
//    }
//
//    // Delete a selected user from the listbox
//    @Listen("onClick = .deleteButton")
//    public void deleteUser() {
//        Set<User> selectedUsers = dataModel.getSelection(); // Get the selected user(s)
//
//        if (selectedUsers != null && !selectedUsers.isEmpty()) {
//            User userToDelete = selectedUsers.iterator().next(); // Get the first selected user
//            userService.delete(userToDelete); // Delete the user from the service
//            dataModel.remove(userToDelete); // Remove the user from the listbox
//            Messagebox.show("User deleted successfully.", "Information", Messagebox.OK, Messagebox.INFORMATION);
//        } else {
//            Messagebox.show("Please select a user to delete!", "Error", Messagebox.OK, Messagebox.ERROR);
//        }
//    }
//
//        @Listen("onClick = #updateButton")
//        public void updateUser() {
//            Set<User> selection = dataModel.getSelection();
//            if (!selection.isEmpty()) {
//                User userToUpdate = selection.iterator().next();
//                userToUpdate.setName(nameLabel.getValue());
//                userToUpdate.setGender(genderLabel.getValue());
//                userToUpdate.setBirthday(birthdayLabel.getValue());
//                userToUpdate.setCountry(countryLabel.getValue());
//
//                userService.update(userToUpdate);
//                Messagebox.show("User updated successfully.", "Information",
//                        Messagebox.OK, Messagebox.INFORMATION);
//                // Optionally refresh the user list
//                userListBox.invalidate();
//            } else {
//                Messagebox.show("Please select a user to update!", "Error",
//                        Messagebox.OK, Messagebox.ERROR);
//            }
//        }
//    }

package data;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Messagebox;

import java.util.List;
import java.util.Set;

public class UserController extends SelectorComposer<Component> {

    @Wire
    private Textbox keywordBox; // For search functionality
    @Wire
    private Listbox userListBox; // Listbox for displaying users
    @Wire
    private Label idLabel; // For displaying user ID
    @Wire
    private Textbox nameLabel; // For editable user name
    @Wire
    private Textbox genderLabel; // For editable user gender
    @Wire
    private Textbox birthdayLabel; // For editable user birthday
    @Wire
    private Textbox countryLabel; // For editable user country

    // Data model to manage user list
    private ListModelList<User> dataModel = new ListModelList<>();
    private UserService userService = new UserServiceImpl(); // UserService to manage user data

    public UserController() {
        dataModel.addAll(userService.findAll()); // Load all users initially
    }

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        userListBox.setModel(dataModel); // Set the data model to the listbox
    }

    // Show user details when a user is selected in the listbox
    @Listen("onSelect = #userListBox")
    public void showDetail() {
        Set<User> selection = dataModel.getSelection();
        if (!selection.isEmpty()) {
            User selected = selection.iterator().next(); // Get the selected user
            idLabel.setValue(selected.getId().toString());
            nameLabel.setValue(selected.getName());
            genderLabel.setValue(selected.getGender());
            birthdayLabel.setValue(selected.getBirthday());
            countryLabel.setValue(selected.getCountry());
        }
    }

    // Search users by keyword entered in the textbox
    @Listen("onClick = #searchButton")
    public void searchUser() {
        String keyword = keywordBox.getValue(); // Get search keyword
        List<User> searchResult = userService.search(keyword); // Fetch users by keyword
        dataModel.clear();
        dataModel.addAll(searchResult); // Update the listbox with search results
    }

    // Delete a selected user from the listbox
    @Listen("onClick = .deleteButton")
    public void deleteUser() {
        Set<User> selectedUsers = dataModel.getSelection(); // Get the selected user(s)

        if (selectedUsers != null && !selectedUsers.isEmpty()) {
            User userToDelete = selectedUsers.iterator().next(); // Get the first selected user
            userService.delete(userToDelete); // Delete the user from the service
            dataModel.remove(userToDelete); // Remove the user from the listbox
            Messagebox.show("User deleted successfully.", "Information", Messagebox.OK, Messagebox.INFORMATION);
        } else {
            Messagebox.show("Please select a user to delete!", "Error", Messagebox.OK, Messagebox.ERROR);
        }
    }

    // Update user details
    @Listen("onClick = #updateButton")
    public void updateUser() {
        Set<User> selection = dataModel.getSelection();
        if (!selection.isEmpty()) {
            User userToUpdate = selection.iterator().next();
            userToUpdate.setName(nameLabel.getValue());
            userToUpdate.setBirthday(birthdayLabel.getValue());
            userToUpdate.setCountry(countryLabel.getValue());

            userService.update(userToUpdate);
            Messagebox.show("User updated successfully.", "Information",
                    Messagebox.OK, Messagebox.INFORMATION);
            // Optionally refresh the user list
            userListBox.invalidate(); // Refresh the listbox to reflect changes
            dataModel.clear();
            dataModel.addAll(userService.findAll());
        } else {
            Messagebox.show("Please select a user to update!", "Error",
                    Messagebox.OK, Messagebox.ERROR);
        }
    }
}
