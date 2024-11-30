package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class LoginApp extends JFrame {
private JTextField emailField;
private JPasswordField passwordField;
private static final String DB_URL =
&quot;jdbc:mysql://localhost:3306/softwaretesting&quot;;
private static final String DB_USER = &quot;root&quot;;
private static final String DB_PASSWORD = &quot;root&quot;;
public LoginApp() {
setTitle(&quot;Login Screen&quot;);
setSize(350, 200);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setLocationRelativeTo(null);
JPanel panel = new JPanel();
panel.setLayout(new GridLayout(3, 2, 10, 10));

panel.add(new JLabel(&quot;Email:&quot;));
emailField = new JTextField();
panel.add(emailField);
panel.add(new JLabel(&quot;Password:&quot;));
passwordField = new JPasswordField();
panel.add(passwordField);
JButton loginButton = new JButton(&quot;Login&quot;);
loginButton.addActionListener(new LoginAction());
panel.add(loginButton);
add(panel);
}
private class LoginAction implements ActionListener {
@Override
public void actionPerformed(ActionEvent e) {
String email = emailField.getText().trim();
String password = new String(passwordField.getPassword()).trim();
if (email.isEmpty() || password.isEmpty()) {
JOptionPane.showMessageDialog(null, &quot;Please enter both email
and password.&quot;, &quot;Input Error&quot;, JOptionPane.ERROR_MESSAGE);
return;
}
String userName = authenticateUser(email, password);
if (userName != null) {
JOptionPane.showMessageDialog(null, &quot;Welcome, &quot; + userName +
&quot;!&quot;, &quot;Login Successful&quot;, JOptionPane.INFORMATION_MESSAGE);
} else {
JOptionPane.showMessageDialog(null, &quot;Invalid email or
password.&quot;, &quot;Login Failed&quot;, JOptionPane.ERROR_MESSAGE);
}
}
}
protected String authenticateUser(String email, String password) {
String userName = null;
String query = &quot;SELECT Name FROM User WHERE Email = ? AND Password =
?&quot;;
try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER,
DB_PASSWORD);
PreparedStatement stmt = conn.prepareStatement(query)) {
stmt.setString(1, email);

stmt.setString(2, password);
try (ResultSet rs = stmt.executeQuery()) {
if (rs.next()) {
userName = rs.getString(&quot;Name&quot;);
}
}
} catch (SQLException e) {
e.printStackTrace();
}
return userName;
}
public static void main(String[] args) {
SwingUtilities.invokeLater(() -&gt; {
LoginApp loginApp = new LoginApp();
loginApp.setVisible(true);
});
}
}
