package mobilitymodels;

import javax.swing.SwingUtilities;

/**
 * @author Tomasz 'TC' Klimek
 */
public class Main {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        try {
//            UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
//        } catch (Exception e) { }
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
		new Window();
	    }
	});
    }
}
