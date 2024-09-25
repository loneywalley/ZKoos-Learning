package data;

import data.Registration;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import java.util.*;
import java.util.stream.Collectors;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class RegistrationViewModel {

    private String id;
    private String name;
    private Date birthdate;
    private String gender;
    private String country;
    private boolean agree;
    private boolean canSubmit;
    private String searchText;

    private ListModelList<Registration> modelList;
    private ListModelList<Registration> filteredList;
    private Map<Registration, Boolean> editing = new HashMap<>();

    @Init
    public void init() {
        // Retrieve the list from session if it exists, otherwise initialize a new list
        Session session = Sessions.getCurrent();
        modelList = (ListModelList<Registration>) session.getAttribute("registrationList");
        if (modelList == null) {
            modelList = new ListModelList<>(new ArrayList<>());
            addDummyData();
            session.setAttribute("registrationList", modelList);
        }
        filteredList = new ListModelList<>(modelList);
    }

    // Adding dummy data for initial testing
    private void addDummyData() {
        modelList.add(new Registration(UUID.randomUUID().toString(),"John Doe", new Date(1985-1900, 6, 15), "Male", "Indonesia"));
        modelList.add(new Registration(UUID.randomUUID().toString(),"Jane Smith", new Date(1990-1900, 1, 20), "Female", "Malaysia"));
        modelList.add(new Registration(UUID.randomUUID().toString(),"Tom Brown", new Date(1988-1900, 10, 30), "Male", "Singapore"));
        modelList.add(new Registration(UUID.randomUUID().toString(),"Lisa Wong", new Date(1995-1900, 4, 10), "Female", "Thailand"));
    }

    // Form validation method
    @Command
    @NotifyChange("canSubmit")
    public void validateForm() {
        canSubmit = name != null && !name.isEmpty() &&
                birthdate != null &&
                gender != null && !gender.isEmpty() &&
                country != null && !country.isEmpty();
    }

    // Search functionality
    @Command
    @NotifyChange("filteredList")
    public void search() {
        if (searchText == null || searchText.isEmpty()) {
            filteredList = new ListModelList<>(modelList);  // Show all records if search is empty
        } else {
            filteredList = new ListModelList<>(modelList.stream()
                    .filter(registration -> registration.getName().toLowerCase().contains(searchText.toLowerCase()) ||
                            registration.getCountry().toLowerCase().contains(searchText.toLowerCase()))
                    .collect(Collectors.toList()));
        }
    }

    // Edit functionality
    @Command
    @NotifyChange("editing")
    public void edit(@BindingParam("reg") Registration reg) {
        editing.put(reg, true);
    }

    // Save functionality
    @Command
    @NotifyChange({"editing", "filteredList", "modelList"})
    public void save(@BindingParam("reg") Registration reg) {
        editing.put(reg, false);

        // Sync with session data
        Session session = Sessions.getCurrent();
        if (session != null) {
            session.setAttribute("registrationList", modelList);
        }
    }

    @Command
    @NotifyChange("modelList")
    public void submit() {
        id = UUID.randomUUID().toString();
        Registration registration = new Registration(id, name, birthdate, gender, country);
        modelList.add(registration);

        // Sync with session data
        Session session = Sessions.getCurrent();
        if (session != null) {
            session.setAttribute("registrationList", modelList);
        }

        clearForm();
        Executions.sendRedirect("playerSearch.zul");
    }


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

    // Delete functionality
    @Command
    @NotifyChange({"filteredList", "modelList"})
    public void delete(@BindingParam("reg") Registration reg) {
        modelList.remove(reg);
        filteredList.remove(reg);

        // Sync with session data
        Session session = Sessions.getCurrent();
        if (session != null) {
            session.setAttribute("registrationList", modelList);
        }
    }

    // Getters and Setters
    public ListModelList<Registration> getFilteredList() {
        return filteredList;
    }

    public Map<Registration, Boolean> getEditing() {
        return editing;
    }


    // Getters and Setters


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Date getBirthdate() { return birthdate; }
    public void setBirthdate(Date birthdate) { this.birthdate = birthdate; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public boolean isAgree() { return agree; }
    public void setAgree(boolean agree) { this.agree = agree; }

    public boolean isCanSubmit() { return canSubmit; }

    public ListModelList<Registration> getModelList() {
        return modelList;
    }
    public String getSearchText() {
        return searchText;
    }
}
