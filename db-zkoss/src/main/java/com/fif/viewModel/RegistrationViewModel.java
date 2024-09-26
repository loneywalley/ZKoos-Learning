package com.fif.viewModel;

import com.fif.entity.Registration;
import com.fif.services.RegisService;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.lang.Strings;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;

import java.util.*;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class RegistrationViewModel {

    @WireVariable
    private RegisService regisService;

    private ListModelList<Registration> regisListModel = new ListModelList<>();
    private int id;
    private String name;
    private Date birthdate;
    private String gender;
    private String country;
    private boolean agree;
    private boolean canSubmit;
    private String searchText = "";  // Default to empty string for search functionality
    private Map<Registration, Boolean> editing = new HashMap<>();

    @Init
    public void init() {
        // Fetch initial data from the service
        List<Registration> regisList = regisService.getRegis();
        regisListModel.addAll(regisList);

        // Store in session for state management
        Session session = Sessions.getCurrent();
        if (regisList == null) {
            session.setAttribute("registrationList", regisListModel);
        }
    }



    @Command
    @NotifyChange("regisListModel")
    public void submit() {
        Registration registration = new Registration(name, birthdate, gender, country);
        registration = regisService.addRegis(registration);  // Add registration to the service
        regisListModel.add(registration);

        // Sync with session data
        Session session = Sessions.getCurrent();
        if (session != null) {
            session.setAttribute("registrationList", regisListModel);
        }

        clearForm();  // Clear form after submission
        Executions.sendRedirect("registrationform.zul");    }

    @Command
    @NotifyChange({"name", "birthdate", "gender", "country", "agree", "canSubmit"})
    public void clearForm() {
        name = "";
        birthdate = null;
        gender = "";
        country = "";
        agree = false;
        canSubmit = false;
    }

    @Command
    @NotifyChange("regisListModel")
    public void search() {
        List<Registration> filteredList = new ArrayList<>();
        for (Registration reg : regisService.getRegis()) {
            if (reg.getName().toLowerCase().contains(searchText.toLowerCase())) {
                filteredList.add(reg);
            }
        }
        regisListModel.clear();
        regisListModel.addAll(filteredList);
    }

    @Command
    @NotifyChange("canSubmit")
    public void validateForm() {
        canSubmit = name != null && !name.isEmpty() &&
                birthdate != null &&
                gender != null && !gender.isEmpty() &&
                country != null && !country.isEmpty();
    }

    @Command
    @NotifyChange("regisListModel")
    public void delete(@BindingParam("reg") Registration reg) {
        regisService.deleteRegis(reg);  // Delete from the service
        regisListModel.remove(reg);  // Remove from the list model

        // Sync with session data
        Session session = Sessions.getCurrent();
        if (session != null) {
            session.setAttribute("registrationList", regisListModel);
        }
    }

    @Command
    @NotifyChange("editing")
    public void edit(@BindingParam("reg") Registration reg) {
        editing.put(reg, true);  // Set the registration in edit mode
    }

    @Command
    @NotifyChange({"editing", "regisListModel"})
    public void save(@BindingParam("reg") Registration reg) {
        try {
            // Update the registration entity via the service
            regisService.updateRegis(reg);

            // Exit the edit mode for the updated registration
            editing.put(reg, false);

            // Sync the updated data with the session and UI list
            Session session = Sessions.getCurrent();
            if (session != null) {
                List<Registration> updatedList = regisService.getRegis();  // Fetch the updated list from the service
                regisListModel.clear();  // Clear the current list
                regisListModel.addAll(updatedList);  // Add all updated registrations to the model list
                session.setAttribute("registrationList", regisListModel);  // Sync with the session
            }

        } catch (Exception e) {
            // Handle any potential errors (log or notify user)
            System.out.println("Error updating registration: " + e.getMessage());
        }
    }


    // Getters and setters for ListModel and filteredList

    public ListModelList<Registration> getRegisListModel() {
        return regisListModel;
    }

    public RegisService getRegisService() {
        return regisService;
    }

    public void setRegisService(RegisService regisService) {
        this.regisService = regisService;
    }

    public void setRegisListModel(ListModelList<Registration> regisListModel) {
        this.regisListModel = regisListModel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isAgree() {
        return agree;
    }

    public void setAgree(boolean agree) {
        this.agree = agree;
    }

    public boolean isCanSubmit() {
        return canSubmit;
    }

    public void setCanSubmit(boolean canSubmit) {
        this.canSubmit = canSubmit;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public Map<Registration, Boolean> getEditing() {
        return editing;
    }

    public void setEditing(Map<Registration, Boolean> editing) {
        this.editing = editing;
    }
}
