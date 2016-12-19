import java.io.IOException;
import java.util.List;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 *
 * @author Fredrik Jonsson & Martin Tornkvist
 * Inlämningsuppgift språkteknologi
 */
@SuppressWarnings("serial")
public class MobileView extends javax.swing.JFrame  {
    /**
     * klassen MobileView hanterar den grafiska interfacet för en äldre mobiltelefon.
     * Använder bilbioteket Swing.
     */
    int currentWordIndex = 0;
	String currentNumberString;
    String currentSentance;
    String previousSentance;
	WordPredictor wp;
	NgramSorter ns;
	Object[][] listOfRankedwords;

    private javax.swing.JButton fourButton;
    private javax.swing.JButton fiveButton;
    private javax.swing.JButton sixButton;
    private javax.swing.JButton sevenButton;
    private javax.swing.JButton eightButton;
    private javax.swing.JButton nineButton;
    private javax.swing.JButton oneButton;
    private javax.swing.JButton twoButton;
    private javax.swing.JButton threeButton;
    private javax.swing.JButton starButton;
    private javax.swing.JButton zeroButton;
    private javax.swing.JButton hashButton;

    private javax.swing.JPanel buttonPanel;
    private javax.swing.JScrollPane screenScroll;
    private javax.swing.JTextArea screen;

    public MobileView() throws IOException {
    	this.wp = new WordPredictor();
    	this.ns = new NgramSorter(2);
        this.currentNumberString = "";
        this.currentSentance = "";
        this.previousSentance = "";
        initComponents();
    }

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
        screen.setEditable(false);
        screen.setFont(screen.getFont().deriveFont(32f));
        screen.setLineWrap(true);
        screen.setWrapStyleWord(true);
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        buttonPanel.setLayout(new java.awt.GridLayout(4, 3));

        oneButton.setText("1 .!?");
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
        starButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	starButtonActionPerformed(evt);
            }
        });
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
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(buttonPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(screenScroll, GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE))
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(screenScroll, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(buttonPanel, GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE))
        );
        getContentPane().setLayout(layout);
        pack();
    }

    private void numberButtonActionPerformed(java.awt.event.ActionEvent evt, String key) {
    	buildNumberString(key);
    }

    private void hashButtonActionPerformed(java.awt.event.ActionEvent evt) {
        showNextWord();
    }

	private void starButtonActionPerformed(java.awt.event.ActionEvent evt) {
        toggleCapitalisation();
    }
    

    

    private void toggleCapitalisation() {
        /**
         * Ändrar mellan stor och liten bokstav
         */
    	if(listOfRankedwords==null ){
            // Hanterar . ! ?
            if (previousSentance.substring(previousSentance.length() -1).equals(".")
                    || previousSentance.substring(previousSentance.length() -1).equals("!")
                    || previousSentance.substring(previousSentance.length() -1).equals("?")){
                if (previousSentance.substring(previousSentance.length() -1).equals(".")){
                    previousSentance = previousSentance.substring(0, previousSentance.length() - 1) + "!";
                } else if (previousSentance.substring(previousSentance.length() -1).equals("!")){
                    previousSentance = previousSentance.substring(0, previousSentance.length() - 1) + "?";
                } else {
                    previousSentance = previousSentance.substring(0, previousSentance.length() - 1) + ".";
                }
                this.screen.setText(previousSentance + currentSentance);
                return;
            }else if(listOfRankedwords==null){
                return;
            }

    	}
		String currentWord = (String)listOfRankedwords[0][1];
		String firstLetter = currentWord.substring(0, 1);
		String restOfword = currentWord.substring(1);
		if(firstLetter.equals(firstLetter.toLowerCase())){
			makeFirstCaps(listOfRankedwords);
		}else if(firstLetter.equals(firstLetter.toUpperCase()) && restOfword.equals(restOfword.toLowerCase()) && currentWord.length()>1){
			makeAllCaps(listOfRankedwords);
		}else{
			makeAllSmall(listOfRankedwords);
		}
		this.screen.setText(previousSentance + currentSentance + (String)listOfRankedwords[currentWordIndex][1]);
    }

    private void makeAllSmall(Object[][] rankedWords) {
        /**
         * Gör om alla ord till små bokstäver
         */
    	for(int i=0; i<rankedWords.length;i++){
			rankedWords[i][1] = ((String)rankedWords[i][1]).toLowerCase();
		}
	}

	private void makeAllCaps(Object[][] rankedWords) {
        /**
         * Gör om alla ord till stora bokstäver
         */
    	for(int i=0; i<rankedWords.length;i++){
			rankedWords[i][1] = ((String)rankedWords[i][1]).toUpperCase();
		}
	}

	private void makeFirstCaps(Object[][] rankedWords) {
        /**
         * Gör om alla ord till första bokstaven stor
         */
		for(int i=0; i<rankedWords.length;i++){
			rankedWords[i][1] = ((String)rankedWords[i][1]).substring(0, 1).toUpperCase() + ((String)rankedWords[i][1]).substring(1);
		}
	}

	private void showNextWord() {
        /**
         * Ändrar visat ord. Tar nästa i listan och börjar om från början om man går igenom alla möjliga ord
         */
		if(listOfRankedwords==null){
			return;
		}else{
			currentWordIndex++;
			if (currentWordIndex < listOfRankedwords.length){
				this.screen.setText(previousSentance + currentSentance + (String)listOfRankedwords[currentWordIndex][1]);
			
			}else{
				currentWordIndex=0;
				this.screen.setText(previousSentance + currentSentance + (String)listOfRankedwords[0][1]);
			}
		}
	}
    
    private void buildNumberString(String numberString) {
        if(numberString.equals("0")){
            //Detta skall skicka in 
            commitString(currentNumberString + " ");
            currentNumberString="";
        }else if(numberString.equals("1")){

            commitString(currentNumberString + "1");
            currentNumberString="";
        }else{
        	currentNumberString+=numberString;
        	commitString(currentNumberString);
        }
    }

    void commitString(String wordString) {
    	String mostProbableWord;
    	if(wordString.substring(wordString.length()-1).equals(" ")){
            if (listOfRankedwords != null){
                mostProbableWord = (String) listOfRankedwords[currentWordIndex][1];
                currentSentance +=mostProbableWord + " ";
                // Sätts till null så det sista ordet inte läggs till varje gång användaren trycker på '0'
                listOfRankedwords = null;
            } else if (!previousSentance.substring(previousSentance.length() -1).equals(" ")){
                previousSentance += " ";
            }
            currentWordIndex = 0;
            this.screen.setText(previousSentance + currentSentance);
    		return;
    	}else if(wordString.substring(wordString.length()-1).equals("1")){
            if (listOfRankedwords != null){
                mostProbableWord = (String) listOfRankedwords[currentWordIndex][1];
                currentSentance +=mostProbableWord + ".";
                previousSentance += currentSentance;
                currentSentance = "";
                // Sätts till null så det sista ordet inte läggs till varje gång användaren trycker på '1'
                listOfRankedwords = null;
            } else {
                previousSentance+= currentSentance + ".";
                currentSentance = "";
                currentWordIndex = 0;
            }
            this.screen.setText(previousSentance);

            return;
        }
    	List<String> t9words = wp.getWordFromNum(wordString);
    	listOfRankedwords = ns.getWordsByFrequency(t9words, currentSentance);
		mostProbableWord = (String) listOfRankedwords[0][1];
		this.screen.setText(previousSentance + currentSentance + mostProbableWord);
	}
}