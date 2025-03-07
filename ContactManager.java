import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
class Contact {
    String name, phone, email;
    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
}
public class ContactManager {
    private JFrame frame;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private DefaultListModel<String> contactListModel;
    private JList<String> contactList;
    private ArrayList<Contact> contacts;
    private JTextField nameField, phoneField, emailField;
    private JLabel detailsLabel;
    public ContactManager() {
        frame = new JFrame("Contact Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        contacts = new ArrayList<>();
        contactListModel = new DefaultListModel<>();
        contactList = new JList<>(contactListModel);
        setupContactListView();
        setupContactDetailsView();
        setupContactCreationView();
        frame.add(cardPanel);
        frame.setVisible(true);
    }
    private void setupContactListView() {
        JPanel listPanel = new JPanel(new BorderLayout());   
        JButton addButton = new JButton("Add New Contact");
        JButton viewButton = new JButton("View Details");
        addButton.addActionListener(e -> cardLayout.show(cardPanel, "Create"));
        viewButton.addActionListener(e -> showContactDetails());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        listPanel.add(new JScrollPane(contactList), BorderLayout.CENTER);
        listPanel.add(buttonPanel, BorderLayout.SOUTH);
        cardPanel.add(listPanel, "List");
    }
    private void setupContactDetailsView() {
        JPanel detailsPanel = new JPanel(new BorderLayout());
        detailsLabel = new JLabel("No contact selected", SwingConstants.CENTER);
        JButton backButton = new JButton("Back to List");   
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "List"));
        detailsPanel.add(detailsLabel, BorderLayout.CENTER);
        detailsPanel.add(backButton, BorderLayout.SOUTH);
        cardPanel.add(detailsPanel, "Details");
    }
    private void setupContactCreationView() {
        JPanel createPanel = new JPanel(new GridLayout(4, 2));   
        createPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        createPanel.add(nameField);
        createPanel.add(new JLabel("Phone:"));
        phoneField = new JTextField();
        createPanel.add(phoneField);
        createPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        createPanel.add(emailField);
        JButton saveButton = new JButton("Save Contact");
        JButton cancelButton = new JButton("Cancel");
        saveButton.addActionListener(e -> saveContact());
        cancelButton.addActionListener(e -> cardLayout.show(cardPanel, "List"));
        createPanel.add(saveButton);
        createPanel.add(cancelButton);
        cardPanel.add(createPanel, "Create");
    }
    private void showContactDetails() {
        int selectedIndex = contactList.getSelectedIndex();
        if (selectedIndex != -1) {
            Contact contact = contacts.get(selectedIndex);
            detailsLabel.setText("Name: " + contact.name + "Phone: " + contact.phone + "Email: " + contact.email);
            cardLayout.show(cardPanel, "Details");
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a contact first.");
        }
    }
    private void saveContact() {
        String name = nameField.getText().trim();
        String phone = phoneField.getText().trim();
        String email = emailField.getText().trim();   
        if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "All fields must be filled out.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        contacts.add(new Contact(name, phone, email));
        contactListModel.addElement(name);
        nameField.setText("");
        phoneField.setText("");
        emailField.setText("");
        cardLayout.show(cardPanel, "List");
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ContactManager::new);
    }

    public void addContact(String alice, String number, String mail) {
    }
}
