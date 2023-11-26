/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package kgosi.takehomeexam;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

class EstateAgent implements IEstateAgent {
    public double CalculateCommission(String propertyPrice, String agentCommission) {
        double property_price = Double.parseDouble(propertyPrice);
        double agent_commission = Double.parseDouble(agentCommission);
        return property_price * (agent_commission / 100);
    }

    class Data {
        private String location;
        private String agentName;
        private double propertyPrice;
        private double commissionPercentage;

        public Data(String location, String agentName, double propertyPrice, double commissionPercentage) {
            this.location = location;
            this.agentName = agentName;
            this.propertyPrice = propertyPrice;
            this.commissionPercentage = commissionPercentage;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getAgentName() {
            return agentName;
        }

        public void setAgentName(String agentName) {
            this.agentName = agentName;
        }

        public double getPropertyPrice() {
            return propertyPrice;
        }

        public void setPropertyPrice(double propertyPrice) {
            this.propertyPrice = propertyPrice;
        }

        public double getCommissionPercentage() {
            return commissionPercentage;
        }

        public void setCommissionPercentage(double commissionPercentage) {
            this.commissionPercentage = commissionPercentage;
        }
    }

    public boolean ValidateData(Data dataToValidate) {
        // Add validation logic as needed
        return true;
    }
}

public class TakehomeExam {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TakehomeExam().createAndShowGUI();
        });
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Estate Agent Report");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        initializeComponents(frame);

        frame.setVisible(true);
    }

    private void initializeComponents(JFrame frame) {
        JLabel imageLabel;
        JButton browseButton = new JButton("Browse");
        imageLabel = new JLabel();

        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadImage(imageLabel);
            }

            private void loadImage(JLabel label) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Choose an Image File");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

                int result = fileChooser.showOpenDialog(frame);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
                    Image image = imageIcon.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
                    imageIcon = new ImageIcon(image);
                    label.setIcon(imageIcon);
                }
            }
        });
        frame.add(browseButton, BorderLayout.NORTH);
        frame.add(imageLabel, BorderLayout.LINE_START);
        frame.setLayout(new GridLayout(6,3));
        JLabel agent_location = new JLabel("AGENT LOCATION:");
        JComboBox<String> location_choice = new JComboBox<>(new String[]{"Cape Town", "Durban", "Pretoria"});
        JLabel agents_name = new JLabel("ESTATE AGENT NAME:");
        JTextField nameField = new JTextField(12);
        JLabel propertyPrice = new JLabel("PROPERTY PRICE:");
        JTextField property_price_Field = new JTextField(12);
        JLabel commissionPercentage = new JLabel("COMMISSION PERCENTAGE:");
        JTextField commission_percentage_Field = new JTextField(12);
        JLabel agentReport = new JLabel("ESTATE AGENT REPORT:");
        JTextArea commissionTextArea = new JTextArea();
        commissionTextArea.setEditable(false);

        JMenu fileMenu = new JMenu("File");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exitMenuItem);

        JMenu toolsMenu = new JMenu("Tools");
        JMenuItem clearMenuItem = new JMenuItem("Clear");
        JMenuItem report_process = new JMenuItem("Process Report");
        JMenuItem saveReportMenuItem = new JMenuItem("Save Report");

        toolsMenu.add(report_process);
        toolsMenu.add(clearMenuItem);
        toolsMenu.add(saveReportMenuItem);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        frame.setJMenuBar(menuBar);

        report_process.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double propertyPrices = Double.parseDouble(property_price_Field.getText());
                double commissionPercentages = Double.parseDouble(commission_percentage_Field.getText());
                double commission = propertyPrices * (commissionPercentages / 100);
                String commissions = Double.toString(commission);
                String commission_percentage = Double.toString(commissionPercentages);
                String propertyPRICE = Double.toString(propertyPrices);
                commissionTextArea.setText("AGENT LOCATION: " + location_choice.getSelectedItem() + "\n"
                        + "AGENT NAME: " + nameField.getText() + "\n"
                        + "PROPERTY PRICE: R" + propertyPRICE + "\n"
                        + "COMMISSION PERCENTAGE: " + commission_percentage + " % \n"
                        + "CALCULATE COMMISSION: R" + commissions);
            }
        });

        clearMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameField.setText("");
                property_price_Field.setText("");
                commission_percentage_Field.setText("");
                commissionTextArea.setText("");
            }
        });

        saveReportMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Save Report");

                int userSelection = fileChooser.showSaveDialog(frame);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    try (PrintWriter writer = new PrintWriter(new FileWriter(fileToSave))) {
                        writer.println(commissionTextArea.getText());
                        JOptionPane.showMessageDialog(frame, "Report saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } catch (IOException ex) {
                        Logger.getLogger(TakehomeExam.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(frame, "Error saving report.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        frame.add(agent_location);
        frame.add(location_choice);
        frame.add(agents_name);
        frame.add(nameField);
        frame.add(propertyPrice);
        frame.add(property_price_Field);
        frame.add(commissionPercentage);
        frame.add(commission_percentage_Field);
        frame.add(agentReport);
        frame.add(commissionTextArea);
    }
}
