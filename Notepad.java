import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * This class represents a notepad application with a graphical user interface (GUI).
 * It allows the user to create, open, save, and edit text files.
 */
public class Notepad extends JFrame implements ActionListener {
    private JTextArea textArea;
    private JFileChooser fileChooser;
    private JLabel statusBar;

    /**
     * Constructor to initialize the notepad application.
     */
    public Notepad() {
        // Set the title of the notepad window
        super("Notepad");

        // Create a JTextArea for the text editing area
        textArea = new JTextArea();

        // Create a JScrollPane to add scrollbars to the text area
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Add the scroll pane to the content pane of the JFrame
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Create a JMenuBar for the menu options
        JMenuBar menuBar = new JMenuBar();

        // Create a JMenu for the file options
        JMenu fileMenu = new JMenu("File");

        // Create JMenuItem for the "New" option
        JMenuItem newMenuItem = new JMenuItem("New");
        newMenuItem.addActionListener(this);
        newMenuItem.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        fileMenu.add(newMenuItem);

        // Create JMenuItem for the "Open" option
        JMenuItem openMenuItem = new JMenuItem("Open");
        openMenuItem.addActionListener(this);
        openMenuItem.setAccelerator(KeyStroke.getKeyStroke('O', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        fileMenu.add(openMenuItem);

        // Create JMenuItem for the "Save" option
        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.addActionListener(this);
        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        fileMenu.add(saveMenuItem);

        // Create JMenuItem for the "Exit" option
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(this);
        exitMenuItem.setAccelerator(KeyStroke.getKeyStroke('Q', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        fileMenu.add(exitMenuItem);

        // Add the file menu to the menu bar
        menuBar.add(fileMenu);

        // Set the menu bar for the JFrame
        setJMenuBar(menuBar);

        // Create a JToolBar for quick access to file operations
        JToolBar toolBar = new JToolBar();
        JButton newButton = new JButton(new ImageIcon("icons/new.png"));
        newButton.setToolTipText("New");
        newButton.addActionListener(this);
        toolBar.add(newButton);

        JButton openButton = new JButton(new ImageIcon("icons/open.png"));
        openButton.setToolTipText("Open");
        openButton.addActionListener(this);
        toolBar.add(openButton);

        JButton saveButton = new JButton(new ImageIcon("icons/save.png"));
        saveButton.setToolTipText("Save");
        saveButton.addActionListener(this);
        toolBar.add(saveButton);

        getContentPane().add(toolBar, BorderLayout.NORTH);

        // Create a status bar to display messages
        statusBar = new JLabel("Ready");
        getContentPane().add(statusBar, BorderLayout.SOUTH);

        // Set the size and visibility of the JFrame
        setSize(800, 600);
        setVisible(true);
    }

    /**
     * ActionListener implementation to handle menu item actions.
     *
     * @param e The ActionEvent triggered by the menu item.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("New")) {
            newFile();
        } else if (command.equals("Open")) {
            openFile();
        } else if (command.equals("Save")) {
            saveFile();
        } else if (command.equals("Exit")) {
            exitApplication();
        }
    }

    /**
     * Creates a new file by clearing the text area.
     */
    private void newFile() {
        textArea.setText("");
        statusBar.setText("New file created");
    }

    /**
     * Opens an existing file and displays its contents in the text area.
     */
    private void openFile() {
        fileChooser = new JFileChooser();

        // Show the open file dialog
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                StringBuilder stringBuilder = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                    stringBuilder.append("\n");
                }

                reader.close();

                textArea.setText(stringBuilder.toString());
                statusBar.setText("Opened: " + file.getName());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error opening file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                statusBar.setText("Error opening file");
            }
        }
    }

    /**
     * Saves the contents of the text area to a file.
     */
    private void saveFile() {
        fileChooser = new JFileChooser();

        // Show the save file dialog
        int result = fileChooser.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write(textArea.getText());
                writer.close();

                JOptionPane.showMessageDialog(this, "File saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                statusBar.setText("Saved: " + file.getName());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error saving file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                statusBar.setText("Error saving file");
            }
        }
    }

    /**
     * Exits the application.
     */
    private void exitApplication() {
        System.exit(0);
    }

    /**
     * Main method to start the Notepad application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Create an instance of Notepad
        Notepad notepad = new Notepad();
    }
}