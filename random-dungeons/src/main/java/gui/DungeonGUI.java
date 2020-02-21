
package gui;

import dungeon.Dungeon;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import ruins.Ruins;

/**
 * This is the GUI class for creating random dungeons using the Dungeon class.
 * @author lauri
 */
public class DungeonGUI extends JFrame implements ActionListener {
    
    /**
     * Constructor for DungeonGUI. Runs initGUI() method.
     */
    public DungeonGUI() {
        initGUI();
    }
    
    /**
     * This method initializes the GUI for the dungeon generator.
     */
    public final void initGUI() {
        
        // x coordinate of first label
        int firstX = 75;
        // y coordinate of first label, increase by 25 from label, 30 from drop down
        int firstY = 10;
        // button/drop down width
        int buttonWidth = 150;
        // button/drop down height
        int buttonHeight = 30;
        
        // create dungeon type label + drop down
        JLabel dungeonTypeLabel = new JLabel("Dungeon type");
        
        String[] dungeonTypes = { "Cavern", "Ruins" };
        JComboBox dungeonList = new JComboBox(dungeonTypes);
        dungeonList.setSelectedIndex(0);
        
        dungeonTypeLabel.setBounds(firstX, firstY, buttonWidth, buttonHeight);
        dungeonList.setBounds(firstX, firstY + 25, buttonWidth, buttonHeight);
        
        getContentPane().add(dungeonTypeLabel);
        getContentPane().add(dungeonList);
        
        
        
        
        // create dungeon size label + drop down
        JLabel dungeonSizeLabel = new JLabel("Dungeon size");
        
        String[] dungeonSizes = { "Small", "Medium", "Large" };
        JComboBox sizeList = new JComboBox(dungeonSizes);
        sizeList.setSelectedIndex(1);

        dungeonSizeLabel.setBounds(firstX, firstY + 55, 
                buttonWidth, buttonHeight);
        sizeList.setBounds(firstX, firstY + 80, 
                buttonWidth, buttonHeight);

        getContentPane().add(dungeonSizeLabel);
        getContentPane().add(sizeList);



        
        // create wall density label + drop down
        JLabel wallDensityLabel = new JLabel("Wall density");
        
        String[] wallDensities = { "Low", "Medium", "High" };
        JComboBox densityList = new JComboBox(wallDensities);
        densityList.setSelectedIndex(1);
        
        wallDensityLabel.setBounds(firstX, firstY + 110, 
                buttonWidth, buttonHeight);
        densityList.setBounds(firstX, firstY + 135, 
                buttonWidth, buttonHeight);
        
        getContentPane().add(wallDensityLabel);
        getContentPane().add(densityList);
        
        // create random seed spinner
        JLabel randomSeedLabel = new JLabel("Random seed (default 0)");
        SpinnerNumberModel seedModel = new SpinnerNumberModel(0, 0, 10000, 1);
        JSpinner seedSpinner = new JSpinner(seedModel);
        
        randomSeedLabel.setBounds(firstX - 15, firstY + 165, 
                buttonWidth + 60, buttonHeight);
        seedSpinner.setBounds(firstX, firstY + 190, buttonWidth, buttonHeight);
        
        getContentPane().add(randomSeedLabel);
        getContentPane().add(seedSpinner);
        
        
        
        
        // create buttons
        JButton generateButton = new JButton("Generate");
        JButton quitButton = new JButton("Quit");
        
        // define button measures & add to frame        
        generateButton.setBounds(firstX, firstY + 240, 
                buttonWidth, buttonHeight);
        quitButton.setBounds(firstX, firstY + 300, buttonWidth, buttonHeight);
        
        getContentPane().setLayout(null);
        getContentPane().add(generateButton);
        getContentPane().add(quitButton);

        setTitle("Dungeon Generator");
        setSize(300, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
        
        /* functionality for generate button
         * 
         * gets user input from the GUI
         * creates a new frame and editor pane for the resulting dungeon
         * creates a CSS style sheet for the text to control line-height and monospace font
         * adds a close button for the new frame
         * creates a dungeon and prints it to the window
         */
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                
                // get user input from GUI for the dungeon generator
                int type = getDungeonType(dungeonList);
                int size = getDungeonSize(sizeList);
                int density = getWallDensity(densityList);
                int seed = getSeed(seedSpinner);
                
                // create new frame (window) for the dungeon
                JFrame printFrame = new JFrame();
                printFrame.setTitle("Random dungeon");
                printFrame.setSize(1500, 1000);
                
                // create a new editor frame for the print output
                JEditorPane dungeonPane = new JEditorPane();
                dungeonPane.setContentType("text/html");
                dungeonPane.setEditable(false);
                dungeonPane.setBounds(0, 0, 1500, 850);
                
                // create CSS style sheet for dungeonPane 
                // in order to change line spacing and font
                HTMLEditorKit kit = new HTMLEditorKit();
                dungeonPane.setEditorKit(kit);
                
                StyleSheet styleSheet = kit.getStyleSheet();
                
                styleSheet.addRule("body { font: 8px courier, sans-serif; "
                        + "line-height: 80%; text-align: center;}"); 

                Document docu = kit.createDefaultDocument();
                dungeonPane.setDocument(docu);
                
                // add editor pane to print frame
                printFrame.getContentPane().add(dungeonPane);
                
                // add a close button to the window
                JButton closeButton = new JButton("Close");
                closeButton.setBounds(750, 900, buttonWidth, buttonHeight);
                printFrame.getContentPane().add(closeButton);
                
                // close button functionality
                closeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        printFrame.setVisible(false);
                    }
                });
                
                // generate dungeon based on selected dungeon type
                if (type == 0) {
                    Dungeon dng = new Dungeon(size, size, density, seed);
                    dng.initializeDungeon();
                    dng.makeDungeon();
                    dng.makeFloodedMap();
                    //dng.printDungeon(); ONLY FOR TESTING
                    dungeonPane.setText(dng.returnDungeonMap());
                    
                } else if (type == 1) {
                    Ruins rns = new Ruins(size, size, density, seed);
                    rns.initializeRuins();
                    rns.createRuins();
                    //rns.printRuins(); ONLY FOR TESTING
                    dungeonPane.setText(rns.returnRuinsMap());
                }
                
                printFrame.getContentPane().setLayout(null);
                printFrame.setLocationRelativeTo(null);
                printFrame.setVisible(true);
                
            }
        });
        
        // functionality for exit button
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
    }
    
    
    @Override
    /**
     * Necessary override for actionPerformed.
     */
    public void actionPerformed(ActionEvent e) {
        System.out.println("nothing!");
    }
    
    /**
     * Returns dungeon type based on the active value in the dungeon type combo box.
     * @param box JCombobox object.
     * @return 0 = Cavern type dungeon, 1 = Ruins type dungeon.
     */
    public int getDungeonType(JComboBox box) {
        String s = (String)box.getSelectedItem();
        
        if (s == "Cavern") {
            return 0;
        } else {
            return 1;
        }
    }
    
    /**
     * Returns an integer for dungeon size based on the active value in the dungeon size combo box.
     * @param box JComboBox object used in the GUI
     * @return "Small" = 50, "Medium" = 100, "Large" = 500.
     */
    public int getDungeonSize(JComboBox box) {
        
        String s = (String)box.getSelectedItem();
        
        if (s == "Small") {
            return 50;
        } else if (s == "Medium") {
            return 100;
        } else {
            return 150;
        }
    }
    
    /**
     * Returns an integer for wall density based on the active value in the wall density combo box.
     * @param box JComboBox object used in the GUI
     * @return "Low" = 40, "Medium" = 45, "High" = 50.
     */
    public int getWallDensity(JComboBox box) {
        String s = (String)box.getSelectedItem();
        
        if (s == "Low") {
            return 40;
        } else if (s == "Medium") {
            return 45;
        } else {
            return 50;
        }
    }
    
    /**
     * Returns a random seed based on the active value in the random seed spinner.
     * @param spinner JSpinner object
     * @return integers between 0-10,000. Out of bounds integers return 0.
     */
    public int getSeed(JSpinner spinner) {
        return (Integer) spinner.getValue();
    }
  
    /**
     * Main class for the program. Starts up the GUI.
     * @param args 
     */
    public static void main(String[] args) {
        DungeonGUI gui = new DungeonGUI();
        gui.setVisible(true);
    }
}
