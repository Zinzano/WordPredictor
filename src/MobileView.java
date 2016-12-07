import java.io.IOException;
import java.util.List;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fredrik
 */
public class MobileView extends javax.swing.JFrame  {
    String currentNumberString;
    String currentWrittenWords;
	WordPredictor wp;
	NgramSorter ns;
	Object[][] listOfRankedwords;
	
    /**
     * Creates new form NewJFrame
     * @throws IOException 
     */
    public MobileView() throws IOException {
    	this.wp = new WordPredictor();
    	this.ns = new NgramSorter(2);
        this.currentNumberString = "";
        this.currentWrittenWords = "";
        initComponents();
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        buttonPanel = new javax.swing.JPanel();
        oneButton = new javax.swing.JButton();
        twoButton = new javax.swing.JButton();
        threeButton = new javax.swing.JButton();
        fourButton = new javax.swing.JButton();
        fiveButton = new javax.swing.JButton();
        sixButton = new javax.swing.JButton();
        sevenButton = new javax.swing.JButton();
        eightButton = new javax.swing.JButton();
        nineButton = new javax.swing.JButton();
        starButton = new javax.swing.JButton();
        zeroButton = new javax.swing.JButton();
        hashButton = new javax.swing.JButton();
        
        screenScroll = new javax.swing.JScrollPane();
        screen = new javax.swing.JTextArea();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        buttonPanel.setLayout(new java.awt.GridLayout(4, 3));

        oneButton.setText("1");
        oneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	numberButtonActionPerformed(evt, "1");
            }
        });
        buttonPanel.add(oneButton);

        twoButton.setText("2 ABC");
        twoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	numberButtonActionPerformed(evt, "2");
            }
        });
        buttonPanel.add(twoButton);

        threeButton.setText("3 DEF");
        threeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	numberButtonActionPerformed(evt, "3");
            }
        });
        buttonPanel.add(threeButton);

        fourButton.setText("4 GHI");
        fourButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	numberButtonActionPerformed(evt, "4");
            }
        });
        buttonPanel.add(fourButton);

        fiveButton.setText("5 JKL");
        fiveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	numberButtonActionPerformed(evt, "5");
            }
        });
        buttonPanel.add(fiveButton);

        sixButton.setText("6 MNO");
        sixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	numberButtonActionPerformed(evt, "6");
            }
        });
        buttonPanel.add(sixButton);

        sevenButton.setText("7 PQRS");
        sevenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	numberButtonActionPerformed(evt, "7");
            }
        });
        buttonPanel.add(sevenButton);

        eightButton.setText("8 TUV");
        eightButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	numberButtonActionPerformed(evt, "8");
            }
        });
        buttonPanel.add(eightButton);

        nineButton.setText("9 WXYZ");
        nineButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	numberButtonActionPerformed(evt, "9");
            }
        });
        buttonPanel.add(nineButton);

        starButton.setText("* ^");
        buttonPanel.add(starButton);

        zeroButton.setText("0 __");
        zeroButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	numberButtonActionPerformed(evt, "0");
            }
        });
        buttonPanel.add(zeroButton);
        hashButton.setText("# toggle");
        buttonPanel.add(hashButton);
        hashButton.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		hashButtonActionPerformed(evt);
            }
        });
        screen.setColumns(20);
        screen.setRows(5);
        screenScroll.setViewportView(screen);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(screenScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(screenScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE))
        );

        pack();
    }

    private void numberButtonActionPerformed(java.awt.event.ActionEvent evt, String key) {
    	buildNumberString(key);
    }

    private void hashButtonActionPerformed(java.awt.event.ActionEvent evt) {
        buildNumberString("9");
    }

    private void starButtonActionPerformed(java.awt.event.ActionEvent evt) {
        buildNumberString("0");
    }
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton eightButton;
    private javax.swing.JButton fiveButton;
    private javax.swing.JButton fourButton;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JScrollPane screenScroll;
    private javax.swing.JTextArea screen;
    private javax.swing.JButton nineButton;
    private javax.swing.JButton oneButton;
    private javax.swing.JButton sevenButton;
    private javax.swing.JButton sixButton;
    private javax.swing.JButton starButton;
    private javax.swing.JButton hashButton;
    private javax.swing.JButton threeButton;
    private javax.swing.JButton twoButton;
    private javax.swing.JButton zeroButton;
    // End of variables declaration//GEN-END:variables

    private void buildNumberString(String numberString) {
        if(numberString.equals("0")){
            //Detta skall skicka in 
            commitString(currentNumberString + " ");
            currentNumberString="";
        }else{
        	currentNumberString+=numberString;
        	commitString(currentNumberString);
        }
    }

    // TODO gör så att vi kan cycla mellan de möjliga orden
    // TODO gör så att vi kan ändra mellan stor/liten boksatv
    void commitString(String wordString) {
        // TODO Den här behöver kunna hantera '.' så han tolkar det som en ny tom rad
    	String mostProbableWord;
    	if(wordString.substring(wordString.length()-1).equals(" ")){
    		List<String> t9words = wp.getWordFromNum(wordString.substring(0, wordString.length()-1));
    		listOfRankedwords = ns.getWordsByFrequency(t9words, currentWrittenWords);
            mostProbableWord = (String) listOfRankedwords[0][1];
    		currentWrittenWords+=mostProbableWord + " ";
    		this.screen.setText(currentWrittenWords);
    		return;
    	}
    	List<String> t9words = wp.getWordFromNum(wordString);
    	listOfRankedwords = ns.getWordsByFrequency(t9words, currentWrittenWords);
		mostProbableWord = (String) listOfRankedwords[0][1];
		this.screen.setText(currentWrittenWords + mostProbableWord);
    
	}
}
